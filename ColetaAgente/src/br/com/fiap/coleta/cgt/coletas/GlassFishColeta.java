package br.com.fiap.coleta.cgt.coletas;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import br.com.fiap.coleta.bo.AgendamentoBO;
import br.com.fiap.coleta.bo.AlarmeBO;
import br.com.fiap.coleta.bo.IndisponibilidadeBO;
import br.com.fiap.coleta.bo.ServidorAplicacaoBO;
import br.com.fiap.coleta.entities.Agendamento;
import br.com.fiap.coleta.entities.Indisponibilidade;
import br.com.fiap.coleta.entities.Glassfish;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.ServidorAplicacaoDeployment;
import br.com.fiap.coleta.entities.ServidorAplicacaoMemoria;
import br.com.fiap.coleta.entities.ServidorAplicacaoMemoriaColeta;
import br.com.fiap.coleta.entities.ServidorAplicacaoThreadColeta;
import br.com.fiap.coleta.entities.enumerators.TipoMemoriaServidorAplicacao;
import br.com.fiap.coleta.util.socket.SocketUtil;

public class GlassFishColeta {

	private Glassfish glassfish;
	
	private ServidorAplicacaoBO servidorAplicacaoBO;
	
	private IndisponibilidadeBO indisponibilidadeBO;
	
	private AgendamentoBO agendamentoBO;
	
	private AlarmeBO alarmeBO;
	
	private SocketUtil socket;
	
	private Date dataColeta;
	
	private Indisponibilidade indisp;
	
	private Boolean ultimoStatus;

	private Boolean ultimoGerenciavel;
	
	private Agendamento agendamento;

	private List<ServidorAplicacaoMemoria> propriedadesMemorias;
	
	public GlassFishColeta(No no){
		this.glassfish = (Glassfish) no;
		this.servidorAplicacaoBO = new ServidorAplicacaoBO();
		this.indisponibilidadeBO = new IndisponibilidadeBO();
		this.alarmeBO = new AlarmeBO();
		this.agendamentoBO = new AgendamentoBO();
		
		if(this.glassfish.getUltimaColeta() != null){
			this.ultimoStatus = this.glassfish.getDisponivel();
			this.ultimoGerenciavel = this.glassfish.getGerenciavel();
		}else{
			this.ultimoStatus = false;
			this.ultimoGerenciavel = false;
		}

	}
	
	public void initColeta(){
	
		this.dataColeta = new Date();
		this.agendamento = this.agendamentoBO.pegaAgendamentoNo(this.glassfish);
		this.glassfish.setUltimaColeta(dataColeta);
		
		// Pega utlima indisponibilidade
		this.indisp = this.indisponibilidadeBO
				.pegaUltimaInstanciaIndisponibilidade(this.glassfish);
		
		if (connect()){
			socket = new SocketUtil(this.glassfish.getHostname(), 9090);

			try{
				socket.openSocket();

				this.setCredentials();
				
				//Pega as propriedades do coletor
				this.getGlassfishRuntime();
				this.propriedadesMemorias = this.getConfigGlassfishMemory();

				//Pega os valores da coleta
				Map<String,ServidorAplicacaoDeployment> deployments = this.getGlassfishDeployments();
				List<ServidorAplicacaoMemoriaColeta> coletasMemorias = this.getGlassfishMemory();
				ServidorAplicacaoThreadColeta threadColeta = this.getGlassfishThread();
				
				// Verifica se as coletas estao vazias, caso estiverem ele nao esta gerenciavel
				this.glassfish.setGerenciavel(true);
				this.glassfish.setUltimaColeta(dataColeta);
				
				//Salva as propriedades
				servidorAplicacaoBO.updateServidorAplicacaoColeta(this.glassfish);
				servidorAplicacaoBO.salvaPropriedadesMemoria(propriedadesMemorias);

				//Salva as coletas
				servidorAplicacaoBO.salvaPropriedadesMemoria(propriedadesMemorias);
				servidorAplicacaoBO.salvaColetasMemoria(coletasMemorias);
				servidorAplicacaoBO.salvaColetasThread(threadColeta);
				servidorAplicacaoBO.salvaMapDeployments(deployments);

				// Ultima coleta
				this.glassfish.setUltimaColeta(dataColeta);

				socket.close();

			}catch(IOException ex){
				this.glassfish.setGerenciavel(false);
				this.servidorAplicacaoBO.updateServidorAplicacaoColeta(this.glassfish);
				System.out.println("Imposs�vel abrir o socket. Verifique se o agente est� instalado no servidor.");
			}catch(Exception ex){
				this.glassfish.setGerenciavel(false);
				this.servidorAplicacaoBO.updateServidorAplicacaoColeta(this.glassfish);
				System.out.println("Glassfish n�o gerenci�vel - "+ glassfish.getHostname() + "- Erros durante a coleta ");
				ex.printStackTrace();
			}finally{
				if(!this.glassfish.getGerenciavel() && this.ultimoGerenciavel){
					this.alarmeBO.geraAlarmeNaoGerenciavel(this.glassfish, ultimoGerenciavel);
				}
			}
		}
	}
	
	private boolean connect(){
		
		boolean result = false;
		
		try{
			// Verifica atraves da pagina padrao do Jboss se ele esta disponivel
			URL url = new URL("http://" + this.glassfish.getHostname() + ":" + this.glassfish.getPort());
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			int code = connection.getResponseCode();
			connection.disconnect();

			if (code == 200){
				this.glassfish.setDisponivel(true);
				result = true;
			}
			else{
				System.out.println("Glassfish Ind�spon�vel - "+ this.glassfish.getHostname() +" - HTTP Status: "+code);
				this.glassfish.setDisponivel(false);
				this.glassfish.setGerenciavel(false);
				this.servidorAplicacaoBO.updateServidorAplicacaoColeta(this.glassfish);
			}
			
			
		}catch(Exception ex){
			
			System.out.println("Glassfish Ind�spon�vel - "+ this.glassfish.getHostname() +" - Falha no Http GET");
			this.glassfish.setDisponivel(false);
			this.glassfish.setGerenciavel(false);
			this.servidorAplicacaoBO.updateServidorAplicacaoColeta(this.glassfish);
			result = false;
			
		}finally{
			
			this.glassfish.setUltimaColeta(dataColeta);
			
			if (!this.glassfish.getDisponivel() && (ultimoStatus || this.indisp == null)){

				if (this.indisp == null) {
					this.indisp = new Indisponibilidade();
					this.indisp.setNo(this.glassfish);
					this.indisp.setInicio(this.dataColeta);
				}
				
				
			} else if (this.glassfish.getDisponivel() && this.indisp !=null){
				this.indisp.setFim(this.dataColeta);
			}
			
			if(indisp != null){
				this.indisponibilidadeBO.salvaIndisponibilidade(indisp);
			}
			
		}
		
		this.alarmeBO.geraAlarmeIndsiponibilidade(this.glassfish, ultimoStatus);
		
		return result;		
	}
	
	private void setCredentials(){
		try{
			this.socket.enviaComando("get glassfish.credentials " + this.glassfish.getJmxUser() + " " + this.glassfish.getJmxSenha() + " " + this.glassfish.getJmxPort());
		}catch (InterruptedException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private Map<String,ServidorAplicacaoDeployment> getGlassfishDeployments(){
		
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get glassfish.deployment"));
			JSONArray jsonArray = json.getJSONArray("deployments");
			System.out.println(json.toString());
			
			Map<String,ServidorAplicacaoDeployment> deployments = this.servidorAplicacaoBO.getMapDeploymentsServidor(this.glassfish);
			
			if(deployments == null){
				deployments = new HashMap<String,ServidorAplicacaoDeployment>();
			}
			
			for(Object o:jsonArray){
				String deploymentName = (String) o;
				
				ServidorAplicacaoDeployment deployment = deployments.get(deploymentName);
				
				if(deployment==null){
					deployment = new ServidorAplicacaoDeployment();
					deployment.setNome(deploymentName);
					deployment.setServidorAplicacao(this.glassfish);
				}
				
				deployment.setAtivo(true);
				
				deployments.put(deploymentName, deployment);
			} 
			
			this.alarmeBO.geraAlarmesDeploymemnts(this.glassfish,deployments);
			
			return deployments;
	
		}catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	private ServidorAplicacaoThreadColeta getGlassfishThread(){
		
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get glassfish.thread"));
			System.out.println(json.toString());
			ServidorAplicacaoThreadColeta threadColeta = new ServidorAplicacaoThreadColeta();
			
			threadColeta.setDataColeta(this.dataColeta);
			threadColeta.setThreadCount(json.getLong("count"));
			threadColeta.setCpuTime(json.getDouble("cpuTime")/( this.agendamento.getIntervalo() *10000000));
			threadColeta.setUserTime(json.getDouble("userTime")/( this.agendamento.getIntervalo() *10000000));
			
			return threadColeta;
			
			
		}catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
	}
	
	private void getGlassfishRuntime(){
		try{
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get glassfish.runtime"));
			
			Date startTime = new Date(json.getLong("startTime"));
			
			this.glassfish.setStartTime(startTime);
			
			Long uptime = json.getLong("uptime")/1000;
			
			this.glassfish.setUptime(uptime);
			
		}catch (InterruptedException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	private List<ServidorAplicacaoMemoria> getConfigGlassfishMemory(){
		try{		
			
			List<ServidorAplicacaoMemoria> retorno = new ArrayList<	ServidorAplicacaoMemoria>();
			
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get glassfish.memory"));
						
			JSONObject jsonHeap = json.getJSONObject("heap");
			JSONObject jsonNonHeap = json.getJSONObject("nonHeap");
			
			ServidorAplicacaoMemoria memoriaHeap = this.servidorAplicacaoBO.getMemoriaTipo(this.glassfish, TipoMemoriaServidorAplicacao.HEAP);
			
			if(memoriaHeap == null){
				memoriaHeap = new ServidorAplicacaoMemoria(this.glassfish);
			}
			memoriaHeap.setTipo(TipoMemoriaServidorAplicacao.HEAP);
			memoriaHeap.setInit(jsonHeap.getLong("init"));
			memoriaHeap.setMax(jsonHeap.getLong("max"));
			
			
			ServidorAplicacaoMemoria memoriaNonHeap = this.servidorAplicacaoBO.getMemoriaTipo(this.glassfish, TipoMemoriaServidorAplicacao.NONHEAP);
			
			if(memoriaNonHeap == null){
				memoriaNonHeap = new ServidorAplicacaoMemoria(this.glassfish);
			}
			
			memoriaNonHeap.setTipo(TipoMemoriaServidorAplicacao.NONHEAP);
			memoriaNonHeap.setInit(jsonNonHeap.getLong("init"));
			memoriaNonHeap.setMax(jsonNonHeap.getLong("max"));
			
			
			retorno.add(memoriaHeap);
			retorno.add(memoriaNonHeap);
			
			return retorno;
			
		}catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	private List<ServidorAplicacaoMemoriaColeta> getGlassfishMemory(){
		try{		
			
			List<ServidorAplicacaoMemoriaColeta> retorno = new ArrayList<	ServidorAplicacaoMemoriaColeta>();
			JSONObject json = JSONObject.fromObject(this.socket.enviaComando("get glassfish.memory"));
			
			JSONObject jsonHeap = json.getJSONObject("heap");
			JSONObject jsonNonHeap = json.getJSONObject("nonHeap");
			
			
			ServidorAplicacaoMemoria memoriaHeap = this.servidorAplicacaoBO.getMemoriaTipo(this.glassfish, TipoMemoriaServidorAplicacao.HEAP);
			ServidorAplicacaoMemoria memoriaNonHeap = this.servidorAplicacaoBO.getMemoriaTipo(this.glassfish, TipoMemoriaServidorAplicacao.NONHEAP);
			
			
			if(memoriaHeap != null){
				ServidorAplicacaoMemoriaColeta coletaMemoriaHeap = new ServidorAplicacaoMemoriaColeta(memoriaHeap);
				
				coletaMemoriaHeap.setDataColeta(this.dataColeta);
				coletaMemoriaHeap.setUsed(jsonHeap.getLong("used"));
				coletaMemoriaHeap.setCommited(jsonHeap.getLong("committed"));
				
				if(this.glassfish.getThreshold() != null){
					BigDecimal utilizacao = new BigDecimal( (coletaMemoriaHeap.getUsed()/this.propriedadesMemorias.get(0).getMax()) *100);
					this.alarmeBO.geraAlarmeMemoriaHeap(this.glassfish,utilizacao);
				}
				
				retorno.add(coletaMemoriaHeap);
				
			}
					
			if(memoriaNonHeap != null){
				ServidorAplicacaoMemoriaColeta coletaMemoriaNonHeap = new ServidorAplicacaoMemoriaColeta(memoriaNonHeap);
				
				coletaMemoriaNonHeap.setDataColeta(this.dataColeta);
				coletaMemoriaNonHeap.setUsed(jsonNonHeap.getLong("used"));
				coletaMemoriaNonHeap.setCommited(jsonNonHeap.getLong("committed"));
				
				retorno.add(coletaMemoriaNonHeap);
				
				if(this.glassfish.getThreshold() != null){
					BigDecimal utilizacao = new BigDecimal( (coletaMemoriaNonHeap.getUsed()/this.propriedadesMemorias.get(1).getMax()) *100);
					this.alarmeBO.geraAlarmeMemoriaNonHeap(this.glassfish,utilizacao);
				}
				
			}
			
			return retorno;
			
		}catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	
	
	
}

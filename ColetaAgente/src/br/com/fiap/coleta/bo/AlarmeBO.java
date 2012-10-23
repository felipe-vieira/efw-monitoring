package br.com.fiap.coleta.bo;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import br.com.fiap.coleta.dao.ColetaDAO;
import br.com.fiap.coleta.entities.Alarme;
import br.com.fiap.coleta.entities.BancoBackup;
import br.com.fiap.coleta.entities.BancoDados;
import br.com.fiap.coleta.entities.BancoDadosThreshold;
import br.com.fiap.coleta.entities.BancoFileColeta;
import br.com.fiap.coleta.entities.BancoJobColeta;
import br.com.fiap.coleta.entities.No;
import br.com.fiap.coleta.entities.Particao;
import br.com.fiap.coleta.entities.Servidor;
import br.com.fiap.coleta.entities.ServidorAplicacao;
import br.com.fiap.coleta.entities.ServidorAplicacaoDeployment;
import br.com.fiap.coleta.entities.ServidorAplicacaoThreshold;
import br.com.fiap.coleta.entities.ServidorThreshold;
import br.com.fiap.coleta.entities.Sla;
import br.com.fiap.coleta.entities.TipoAlarme;
import br.com.fiap.coleta.entities.Usuario;
import br.com.fiap.coleta.entities.enumerators.CriticidadeAlarme;
import br.com.fiap.coleta.util.cgt.SendEmail;

public class AlarmeBO {

	private UsuarioBO usuario;
	private ColetaDAO coletaDAO;
	private Map<Integer,TipoAlarme> tipos;
    private SendEmail email;
    
	
	public AlarmeBO(){
		
		this.usuario = new UsuarioBO();
		this.coletaDAO = new ColetaDAO();
		this.email = new SendEmail();
		geraMapaAlarmes();
	}
	
	public void processaEmail(No no, Alarme alarme, String problema){
		
		try{
			
			DateFormat df = new SimpleDateFormat("MMMM");
			df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("pt", "BR"));
			
			TipoAlarme tipo = alarme.getTipo();
	
			BigDecimal valor = null;
			BigDecimal threshold = null;
			
			String strValor = "N/A";
			String strThreshold = "N/A";
			
			String mensagem = "";

	
			if (alarme.getValor() != null){
				valor = alarme.getValor().setScale(2,BigDecimal.ROUND_HALF_EVEN);
			}

			if (alarme.getValorLimite() != null){
				threshold = alarme.getValorLimite().setScale(2,BigDecimal.ROUND_HALF_EVEN);
			}

			
			if (tipo.getThreshold() == true){
				strValor = valor.toString() + tipo.getUnidade();
				strThreshold = threshold.toString() + tipo.getUnidade();
			}
			
			if (tipo.getMensagem() != null && alarme.getParametro() != null){
				
				String[] parametros = alarme.getParametro().split(";");
				mensagem =  tipo.getMensagem();
				
				for (String parametro : parametros) {
					mensagem = mensagem.replaceFirst("\\?", parametro);
				}
								
			}
			else{
				mensagem = tipo.getMensagem();
			}
			
			
			String email = "<table>" +  
					"<tr><th colspan=2>" + mensagem + "</th></tr>" +
					"<tr><td><b>Hostname:</b></td><td>" + no.getHostname() + "</td></tr>" +
					"<tr><td><b>Data:</b></td><td>" + df.format(alarme.getData()) + "</td></tr>" +
					"<tr><td><b>Mensagem:</b></td><td>" + mensagem + "</td></tr>" +
					"<tr><td><b>Tipo:</b></td><td>" + tipo.getDescricao() + "</td></tr>" +
					"<tr><td><b>Criticidade:</b></td><td>" + alarme.getCriticidade() + "</td></tr>" +
					"<tr><td><b>Valor:</b></td><td>" + strValor + "</td></tr>" +
					"<tr><td><b>Threshold:</b></td><td>" + strThreshold + "</td></tr>" +
					"</table>";
			coletaDAO.salvaColeta(alarme);
			
			this.enviaEmail(usuario.listaUsuariosEmail(), "tcc@wspi.com.br", mensagem, email, "smtp.gmail.com", "tcc@wspi.com.br", "123@fiap", 465);
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public void processaEmail(Sla sla, Alarme alarme, String problema){
		
		try{
			DateFormat df = new SimpleDateFormat("MMMM");
			df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("pt", "BR"));
			
			TipoAlarme tipo = alarme.getTipo();
			
			BigDecimal valor = null;
			BigDecimal threshold = null;
			
			String strValor = "N/A";
			String strThreshold = "N/A";
			
			String mensagem = "";

	
			if (alarme.getValor() != null){
				valor = alarme.getValor().setScale(2,BigDecimal.ROUND_HALF_EVEN);
			}

			if (alarme.getValorLimite() != null){
				threshold = alarme.getValorLimite().setScale(2,BigDecimal.ROUND_HALF_EVEN);
			}

			
			if (tipo.getThreshold() == true){
				strValor = valor.toString() + tipo.getUnidade();
				strThreshold = threshold.toString() + tipo.getUnidade();
			}
			
			if (tipo.getMensagem() != null && alarme.getParametro() != null){
				
				String[] parametros = alarme.getParametro().split(";");
				mensagem =  tipo.getMensagem();
				
				for (String parametro : parametros) {
					mensagem = mensagem.replaceFirst("\\?", parametro);
				}
								
			}
			else{
				mensagem = tipo.getMensagem();
			}
			
			String email = "<table>" +  
					"<tr><th colspan=2>" + mensagem + "</th></tr>" +
					"<tr><td><b>SLA:</b></td><td>" + sla.getNome() + "</td></tr>" +
					"<tr><td><b>Hostname:</b></td><td>" + alarme.getNo().getHostname() + "</td></tr>" +
					"<tr><td><b>Data:</b></td><td>" + df.format(alarme.getData()) + "</td></tr>" +
					"<tr><td><b>Mensagem:</b></td><td>" + mensagem + "</td></tr>" +
					"<tr><td><b>Tipo:</b></td><td>" + tipo.getDescricao() + "</td></tr>" +
					"<tr><td><b>Criticidade:</b></td><td>" + alarme.getCriticidade() + "</td></tr>" +
					"<tr><td><b>Valor:</b></td><td>" + valor + "</td></tr>" +
					"<tr><td><b>Threshold:</b></td><td>" + threshold + "</td></tr>" +
					"</table>";
			coletaDAO.salvaColeta(alarme);
			this.enviaEmail(usuario.listaUsuariosEmail(), "tcc@wspi.com.br", mensagem, email, "smtp.gmail.com", "tcc@wspi.com.br", "123@fiap", 465);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/*
	public void processaEmail(Servidor servidor, Alarme alarme, String problema){
		
		try{
			
			DateFormat df = new SimpleDateFormat("MMMM");
			df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("pt", "BR"));
			
			DecimalFormat decimal = new DecimalFormat("#,##");
			
			TipoAlarme tipo = alarme.getTipo();
		
			String valor = "";
                        String threshold = "";
                        String mensagem = "";


                        if (alarme.getValor() != null){
                                valor = decimal.format(alarme.getValor().toString());
                        }

                        if (alarme.getValorLimite() != null){
                        	threshold = alarme.getValorLimite().toString();
                        }
	
			
			if (tipo.getThreshold() == true){
				valor += tipo.getUnidade();
				threshold += tipo.getUnidade();
			}
			
			if (tipo.getMensagem() != null && alarme.getParametro() != null){
				mensagem = tipo.getMensagem().replace("?", alarme.getParametro());
			}
			else{
				mensagem = problema;
			}
			
			String email = "<table>" +  
					"<tr><th colspan=2>" + problema + "</th></tr>" +
					"<tr><td><b>Hostname:</b></td><td>" + servidor.getHostname() + "</td></tr>" +
					"<tr><td><b>Data:</b></td><td>" + df.format(alarme.getData()) + "</td></tr>" +
					"<tr><td><b>Mensagem:</b></td><td>" + mensagem + "</td></tr>" +
					"<tr><td><b>Tipo:</b></td><td>" + tipo.getDescricao() + "</td></tr>" +
					"<tr><td><b>Criticidade:</b></td><td>" + alarme.getCriticidade() + "</td></tr>" +
					"<tr><td><b>Valor:</b></td><td>" + valor + "</td></tr>" +
					"<tr><td><b>Threshold:</b></td><td>" + threshold + "</td></tr>" +
					"</table>";
			coletaDAO.salvaColeta(alarme);
			this.enviaEmail(usuario.listaUsuariosEmail(), "tcc@wspi.com.br", problema, email, "smtp.gmail.com", "tcc@wspi.com.br", "123@fiap", 465);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void processaEmail(ServidorAplicacao sa, Alarme alarme, String problema){
		
		try{
			
			DateFormat df = new SimpleDateFormat("MMMM");
			df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("pt", "BR"));
			
			DecimalFormat decimal = new DecimalFormat("#,##");
			
			TipoAlarme tipo = alarme.getTipo();
			
			String valor = "";
                        String threshold = "";
                        String mensagem = "";


                        if (alarme.getValor() != null){
                        	valor = decimal.format(alarme.getValor().toString());
                        }

                        if (alarme.getValorLimite() != null){
                        	threshold = alarme.getValorLimite().toString();
                        }
			
	
			if (tipo.getThreshold() == true){
				valor += tipo.getUnidade();
				threshold += tipo.getUnidade();
			}
			
			if (tipo.getMensagem() != null && alarme.getParametro() != null){
				mensagem = tipo.getMensagem().replace("?", alarme.getParametro());
			}
			else{
				mensagem = problema;
			}
			
			String email = "<table>" +  
					"<tr><th colspan=2>" + problema + "</th></tr>" +
					"<tr><td><b>Hostname:</b></td><td>" + sa.getHostname() + "</td></tr>" +
					"<tr><td><b>Data:</b></td><td>" + df.format(alarme.getData()) + "</td></tr>" +
					"<tr><td><b>Mensagem:</b></td><td>" + mensagem + "</td></tr>" +
					"<tr><td><b>Tipo:</b></td><td>" + tipo.getDescricao() + "</td></tr>" +
					"<tr><td><b>Criticidade:</b></td><td>" + alarme.getCriticidade() + "</td></tr>" +
					"<tr><td><b>Valor:</b></td><td>" + valor + "</td></tr>" +
					"<tr><td><b>Threshold:</b></td><td>" + threshold + "</td></tr>" +
					"</table>";
			
			coletaDAO.salvaColeta(alarme);
			this.enviaEmail(usuario.listaUsuariosEmail(), "tcc@wspi.com.br", problema, email, "smtp.gmail.com", "tcc@wspi.com.br", "123@fiap", 465);
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	

	
public void processaEmail(BancoDados bd, Alarme alarme, String problema){
		
		try{
			
			DateFormat df = new SimpleDateFormat("MMMM");
			df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("pt", "BR"));
			
			DecimalFormat decimal = new DecimalFormat("#,##");
			
			TipoAlarme tipo = alarme.getTipo();
			
			
			String valor = "";
                        String threshold = "";
                        String mensagem = "";


                        if (alarme.getValor() != null){
                        	valor = decimal.format(alarme.getValor().toString());
                        }

                        if (alarme.getValorLimite() != null){
                        	threshold = alarme.getValorLimite().toString();
                        }
	
			
			if (tipo.getThreshold() == true){
				valor += tipo.getUnidade();
				threshold += tipo.getUnidade();
			}
			
			if (tipo.getMensagem() != null && alarme.getParametro() != null){
				mensagem = tipo.getMensagem().replace("?", alarme.getParametro());
			}
			else{
				mensagem = problema;
			}
			
			String email = "<table>" +  
					"<tr><th colspan=2>" + problema + "</th></tr>" +
					"<tr><td><b>Nome:</b></td><td>" + bd.getNome() + "</td></tr>" +
					"<tr><td><b>Data:</b></td><td>" + df.format(alarme.getData()) + "</td></tr>" +
					"<tr><td><b>Mensagem:</b></td><td>" + mensagem + "</td></tr>" +
					"<tr><td><b>Tipo:</b></td><td>" + tipo.getDescricao() + "</td></tr>" +
					"<tr><td><b>Criticidade:</b></td><td>" + alarme.getCriticidade() + "</td></tr>" +
					"<tr><td><b>Valor:</b></td><td>" + valor + "</td></tr>" +
					"<tr><td><b>Threshold:</b></td><td>" + threshold + "</td></tr>" +
					"</table>";
			coletaDAO.salvaColeta(alarme);
			this.enviaEmail(usuario.listaUsuariosEmail(), "tcc@wspi.com.br", problema, email, "smtp.gmail.com", "tcc@wspi.com.br", "123@fiap", 465);
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	*/
	public void enviaEmail(List<Usuario> destinatario, String remetente, String assunto, String msg, String host, String usuario, String senha, int porta){
		this.email.setTo(destinatario);
		this.email.setFrom(remetente);
		this.email.setSubject(assunto);
		this.email.setMessage(msg);
		this.email.setHost(host);
		this.email.setUser(usuario);
		this.email.setPassword(senha);
		this.email.setPort(porta);
		this.email.send();
	}
	
	public void geraMapaAlarmes(){
		
		List<TipoAlarme> listaTipos = coletaDAO.listAll(TipoAlarme.class);
		tipos = new HashMap<Integer, TipoAlarme>();
		
		if(listaTipos != null && listaTipos.size() > 0){
			for (TipoAlarme tipoAlarme : listaTipos) {
				tipos.put(tipoAlarme.getId(), tipoAlarme);
			}
		}
					
	}
		
	
	public void geraAlarmeIndsiponibilidade(No no, Boolean ultimoStatus){
		if(!no.getDisponivel() && no.getDisponivel() != ultimoStatus){
			Alarme alarme = new Alarme();
			alarme.setData(new Date());
			alarme.setTipo(tipos.get(1));
			alarme.setNo(no);
			alarme.setCriticidade(CriticidadeAlarme.CRITICO);
			
			String problema = "Servidor " + no.getNome() + " indisponível";
			
			processaEmail(no, alarme, problema);
			
			
		}
	}
	
	public void geraAlarmeNaoGerenciavel(No no, Boolean ultimoStatus){
		if(!no.getGerenciavel() && ultimoStatus){
			Alarme alarme = new Alarme();
			alarme.setData(new Date());
			alarme.setTipo(tipos.get(2));
			alarme.setNo(no);			
			alarme.setCriticidade(CriticidadeAlarme.CRITICO);
			
			String problema = "Servidor " + no.getNome() + " não gerenciável";
			
			processaEmail(no, alarme, problema);
		}
	}
	
	public void geraAlarmeCpu(Servidor servidor, Double utilizacao) {
		BigDecimal valor = new BigDecimal(utilizacao);
		ServidorThreshold threshold = servidor.getThreshold();
		
		if(threshold != null && threshold.getLimiteCpu() != null){
			if(valor.compareTo(threshold.getLimiteCpu()) == 1){
				Alarme alarme = new Alarme();
				alarme.setData(new Date());
				alarme.setTipo(tipos.get(3));
				alarme.setValor(valor);
				alarme.setValorLimite(threshold.getLimiteCpu());
				alarme.setNo(servidor);
				alarme.setCriticidade(CriticidadeAlarme.ALERTA);
				
				String problema = "Utilização da CPU do servidor " + servidor.getNome() + " muito alta";
				
				processaEmail(servidor, alarme, problema);
			}
		}
		
	}
	
	public void geraAlarmeMemoria(Servidor servidor, BigDecimal utilizacao){
		
		ServidorThreshold threshold = servidor.getThreshold();
		
		if(threshold != null && threshold.getLimiteMemoria() != null){
			if(utilizacao.compareTo(servidor.getThreshold().getLimiteMemoria()) == 1){
				Alarme alarme = new Alarme();
				alarme.setData(new Date());
				alarme.setTipo(tipos.get(4));
				alarme.setValor(utilizacao);
				alarme.setValorLimite(threshold.getLimiteMemoria());
				alarme.setNo(servidor);
				alarme.setCriticidade(CriticidadeAlarme.ALERTA);
				
				String problema = "Utilização da memória do servidor " + servidor.getNome() + " muito alta";
				
				processaEmail(servidor, alarme, problema);
			}
		}
				
	}


	public void geraAlarmeParticao(Servidor servidor, Particao particao, BigDecimal utilizacao){
		
		ServidorThreshold threshold = servidor.getThreshold();
		
		if(threshold != null && threshold.getLimiteParticao() != null){
			if(utilizacao.compareTo(servidor.getThreshold().getLimiteParticao()) == 1){
				Alarme alarme = new Alarme();
				alarme.setData(new Date());
				alarme.setTipo(tipos.get(5));
				alarme.setValor(utilizacao);
				alarme.setValorLimite(threshold.getLimiteParticao());
				alarme.setNo(servidor);
				alarme.setParametro(particao.getNome());
				alarme.setCriticidade(CriticidadeAlarme.ALERTA);
				
				String problema = "Utilização do disco do servidor " + servidor.getNome() + " muito alto";
				
				processaEmail(servidor, alarme, problema);
			}			
		}
	}

	public void geraAlarmeMemoriaHeap(ServidorAplicacao sa, BigDecimal utilizacao) {
		ServidorAplicacaoThreshold threshold = sa.getThreshold();
		
		if(threshold != null && threshold.getThresholdHeap() != null){
			if(utilizacao.compareTo(threshold.getThresholdHeap()) == 1){
				Alarme alarme = new Alarme();
				alarme.setData(new Date());
				alarme.setTipo(tipos.get(8));
				alarme.setValor(utilizacao);
				alarme.setValorLimite(threshold.getThresholdHeap());
				alarme.setNo(sa);
				alarme.setCriticidade(CriticidadeAlarme.ALERTA);
				
				String problema = "Utilização da memória Heap do servidor de aplicação " + sa.getNome() + " muito alto";
				
				processaEmail(sa, alarme, problema);
			}	
		}
		
	}
	
	public void geraAlarmeMemoriaNonHeap(ServidorAplicacao sa, BigDecimal utilizacao) {
		ServidorAplicacaoThreshold threshold = sa.getThreshold();
		
		if(threshold != null && threshold.getThresholdNonHeap() != null){
			if(utilizacao.compareTo(threshold.getThresholdNonHeap()) == 1){
				Alarme alarme = new Alarme();
				alarme.setData(new Date());
				alarme.setTipo(tipos.get(9));
				alarme.setValor(utilizacao);
				alarme.setValorLimite(threshold.getThresholdNonHeap());
				alarme.setNo(sa);
				alarme.setCriticidade(CriticidadeAlarme.ALERTA);
				
				String problema = "Utilização da memória nonHeap do servidor de aplicação " + sa.getNome() + " muito alto";
				
				processaEmail(sa, alarme, problema);
			}	
		}
		
	}

	public void geraAlarmesDeploymemnts(ServidorAplicacao sa,
			Map<String, ServidorAplicacaoDeployment> deployments) {
		
		Integer deployCount = 0;
		if(deployments != null){
			Set<String> keys = deployments.keySet();
			for (String key : keys) {
				ServidorAplicacaoDeployment deployment = deployments.get(key);
				if(deployment.getAtivo()){
					deployCount++;
				}else{
					Alarme alarme = new Alarme();
					alarme.setData(new Date());
					alarme.setTipo(tipos.get(7));
					alarme.setNo(sa);
					alarme.setParametro(deployment.getNome());
					alarme.setCriticidade(CriticidadeAlarme.ALERTA);
					
					String problema = "Aplicação " + deployment.getNome() + " do servidor de aplicação " + sa.getNome() + " está indisponível";
					
					processaEmail(sa, alarme, problema);				
				}
			}
		}
		
		if(deployCount == 0){
			Alarme alarme = new Alarme();
			alarme.setData(new Date());
			alarme.setTipo(tipos.get(6));
			alarme.setNo(sa);
			alarme.setCriticidade(CriticidadeAlarme.ALERTA);
			
			String problema = "Nenhuma aplicação rodando no servidor de aplicação " + sa.getNome();
			
			processaEmail(sa, alarme, problema);		
		}
	}

	public void geraAlarmeMemoriaBancoDados(BancoDados bd,
			BigDecimal utilizacao) {
		
		BancoDadosThreshold threshold = bd.getThreshold();
		
		if(threshold != null && threshold.getLimiteMemoria() != null){
			if(utilizacao.compareTo(threshold.getLimiteMemoria()) == 1){
				Alarme alarme = new Alarme();
				alarme.setData(new Date());
				alarme.setTipo(tipos.get(13));
				alarme.setNo(bd);
				alarme.setValor(utilizacao);
				alarme.setCriticidade(CriticidadeAlarme.ALERTA);
				
				String problema = "Utilização da memória do servidor de banco de dados " + bd.getNome() + " muito alta";
				
				processaEmail(bd, alarme, problema);
			}
		}
		
	}

	public void geraAlarmeFileBancoDados(BancoDados bd,
			BancoFileColeta coleta) {

		BancoDadosThreshold threshold = bd.getThreshold();
		
		if(threshold != null && threshold.getLimiteFile() != null && coleta.getFile().getMaxSize() != -1){
			BigDecimal utilizacao = new BigDecimal((coleta.getSize().doubleValue()/coleta.getFile().getMaxSize())*100);
			if(utilizacao.compareTo(threshold.getLimiteFile()) == 1){
				Alarme alarme = new Alarme();
				alarme.setData(new Date());
				alarme.setTipo(tipos.get(12));
				alarme.setNo(bd);
				alarme.setValor(utilizacao);
				alarme.setCriticidade(CriticidadeAlarme.ALERTA);
				
				String problema = "Tamanho do arquivo do banco de dados " + bd.getNome() + " acima do normal";
				
				processaEmail(bd, alarme, problema);
			}
			
		}
	}

	public void geraAlarmeStatus(BancoDados bd, String ultimoStatusBanco) {
		
		if(!bd.getStatus().equals("ONLINE") && !bd.getStatus().equals("ACTIVE") && !bd.getStatus().equals("ultimoStatusBanco") ){
			Alarme alarme = new Alarme();
			alarme.setData(new Date());
			alarme.setTipo(tipos.get(16));
			alarme.setNo(bd);
			alarme.setParametro(bd.getStatus());
			alarme.setCriticidade(CriticidadeAlarme.CRITICO);
			
			String problema = "Banco de dados " + bd.getNome() + " - Status Alterado";
			processaEmail(bd, alarme, problema);
		}
	}

	public void geraAlarmesJobs(BancoDados bd, BancoJobColeta coleta,
			String strStatus) {

		if(coleta.getStatus() != 2){
			Alarme alarme = new Alarme();
			alarme.setData(new Date());
			alarme.setTipo(tipos.get(14));
			alarme.setNo(bd);
			alarme.setParametro(coleta.getBancoJob().getJobName()+";"+strStatus);
			alarme.setCriticidade(CriticidadeAlarme.ALERTA);
			
			String problema = "Job " + coleta.getBancoJob().getJobName() + " - Problema na execu��o";
			processaEmail(bd, alarme, problema);
		}
		
		if(bd.getThreshold() != null && bd.getThreshold().getLimiteTempoJob() != null){
			if(coleta.getExecutionTime() > bd.getThreshold().getLimiteTempoJob()){
				Alarme alarme = new Alarme();
				alarme.setData(new Date());
				alarme.setTipo(tipos.get(17));
				alarme.setNo(bd);
				alarme.setParametro(coleta.getBancoJob().getJobName());
				alarme.setValor(new BigDecimal(coleta.getExecutionTime().doubleValue()));
				alarme.setValorLimite(new BigDecimal(bd.getThreshold().getLimiteTempoJob().doubleValue()));
				alarme.setCriticidade(CriticidadeAlarme.ALERTA);
				String problema = "Job " + coleta.getBancoJob().getJobName() + " - Tempo de execu��o acima do esperado";
				processaEmail(bd, alarme, problema);
			}
		}
	}
	
	public void geraAlarmeUltimoBackup(BancoDados bd, BancoBackup ultimoBackup){
		BancoDadosThreshold threshold = bd.getThreshold();
		
		if(threshold != null && threshold.getLimiteTempoBackup() != null && ultimoBackup != null){
			Long secsUltimo = ultimoBackup.getBackupStartDate().getTime()/1000;
			Long secsNow = new Date().getTime()/1000;
			Long diff = secsNow - secsUltimo;
			
			if(diff > threshold.getLimiteTempoBackup()){
				Alarme alarme = new Alarme();
				alarme.setData(new Date());
				alarme.setTipo(tipos.get(15));
				alarme.setNo(bd);
				alarme.setValor(new BigDecimal(diff.doubleValue()));
				alarme.setValorLimite(new BigDecimal(threshold.getLimiteTempoBackup().doubleValue()));
				alarme.setCriticidade(CriticidadeAlarme.ALERTA);
				String problema = "Banco de dados " + bd.getNome() + " - Muito tempo sem backup";
				processaEmail(bd, alarme, problema);
			}
			
		}
		
	}
	
	public void geraAlarmeSlaDiario(Sla sla, No no, BigDecimal percentual) {

		
			if(percentual.compareTo(sla.getMeta()) == -1){
				Alarme alarme = new Alarme();
				alarme.setData(new Date());
				alarme.setTipo(tipos.get(18));
				alarme.setNo(no);
				alarme.setValor(percentual);
				alarme.setValorLimite(sla.getMeta());
				alarme.setCriticidade(CriticidadeAlarme.CRITICO);
				
				String problema = "SLA diário " + sla.getNome();
				
				processaEmail(sla, alarme, problema);
			}
	}

	public void geraAlarmeSlaMensal(Sla sla, No no, BigDecimal percentual) {
	
		if(percentual.compareTo(sla.getMeta()) == -1){
			Alarme alarme = new Alarme();
			alarme.setData(new Date());
			alarme.setTipo(tipos.get(19));
			alarme.setNo(no);
			alarme.setValor(percentual);
			alarme.setValorLimite(sla.getMeta());
			alarme.setCriticidade(CriticidadeAlarme.CRITICO);
		
			String problema = "SLA mensal " + sla.getNome();
			
			processaEmail(sla, alarme, problema);
		}
	
	}
	
}

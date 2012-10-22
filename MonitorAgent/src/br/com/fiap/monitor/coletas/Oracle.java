package br.com.fiap.monitor.coletas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import br.com.fiap.monitor.coletas.estrutura.ReturnObject;
import br.com.fiap.monitor.factory.OracleConnectionFactory;


public class Oracle {

		private Connection conn;
		
		private static Oracle instance;
		
		private String user, password, host, instancia;
		private int port;
	    
	    public static Oracle getInstance() {
	    	
			if(Oracle.instance == null){
				Oracle.instance = new Oracle();
			}
			
			return Oracle.instance;
			
		}
		
	    public void setCredentials(String user, String password, String host, int port, String instancia){
			this.user = user;
			this.password = password;
			this.host = host;
			this.port = port;
			this.instancia = instancia;
		}
	    
		public void connect(){
			try{
				this.conn = OracleConnectionFactory.getConnection(this.user, this.password, this.host, this.port, this.instancia);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		public void disconnect(){
			try{
				this.conn.close();
		}	catch (Exception e){
			e.printStackTrace();
		}
		
		}
		
		public ReturnObject getMemory(){
			
			StringBuilder sql = new StringBuilder();
			ReturnObject retorno = new ReturnObject();
			
			sql.append(" create global temporary table  ");
			sql.append(" memory_usage (counter_name Varchar(50), cntr_value integer, MB numeric(8,2)) on commit preserve rows ");
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				stmt.execute();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			sql = new StringBuilder();
			
			sql.append(" insert into memory_usage ((select 'Target Server Memory (KB)' as counter_name, round(sum((SELECT SUM(VALUE)/1024 ");
			sql.append(" FROM V$PGASTAT WHERE NAME = 'aggregate PGA target parameter') +  ( select sum(bytes)/1024 from v$sgastat)),0) as cntr_value, ");
			sql.append(" round(sum((SELECT SUM(VALUE)/1024/1024 FROM V$PGASTAT WHERE NAME = 'aggregate PGA target parameter') +  ");
			sql.append(" ( select sum(bytes)/1024/1024 from v$sgastat)),0) as MB	from dual)) ");
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				stmt.execute();
				

				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			sql = new StringBuilder();
	
			sql.append(" select * from memory_usage ");
			
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					retorno.putValue("totalMemory", rs.getLong("cntr_value"));
				}
					
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			sql = new StringBuilder();
			sql.append("truncate table memory_usage");
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				stmt.execute();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			sql = new StringBuilder();
			
			sql.append("drop table memory_usage");
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				stmt.execute();
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			return retorno;
			
		}
		
		public ReturnObject getConfigMemory(){
			
			StringBuilder sql = new StringBuilder();
			ReturnObject retorno = new ReturnObject();
			
			sql.append(" create global temporary table  ");
			sql.append(" memory_usage (counter_name Varchar(50), cntr_value integer, MB numeric(8,2)) on commit preserve rows");
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				stmt.execute();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			sql = new StringBuilder();
			
			sql.append(" insert into memory_usage ((select 'Total Server Memory (KB)' as counter_name ,  ");
			sql.append(" round(sum((SELECT SUM(VALUE)/1024 FROM V$PGASTAT WHERE NAME = 'maximum PGA allocated') +  ");
			sql.append(" ( select sum(bytes)/1024 from v$sgastat)),0) as cntr_value,  ");
			sql.append(" round(sum((SELECT SUM(VALUE)/1024 FROM V$PGASTAT WHERE NAME = 'maximum PGA allocated') +  ");
			sql.append(" ( select sum(bytes)/1024 from v$sgastat)),0) as KB from dual))  ");
				
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				stmt.execute();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			sql = new StringBuilder();
			
			sql.append(" select * from memory_usage ");
			
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){

					retorno.putValue("targetMemory", rs.getLong("cntr_value"));
				}
					
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			sql = new StringBuilder();
			
			sql.append("truncate table memory_usage ");
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				stmt.execute();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			sql = new StringBuilder();
			
			sql.append("drop table memory_usage ");
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				stmt.execute();
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			return retorno;
			
		}
		
		public ReturnObject getConfigVersion(){
			
			StringBuilder sql = new StringBuilder();
			ReturnObject retorno = new ReturnObject();
			
			sql.append(" select version , edition from v$instance ");
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){

					retorno.putValue("Version", rs.getString("version"));
					retorno.putValue("Edition", rs.getString("edition"));
				}
					
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			return retorno;
			
		}
		
		public ReturnObject getStatus(){
			
			StringBuilder sql = new StringBuilder();
			ReturnObject retorno = new ReturnObject();
			
			sql.append(" SELECT database_status FROM V$INSTANCE ");
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){

					retorno.putValue("Status", rs.getString("database_status"));
				}
					
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			return retorno;
			
		}
		
		public ReturnObject getConfigCollation(){
			
			StringBuilder sql = new StringBuilder();
			ReturnObject retorno = new ReturnObject();
			
			sql.append(" select value from v$nls_parameters where parameter = 'NLS_LANGUAGE' ");
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){

					retorno.putValue("Collation", rs.getString("value"));
				}
					
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			return retorno;
			
		}
		
		public ReturnObject getConfigFiles(){
			
			StringBuilder sql = new StringBuilder();
			ReturnObject retorno = new ReturnObject();
			
			sql.append(" Select t.tablespace_name as idFile, SUBSTR(d.file_name,1,80) as Filepath, ");
			sql.append(" ROUND(MAX(d.bytes)/1024,2) as Sizes, ROUND(MAX(t.max_extents)/1024,2) as Maxsizes, ");
			sql.append(" t.pct_increase as Growth, t.status as Situacao, substr(file_name,instr(file_name,'\\',-1)+1,50) as sFileName ");
			sql.append(" FROM DBA_FREE_SPACE f, DBA_DATA_FILES d, DBA_TABLESPACES t ");
			sql.append(" WHERE t.tablespace_name = d.tablespace_name AND f.tablespace_name(+) = d.tablespace_name ");
			sql.append(" AND f.file_id(+) = d.file_id GROUP BY t.tablespace_name, d.file_name, t.pct_increase, t.status union ");
			sql.append(" Select substr(f.member,instr(f.member,'\\',-1)+1, length(substr(f.member,instr(f.member,'\\',-1)+1))-4) as idFile, ");
			sql.append(" f.member, ROUND(MAX(l.bytes)/1024,2), 0, 0, l.status, substr(f.member,instr(f.member,'\\',-1)+1,50) ");
			sql.append(" from v$logfile f, v$log l where l.group# = f.group# group by f.member, l.bytes, l.status ORDER BY 1,3 DESC ");

			List<Map<String, Object>> listFiles = new ArrayList<Map<String,Object>>();
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()){
					
					Map<String,Object> value = new HashMap<String, Object>();
					

					value.put("File", rs.getString("idFile"));
					value.put("FilePath", rs.getString("Filepath"));
					value.put("Size", rs.getLong("Sizes"));
					value.put("Maxsize", rs.getLong("Maxsizes"));
					value.put("Growth", rs.getString("Growth"));
					value.put("Situacao", rs.getString("Situacao"));
					value.put("FileName", rs.getString("sFileName"));
					
					listFiles.add(value);
				}
					
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			JSONArray json = JSONArray.fromObject(listFiles);
			retorno.putValue("files", json);			
			return retorno;
			
		}
		
		
		public ReturnObject getConfigBackup(Long setCount){
			
			StringBuilder sql = new StringBuilder();
			ReturnObject retorno = new ReturnObject();
			
			sql.append(" select i.instance_name as InstanceName, substr(f.fname,instr(f.fname,'\\',-1)+1,50) as FileName, "); 
			sql.append(" s.start_time as Backup_start_date , s.elapsed_seconds as TempodeExecucao, i.host_name as ServerName, s.set_count, ");
			sql.append(" case(s.backup_type) when 'D' then 'Full Backup' when 'I' then 'Incremental Backup' ");
			sql.append(" when 'L' then 'Redo Logs' end as RecoveryModel, ROUND(MAX(f.bytes)/1024,2) as TamanhoKB "); 
			sql.append(" from v$instance i, v$backup_files f, v$backup_set s where s.recid = f.bs_key and "); 
			sql.append(" substr(f.fname,instr(f.fname,'.',-1)+1,50) = 'BKP' "); 
			sql.append(" and s.set_count > ? ");
			sql.append(" group by i.instance_name, f.fname, s.start_time, s.set_count, s.elapsed_seconds, i.host_name, s.backup_type ");
								
			List<Map<String, Object>> listBackup = new ArrayList<Map<String,Object>>();
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				stmt.setLong(1, setCount);
				
				ResultSet rs = stmt.executeQuery();
								
				while(rs.next()){
					
					Map<String,Object> value = new HashMap<String, Object>();
					
					value.put("InstanceName", rs.getString("database_name"));
					value.put("FileName", rs.getString("name"));
					value.put("BackupStartDate", rs.getString("Backup_start_date"));
					value.put("TempoExecucao", rs.getString("Tempo de Execucao"));
					value.put("ServerName", rs.getString("server_name"));
					value.put("RecoveryModel", rs.getString("recovery_model"));
					value.put("Tamanho", rs.getString("Tamanho (KB)"));
					value.put("Tipo", rs.getString("Tipo de Backup"));
					value.put("SetCount",rs.getLong("backup_set_id"));
										
					listBackup.add(value);
				}
					
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			JSONArray json = JSONArray.fromObject(listBackup);
			retorno.putValue("backup", json);
			return retorno;
			
		}
				
		public ReturnObject getConfigJobHistory(){
			
			StringBuilder sql = new StringBuilder();
			ReturnObject retorno = new ReturnObject();
			
			sql.append("SELECT LOG_ID, OWNER, JOB_NAME, STATUS, ACTUAL_START_DATE, cast((to_number(extract(second from RUN_DURATION)) + "); 
			sql.append("		to_number(extract(minute from RUN_DURATION)) * 60 + ");
			sql.append("		to_number(extract(hour from RUN_DURATION))   * 60 * 60 + ");
			sql.append("		to_number(extract(day from RUN_DURATION))  * 60 * 60* 24) as integer) as RUN_DURATION,ADDITIONAL_INFO from dba_scheduler_job_run_details a");
			sql.append("		WHERE log_id = (SELECT max(log_id) from dba_scheduler_job_run_details b WHERE  b.JOB_NAME = a.JOB_NAME )");
			sql.append("		order by actual_start_date desc");
								
			List<Map<String, Object>> listJobHistory = new ArrayList<Map<String,Object>>();
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				stmt.toString();
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()){
					
					Map<String,Object> value = new HashMap<String, Object>();
					
					value.put("LogID", rs.getLong("LOG_ID"));
					value.put("Owner", rs.getString("OWNER"));
					value.put("JobName", rs.getString("JOB_NAME"));
					value.put("Status", rs.getString("STATUS"));
					value.put("DataExecucao", rs.getTimestamp("ACTUAL_START_DATE").getTime());
					value.put("Duracao", rs.getString("RUN_DURATION"));
					value.put("MensagemSQL", rs.getString("ADDITIONAL_INFO"));
					
					listJobHistory.add(value);
				}
					
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			
			JSONArray json = JSONArray.fromObject(listJobHistory);
			retorno.putValue("jobhistory", json);
			return retorno;
			
		}				
	
}

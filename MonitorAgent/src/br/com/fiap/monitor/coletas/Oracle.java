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
		
		public Oracle(){
			try{
				this.conn = OracleConnectionFactory.getConnection();
			} catch (SQLException ex) {
				ex.printStackTrace();
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
					//Esse put value é tipo um chave valor que eu fiz pra facilitar o retorno
					retorno.putValue("targetMemory", rs.getString("cntr_value"));
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
					//Esse put value é tipo um chave valor que eu fiz pra facilitar o retorno
					retorno.putValue("totalMemory", rs.getString("cntr_value"));
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
		
		public ReturnObject getVersion(){
			
			StringBuilder sql = new StringBuilder();
			ReturnObject retorno = new ReturnObject();
			
			sql.append(" select concat('Version: ', version ) as VERSION, concat('Edition: ',edition) as EDITION from v$instance ");
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					//Esse put value é tipo um chave valor que eu fiz pra facilitar o retorno
					retorno.putValue("Version", rs.getString("VERSION"));
					retorno.putValue("Edition", rs.getString("EDITION"));
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
					//Esse put value é tipo um chave valor que eu fiz pra facilitar o retorno
					retorno.putValue("Status", rs.getString("database_status"));
				}
					
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			return retorno;
			
		}
		
		public ReturnObject getCollation(){
			
			StringBuilder sql = new StringBuilder();
			ReturnObject retorno = new ReturnObject();
			
			sql.append(" select value from v$nls_parameters where parameter = 'NLS_LANGUAGE' ");
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					//Esse put value é tipo um chave valor que eu fiz pra facilitar o retorno
					retorno.putValue("Collation", rs.getString("value"));
				}
					
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			return retorno;
			
		}
		
		public ReturnObject getDrive(){
			
			StringBuilder sql = new StringBuilder();
			ReturnObject retorno = new ReturnObject();
			
			sql.append(" Select t.tablespace_name as Files, SUBSTR(d.file_name,1,80) as Filepath, ");
			sql.append(" ROUND(MAX(d.bytes)/1024,2) as Sizes, ROUND(MAX(t.max_extents)/1024,2) as Maxsizes, ");
			sql.append(" t.pct_increase as Growth, t.status as Situacao, substr(file_name,instr(file_name,'\',-1)+1,50) as Filename ");
			sql.append(" FROM DBA_FREE_SPACE f, DBA_DATA_FILES d, DBA_TABLESPACES t ");
			sql.append(" WHERE t.tablespace_name = d.tablespace_name AND f.tablespace_name(+) = d.tablespace_name ");
			sql.append(" AND f.file_id(+) = d.file_id GROUP BY t.tablespace_name, d.file_name, t.pct_increase, t.status union ");
			sql.append(" select substr(f.member,instr(f.member,'\',-1)+1, length(substr(f.member,instr(f.member,'\',-1)+1))-4), ");
			sql.append(" f.member, ROUND(MAX(l.bytes)/1024,2), 0, 0, l.status, substr(f.member,instr(f.member,'\',-1)+1,50) ");
			sql.append(" from v$logfile f, v$log l where l.group# = f.group# group by f.member, l.bytes, l.status ORDER BY 1,3 DESC ");

			List<Map<String, Object>> listDrive = new ArrayList<Map<String,Object>>();
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()){
					
					Map<String,Object> value = new HashMap<String, Object>();
					
					//Esse put value é tipo um chave valor que eu fiz pra facilitar o retorno
					value.put("File", rs.getString("Files"));
					value.put("File Path", rs.getString("Filepath"));
					value.put("Size", rs.getString("Sizes"));
					value.put("Maxsize", rs.getString("Maxsizes"));
					value.put("Growth", rs.getString("Growth"));
					value.put("Situacao", rs.getString("Situacao"));
					value.put("File Name", rs.getString("Filename"));
					
					listDrive.add(value);
				}
					
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			JSONArray json = JSONArray.fromObject(listDrive);
			retorno.putValue("drive", json);			
			return retorno;
			
		}
		
		
		public ReturnObject getBackup(){
			
			StringBuilder sql = new StringBuilder();
			ReturnObject retorno = new ReturnObject();
			
			sql.append(" select i.instance_name as InstanceName, substr(f.fname,instr(f.fname,'\',-1)+1,50) as FileName, ");
			sql.append(" s.start_time as Backup_start_date , s.elapsed_seconds  as TempodeExecucao, i.host_name as ServerName, ");
			sql.append(" case(s.backup_type) when 'D' then 'Full Backup' when 'I' then 'Incremental Backup' ");
			sql.append(" when 'L' then 'Redo Logs' end as RecoveryModel, ROUND(MAX(f.bytes)/1024,2) as TamanhoKB ");
			sql.append(" from v$instance i, v$backup_files f, v$backup_set s where s.recid = f.bs_key and ");
			sql.append(" substr(f.fname,instr(f.fname,'.',-1)+1,50) = 'BKP' ");
			sql.append(" AND s.start_time >= to_timestamp(concat(to_char(sysdate - interval '1' DAY) || ' ',to_char('18:00:00')),'DD:MM:YY HH24:MI:SS') ");
			sql.append(" and s.start_time < sysdate + interval '1' DAY ");
			sql.append(" group by i.instance_name, f.fname, s.start_time, s.elapsed_seconds, i.host_name, s.backup_type ");
			
					
			List<Map<String, Object>> listBackup = new ArrayList<Map<String,Object>>();
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()){
					
					Map<String,Object> value = new HashMap<String, Object>();
					//Esse put value é tipo um chave valor que eu fiz pra facilitar o retorno
					
					value.put("Instance Name", rs.getString("INSTANCENAME"));
					value.put("File Name", rs.getString("FILENAME"));
					value.put("Backup Start Date", rs.getString("BACKUP_START_DATE"));
					value.put("Tempo de Execucao (s)", rs.getString("TEMPODEEXECUCAO"));
					value.put("Server Name", rs.getString("SERVERNAME"));
					value.put("Recovery Model", rs.getString("RECOVERYMODEL"));
					value.put("Tamanho (KB)", rs.getString("TAMANHOKB"));
										
					listBackup.add(value);
				}
					
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			JSONArray json = JSONArray.fromObject(listBackup);
			retorno.putValue("backup", json);
			return retorno;
			
		}
		
		 
		public ReturnObject getJobRunning(){
			
			StringBuilder sql = new StringBuilder();
			ReturnObject retorno = new ReturnObject();
			
			sql.append(" select job, to_timestamp(concat(to_char(last_date) || ' ',to_char(last_sec)),'DD:MM:YY HH24:MI:SS') as RunRequestedDate,");
			sql.append(" (to_date(this_sec,'hh24:mi:ss') - to_date(last_sec,'hh24:mi:ss')) *60*60*24 as ExecutionTime ");
			sql.append(" from dba_jobs_running ");
			sql.append(" where last_date is not null and last_sec is not null");
					
			List<Map<String, Object>> listJobRunning = new ArrayList<Map<String,Object>>();
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()){
					
					Map<String,Object> value = new HashMap<String, Object>();
					//Esse put value é tipo um chave valor que eu fiz pra facilitar o retorno
					
					value.put("Job Name", rs.getString("JOB"));
					value.put("Run Date", rs.getString("RUNREQUESTEDDATE"));
					value.put("Execution Time", rs.getString("EXECUTIONTIME"));
					
					listJobRunning.add(value);
				}
					
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			JSONArray json = JSONArray.fromObject(listJobRunning);
			retorno.putValue("jobrunning", json);
			return retorno;
			
		}				
				
				
				
		public ReturnObject getJobHistory(){
			
			StringBuilder sql = new StringBuilder();
			ReturnObject retorno = new ReturnObject();
			
			sql.append(" select JOB_NAME, STATUS, ACTUAL_START_DATE, RUN_DURATION,ADDITIONAL_INFO from dba_scheduler_job_run_details WHERE STATUS <> 'SUCCEEDED' and ");
			sql.append(" log_date >= to_timestamp(concat(to_char(sysdate - interval '1' DAY) || ' ',to_char('17:00:00')),'DD:MM:YY HH24:MI:SS') ");
			sql.append(" order by actual_start_date desc ");
								
			List<Map<String, Object>> listJobHistory = new ArrayList<Map<String,Object>>();
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()){
					
					Map<String,Object> value = new HashMap<String, Object>();
					//Esse put value é tipo um chave valor que eu fiz pra facilitar o retorno
					
					value.put("Job Name", rs.getString("JOB_NAME"));
					value.put("Status", rs.getString("STATUS"));
					value.put("Data de Execucao", rs.getString("ACTUAL_START_DATE"));
					value.put("Duracao", rs.getString("RUN_DURATION"));
					value.put("Mensagem SQL", rs.getString("ADDITIONAL_INFO"));
					
					listJobHistory.add(value);
				}
					
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			sql = new StringBuilder();
			sql.append("drop table ##Result_History_Jobs");
			
			try{
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				stmt.execute();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
			JSONArray json = JSONArray.fromObject(listJobHistory);
			retorno.putValue("jobhistory", json);
			return retorno;
			
		}				
	
}

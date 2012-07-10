package br.com.fiap.monitor.coletas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.fiap.monitor.coletas.estrutura.ReturnObject;

import br.com.fiap.monitor.factory.SqlServerConnectionFactory;

import net.sf.json.JSONArray;



public class SqlServer {

	private Connection conn;
	
	public SqlServer(){
		try{
			this.conn = SqlServerConnectionFactory.getConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Metodos com config são os de inventário, esse pega o total de memoria reservada no mysql
	 * Baseado na primeira query
	 * @return
	 */
	public ReturnObject getConfigMemory(){
		
		StringBuilder sql = new StringBuilder();
		ReturnObject retorno = new ReturnObject();
		
		sql.append(" SELECT counter_name ,cntr_value ");
		sql.append("     FROM master.sys.dm_os_performance_counters");
		sql.append("     WHERE counter_name = 'Total Server Memory (KB)'");
		
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
			
		return retorno;
		
	}
	
	
	public ReturnObject getMemory(){
		
		StringBuilder sql = new StringBuilder();
		ReturnObject retorno = new ReturnObject();
		
		sql.append(" SELECT counter_name ,cntr_value ");
		sql.append("     FROM master.sys.dm_os_performance_counters");
		sql.append("     WHERE counter_name = 'Target Server Memory (KB)'");
		sql.append("     OR counter_name = 'Stolen Server Memory (KB)'");
		
		List<Map<String, Object>> listMemory = new ArrayList<Map<String,Object>>();
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				
				Map<String,Object> value = new HashMap<String, Object>();
				
				if(rs.getString("counter_name").trim().equals("Target Server Memory (KB)")){
					//Esse put value é tipo um chave valor que eu fiz pra facilitar o retorno
					value.put("targetMemory", rs.getString("cntr_value"));
				}else if(rs.getString("counter_name").trim().equals("Stolen Server Memory (KB)"))
					value.put("stolenMemory", rs.getString("cntr_value"));
				
				listMemory.add(value);
			}
				
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
			
		JSONArray json = JSONArray.fromObject(listMemory);
		retorno.putValue("memory", json);	
		return retorno;
		
	}
	
	public ReturnObject getConfigVersion(){
		
		StringBuilder sql = new StringBuilder();
		ReturnObject retorno = new ReturnObject();
		
		sql.append(" SELECT 'SQL Server Version ' + CAST(SERVERPROPERTY('productversion') AS VARCHAR(50)) as VERSION, ");
		sql.append("'SQL Server ' + CAST(SERVERPROPERTY ('edition') AS VARCHAR(50)) as EDITION");
				
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
	
	public ReturnObject getStatus(String database){
		
		StringBuilder sql = new StringBuilder();
		ReturnObject retorno = new ReturnObject();
		
		sql.append(" select DATABASEPROPERTYEX (?,'status') as STATUS");
		
						
		try{
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, database);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				//Esse put value é tipo um chave valor que eu fiz pra facilitar o retorno
				retorno.putValue("Status", rs.getString("STATUS"));
			}
				
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
			
		return retorno;
		
	}
	
	public ReturnObject getConfigCollation(String database){
		
		StringBuilder sql = new StringBuilder();
		ReturnObject retorno = new ReturnObject();
		
		sql.append(" select DATABASEPROPERTYEX (?,'collation') as COLLATION");
		
						
		try{
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, database);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				//Esse put value é tipo um chave valor que eu fiz pra facilitar o retorno
				retorno.putValue("Collation", rs.getString("COLLATION"));
			}
				
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
			
		return retorno;
		
	}
	
	public ReturnObject getConfigDrive(){
		
		StringBuilder sql = new StringBuilder();
		ReturnObject retorno = new ReturnObject();
		
		sql.append(" create table ##dbspace(	[DatabaseName] varchar(250) , [FilePath] varchar(250) , [Size] bigint); ");
		sql.append(" Exec SP_MSForEachDB 'Use ? Insert into ##dbspace select Convert(Varchar(25),DB_Name()), Filename , ");
		sql.append(" cast(Size * 8 as bigint) Size	from sys.sysfiles';");
		sql.append(" CREATE TABLE ##space (drive varchar(10), mbfree int);");
		sql.append(" INSERT INTO ##space EXEC master.dbo.xp_fixeddrives;");
		sql.append(" alter table ##space add tamanhosql int;");
		sql.append(" update ##space set tamanhosql = (select SUM(cast((b.size) as int))");
		sql.append(" from ##dbspace b where ##space.drive  = SUBSTRING(b.filepath,1,1));");
		sql.append(" update ##space set drive = drive + ':';");
		sql.append(" select * from ##space;");

		List<Map<String, Object>> listDrive = new ArrayList<Map<String,Object>>();
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				
				Map<String,Object> value = new HashMap<String, Object>();
				
				//Esse put value é tipo um chave valor que eu fiz pra facilitar o retorno
				value.put("Drive", rs.getString("drive"));
				value.put("MB Free", rs.getString("mbfree"));
				value.put("Tamanho do SQL", rs.getString("tamanhosql"));
				
				listDrive.add(value);
			}
				
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		sql = new StringBuilder();
		sql.append("drop table ##dbspace;");
		sql.append("drop table ##space;");
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			stmt.execute();
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		JSONArray json = JSONArray.fromObject(listDrive);
		retorno.putValue("drive", json);			
		return retorno;
		
	}
	
	
	public ReturnObject getConfigFiles(){
	
		StringBuilder sql = new StringBuilder();
		ReturnObject retorno = new ReturnObject();
		
		sql.append(" create table ##dbfiles(		[DatabaseName] varchar(250) , [FilePath] varchar(250) , [Size] bigint, [MaxSize] bigint, ");
		sql.append(" Growth varchar(100), Situacao varchar(15), [FileName] Varchar(30));");
		sql.append(" Exec SP_MSForEachDB 'Use ? Insert into ##dbfiles select Convert(Varchar(25),DB_Name()), Filename ,");
		sql.append(" cast(Size * 8 as bigint) Size,");
		sql.append(" cast(case when MaxSize = -1 then -1 else cast(MaxSize as bigint)* 8 end as bigint) MaxSize,");
		sql.append(" case when substring(cast(Status as varchar),1,2) = 10 then cast(Growth as varchar) + '' %''");
		sql.append(" else cast (cast((Growth * 8 )/1024.00 as numeric(15,2)) as varchar) + '' MB''end Growth,");
		sql.append(" case when MaxSize = -1 then ''OK'' ");
		sql.append(" when ( case when substring(cast(Status as varchar),1,2) = 10 ");
		sql.append(" then (cast(Size as bigint)* 8 / 1024.00) * ((Growth/100.00) + 1) ");
		sql.append(" else (cast(Size as bigint) * 8/ 1024.00) + cast((Growth * 8 )/1024.00 as numeric(15,2)) ");
		sql.append(" end	) < (cast(MaxSize as bigint) * 8/1024.00) then ''OK'' else ''PROBLEMA''");
		sql.append(" end Situacao,Convert(Varchar(30),Name)");
		sql.append(" from sys.sysfiles with(nolock) 	order by Situacao, Size desc';");
		sql.append(" select * from ##dbfiles;");
		
		List<Map<String, Object>> listFiles = new ArrayList<Map<String,Object>>();
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				
				Map<String,Object> value = new HashMap<String, Object>();
				
				value.put("DatabaseName", rs.getString("DatabaseName"));
				value.put("FilePath", rs.getString("FilePath"));
				value.put("Size", rs.getString("Size"));
				value.put("Maxsize", rs.getString("MaxSize"));
				value.put("Growth", rs.getString("Growth"));
				value.put("Situacao", rs.getString("Situacao"));
				value.put("File Name", rs.getString("FileName"));
				
				listFiles.add(value);
				
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		sql = new StringBuilder();
		sql.append("drop table ##dbfiles;");
			
		try{
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			stmt.execute();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		JSONArray json = JSONArray.fromObject(listFiles);
		retorno.putValue("files", json);
		return retorno;
		
	}
	
	public ReturnObject getLogSpace(){
		
		StringBuilder sql = new StringBuilder();
		ReturnObject retorno = new ReturnObject();
		
		sql.append(" DBCC SQLPERF (LOGSPACE); ");
				
		List<Map<String, Object>> listLogSpace = new ArrayList<Map<String,Object>>();
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				
				Map<String,Object> value = new HashMap<String, Object>();
				//Esse put value é tipo um chave valor que eu fiz pra facilitar o retorno
				
				value.put("Database Name", rs.getString("Database Name"));
				value.put("Log Size (MB)", rs.getString("Log Size (MB)"));
				value.put("Log Space Used (%)", rs.getString("Log Space Used (%)"));
				
				listLogSpace.add(value);
			}
				
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		JSONArray json = JSONArray.fromObject(listLogSpace);
		retorno.putValue("logspace", json);
		return retorno;
		
	}
	
	public ReturnObject getBackup(){
		
		StringBuilder sql = new StringBuilder();
		ReturnObject retorno = new ReturnObject();
		
		sql.append(" SELECT database_name, name,Backup_start_date, ");
		sql.append(" case when Backup_finish_date IS NULL then 'EM EXECUÇÃO' else ");
		sql.append(" datediff(mi,Backup_start_date,Backup_finish_date) end [Tempo de Execucao],");
		sql.append(" server_name,recovery_model, cast(backup_size as numeric(15)) [Tamanho (B)], ");
		sql.append(" case when type = 'D' then 'Database' ");
		sql.append(" when type = 'I' then 'Differential Database' ");
		sql.append(" when type = 'L' then 'Log' ");
		sql.append(" when type = 'F' then 'File ou Filegroup' ");
		sql.append(" when type = 'G' then 'Differential File' ");
		sql.append(" when type = 'P' then 'Partial' ");
		sql.append(" when type = 'Q' then 'Differential Partial' ");
		sql.append(" end [Tipo de Backup]");
		sql.append(" FROM msdb.dbo.backupset B");
		sql.append(" INNER JOIN msdb.dbo.backupmediafamily BF ON B.media_set_id = BF.media_set_id");
		sql.append(" where Backup_start_date >= dateadd(hh, 18 ,cast(floor(cast(GETDATE() as float)) as datetime) - 1 ) ");
		sql.append(" and Backup_start_date < dateadd (day, 1, cast(floor(cast(GETDATE() as float)) as datetime)) ; ");
		
				
		List<Map<String, Object>> listBackup = new ArrayList<Map<String,Object>>();
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				
				Map<String,Object> value = new HashMap<String, Object>();
				//Esse put value é tipo um chave valor que eu fiz pra facilitar o retorno
				
				value.put("Database Name", rs.getString("database_name"));
				value.put("Backup Name", rs.getString("name"));
				value.put("Start Date", rs.getString("Backup_start_date"));
				value.put("Tempo de Execucao", rs.getString("Tempo de Execucao"));
				value.put("Server Name", rs.getString("server_name"));
				value.put("Recovery Model", rs.getString("recovery_model"));
				value.put("Tamanho KB", rs.getString("Tamanho (KB)"));
				value.put("Tipo de Backup", rs.getString("Tipo de Backup"));
				
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
		
		sql.append(" select name, run_Requested_Date, datediff(mi,run_Requested_Date,getdate()) as 'Execution Time' ");
		sql.append(" from msdb.dbo.sysjobactivity A ");
		sql.append(" join msdb.dbo.sysjobs B on A.job_id = B.job_id ");
		sql.append(" where start_Execution_Date is not null and stop_execution_date is null; ");
				
		List<Map<String, Object>> listJobRunning = new ArrayList<Map<String,Object>>();
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				
				Map<String,Object> value = new HashMap<String, Object>();
				//Esse put value é tipo um chave valor que eu fiz pra facilitar o retorno
				
				value.put("Job Name", rs.getString("name"));
				value.put("Run Date", rs.getString("run_Requested_Date"));
				value.put("Execution Time", rs.getString("Execution Time"));
				
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
		
		sql.append(" create table ##Result_History_Jobs( ");
		sql.append(" Cod int identity(1,1),Instance_Id int, Job_Id varchar(255), ");
		sql.append(" Job_Name varchar(255),Step_Id int,Step_Name varchar(255), ");
		sql.append(" Sql_Message_Id int,Sql_Severity int,SQl_Message varchar(3990), ");
		sql.append(" Run_Status int, Run_Date varchar(20),");
		sql.append(" Run_Time varchar(20),Run_Duration int,Operator_Emailed varchar(100),Operator_NetSent varchar(100), ");
		sql.append(" Operator_Paged varchar(100),Retries_Attempted int, Nm_Server varchar(100));");
		sql.append(" declare @ontem varchar (8);");
		sql.append(" set @ontem = convert (varchar(8),(dateadd (day, -1, getdate())),112);");
		sql.append(" insert into ##Result_History_Jobs ");
		sql.append(" exec Msdb.dbo.SP_HELP_JOBHISTORY @mode = 'FULL' , @start_run_date = @ontem;");
		sql.append(" select Job_Name, case when Run_Status = 0 then 'Failed' ");
		sql.append(" when Run_Status = 1 then 'Succeeded' ");
		sql.append(" when Run_Status = 2 then 'Retry (step only)' ");
		sql.append(" when Run_Status = 3 then 'Canceled' ");
		sql.append(" when Run_Status = 4 then 'In-progress message' ");
		sql.append(" when Run_Status = 5 then 'Unknown' end Status, ");
		sql.append(" cast(Run_Date + ' ' + ");
		sql.append(" right('00' + substring(Run_time,(len(Run_time)-5),2) ,2)+ ':' + ");
		sql.append(" right('00' + substring(Run_time,(len(Run_time)-3),2) ,2)+ ':' + ");
		sql.append(" right('00' + substring(Run_time,(len(Run_time)-1),2) ,2) as varchar) Dt_Execucao, ");
		sql.append(" cast(Run_Duration as integer)/60 Run_Duration, ");
		sql.append(" SQL_Message ");
		sql.append(" from ##Result_History_Jobs ");
		sql.append(" where cast(Run_Date + ' ' + right('00' + substring(Run_time,(len(Run_time)-5),2) ,2)+ ':' + ");
		sql.append(" right('00' + substring(Run_time,(len(Run_time)-3),2) ,2)+ ':' + ");
		sql.append(" right('00' + substring(Run_time,(len(Run_time)-1),2) ,2) as datetime) >= ");
		sql.append(" convert (varchar(8),(dateadd (day, -1, getdate())),112) + ' 17:00' ");
		sql.append(" and Step_Id = 0	and Run_Status <> 1 ");
		sql.append(" order by Dt_Execucao desc ;");
		sql.append(" ");
				
		List<Map<String, Object>> listJobHistory = new ArrayList<Map<String,Object>>();
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				
				Map<String,Object> value = new HashMap<String, Object>();
				//Esse put value é tipo um chave valor que eu fiz pra facilitar o retorno
				
				value.put("Job Name", rs.getString("Job_Name"));
				value.put("Status", rs.getString("Status"));
				value.put("Data de Execucao", rs.getString("Dt_Execucao"));
				value.put("Duracao", rs.getString("Run_Duration"));
				value.put("Mensagem SQL", rs.getString("SQL_Message"));
				
				listJobHistory.add(value);
			}
				
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		sql = new StringBuilder();
		sql.append("drop table ##Result_History_Jobs;");
		
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

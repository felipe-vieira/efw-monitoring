package br.com.fiap.coleta.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.fiap.coleta.cgt.coletas.SqlServerColeta;

public class SQLServer extends BancoDados implements Serializable{
	
	private static final long serialVersionUID = -4029595958441788034L;
	
	private Long StolenServerMemory;
	
	private String Drive;
	
	private Long FreeDriveSpace;
	
	private Long SQLSize;
	
	private String DBName;
	
	private BigDecimal LogSize;
	
	private BigDecimal LogSpaceUsedPercent;

	public String getDrive() {
		return Drive;
	}

	public void setDrive(String drive) {
		Drive = drive;
	}

	public Long getFreeDriveSpace() {
		return FreeDriveSpace;
	}

	public void setFreeDriveSpace(Long freeDriveSpace) {
		FreeDriveSpace = freeDriveSpace;
	}

	public Long getSQLSize() {
		return SQLSize;
	}

	public void setSQLSize(Long sQLSize) {
		SQLSize = sQLSize;
	}

	public String getDBName() {
		return DBName;
	}

	public void setDBName(String dBName) {
		DBName = dBName;
	}

	public BigDecimal getLogSize() {
		return LogSize;
	}

	public void setLogSize(BigDecimal logSize) {
		LogSize = logSize;
	}

	public BigDecimal getLogSpaceUsedPercent() {
		return LogSpaceUsedPercent;
	}

	public void setLogSpaceUsedPercent(BigDecimal logSpaceUsedPercent) {
		LogSpaceUsedPercent = logSpaceUsedPercent;
	}
	
	public Long getStolenServerMemory() {
		return StolenServerMemory;
	}

	public void setStolenServerMemory(Long stolenServerMemory) {
		StolenServerMemory = stolenServerMemory;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((DBName == null) ? 0 : DBName.hashCode());
		result = prime * result + ((Drive == null) ? 0 : Drive.hashCode());
		result = prime * result
				+ ((FreeDriveSpace == null) ? 0 : FreeDriveSpace.hashCode());
		result = prime * result + ((LogSize == null) ? 0 : LogSize.hashCode());
		result = prime
				* result
				+ ((LogSpaceUsedPercent == null) ? 0 : LogSpaceUsedPercent
						.hashCode());
		result = prime * result + ((SQLSize == null) ? 0 : SQLSize.hashCode());
		result = prime
				* result
				+ ((StolenServerMemory == null) ? 0 : StolenServerMemory
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SQLServer other = (SQLServer) obj;
		if (DBName == null) {
			if (other.DBName != null)
				return false;
		} else if (!DBName.equals(other.DBName))
			return false;
		if (Drive == null) {
			if (other.Drive != null)
				return false;
		} else if (!Drive.equals(other.Drive))
			return false;
		if (FreeDriveSpace == null) {
			if (other.FreeDriveSpace != null)
				return false;
		} else if (!FreeDriveSpace.equals(other.FreeDriveSpace))
			return false;
		if (LogSize == null) {
			if (other.LogSize != null)
				return false;
		} else if (!LogSize.equals(other.LogSize))
			return false;
		if (LogSpaceUsedPercent == null) {
			if (other.LogSpaceUsedPercent != null)
				return false;
		} else if (!LogSpaceUsedPercent.equals(other.LogSpaceUsedPercent))
			return false;
		if (SQLSize == null) {
			if (other.SQLSize != null)
				return false;
		} else if (!SQLSize.equals(other.SQLSize))
			return false;
		if (StolenServerMemory == null) {
			if (other.StolenServerMemory != null)
				return false;
		} else if (!StolenServerMemory.equals(other.StolenServerMemory))
			return false;
		return true;
	}

	@Override
	public void coleta(){
		SqlServerColeta coleta = new SqlServerColeta(this);
		coleta.initColeta();
	}	
	
}

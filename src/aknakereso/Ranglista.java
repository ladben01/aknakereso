package aknakereso;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ranglista implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3313836580563615984L;
	private String idoStr;
	private String nehezseg;
	private String dateStr;
	
	public Ranglista(long i, String n)
	{
		idoStr = String.valueOf(i/1000/60) + "min " + String.valueOf(i / 1000 % 60) + "s";
		nehezseg = n;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		dateStr = formatter.format(date);
	}
	
	public String getIdo()
	{
		return idoStr;
	}
	public String getNehezseg()
	{
		return nehezseg;
	}
	public String getDate()
	{
		return dateStr;
	}
	public String toString()
	{
		return dateStr + " " + nehezseg + " " + idoStr;
	}
}

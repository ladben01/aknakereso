package aknakereso;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

public class Mezo extends JButton
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4806995254342123484L;
	private boolean felfedve;
	private boolean zaszlo;
	private char tartalom;
	private int xPos;
	private int yPos;
	
	public Mezo(char c, int x, int y)
	{
		felfedve = false;
		zaszlo = false;
		tartalom = c;
		xPos = x;
		yPos = y;
		this.setSize(new Dimension(16,16));
		this.setForeground(Color.black);
	}
	
	public boolean felfed()
	{
		felfedve = true;
		if(tartalom != ' ')
		{
			setText(String.valueOf(tartalom));
			if(tartalom == '@') setBackground(Color.red);
			else this.setContentAreaFilled(false);
			
			return false;
		}
		else
		{
			setBackground(Color.white);
			this.setContentAreaFilled(false);
			return true;
		}
		
	}
	public boolean getZaszlo()
	{
		return zaszlo;
	}
	public int getPosX()
	{
		return xPos;
	}
	public int getPosY()
	{
		return yPos;
	}
	public char getContent()
	{
		return tartalom;
	}
	public boolean felfedveVan()
	{
		return felfedve;
	}
	
	public void setContent(char ujTartalom)
	{
		tartalom = ujTartalom;
	}
	public void setZaszlo()
	{
		zaszlo = !zaszlo;
		if(zaszlo)
			setText(Character.toString('P'));
		else setText(null);
	}
}

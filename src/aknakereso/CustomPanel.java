package aknakereso;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class CustomPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4530049619929944208L;
	protected CardLayout layout;
	protected CustomPanel otherPanel;
	protected Tabla aJatek;
	protected CustomFrame ablak;
	
	public void changeLayout(String newLayout)
	{
		layout.show(this, newLayout);
	}
	public void setOtherPanel(CustomPanel panel)
	{
		this.otherPanel = panel;
	}
	public void changeMode(String mode)
	{
		
	}
}

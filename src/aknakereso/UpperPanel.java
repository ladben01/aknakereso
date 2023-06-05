package aknakereso;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UpperPanel extends CustomPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7900620210453939928L;
	private JButton valto;
	private JButton visszaW;
	private JButton visszaL;
	private JCheckBox egygombos;
	private JLabel aknaCount;
	
	public UpperPanel(CustomFrame frame)
	{
		ablak = frame;
		JPanel aCim = new JPanel();
		JPanel aGyozelem = new JPanel();
		JPanel aVesztes = new JPanel();
		JPanel aToolbar = new JPanel();
		JPanel aPause = new JPanel();
		visszaW = new JButton("Men�");
		visszaL = new JButton("Men�");
		visszaW.setActionCommand("menubeVissza");
		visszaW.addActionListener(this);
		visszaL.setActionCommand("menubeVissza");
		visszaL.addActionListener(this);
		
		layout = new CardLayout();
		
		setLayout(layout);
		add(aCim,"cim");
		add(aToolbar,"toolbar");
		add(aPause,"pause");
		add(aGyozelem,"gyozelem");
		add(aVesztes,"vesztes");
		
		JLabel cim = new JLabel("Aknakeres�");
		aCim.add(cim);
		valto = new JButton("Felfed�s");
		valto.setActionCommand("valto");
		valto.addActionListener(this);
		JButton pauseButton = new JButton("Pause");
		pauseButton.setActionCommand("pause");
		pauseButton.addActionListener(this);
		egygombos = new JCheckBox("Egygombos m�d");
		egygombos.setSelected(true);
		egygombos.setActionCommand("egygombos");
		egygombos.addActionListener(this);
		aknaCount = new JLabel("10");
		aToolbar.add(pauseButton);
		aToolbar.add(egygombos);
		aToolbar.add(valto);
		aToolbar.add(aknaCount);
		JLabel nyertel = new JLabel("Gratul�lok, nyert�l!");
		aGyozelem.add(nyertel);
		aGyozelem.add(visszaW);
		JLabel vesztettel = new JLabel("Sajnos vesztett�l!");
		aVesztes.add(vesztettel);
		aVesztes.add(visszaL);
		JLabel pause = new JLabel("J�t�k meg�ll�tva...");
		aPause.add(pause);
	}
	public void changeMode(String mode)
	{
		if(mode == "egygombos")
		{
			valto.setEnabled(!valto.isEnabled());
		}
		else if(mode == "valto")
		{
			if(valto.getText().equals("Felfed�s"))
				valto.setText("Z�szl�");
			else valto.setText("Felfed�s");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getActionCommand().equals("valto"))
		{
			changeMode("valto");
			otherPanel.changeMode("valto");
		}
		else if(ae.getActionCommand().equals("egygombos"))
		{
			changeMode("egygombos");
			otherPanel.changeMode("egygombos");
		}
		else if(ae.getActionCommand().equals("pause"))
		{
			changeLayout("pause");
			otherPanel.aJatek.pauseGame();
			otherPanel.changeLayout("pause");
		}
		else if(ae.getActionCommand().equals("menubeVissza"))
		{
			layout.show(this, "cim");
			otherPanel.changeLayout("menu");
			ablak.changeFrameRes("menu");
		}
	}
	public void updateCounter(int count) 
	{
		aknaCount.setText(String.valueOf(count));
	}
	public boolean IsEgygombos() {
		return egygombos.isSelected();
	}
	public boolean IsZaszloMode(){
		return valto.getText().equals("Z�szl�");
	}
	public void loseGame()
	{
		changeLayout("vesztes");
	}
}

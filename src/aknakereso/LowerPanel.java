package aknakereso;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LowerPanel extends CustomPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1609856385223186778L;
	private final int KONNYU_X = 8;
	private final int KONNYU_Y = 8;
	private final int KONNYU_A = 10;
	private final int HALADO_X = 16;
	private final int HALADO_Y = 16;
	private final int HALADO_A = 40;
	private final int MESTER_Y = 30;
	private final int MESTER_X = 16;
	private final int MESTER_A = 99;
	private String nehezseg;
	
	private JPanel aMenu;
	private JPanel aNehezseg;
	private JPanel aRanglista;
	private JPanel aPause;
	JButton ujJatek;
	JButton folytatas;
	JButton ranglista;
	JButton kilepes;
	
	JButton konnyuB;
	JButton haladoB;
	JButton mesterB;
	JButton visszaN;
	
	private RanglistaData ranglistaData = new RanglistaData();
	
	public LowerPanel(CustomFrame frame)
	{
		ablak = frame;
		Dimension dimension = new Dimension(150, 50);
		ujJatek = new JButton("Új játék");
		folytatas = new JButton("Folytatás");
		ranglista = new JButton("Ranglista");
		kilepes = new JButton("Kilépés");
		
		konnyuB = new JButton("Könnyû");
		JLabel  konnyuL = new JLabel("8x8-as mezõ, 10 akna",JLabel.LEFT);
		haladoB = new JButton("Haladó");
		JLabel  haladoL = new JLabel("16x16-as mezõ, 40 akna",JLabel.LEFT);
		mesterB = new JButton("Mester");
		JLabel  mesterL = new JLabel("30x16-as mezõ, 99 akna",JLabel.LEFT);
		visszaN = new JButton("Vissza");
		
		JButton visszaR = new JButton("Vissza");
		
		JButton visszaP = new JButton("Vissza");
		JButton mentes = new JButton("Mentés");
		JButton betoltes = new JButton("Betöltés");
		JButton menu = new JButton("Vissza a menübe");
		
		loadRanglista();
		
		aMenu = new JPanel();
		aNehezseg = new JPanel();
		aRanglista = new JPanel();
		aPause = new JPanel();
		Tabla initialJatek = new Tabla(1,1,0,this,(UpperPanel) otherPanel);
		aJatek = initialJatek;
		layout = new CardLayout();
		
		setLayout(layout);
		add(aMenu,"menu");
		add(aNehezseg,"nehezseg");
		add(aRanglista,"ranglista");
		add(aPause,"pause");
		add(aJatek,"jatek");
		
		ujJatek.setActionCommand("ujJatek");
		ujJatek.addActionListener(this);
		ujJatek.setMaximumSize(dimension);
		folytatas.setActionCommand("betoltes");
		folytatas.addActionListener(this);
		folytatas.setMaximumSize(dimension);
		ranglista.setActionCommand("ranglista");
		ranglista.addActionListener(this);
		ranglista.setMaximumSize(dimension);
		kilepes.setActionCommand("kilepes");
		kilepes.addActionListener(this);
		kilepes.setMaximumSize(dimension);
		ujJatek.setAlignmentX(CENTER_ALIGNMENT);
		folytatas.setAlignmentX(CENTER_ALIGNMENT);
		ranglista.setAlignmentX(CENTER_ALIGNMENT);
		kilepes.setAlignmentX(CENTER_ALIGNMENT);
		aMenu.add(ujJatek);
		aMenu.add(folytatas);
		aMenu.add(ranglista);
		aMenu.add(kilepes);
		aMenu.setLayout(new BoxLayout(aMenu, BoxLayout.PAGE_AXIS));
		
		konnyuB.setActionCommand("startKonnyu");
		konnyuB.addActionListener(this);
		haladoB.setActionCommand("startHalado");
		haladoB.addActionListener(this);
		mesterB.setActionCommand("startMester");
		mesterB.addActionListener(this);
		visszaN.setActionCommand("menubeVissza");
		visszaN.addActionListener(this);
		aNehezseg.setLayout(new BorderLayout());
		JPanel aNehezsegGombok = new JPanel();
		aNehezsegGombok.setLayout(new BoxLayout(aNehezsegGombok,BoxLayout.PAGE_AXIS));
		JPanel aNehezsegLeirasok = new JPanel();
		aNehezsegLeirasok.setLayout(new BoxLayout(aNehezsegLeirasok,BoxLayout.PAGE_AXIS));
		konnyuB.setMaximumSize(dimension);
		konnyuB.setAlignmentX(CENTER_ALIGNMENT);
		konnyuL.setMaximumSize(dimension);
		konnyuL.setPreferredSize(dimension);
		konnyuL.setAlignmentX(CENTER_ALIGNMENT);
		haladoB.setMaximumSize(dimension);
		haladoB.setAlignmentX(CENTER_ALIGNMENT);
		haladoL.setMaximumSize(dimension);
		haladoL.setPreferredSize(dimension);
		haladoL.setAlignmentX(CENTER_ALIGNMENT);
		mesterB.setMaximumSize(dimension);
		mesterB.setAlignmentX(CENTER_ALIGNMENT);
		mesterL.setMaximumSize(dimension);
		mesterL.setPreferredSize(dimension);
		mesterL.setAlignmentX(CENTER_ALIGNMENT);
		visszaN.setPreferredSize(dimension);
		aNehezsegGombok.add(konnyuB);
		aNehezsegLeirasok.add(konnyuL);
		aNehezsegGombok.add(haladoB);
		aNehezsegLeirasok.add(haladoL);
		aNehezsegGombok.add(mesterB);
		aNehezsegLeirasok.add(mesterL);
		aNehezseg.add(aNehezsegGombok,BorderLayout.CENTER);
		aNehezseg.add(aNehezsegLeirasok,BorderLayout.EAST);
		aNehezseg.add(visszaN,BorderLayout.SOUTH);
		
		
		visszaR.setActionCommand("menubeVissza");
		visszaR.addActionListener(this);
		JTable rangtable = new JTable();
		rangtable.setModel(ranglistaData);
		rangtable.setAutoCreateRowSorter(true);
        JScrollPane scroll = new JScrollPane(rangtable);
        scroll.setPreferredSize(new Dimension(350,200));
        aRanglista.add(scroll);
		aRanglista.add(visszaR);
		visszaP.setActionCommand("jatekbaVissza");
		visszaP.addActionListener(this);
		mentes.setActionCommand("mentes");
		mentes.addActionListener(this);
		betoltes.setActionCommand("betoltes");
		betoltes.addActionListener(this);
		menu.setActionCommand("menubeVissza");
		menu.addActionListener(this);
		visszaP.setAlignmentX(CENTER_ALIGNMENT);
		visszaP.setMaximumSize(dimension);
		mentes.setAlignmentX(CENTER_ALIGNMENT);
		mentes.setMaximumSize(dimension);
		betoltes.setAlignmentX(CENTER_ALIGNMENT);
		betoltes.setMaximumSize(dimension);
		menu.setAlignmentX(CENTER_ALIGNMENT);
		menu.setMaximumSize(dimension);
		aPause.setLayout(new BoxLayout(aPause, BoxLayout.PAGE_AXIS));
		aPause.add(visszaP);
		aPause.add(mentes);
		aPause.add(betoltes);
		aPause.add(menu);
		
	}
	public void changeMode(String mode)
	{
		if(mode == "egygombos")
			aJatek.changeEgygombos();
		else if(mode == "valto")
			aJatek.changeZaszloMode();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getActionCommand().equals("ujJatek"))
		{
			layout.show(this, "nehezseg");
		}
		else if(ae.getActionCommand().equals("ranglista"))
		{
			layout.show(this, "ranglista");
		}
		else if(ae.getActionCommand().equals("kilepes"))
		{
			ablak.ExitGame();
		}
		else if(ae.getActionCommand().equals("startKonnyu"))
		{
			remove(aJatek);
			aJatek = new Tabla(KONNYU_X,KONNYU_Y,KONNYU_A,this,(UpperPanel) otherPanel);
			add(aJatek,"jatek");
			layout.show(this,"jatek");
			otherPanel.changeLayout("toolbar");
			ablak.changeFrameRes("konnyu");
		}
		else if(ae.getActionCommand().equals("startHalado"))
		{
			remove(aJatek);
			aJatek = new Tabla(HALADO_X,HALADO_Y,HALADO_A,this,(UpperPanel) otherPanel);
			add(aJatek,"jatek");
			layout.show(this,"jatek");
			otherPanel.changeLayout("toolbar");
			ablak.changeFrameRes("halado");
		}
		else if(ae.getActionCommand().equals("startMester"))
		{
			remove(aJatek);
			aJatek = new Tabla(MESTER_X,MESTER_Y,MESTER_A,this,(UpperPanel) otherPanel);
			add(aJatek,"jatek");
			layout.show(this,"jatek");
			otherPanel.changeLayout("toolbar");
			ablak.changeFrameRes("mester");
		}
		else if(ae.getActionCommand().equals("menubeVissza"))
		{
			aJatek.pauseGame();
			layout.show(this, "menu");
			otherPanel.changeLayout("cim");
			ablak.changeFrameRes("menu");
		}
		else if(ae.getActionCommand().equals("jatekbaVissza"))
		{
			layout.show(this, "jatek");
			aJatek.resumeGame();
			otherPanel.changeLayout("toolbar");
		}
		else if(ae.getActionCommand().equals("betoltes"))
		{
			remove(aJatek);
			load();
			ablak.changeFrameRes(getDim(aJatek.getSorCount()));
			add(aJatek,"jatek");
			layout.show(this, "jatek");
			aJatek.resumeGame();
			otherPanel.changeLayout("toolbar");
		}
		else if(ae.getActionCommand().equals("mentes"))
		{
			save();
		}
		
	}
	public void load()
	{
		try
		{
			FileInputStream fin = new FileInputStream("save.ser");
			ObjectInputStream oin = new ObjectInputStream(fin);
			aJatek = (Tabla) oin.readObject();
			oin.close();
			fin.close();
			if(aJatek.checkZaszloMode()) aJatek.changeZaszloMode();
			System.out.println("Sikeresen beolvastad a játékállást!");
		}
		catch(IOException e)
		{
			System.out.println("Hiba a fajl olvasasanal: " + e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void save()
	{
		try
		{
			FileOutputStream fout = new FileOutputStream("save.ser");
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(aJatek);
			oout.close();
			fout.close();
			System.out.println("Sikeresen mentetted a játékállást!");
		}
		catch(IOException e)
		{
			System.out.println("Hiba a fajlba irasnal: " + e);
		}
	}
	@SuppressWarnings("unchecked")
	public void loadRanglista()
	{
		try
		{
			FileInputStream fin = new FileInputStream("ranglista.dat");
			ObjectInputStream oin = new ObjectInputStream(fin);
			ranglistaData.ranglista = (List<Ranglista>) oin.readObject();
			oin.close();
			fin.close();
			System.out.println("Sikeresen beolvastad a ranglistát!");
		}
		catch(IOException e)
		{
			System.out.println("Hiba a fajl olvasasanal: " + e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void saveRanglista()
	{
		try
		{
			FileOutputStream fout = new FileOutputStream("ranglista.dat");
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(ranglistaData.ranglista);
			oout.close();
			fout.close();
			System.out.println("Sikeresen mentetted a ranglistát!");
		}
		catch(IOException e)
		{
			System.out.println("Hiba a fajlba irasnal: " + e);
		}
	}
	public void addRanglistaElem(long ido, String nehezseg) 
	{
		ranglistaData.addListaelem(ido, nehezseg);
		saveRanglista();
		
	}
	// játék megnyerése
	public void winGame(long ido)
	{
		otherPanel.changeLayout("gyozelem");
		addRanglistaElem(ido, getDim(aJatek.getSorCount()));
	}
	// a játék nehézségét adja meg a táblaméret alapján
	public String getDim(int x)
	{
		switch(x)
		{
		default:
		case KONNYU_X:
			nehezseg = "konnyu";
			return nehezseg;
		case HALADO_X:
			nehezseg = "halado";
			return nehezseg;
		case MESTER_Y:
			nehezseg = "mester";
			return nehezseg;
		}
	}
	
}

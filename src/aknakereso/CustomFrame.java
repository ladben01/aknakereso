package aknakereso;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class CustomFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -462643166529385883L;
	// A j�t�k ablaka
	/*
	 * A j�t�k m�retei:
	 * - MENU_X �s MENU_Y a men� m�retei
	 * - MEZO_SIZE egy mez� m�rete pixelekben
	 * - *NEH�ZS�G*_X, _Y  az adott neh�zs�g oszlopainak, sorainak sz�ma
	 * - TOOLBAR_Y a j�t�k sor�n l�that� eszk�zt�r m�rete
	 */
	private final int MENU_X = 450;
	private final int MENU_Y = 300;
	private final int TOOLBAR_Y = 50;
	private final int MEZO_SIZE = 50;
	private final int KONNYU_X = 8;
	private final int KONNYU_Y = 8;
	private final int HALADO_X = 16;
	private final int HALADO_Y = 16;
	private final int MESTER_X = 30;
	private final int MESTER_Y = 16;
	// Az ablak m�rete k�l�nb�z� esetekben
	private final Dimension MENU_DIM = new Dimension(MENU_X,MENU_Y);
	private final Dimension KONNYU_DIM = new Dimension(MEZO_SIZE * KONNYU_X,MEZO_SIZE * KONNYU_Y + TOOLBAR_Y);
	private final Dimension HALADO_DIM = new Dimension(MEZO_SIZE * HALADO_X,MEZO_SIZE * HALADO_Y + TOOLBAR_Y);
	private final Dimension MESTER_DIM = new Dimension(MEZO_SIZE * MESTER_X,MEZO_SIZE * MESTER_Y + TOOLBAR_Y);
	
	private LowerPanel lower;
	private UpperPanel upper;
	
	public CustomFrame()
	{
		// nem m�retezhet�, x-re kattintva kil�p
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		// a panelek l�trehoz�sa
		// lower = a men��rt �s a j�t�k�rt felel
		// upper = a toolbar �s kieg�sz�t� labelek
		upper = new UpperPanel(this);
		lower = new LowerPanel(this);
		
		upper.setPreferredSize(new Dimension(MENU_X,TOOLBAR_Y));
		lower.setOtherPanel(upper);
		upper.setOtherPanel(lower);
		add(upper, BorderLayout.NORTH);
		add(lower,BorderLayout.CENTER);
		
		// ablak default m�ret�nek be�ll�t�sa �s megjelen�t�se
		setPreferredSize(MENU_DIM);
		pack();
		
	}
	public static void main(String[] args)
	{
		// ablak l�trehoz�sa
		CustomFrame ablak = new CustomFrame();
		ablak.setVisible(true);
	}
	
	// kil�p�s a j�t�kb�l a "Kil�p�s" gombbal
	public void ExitGame()
	{
		dispose();
	}
	// az ablak m�ret�nek m�dos�t�sa
	public void changeFrameRes(String type)
	{
		switch(type)
		{
		default:
		case "menu":
			setPreferredSize(MENU_DIM);
			break;
		case "konnyu":
			setPreferredSize(KONNYU_DIM);
			break;
		case "halado":
			setPreferredSize(HALADO_DIM);
			break;
		case "mester":
			setPreferredSize(MESTER_DIM);
			break;
		}
		pack();
	}
	// az als� panel getterje
	public LowerPanel getLowerPanel()
	{
		return lower;
	}
	// a fels� panel getterje
	public UpperPanel getUpperPanel()
	{
		return upper;
	}
	
	
}

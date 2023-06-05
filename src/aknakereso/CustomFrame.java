package aknakereso;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class CustomFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -462643166529385883L;
	// A játék ablaka
	/*
	 * A játék méretei:
	 * - MENU_X és MENU_Y a menü méretei
	 * - MEZO_SIZE egy mezõ mérete pixelekben
	 * - *NEHÉZSÉG*_X, _Y  az adott nehézség oszlopainak, sorainak száma
	 * - TOOLBAR_Y a játék során látható eszköztár mérete
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
	// Az ablak mérete kölünbözõ esetekben
	private final Dimension MENU_DIM = new Dimension(MENU_X,MENU_Y);
	private final Dimension KONNYU_DIM = new Dimension(MEZO_SIZE * KONNYU_X,MEZO_SIZE * KONNYU_Y + TOOLBAR_Y);
	private final Dimension HALADO_DIM = new Dimension(MEZO_SIZE * HALADO_X,MEZO_SIZE * HALADO_Y + TOOLBAR_Y);
	private final Dimension MESTER_DIM = new Dimension(MEZO_SIZE * MESTER_X,MEZO_SIZE * MESTER_Y + TOOLBAR_Y);
	
	private LowerPanel lower;
	private UpperPanel upper;
	
	public CustomFrame()
	{
		// nem méretezhetõ, x-re kattintva kilép
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		// a panelek létrehozása
		// lower = a menüért és a játékért felel
		// upper = a toolbar és kiegészítõ labelek
		upper = new UpperPanel(this);
		lower = new LowerPanel(this);
		
		upper.setPreferredSize(new Dimension(MENU_X,TOOLBAR_Y));
		lower.setOtherPanel(upper);
		upper.setOtherPanel(lower);
		add(upper, BorderLayout.NORTH);
		add(lower,BorderLayout.CENTER);
		
		// ablak default méretének beállítása és megjelenítése
		setPreferredSize(MENU_DIM);
		pack();
		
	}
	public static void main(String[] args)
	{
		// ablak létrehozása
		CustomFrame ablak = new CustomFrame();
		ablak.setVisible(true);
	}
	
	// kilépés a játékból a "Kilépés" gombbal
	public void ExitGame()
	{
		dispose();
	}
	// az ablak méretének módosítása
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
	// az alsó panel getterje
	public LowerPanel getLowerPanel()
	{
		return lower;
	}
	// a felsõ panel getterje
	public UpperPanel getUpperPanel()
	{
		return upper;
	}
	
	
}

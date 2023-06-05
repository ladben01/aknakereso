package aknakereso;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Tabla extends JPanel implements ActionListener, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2263895368958605858L;
	private char AKNA = '@';
	private ArrayList<Integer> aknak = new ArrayList<Integer>();
	private int sorCount;
	private int oszlopCount;
	private int aknaCount;
	private int zaszloCount;
	private boolean egygombos = true;
	private boolean zaszloMode = false;
	private boolean elsoLepes = true;
	private UpperPanel upper;
	private LowerPanel lower;
	
	GridLayout layout;
	long elteltIdo;
	long startIdo;
	ArrayList<Mezo> mezoList = new ArrayList<Mezo>();
	
	public Tabla(int x, int y, int aCount, LowerPanel l, UpperPanel u)
	{
		oszlopCount = x;
		sorCount = y;
		lower = l;
		upper = u;
		layout = new GridLayout(x,y);
		this.setLayout(layout);
		
		elteltIdo = 0;
		startIdo = System.currentTimeMillis();
		
		aknaCount = aCount;
		zaszloCount = 0;
		
		
		Mezo m;
		for(int i = 0; i < x; i++)
		{
			for(int j = 0; j < y; j++)
			{
				char content;
				content = ' ';
				m = new Mezo(content,i,j);
				
				m.addActionListener(this);
				m.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mousePressed(MouseEvent e)
					{
						if (SwingUtilities.isRightMouseButton(e))
						{
							Mezo myMezo = (Mezo)e.getSource();  
							if(!egygombos)
				        	{
				    			
					    		if(!myMezo.felfedveVan()) 
					    		{
					    			if(zaszloCount != aknaCount)
					        		{
					    				myMezo.setZaszlo();
						    			if(myMezo.getZaszlo()) zaszloCount++;
						    			else zaszloCount--;
					    			}
					    			else if(myMezo.getZaszlo())
					    			{
					    				myMezo.setZaszlo();
					    				zaszloCount--;
					    			}
					    			upper.updateCounter(aknaCount - zaszloCount);
				    			}
				        	}
			            }
			        }
			    });
				
				mezoList.add(m);
				this.add(m);
			}
		}
	}
	
	public void setAknaList(Mezo elsoMezo)
	{
		while(aknak.size() != aknaCount)
		{
			Random rand = new Random();
			int ujAkna = rand.nextInt(oszlopCount*sorCount);
			if(!aknak.contains(ujAkna) &&  mezoList.get(ujAkna) != elsoMezo)
			{
				if(!szomszedMezok(mezoList.get(ujAkna),elsoMezo,false))
				{
					aknak.add(ujAkna);
					mezoList.get(ujAkna).setContent(AKNA);
				}
			}
		}
	}
	public void setSzomszedAknaCount(Mezo myMezo)
	{
		int counter = 0;
		for(Mezo curr : mezoList)
		{
			if(szomszedMezok(curr,myMezo,true))
				counter++;
		}
		if(counter != 0) myMezo.setContent((char)(counter+48));
		
	}
	public boolean szomszedMezok(Mezo szomszedMezo, Mezo myMezo, boolean aknaKereses)
	{
		if(szomszedMezo.getPosX() == myMezo.getPosX())
		{	
			if(szomszedMezo.getPosY() == myMezo.getPosY()-1 ||  
			   szomszedMezo.getPosY() == myMezo.getPosY()+1)
			{
				if(!aknaKereses)
					return true;
				else if(szomszedMezo.getContent() == AKNA)
					return true;
			}
		}
		if(szomszedMezo.getPosX() == myMezo.getPosX()-1)
		{
			if(szomszedMezo.getPosY() == myMezo.getPosY()-1 || 
			   szomszedMezo.getPosY() == myMezo.getPosY() || 
			   szomszedMezo.getPosY() == myMezo.getPosY()+1)
			{
				if(!aknaKereses)
					return true;
				else if(szomszedMezo.getContent() == AKNA)
					return true;
			}
		}
		if(szomszedMezo.getPosX() == myMezo.getPosX()+1)
		{
			if(szomszedMezo.getPosY() == myMezo.getPosY()-1 || 
			   szomszedMezo.getPosY() == myMezo.getPosY() || 
			   szomszedMezo.getPosY() == myMezo.getPosY()+1)
			{
				if(!aknaKereses)
					return true;
				else if(szomszedMezo.getContent() == AKNA)
					return true;
			}
		}
		return false;
	}
	public void szomszedFelfed(Mezo myMezo)
	{
        for(Mezo curr : mezoList)
    	{
    		if(szomszedMezok(curr,myMezo,false))
    		{	
    			if(!curr.felfedveVan() && !curr.getZaszlo()) 
    			{
    				curr.felfed();
    				if(curr.getContent() == ' ') szomszedFelfed(curr);
    				else if(curr.getContent() == AKNA)
		    		{
		    			for(Mezo m:mezoList)
		    				m.felfed();
		    			upper.loseGame();
		    		}
    			}
    		}
        }
	}
	public void actionPerformed(ActionEvent ae)
    {
    	zaszloMode = upper.IsZaszloMode();
    	egygombos = upper.IsEgygombos();
    	
		Mezo myMezo = (Mezo)ae.getSource();  
    	if(elsoLepes)
    	{
    		if(zaszloMode && egygombos)
    		{
    			if(zaszloCount != aknaCount)
    			{
    				myMezo.setZaszlo();
	    			if(myMezo.getZaszlo()) zaszloCount++;
	    			else zaszloCount--;
    			}
    			else if(myMezo.getZaszlo())
    			{
    				myMezo.setZaszlo();
    				zaszloCount--;
    			}
    		}
    		else
    		{
    			setAknaList(myMezo);
    			elsoLepes = false;
    			for(Mezo curr : mezoList)
    			{
    				if(curr.getContent() != AKNA) setSzomszedAknaCount(curr);
    			}
    			myMezo.felfed();
    			szomszedFelfed(myMezo);
    		}
    			
    	}
    	else
    	{
    		if(zaszloMode && egygombos )
        	{
    			
	    		if(!myMezo.felfedveVan()) 
	    		{
	    			if(zaszloCount != aknaCount)
	        		{
	    				myMezo.setZaszlo();
		    			if(myMezo.getZaszlo()) zaszloCount++;
		    			else zaszloCount--;
	    			}
	    			else if(myMezo.getZaszlo())
	    			{
	    				myMezo.setZaszlo();
	    				zaszloCount--;
	    			}
    			}
        	}
        	else
        	{
        		if(!myMezo.getZaszlo())
        		{
        			if(!myMezo.felfedveVan()) 
    				{
    		    		myMezo.felfed();
    		    		if(myMezo.getContent() == ' ') 
    		    			szomszedFelfed(myMezo);
    		    		else if(myMezo.getContent() == AKNA)
    		    		{
    		    			for(Mezo curr:mezoList)
    		    				curr.felfed();
    		    			upper.loseGame();
    		    		}
    				}
    		    	else szomszedFelfed(myMezo);
        		}
        	}
    	}
    	if(ae.getSource().getClass() == aknakereso.Mezo.class) upper.updateCounter(aknaCount - zaszloCount);
    	if(aknaCount - zaszloCount == 0)
    	{	
    		boolean winChance = true;
    		for(Mezo mezo1 : mezoList)
    		{
    			for(Mezo mezo2 : mezoList)
    			{
    				if(szomszedMezok(mezo1,mezo2,true))
    				{
    					if(!mezo1.getZaszlo()) winChance = false; 
    				}
    			}
    		}
    		if(winChance)
    		{
    			pauseGame();
    			lower.winGame(elteltIdo);
    		}
    	}
    	
    }
	public void pauseGame()
	{
		elteltIdo += System.currentTimeMillis() - startIdo;
	}
	public void resumeGame()
	{
		startIdo = System.currentTimeMillis();
	}

	public int getZaszloCount() 
	{
		return zaszloCount;
	}
	
	public int getOszlopCount() 
	{
		return oszlopCount;
	}
	
	public int getSorCount() 
	{
		return sorCount;
	}

	public void changeEgygombos() 
	{
		egygombos = !egygombos;
	}

	public void changeZaszloMode() 
	{
		zaszloMode = ! zaszloMode;
	}
	public boolean checkZaszloMode() 
	{
		return zaszloMode;
	}
	
}

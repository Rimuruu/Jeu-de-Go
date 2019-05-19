import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Panel extends JPanel{
	public Image goban;

	public Image pionn;
	public Image pionnt;
	public Image pionbt;
	public Image pionb;
	public int[][] plateau;
	public Case[][] plat;
	public boolean init;
	public int j;
	public Fenetre fenetre;
	public int lastx;
	public int lasty;
	Panel(Fenetre fenetre){
		super();
		this.setSize(1100,900);
		this.setLocation(0,0);
		addMouseListener(fenetre);
		addMouseMotionListener(fenetre);

		goban = Toolkit.getDefaultToolkit().getImage("goban"+fenetre.param.size+".png");
		pionn = Toolkit.getDefaultToolkit().getImage("pionnoir"+fenetre.param.size+".png");
		pionnt = Toolkit.getDefaultToolkit().getImage("pionnoir"+fenetre.param.size+"t.png");
		pionbt = Toolkit.getDefaultToolkit().getImage("pionblanc"+fenetre.param.size+"t.png");
		pionb = Toolkit.getDefaultToolkit().getImage("pionblanc"+fenetre.param.size+".png");
		plateau = new int[fenetre.param.size][fenetre.param.size];
		plat = new Case[fenetre.param.size][fenetre.param.size];
		Panel.setCase(plat,fenetre.param.size);
		init = false;
		j=1;
		lasty = 0;
		lastx = 0;
		this.fenetre = fenetre;

		
	

	}

	public static void setCase(Case[][] plat,int size){
		if (size == 9) {
			for (int x = 0,pox = 69-46 ;x<9;x=x+1,pox=pox+95) {
				for (int y = 0,poy = 69-46 ;y<9;y=y+1,poy=poy+95) {
					plat[x][y] = new Case(pox,poy,x,y);
					
				}
			}

			
		}
		else if (size == 19) {
			for (int x = 0,pox = 45-22 ;x<19;x=x+1,pox=pox+45) {
				for (int y = 0,poy = 45-22 ;y<19;y=y+1,poy=poy+45) {
					plat[x][y] = new Case(pox,poy,x,y);
					
				}
			}

			
		}
		else if (size == 13) {
			for (int x = 0,pox = 68-32 ;x<13;x=x+1,pox=pox+64) {
				for (int y = 0,poy = 68-32 ;y<13;y=y+1,poy=poy+64) {
					plat[x][y] = new Case(pox,poy,x,y);
					
				}
			}

			
		}

	}

	public void posePion(Graphics g){
		for (int x = 0 ;x<fenetre.param.size;x=x+1) {
			for (int y = 0 ;y<fenetre.param.size;y=y+1) {
				if (plat[x][y].contenue == 1) {
					g.drawImage(pionn,plat[x][y].x,plat[x][y].y,this);				
				}
				else if (plat[x][y].contenue == 2) {
					g.drawImage(pionb,plat[x][y].x,plat[x][y].y,this);				
				}
				else if (plat[x][y].contenue == 3) {
					g.drawImage(pionnt,plat[x][y].x,plat[x][y].y,this);				
				}
				else if (plat[x][y].contenue == 4) {
					g.drawImage(pionbt,plat[x][y].x,plat[x][y].y,this);				
				}
			}
		}
	}



	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(goban,0,0,this);
		posePion(g);
		
	}

	public void placerPion(MouseEvent e){
		int y = e.getY();
		int x = e.getX();
		int button = e.getButton();
		System.out.println("x "+x+" y "+y);
		Case casepick = Case.searchCase(plat,x,y,fenetre.param.size);
		if (casepick != null) {
			
		
			if (x>40 && y > 40 && x<900 && y < 900) {
				if (button == MouseEvent.BUTTON1 && this.j == 1 && casepick.contenue!=2  && casepick.contenue!=1) {
					casepick.contenue=1;
					Case.setLiberter(this.plat,casepick,fenetre.param.size);
					System.out.println("Liberter : "+casepick.sizeLiberter(this.plat,new LinkedList<Case>(),fenetre.param.size));
					this.j=2;
				
				}
				else if (button == MouseEvent.BUTTON1 && this.j == 2 && casepick.contenue!=1  && casepick.contenue!=2) {
					casepick.contenue=2;
					Case.setLiberter(this.plat,casepick,fenetre.param.size);
					System.out.println("Liberter : "+casepick.sizeLiberter(this.plat,new LinkedList<Case>(),fenetre.param.size));
					this.j=1;
				
				}
			Case.checkLiberter(this.plat,fenetre.param.size,casepick);
		}
}
		
		
		this.repaint(); 
	}

	public void mouseOver(MouseEvent e){
		int y = e.getY();
		int x = e.getX();
		Case lastcase = Case.searchCase(plat,lastx,lasty,fenetre.param.size);
		Case casepick = Case.searchCase(plat,x,y,fenetre.param.size);
		if (casepick != null) {
			
		
		if (x>40 && y > 40 && x<900 && y < 900) {
			if (this.j == 1 && casepick.contenue!=2  && casepick.contenue!=1) {
				if (lastx != 0 && lasty != 0) {
					
						
						
						if (lastcase.contenue!=1  && lastcase.contenue!=2) {
							lastcase.contenue=0;
						
						}
							
						
						casepick.contenue=3;
						//System.out.println("Lastx "+lastx+" Lasty "+lasty);
						//System.out.println("x "+x+" y "+y);
						
					
					
				}
				
				lastx = x;
				lasty = y;

		
			}
			else if (this.j == 2 && casepick.contenue!=1  && casepick.contenue!=2) {
				if (lastx != 0 && lasty != 0) {
				
						if (lastcase.contenue!=1  && lastcase.contenue!=2) {
							lastcase.contenue=0;
										}
						
						casepick.contenue=4;
						
					
				}
			
				lastx = x;
				lasty = y;
		
			}
			
		}
		this.repaint(); 
	}

	}



}
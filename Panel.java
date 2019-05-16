import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Panel extends JPanel implements MouseListener,MouseMotionListener{
	private Image goban;

	private Image pionn;
	private Image pionb;
	private int[][] plateau;
	public boolean init;
	public int j;
	public Fenetre fenetre;
	Panel(Fenetre fenetre){
		super();
		this.setSize(1100,900);
		this.setLocation(0,0);
		addMouseListener(this);

		goban = Toolkit.getDefaultToolkit().getImage("goban9.png");
		pionn = Toolkit.getDefaultToolkit().getImage("pionnoir9.png");
		pionb = Toolkit.getDefaultToolkit().getImage("pionblanc9.png");
		plateau = new int[9][9];
		init = false;
		j=1;
		this.fenetre = fenetre;

		
	

	}

	public void posePion(Graphics g){
		for (int x = 0,pox = 69-46 ;x<9;x=x+1,pox=pox+95) {
			for (int y = 0,poy = 69-46 ;y<9;y=y+1,poy=poy+95) {
				if (plateau[x][y] == 1) {
					g.drawImage(pionn,pox,poy,this);				
				}
				else if (plateau[x][y] == 2) {
					g.drawImage(pionb,pox,poy,this);				
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

	@Override
	public void mouseExited(MouseEvent e){}
	@Override
	public void mouseEntered(MouseEvent e){}
	@Override
	public void mouseReleased(MouseEvent e){}
	@Override
	public void mouseClicked(MouseEvent e){
		

	}
	@Override
	public void mousePressed(MouseEvent e){
		int y = e.getY();
		int x = e.getX();
		int button = e.getButton();
		if (button == MouseEvent.BUTTON1 && this.j == 1 && plateau[x/100][y/100]!=2) {
			plateau[x/100][y/100]=1;
			j=2;
		}
		else if (button == MouseEvent.BUTTON1 && this.j == 2 && plateau[x/100][y/100]!=1) {
			plateau[x/100][y/100]=2;
			j=1;
		}
		System.out.println("x : "+x+" y : "+y);
		
		repaint(); 
	}
	@Override
	public void mouseDragged(MouseEvent e){}
	@Override
	public void mouseMoved(MouseEvent e){}

}
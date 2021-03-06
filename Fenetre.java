import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Fenetre extends JFrame implements MouseListener,MouseMotionListener,ActionListener{
	public Menu menu;
	public Panel panel;
	public Parametre param;
	public JPanel container;
	public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public int width = (int) screenSize.getWidth();
	public int height = (int) screenSize.getHeight();
	Fenetre(){
		this.setSize(1100,928);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocation(width/4,height/16);
		this.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
		param = new Parametre(this);
		menu = new Menu(this);
		this.setContentPane(menu);
		

	}

	@Override
	public void mouseExited(MouseEvent e){
	
	}
	@Override
	public void mouseEntered(MouseEvent e){
		
	}
	@Override
	public void mouseReleased(MouseEvent e){}
	@Override
	public void mouseClicked(MouseEvent e){
		

	}
	@Override
	public void mousePressed(MouseEvent e){
		if (this.getContentPane() instanceof Partie) {
			if (panel.statut == 0) {
				panel.placerPion(e);
			}
			else if (panel.statut == 1) {
				panel.selectionGroupeMort(e);
			}
			
		}
		
	}
	@Override
	public void mouseDragged(MouseEvent e){
		
	}
	@Override
	public void mouseMoved(MouseEvent e){
		if (this.getContentPane() instanceof Partie) {
			if (panel.statut == 0) {
			panel.mouseOver(e);}
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (this.getContentPane() instanceof Menu) {
			this.menu.jouer(e);
		}
		else if (this.getContentPane() instanceof Parametre) {
			this.param.select(e);
			
		}
		if (this.getContentPane() instanceof Partie) {
			this.panel.score.buttonAction(e);
		}
		
		
	}


}
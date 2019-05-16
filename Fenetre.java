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
	public Container container;
	Fenetre(){
		this.setSize(1100,928);
	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLocation(300,200);
		this.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
		addMouseListener(this);
		addMouseMotionListener(this);
		param = new Parametre(this);
		menu = new Menu(this);
		this.setContentPane(menu);
		/*this.panel = new Panel(this);
		this.add(panel);*/

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
		if (this.getContentPane() instanceof Panel) {
			panel.placerPion(e);
		}
		
	}
	@Override
	public void mouseDragged(MouseEvent e){
		/*if (this.getContentPane() instanceof Panel) {
			panel.mouseOver(e);
		}*/
	}
	@Override
	public void mouseMoved(MouseEvent e){
		if (this.getContentPane() instanceof Panel) {
			panel.mouseOver(e);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (this.getContentPane() instanceof Menu) {
			this.menu.jouer(e);
		}
		else if (this.getContentPane() instanceof Parametre) {
			this.param.select(e);
			
		}
		
		
	}


}
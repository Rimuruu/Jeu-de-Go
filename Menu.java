import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;



public class Menu extends JPanel implements MouseListener,MouseMotionListener,ActionListener{
	public JButton play;
	public JButton parametre;
	public Fenetre fenetre;

	Menu(Fenetre fenetre){
		super();
		this.setSize(1100,900);
		this.setLocation(0,0);
		addMouseListener(this);
		play = new JButton("Jouer");
		parametre = new JButton("Parametre");
		play.addActionListener(this);
		parametre.addActionListener(this);
		this.add(play,BorderLayout.CENTER);
		this.add(parametre,BorderLayout.CENTER);
		this.fenetre = fenetre;


 

	}

		@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	
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
		
	}
	@Override
	public void mouseDragged(MouseEvent e){}
	@Override
	public void mouseMoved(MouseEvent e){}
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == play){
			System.out.println("Vous avez cliqué ici.");
		} else if(source == parametre){
			System.out.println("Vous avez cliqué là.");	
		}
	}



}
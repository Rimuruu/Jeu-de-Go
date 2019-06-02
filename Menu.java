import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;



public class Menu extends JPanel{
	public JButton play;
	public JButton parametre;
	public Fenetre fenetre;
	Menu(Fenetre fenetre){
		super();
		this.setSize(1100,900);
		this.setLocation(0,0);
		addMouseListener(fenetre);
		play = new JButton("Jouer");
		parametre = new JButton("Parametre");
		play.addActionListener(fenetre);
		parametre.addActionListener(fenetre);
		play.setAlignmentX(Component.CENTER_ALIGNMENT);
		parametre.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(Box.createRigidArea(new Dimension(250,250)));
		this.add(play);
		this.add(Box.createRigidArea(new Dimension(50,50)));
		this.add(parametre);
		this.fenetre = fenetre;
		



 

	}

		@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	
	}

	public void jouer(ActionEvent e){
		Object source = e.getSource();
		if(source == fenetre.menu.play){
			fenetre.panel = new Panel(fenetre);
			fenetre.container = new Partie(fenetre.panel);
			fenetre.container.setLayout(null);
			

			fenetre.container.add(fenetre.panel.score);
			fenetre.container.add(fenetre.panel);
			

			fenetre.repaint();
			fenetre.revalidate();

			fenetre.setContentPane(fenetre.container);
			fenetre.repaint();
			fenetre.revalidate();
			
			
		} else if(source == fenetre.menu.parametre){
			fenetre.repaint();
			fenetre.revalidate();

			fenetre.setContentPane(fenetre.param);
			fenetre.repaint();
			fenetre.revalidate();
			
		}

	}



}
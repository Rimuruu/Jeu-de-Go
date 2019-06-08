import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;



public class Parametre extends JPanel{
	public JButton goban9;
	public JButton goban13;
	public JButton goban19;
	public JButton handi;
	public JLabel goban = new JLabel("Selectionner le goban :  ");
	public JLabel texth = new JLabel("Handicape 7.5 points pour le Joueur 2 :  ");
	public JLabel textp = new JLabel("Pierres de handicape : ");
	public Fenetre fenetre;
	public int size;
	public float handicap;
	public int pionh = 0;
	public JLabel piont = new JLabel("  0  ");
	public JButton arrowl =new JButton("<<");
	public JButton arrowr =new JButton(">>");

	Parametre(Fenetre fenetre){
		super();
		this.setSize(1100,900);
		this.setLocation(0,0);
		addMouseListener(fenetre);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel container1 = new JPanel();
		container1.setLayout(new BoxLayout(container1, BoxLayout.X_AXIS));
		JPanel container2 = new JPanel();
		container2.setLayout(new BoxLayout(container2, BoxLayout.X_AXIS));
		JPanel container3 = new JPanel();
		container3.setLayout(new BoxLayout(container3, BoxLayout.X_AXIS));

		goban9=new JButton("Goban 9x9");
		goban13=new JButton("Goban 13x13");
		goban19=new JButton("Goban 19x19");
		handi=new JButton("Handicape");

		goban9.setAlignmentX(Component.CENTER_ALIGNMENT);
		goban13.setAlignmentX(Component.CENTER_ALIGNMENT);
		goban19.setAlignmentX(Component.CENTER_ALIGNMENT);
		handi.setAlignmentX(Component.CENTER_ALIGNMENT);

		goban9.addActionListener(fenetre);
		goban13.addActionListener(fenetre);
		goban19.addActionListener(fenetre);
		handi.addActionListener(fenetre);
		arrowl.addActionListener(fenetre);
		arrowr.addActionListener(fenetre);

		container1.add(goban);
		container1.add(goban9);
		container1.add(goban13);
		container1.add(goban19);

		container2.add(texth);
		container2.add(handi);

		container3.add(textp);
		container3.add(arrowl);
		container3.add(piont);
		container3.add(arrowr);

		this.add(container1);
		this.add(container2);
		this.add(container3);
		this.fenetre = fenetre;
		this.size = 9;
		this.handicap = 0f;


 

	}

		@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	
	}

	public void select(ActionEvent e){
		Object source = e.getSource();
		if(source == this.goban9){
			this.size = 9;
			fenetre.repaint();
			fenetre.revalidate();

			fenetre.setContentPane(fenetre.menu);
			fenetre.repaint();
			fenetre.revalidate();
			
	

	}
		else if(source == this.goban13){
			this.size = 13;
			fenetre.repaint();
			fenetre.revalidate();

			fenetre.setContentPane(fenetre.menu);
			fenetre.repaint();
			fenetre.revalidate();
			
	

	}
		else if(source == this.goban19){
			this.size = 19;
			fenetre.repaint();
			fenetre.revalidate();

			fenetre.setContentPane(fenetre.menu);
			fenetre.repaint();
			fenetre.revalidate();
			
	

	}
		else if (source == this.handi) {
			if (this.handicap == 0f) {
				this.handicap = 7.5f;
				this.handi.setBorderPainted(false);
				this.handi.setFocusPainted(false);
				this.handi.setContentAreaFilled(false);

			}
			else{
				this.handicap = 0f;
				this.handi.setBorderPainted(true);
				this.handi.setFocusPainted(true);
				this.handi.setContentAreaFilled(true);
			}
			
		}
		else if (source == this.arrowl) {
			if (pionh != 0) {
				pionh = pionh -1;
				piont.setText("  "+pionh+"  ");
			}
	}
		else if (source == this.arrowr) {
			if (pionh < 9 ) {
				pionh = pionh +1;
				piont.setText("  "+pionh+"  ");
			}

	}
}
}
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
	public JButton pashorloge;
	public JButton absolue;
	public JButton byoyomi;
	public JLabel goban = new JLabel("Selectionner le goban :  ");
	public JLabel texth = new JLabel("Handicape 7.5 points pour le Joueur 2 :  ");
	public JLabel textp = new JLabel("Pierres de handicape : ");
	public JLabel textt = new JLabel("Horloge : ");
	public Fenetre fenetre;
	public int size;
	public float handicap;
	public int pionh = 0;
	public JLabel piont = new JLabel("  0  ");
	public JButton arrowl =new JButton("<<");
	public JButton arrowr =new JButton(">>");
	public int horloge = 0;
	public JButton valider =new JButton("Valider");



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
		JPanel container4 = new JPanel();
		container4.setLayout(new BoxLayout(container4, BoxLayout.X_AXIS));

		goban9=new JButton("Goban 9x9");
		goban13=new JButton("Goban 13x13");
		goban19=new JButton("Goban 19x19");
		handi=new JButton("Handicape");
		pashorloge=new JButton("Aucune horloge");
		absolue=new JButton("Horloge Absolue");
		byoyomi=new JButton("Byo-Yomi");

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
		absolue.addActionListener(fenetre);
		pashorloge.addActionListener(fenetre);
		byoyomi.addActionListener(fenetre);
		valider.addActionListener(fenetre);

		this.pashorloge.setBorderPainted(false);
		this.pashorloge.setFocusPainted(false);
		this.pashorloge.setContentAreaFilled(false);
		this.goban9.setBorderPainted(false);
		this.goban9.setFocusPainted(false);
		this.goban9.setContentAreaFilled(false);

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

		container4.add(textt);
		container4.add(pashorloge);
		container4.add(absolue);
		container4.add(byoyomi);


		this.add(container1);
		this.add(container2);
		this.add(container3);
		this.add(container4);
		this.add(valider);
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
			this.goban9.setBorderPainted(false);
			this.goban9.setFocusPainted(false);
			this.goban9.setContentAreaFilled(false);
			this.goban13.setBorderPainted(true);
			this.goban13.setFocusPainted(true);
			this.goban13.setContentAreaFilled(true);
			this.goban19.setBorderPainted(true);
			this.goban19.setFocusPainted(true);
			this.goban19.setContentAreaFilled(true);
			
	

	}
		else if(source == this.goban13){
			this.size = 13;
			this.goban13.setBorderPainted(false);
			this.goban13.setFocusPainted(false);
			this.goban13.setContentAreaFilled(false);
			this.goban19.setBorderPainted(true);
			this.goban19.setFocusPainted(true);
			this.goban19.setContentAreaFilled(true);
			this.goban9.setBorderPainted(true);
			this.goban9.setFocusPainted(true);
			this.goban9.setContentAreaFilled(true);
			
	

	}
		else if(source == this.goban19){
			this.size = 19;
			this.goban19.setBorderPainted(false);
			this.goban19.setFocusPainted(false);
			this.goban19.setContentAreaFilled(false);
			this.goban13.setBorderPainted(true);
			this.goban13.setFocusPainted(true);
			this.goban13.setContentAreaFilled(true);
			this.goban9.setBorderPainted(true);
			this.goban9.setFocusPainted(true);
			this.goban9.setContentAreaFilled(true);
			
	

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
		else if (source == this.pashorloge) {
			this.horloge =0;
			this.pashorloge.setBorderPainted(false);
			this.pashorloge.setFocusPainted(false);
			this.pashorloge.setContentAreaFilled(false);
			this.byoyomi.setBorderPainted(true);
			this.byoyomi.setFocusPainted(true);
			this.byoyomi.setContentAreaFilled(true);
			this.absolue.setBorderPainted(true);
			this.absolue.setFocusPainted(true);
			this.absolue.setContentAreaFilled(true);

		}
		else if (source == this.absolue) {
			this.horloge =1;
			this.absolue.setBorderPainted(false);
			this.absolue.setFocusPainted(false);
			this.absolue.setContentAreaFilled(false);
			this.byoyomi.setBorderPainted(true);
			this.byoyomi.setFocusPainted(true);
			this.byoyomi.setContentAreaFilled(true);
			this.pashorloge.setBorderPainted(true);
			this.pashorloge.setFocusPainted(true);
			this.pashorloge.setContentAreaFilled(true);

		}
		else if (source == this.byoyomi) {
			this.horloge =2;
			this.byoyomi.setBorderPainted(false);
			this.byoyomi.setFocusPainted(false);
			this.byoyomi.setContentAreaFilled(false);
			this.absolue.setBorderPainted(true);
			this.absolue.setFocusPainted(true);
			this.absolue.setContentAreaFilled(true);
			this.pashorloge.setBorderPainted(true);
			this.pashorloge.setFocusPainted(true);
			this.pashorloge.setContentAreaFilled(true);
		}
		else if (source == this.valider) {
			fenetre.repaint();
			fenetre.revalidate();

			fenetre.setContentPane(fenetre.menu);
			fenetre.repaint();
			fenetre.revalidate();
		}
	}
}
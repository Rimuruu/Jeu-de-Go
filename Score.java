import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.event.*;

public class Score extends JPanel implements ListSelectionListener{
	public Fenetre fenetre;

	public JList<Plateau> list;
	public DefaultListModel<Plateau> listModel;
	public JScrollPane scroll;
	public float scoren;
	public float scoreb;
	public JLabel scoreblanc;
	public JLabel scorenoir;
	public JButton passe;
	public JButton quitter;
	public JButton validergroup;
	public JLabel temps;
	Score(Fenetre fenetre){
		super();
		this.setSize(300,900);
		this.setLocation(900,0);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//addMouseListener(fenetre);
		//addMouseMotionListener(fenetre);
		//setBackground(Color.BLACK);
		this.fenetre = fenetre;
	
		this.listModel = new DefaultListModel<Plateau>();
		this.list = new JList<Plateau>(this.listModel);
		InputMap inputMap = this.list.getInputMap();
		InputMap parentInputMap = inputMap.getParent();
		parentInputMap.clear();
		list.setLayoutOrientation(JList.VERTICAL);
		//list.setVisibleRowCount(10);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if (fenetre.param.pionh != 0) {
			this.scoreb = 0f;
		}
		else{
			this.scoreb = fenetre.param.handicap;
		}
		
		
		this.scoren = 0f;

		scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(300,400));
		scroll.setMaximumSize(new Dimension(300,400));
		scoreblanc = new JLabel("Prisonnier Pion Noir : "+this.scoreb);
		scorenoir = new JLabel("Prisonnier Pion Blanc : "+0f);
		passe=new JButton("Passer son tour");
		quitter=new JButton("Quitter");
		temps = new JLabel("Temps : 60");
		validergroup=new JButton("Groupes morts");
		validergroup.addActionListener(fenetre);
		passe.addActionListener(fenetre);
		quitter.addActionListener(fenetre);
		if (fenetre.param.horloge == 1) {
			this.add(Box.createRigidArea(new Dimension(10,10)));
			this.add(temps);
		}
		this.add(Box.createRigidArea(new Dimension(10,10)));
		this.add(passe);
		this.add(Box.createRigidArea(new Dimension(10,10)));
		this.add(scoreblanc);
		this.add(scorenoir);
		this.add(Box.createRigidArea(new Dimension(10,10)));
		this.add(scroll);
		this.add(Box.createRigidArea(new Dimension(10,10)));
		list.addListSelectionListener(this);
	

	}

	public void buttonAction(ActionEvent e){
		Object source = e.getSource();
		if(source == this.passe && fenetre.panel.statut == 0){
			fenetre.panel.passerTour();
		}
		else if (source == this.quitter) {

			fenetre.repaint();
			fenetre.revalidate();

			fenetre.setContentPane(fenetre.menu);
			fenetre.repaint();
			fenetre.revalidate();
			
		}
		else if (source == this.validergroup) {
			fenetre.panel.supprimerGroupMort();
		}
	}

	public void scrolled() {
            Dimension vpSize = this.scroll.getViewport().getExtentSize();
            Dimension logSize = this.getSize();

            int height = logSize.height - vpSize.height;
            this.scroll.getViewport().setViewPosition(new Point(0, height));
        }




	public Plateau copyPlateau(Plateau plateau){

		Plateau copy = new Plateau(this.fenetre);
		for (int i = 0;i<fenetre.param.size;i=i+1) {
			for (int y = 0;y<fenetre.param.size;y=y+1) {
				copy.plat[i][y] = new Case(plateau.plat[i][y].x,plateau.plat[i][y].y,plateau.plat[i][y].index,plateau.plat[i][y].indey,this.fenetre.panel);
				copy.plat[i][y].contenue = plateau.plat[i][y].contenue;
				if (copy.plat[i][y].contenue == 3 || copy.plat[i][y].contenue == 4) {
					copy.plat[i][y].contenue = 0;
				}
			}	
		}
		for (int i = 0;i<fenetre.param.size;i=i+1) {
			for (int y = 0;y<fenetre.param.size;y=y+1) {
				 for(Case cases: plateau.plat[i][y].liberte)
      			{
    	  			copy.plat[i][y].liberte.add(copy.plat[cases.index][cases.indey]);
      			}
				
			}	
		}
		copy.j=plateau.j;
		copy.lastj1=plateau.lastj1;
		copy.lastj2=plateau.lastj2;
		copy.scoren = plateau.scoren;
		copy.scoreb = plateau.scoreb;
		copy.nbPasser = plateau.nbPasser;
		copy.nbhoshi = plateau.nbhoshi;
		return copy;

	}

		@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		

	
	}

	@Override
	public void valueChanged(ListSelectionEvent e){
		if (e.getValueIsAdjusting()) {
			//System.out.println(this.list.getSelectedIndex());
			fenetre.panel.swapPlateau(listModel.getElementAt(list.getSelectedIndex()));

			
		}

	}


}
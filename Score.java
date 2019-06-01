import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Score extends JPanel{
	public Fenetre fenetre;
	public LinkedList<Case[][]> historique;
	public JList<Case[][]> list;
	public DefaultListModel listModel;
	public JScrollPane scroll;
	public JLabel scoreblanc;
	public JLabel scorenoir;
	Score(Fenetre fenetre){
		super();
		this.setSize(300,900);
		this.setLocation(900,0);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//addMouseListener(fenetre);
		//addMouseMotionListener(fenetre);
		//setBackground(Color.BLACK);
		this.fenetre = fenetre;
		this.historique = new LinkedList<Case[][]>();
		this.listModel = new DefaultListModel();
		this.list = new JList<Case[][]>(this.listModel);
		list.setLayoutOrientation(JList.VERTICAL);
		//list.setVisibleRowCount(10);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);

		scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(340,400));
		scroll.setMaximumSize(new Dimension(340,400));
		scoreblanc = new JLabel("Score Pion Blanc : 0");
		scorenoir = new JLabel("Score Pion Noir : 0");
		this.add(scoreblanc);
		this.add(scorenoir);
		this.add(scroll);
	

	}

	public void scrolled() {
            Dimension vpSize = this.scroll.getViewport().getExtentSize();
            Dimension logSize = this.getSize();

            int height = logSize.height - vpSize.height;
            this.scroll.getViewport().setViewPosition(new Point(0, height));
        }




	public Case[][] copyPlateau(Case[][] plat){
		Case[][] copy = new Case[fenetre.param.size][fenetre.param.size];
		for (int i = 0;i<fenetre.param.size;i=i+1) {
			for (int y = 0;y<fenetre.param.size;y=y+1) {
				copy[i][y] = new Case(plat[i][y].x,plat[i][y].y,plat[i][y].index,plat[i][y].indey,this.fenetre.panel);
				copy[i][y].contenue = plat[i][y].contenue;
			}	
		}
		for (int i = 0;i<fenetre.param.size;i=i+1) {
			for (int y = 0;y<fenetre.param.size;y=y+1) {
				 for(Case cases: plat[i][y].liberte)
      			{
    	  			copy[i][y].liberte.add(copy[cases.index][cases.indey]);
      			}
				
			}	
		}

		return copy;

	}

		@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		

	
	}


}
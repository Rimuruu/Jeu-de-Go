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
	
		this.listModel = new DefaultListModel<Plateau>();
		this.list = new JList<Plateau>(this.listModel);
		list.setLayoutOrientation(JList.VERTICAL);
		//list.setVisibleRowCount(10);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		

		scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(340,400));
		scroll.setMaximumSize(new Dimension(340,400));
		scoreblanc = new JLabel("Score Pion Blanc : "+fenetre.param.handicap);
		scorenoir = new JLabel("Score Pion Noir : "+0f);
		this.add(scoreblanc);
		this.add(scorenoir);
		this.add(scroll);
		list.addListSelectionListener(this);
	

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
		return copy;

	}

		@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		

	
	}

	@Override
	public void valueChanged(ListSelectionEvent e){
		if (e.getValueIsAdjusting()) {
			System.out.println(this.list.getSelectedIndex());
			fenetre.panel.swapPlateau(listModel.getElementAt(list.getSelectedIndex()));

			
		}

	}


}
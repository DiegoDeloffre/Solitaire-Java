import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FenetreSolitaire extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private VueSolitaire vueSolitaire;		// vue du solitaire
	private ControleurMenu controleur;		// controleur du menu
	private JFrame frame;					// JFrame de la fenetre
	
	/**
	 * Crée la fenetre du jeu
	 * Ajoute une barre de menu à la vue
	 */
	public FenetreSolitaire() {
		this.controleur = new ControleurMenu(this);
		this.vueSolitaire = new VueSolitaire();
		this.frame= new JFrame();
		
		JMenuBar menu = new JMenuBar();
		JMenu jeu = new JMenu("Jeu");
		jeu.addActionListener(this.controleur);
		menu.add(jeu);
		
		JMenuItem rejouer = new JMenuItem("Rejouer");
		JMenuItem quit = new JMenuItem("Quitter");
		rejouer.addActionListener(this.controleur);
		quit.addActionListener(this.controleur);
		//rejouer.setPreferredSize(new Dimension(50, 25));
		jeu.add(rejouer);
		jeu.add(quit);
		
		this.frame.setJMenuBar(menu);	
		this.frame.setLayout(new GridLayout(1,1));

		this.frame.add(this.vueSolitaire);
		this.frame.pack();
		this.frame.setVisible(true);
		this.frame.setTitle("Solitaire");
		this.frame.setResizable(false);
		this.frame.setSize(450, 600);
	}

	/**
	 * crée une nouvelle vue
	 */
	public void creerNouvelleVue() {
		this.frame.remove(this.vueSolitaire);
		this.vueSolitaire = new VueSolitaire();
		this.frame.add(this.vueSolitaire);
		this.frame.pack();
		this.frame.setVisible(true);
		this.frame.setTitle("Solitaire");
		this.frame.setResizable(false);
		this.frame.setSize(450, 600);
	}
	
}

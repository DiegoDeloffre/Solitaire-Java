import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VueSolitaire  extends JPanel {
	
	private static final long serialVersionUID = -3079027632174031489L;
	private ModeleSolitaire modele;				// modèle du solitaire
	private CaseSolitaire[][] tabBilles;		// tableau de CaseSolitaire
	private ControleurSolitaire controleur;		// controleur du solitaire
	private JTextField commentaire;				// JTextField du commentaire
	
	/**
	 * Construit la vue du solitaire
	 */
	public VueSolitaire() {
		this.controleur=new ControleurSolitaire(this);
		this.modele = new ModeleSolitaire();
		
		this.setLayout(new BorderLayout());
		
		JPanel tete = new JPanel();
		tete.setLayout(new BorderLayout());
		this.add(tete, BorderLayout.NORTH);
		
		JLabel titre = new JLabel("Jeu du solitaire");
		titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		titre.setPreferredSize(new Dimension(300, 50));
		titre.setFont(new Font("Arial", Font.BOLD, 24));
		
		this.commentaire = new JTextField("...");
		this.commentaire.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		this.commentaire.setPreferredSize(new Dimension(300, 50));
		this.commentaire.setFont(new Font("Arial", Font.TRUETYPE_FONT, 14));
		this.commentaire.setEditable(false);
		
		tete.add(titre,BorderLayout.NORTH);
		tete.add(this.commentaire,BorderLayout.SOUTH);
		
		JPanel jeu = new JPanel();
		jeu.setPreferredSize(new Dimension(420, 420));
		jeu.setLayout(new GridLayout(7, 7));
		
		this.add(jeu, BorderLayout.SOUTH);
		this.tabBilles=new CaseSolitaire[7][7];
		
		for(int i=0;i<7;i++) {
			for(int j=0;j<7;j++) {
				if(this.modele.getPlateau()[i][j]==1) {
					this.tabBilles[i][j]=new CaseSolitaire(i, j);
					this.tabBilles[i][j].setIcon(new ImageIcon(getClass().getResource("bille.png")));
					this.tabBilles[i][j].addActionListener(controleur);
					this.tabBilles[i][j].setEnabled(false);
					jeu.add(this.tabBilles[i][j],i,j);
				}else if(this.modele.getPlateau()[i][j]==2) {
					this.tabBilles[i][j]=new CaseSolitaire(i, j);	
					this.tabBilles[i][j].setIcon(new ImageIcon(getClass().getResource("trou.png")));
					this.tabBilles[i][j].addActionListener(controleur);
					this.tabBilles[i][j].setEnabled(false);
					jeu.add(this.tabBilles[i][j],i,j);
				}else {
					this.tabBilles[i][j]=new CaseSolitaire(i, j);
					jeu.add(this.tabBilles[i][j],i,j);
					this.tabBilles[i][j].setVisible(false);
				}
			}
		}
		this.tabBilles[3][1].setEnabled(true);
		this.tabBilles[3][5].setEnabled(true);
		this.tabBilles[1][3].setEnabled(true);
		this.tabBilles[5][3].setEnabled(true);
	}
	
	/**
	 * Déplace la bille et actualise le tableau du modèle
	 * @param depart représente la case de départ
	 * @param arrivee représente la case d'arrivée
	 */
	public void deplacerBille(CaseSolitaire depart, CaseSolitaire arrivee) {
		if(depart.getLigne()==arrivee.getLigne()) {
			if(depart.getColonne()<arrivee.getColonne()) {
				this.modele.getPlateau()[depart.getLigne()][depart.getColonne()]=2;
				this.modele.getPlateau()[depart.getLigne()][depart.getColonne()+1]=2;
				this.modele.getPlateau()[arrivee.getLigne()][arrivee.getColonne()]=1;
			}else {
				this.modele.getPlateau()[depart.getLigne()][depart.getColonne()]=2;
				this.modele.getPlateau()[depart.getLigne()][depart.getColonne()-1]=2;
				this.modele.getPlateau()[arrivee.getLigne()][arrivee.getColonne()]=1;
			}
		}else if (depart.getColonne()==arrivee.getColonne()) {
			if(depart.getLigne()<arrivee.getLigne()) {
				this.modele.getPlateau()[depart.getLigne()][depart.getColonne()]=2;
				this.modele.getPlateau()[depart.getLigne()+1][depart.getColonne()]=2;
				this.modele.getPlateau()[arrivee.getLigne()][arrivee.getColonne()]=1;
			}else {
				this.modele.getPlateau()[depart.getLigne()][depart.getColonne()]=2;
				this.modele.getPlateau()[depart.getLigne()+-1][depart.getColonne()]=2;
				this.modele.getPlateau()[arrivee.getLigne()][arrivee.getColonne()]=1;
			}
		}
	}
	
	/**
	 * met à jour la vue en fonction du tableau du modele
	 * Place une bille si il y a un 1 et une case vide si il y a un 2
	 */
	public void majTab() {
		for(int i=0;i<7;i++) {
			for(int j=0;j<7;j++) {
				if(this.modele.getPlateau()[i][j]==1) {
					this.tabBilles[i][j].setIcon(new ImageIcon(getClass().getResource("bille.png")));	
					this.tabBilles[i][j].setEnabled(false);
				}else if(this.modele.getPlateau()[i][j]==2) {
					this.tabBilles[i][j].setIcon(new ImageIcon(getClass().getResource("trou.png")));	
					this.tabBilles[i][j].setEnabled(false);
				}
			}
		}
		this.repaint();
	}
	
	/**
	 * Active les boutons pour lesquels un déplacement est possible
	 * @return le nombre de boutons activés
	 */
	public int activerBoutons() {
		int cpt = 0;
		for(int i=0;i<7;i++) {
			for(int j=0;j<7;j++) {
				//si la case actuelle est vide
				if(this.modele.getPlateau()[i][j]==2) {
					//si le compteur ne dépasse pas la taille maximale du tableau
					if(i-2>=0) {
						// si les cases des deux lignes précédentes de la colonne courante sont vides
						if(((this.modele.getPlateau()[i-1][j]==1) && (this.modele.getPlateau()[i-2][j]==1))) {
							this.tabBilles[i-2][j].setEnabled(true);
							cpt++;
						}
					}
					//si le compteur ne dépasse pas la taille maximale du tableau
					if(j-2>=0) {
						// si les cases des deux colonnes précédentes de la ligne courante sont vides
						if(((this.modele.getPlateau()[i][j-1]==1)  && (this.modele.getPlateau()[i][j-2]==1))) {
							this.tabBilles[i][j-2].setEnabled(true);
							cpt++;
						}
					}
					//si le compteur ne dépasse pas la taille maximale du tableau
					if(i+2<7) {
						// si les cases des deux lignes suivantes de la colonne courante sont vides
						if(((this.modele.getPlateau()[i+1][j]==1)  && (this.modele.getPlateau()[i+2][j]==1))) {
							this.tabBilles[i+2][j].setEnabled(true);
							cpt++;
						}
					}
					//si le compteur ne dépasse pas la taille maximale du tableau
					if(j+2<7) {
						// si les cases des deux colonnes suivantes de la ligne courante sont vides
						if(((this.modele.getPlateau()[i][j+1]==1) && (this.modele.getPlateau()[i][j+2]==1))) {
							this.tabBilles[i][j+2].setEnabled(true);
							cpt++;
						}
					}
				}
			}
		}
		return cpt;
	}
		
	/**
	 * désactive tout les boutons de la grille de jeu
	 */
	public void desactiverBoutons() {
		for(int i=0;i<7;i++) {
			for(int j=0;j<7;j++) {
				this.tabBilles[i][j].setEnabled(false);
			}
		}
	}
	
	/**
	 * Active les boutons possibles pour déplacer la bille
	 * @param c représente la case cliquée
	 */
	public void activerBoutonDest(CaseSolitaire c) {
		int i = c.getLigne();
		int j = c.getColonne();
		//si le compteur ne dépasse pas la taille maximale du tableau
		if(i-2>=0) {
			// si la case sur la même colonne et sur la ligne précédente est pleine
			// et si la case sur la même colonne et deux lignes avant est vide
			if(this.modele.getPlateau()[i-1][j]==1 && this.modele.getPlateau()[i-2][j]==2) {
				this.tabBilles[i-2][j].setEnabled(true);
			}
		}
		//si le compteur ne dépasse pas la taille maximale du tableau
		if(j-2>=0) {
			// si la case sur la même ligne et sur la colonne précédente est pleine
			// et si la case sur la même ligne et deux colonnes avant est vide
			if(this.modele.getPlateau()[i][j-1]==1 && this.modele.getPlateau()[i][j-2]==2) {
				this.tabBilles[i][j-2].setEnabled(true);
			}
		}
		//si le compteur ne dépasse pas la taille maximale du tableau
		if(i+2<7) {
			// si la case sur la même colonne et sur la ligne suivante est pleine
			// et si la case sur la même colonne et deux lignes après est vide
			if(this.modele.getPlateau()[i+1][j]==1 && this.modele.getPlateau()[i+2][j]==2) {
				this.tabBilles[i+2][j].setEnabled(true);
			}	
		}
		//si le compteur ne dépasse pas la taille maximale du tableau
		if(j+2<7) {
			// si la case sur la même ligne et sur la colonne suivante est pleine
			// et si la case sur la même ligne et deux colonnes après est vide
			if(this.modele.getPlateau()[i][j+1]==1 && this.modele.getPlateau()[i][j+2]==2) {
				this.tabBilles[i][j+2].setEnabled(true);
			}	
		}
	}
	
	/**
	 * Détermine si la partie est bloqué
	 * @return true si la partie est bloquée et false sinon
	 */
	public boolean partieBloquee() {
		if(this.activerBoutons()==0) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * @return le JTextField des commentaires de jeu
	 */
	public JTextField getCommentaire() {
		return commentaire;
	}
}

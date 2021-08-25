import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurSolitaire implements ActionListener{
	
	private enum Jeu{CHOIX_DEPART,CHOIX_ARRIVEE}	//Enumeration des états du jeu
	private Jeu jeu;
	
	private VueSolitaire vue;						//vue du solitaire
	private ModeleSolitaire modele;					//modele du solitaire
	private CaseSolitaire caseDepart=null;			//instancie une case à null
	
	/**
	 * Crée le controleur pour le solitaire
	 * @param vue représente la vue du solitaire
	 */
	public ControleurSolitaire(VueSolitaire vue) {
		this.jeu=Jeu.CHOIX_DEPART;
		this.modele=new ModeleSolitaire();
		this.vue=vue;		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CaseSolitaire c = (CaseSolitaire)e.getSource();
		switch(jeu) {
		case CHOIX_DEPART:
			this.caseDepart = c;
			this.vue.desactiverBoutons();
			this.vue.activerBoutonDest(c);
			this.jeu=Jeu.CHOIX_ARRIVEE;
			break;
		case CHOIX_ARRIVEE:
			this.vue.deplacerBille(this.caseDepart, c);
			this.vue.majTab();
			if(this.modele.partieFinie()) {
				this.vue.desactiverBoutons();
				this.vue.getCommentaire().setText("Bravo, vous avez gagné ! Voulez-vous rejouer ?");
			}else if(this.vue.partieBloquee()){
				this.vue.desactiverBoutons();
				this.vue.getCommentaire().setText("Aucun déplacement possible. Voulez-vous rejouer ?");
			}else {
				this.vue.activerBoutons();
				this.jeu=Jeu.CHOIX_DEPART;
			}
			break;
		}
	}

}

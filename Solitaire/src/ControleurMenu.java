import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JMenuItem;

public class ControleurMenu implements ActionListener, Serializable{
	
	private static final long serialVersionUID = -3811025026251192512L;
	private FenetreSolitaire fenetre;	//fenetre du solitaire

	/**
	 * Crée le controleur pour le menu
	 * @param fenetre représente la fenetre du solitaire
	 */
	public ControleurMenu(FenetreSolitaire fenetre) {
		this.fenetre= fenetre;	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem m = (JMenuItem)e.getSource();
		if(m.getText().equals("Quitter")) {
			System.exit(0);
		}else if(m.getText().equals("Rejouer")) {
			this.fenetre.creerNouvelleVue();
		}
	}
}

import javax.swing.JButton;

public class CaseSolitaire extends JButton{
	
	private static final long serialVersionUID = -6046813623975415449L;
	private int colonne;	// numero de la colonne de la case
	private int ligne;		// numero de la ligne de la case
	
	/**
	 * Construit une case
	 * @param ligne représente le numero de ligne de la case
	 * @param colonne représente le numero de colonne de la case
	 */
	public CaseSolitaire(int ligne,int colonne) {
		super();
		this.colonne = colonne;
		this.ligne = ligne;
	}
	
	/**
	 * @return la colonne de la case
	 */
	public int getColonne() {
		return colonne;
	}
	
	/**
	 * @return la ligne de la case
	 */
	public int getLigne() {
		return ligne;
	}
	
	

}

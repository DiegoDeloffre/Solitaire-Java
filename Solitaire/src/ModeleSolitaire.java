public class ModeleSolitaire {
	
	private int[][] plateau;	//plateau de jeu
	
	/**
	 * Construit le modèle du solitaire
	 * Construit un tableau vide et le remplit
	 */
	public ModeleSolitaire() {
		this.plateau = new int[7][7];
		this.remplir();
	}
	
	/**
	 * remplit le tableau plateau
	 */
	public void remplir() {
		this.plateau[0][2]=1;
		this.plateau[0][3]=1;
		this.plateau[0][4]=1;
		
		this.plateau[1][2]=1;
		this.plateau[1][3]=1;
		this.plateau[1][4]=1;
		
		this.plateau[2][0]=1;
		this.plateau[2][1]=1;
		this.plateau[2][2]=1;
		this.plateau[2][3]=1;
		this.plateau[2][4]=1;
		this.plateau[2][5]=1;
		this.plateau[2][6]=1;
		
		this.plateau[3][0]=1;
		this.plateau[3][1]=1;
		this.plateau[3][2]=1;
		this.plateau[3][3]=2;
		this.plateau[3][4]=1;
		this.plateau[3][5]=1;
		this.plateau[3][6]=1;
		
		this.plateau[4][0]=1;
		this.plateau[4][1]=1;
		this.plateau[4][2]=1;
		this.plateau[4][3]=1;
		this.plateau[4][4]=1;
		this.plateau[4][5]=1;
		this.plateau[4][6]=1;		
		
		this.plateau[5][2]=1;
		this.plateau[5][3]=1;
		this.plateau[5][4]=1;
		
		this.plateau[6][2]=1;
		this.plateau[6][3]=1;
		this.plateau[6][4]=1;
	}

	/**
	 * @return le plateau de jeu
	 */
	public int[][] getPlateau() {
		return plateau;
	}
	
	/**
	 * Détermine si la partie est finie
	 * @return vrai si oui et faux sinon
	 */
	public boolean partieFinie() {
		int cpt = 0;
		for(int i=0;i<7;i++) {
			for(int j=0;j<7;j++) {
				if(this.plateau[i][j]==1) {
					cpt++;
				}
			}
		}
		
		if(cpt==1) {
			return true;
		}else {
			return false;
		}
	}
}
package menschaerger;


public class Spieler {
	short[] moeglicheZuege = {0,0,0,0};
	int[] figuren = {0,0,0,0};
	
	public Spieler() {
		// TODO Auto-generated constructor stub
	}

	
	public short[] moeglicheZuegeBerechnen() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void spielZug() {
		short wurf = SpielVerhalten.wuerfeln();
		moeglicheZuege = this.moeglicheZuegeBerechnen();
		short figur = SpielVerhalten.inputZahl(String.format("Figuren (%d), (%d), (%d), (%d) können ziehen. Auswahl: ", this.moeglicheZuege[1], this.moeglicheZuege[2], this.moeglicheZuege[3], this.moeglicheZuege[4]));
		figuren[figur] = (short) (figuren[figur] + wurf);			//Ziehen
		if ( (figuren[figur] > 0) && (figuren[figur] < 41 )) {		//Rausschmeissen
			for (int i = 0; i < SpielVerhalten.anzahlSpieler; i++) {
				for (int j = 1; j <= 4; j++){
					if (figuren[figur] == (SpielVerhalten.spieler[i].figuren[j] + i*10)){
						SpielVerhalten.spieler[j].figuren[j] = 0;
					}
				
					
				}
			}
		}
	}
}

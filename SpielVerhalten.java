

package menschaerger;

import org.apache.commons.io.IOUtils;
import java.util.Random;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class SpielVerhalten {

	ArrayList<Spieler> spielerListe = new ArrayList<Spieler>();

	public SpielVerhalten(String[] args) {
		// Hey constructor!
		short anzahlSpieler = 0;
		anzahlSpieler = inputZahl("Anzahl Spieler eingeben: ");
		//Spieler anlegen:		
		
        int i = 0;
        while (i < anzahlSpieler) {
            spielerListe.add(new Spieler());
            i++;
        }
	}

	public static short inputZahl(String sFrage)  {
        System.out.println(sFrage);
        String sInput;
        Scanner scanIn = new Scanner(System.in);
        sInput = scanIn.nextLine();
        scanIn.close();            
        System.out.println(sInput);
        return Short.parseShort(sInput);
	}
	
	public void spielZug(short spielerNummer) {
		short wurf = wuerfeln();
		for(Spieler spieler: this.spielerListe){
			short[] moeglicheZuege = spieler.moeglicheZuegeBerechnen();
		};
		short figur = inputZahl(String.format("Figuren (%d) können ziehen. Auswahl: ", moeglicheZuege[1]));
		for(Spieler spieler: this.spielerListe){
			
		}
	}
	
	private static short wuerfeln() { 
		Random rand = new Random();
			int w = rand.nextInt(5);
			w++;
			return (short) w;
	}
}

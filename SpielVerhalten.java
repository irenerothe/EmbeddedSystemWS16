

package menschaerger;

//import org.apache.commons.io.IOUtils;
import java.util.Random;
//import java.io.IOException;
import java.util.Scanner;
//import java.util.ArrayList;

public class SpielVerhalten {
	public static short anzahlSpieler = 0;
	public static Spieler[] spieler;
	
	
	public SpielVerhalten() {
		// TODO Auto-generated constructor stub
		// Hey constructor!
		
		anzahlSpieler = inputZahl("Anzahl Spieler eingeben: ");
		//Spieler anlegen:		
		
		spieler = new Spieler[anzahlSpieler];
	}


	public static void main(String[] args){
		int i = 1;
		while (i > 0) {
			for (int k = 0; k < anzahlSpieler; k++){
				spieler[k].spielZug();
			}
			
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
	
	
	
	public static short wuerfeln() { 
		Random rand = new Random();
			int w = rand.nextInt(5);
			w++;
			return (short) w;
	}
}

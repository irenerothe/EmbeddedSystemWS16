//import org.apache.commons.io.IOUtils;
import java.util.Random;

public class SpielVerhalten {

  public static int anzahlSpieler = 0;
  public static Spieler[] SpielerX;
  
  Random rand = new Random();

//------------------------------------------------------------------------------ 

  // Constuctor erfragt die Anzahl der Spieler und erstellt die Spieler Objekte
  public SpielVerhalten() {
  
    int cnt_spieler;
    
    //System.out.println("\n  DEBUG: public SpielVerhalten() (Constructor)\n");
                                                                                        
    System.out.print("Geben sie die Anzahl der Spieler ein:");
    anzahlSpieler = IOUtils.readInt();
    while ( anzahlSpieler < 1 || anzahlSpieler > 4 ) { 
      System.out.print("Ungültige Eingabe! Geben sie die Anzahl der Spieler ein:");
      anzahlSpieler = IOUtils.readInt();
    }
    
    SpielerX = new Spieler[anzahlSpieler];
    for (cnt_spieler=0; cnt_spieler<anzahlSpieler; cnt_spieler++) {
      SpielerX[cnt_spieler] = new Spieler();
    }
    
    System.out.println(anzahlSpieler + " Spieler erzeugt.");

  }
  
//------------------------------------------------------------------------------ 
  
  public void zugablauf(int cnt_spieler){
    int cnt_figur=1;
    int wurfel;
    int erfolg=0;
    
    //System.out.println("\n  DEBUG: public void zugablauf(int)\n");
         
    // würfeln
    wurfel = rand.nextInt(6);
    wurfel++;
    
    System.out.println("Spieler " + (cnt_spieler));
    System.out.println("Wuerfelwert: " + wurfel);
    
    if (zugmoglich(cnt_spieler, 1, wurfel)==1 ||                                // ist ein zug möglich
        zugmoglich(cnt_spieler, 2, wurfel)==1 ||
        zugmoglich(cnt_spieler, 3, wurfel)==1 ||
        zugmoglich(cnt_spieler, 4, wurfel)==1 ) {
      
      while ( erfolg != 1 ) {                                                   // welche figur soll den zug ausführen
        System.out.print("Auf welche Figur soll der Zug mit dem Würfelwert " + wurfel + " angewendet werden? ");
        cnt_figur = IOUtils.readInt();
        if ( cnt_figur<1 || cnt_figur>4 ) {
          System.out.println("Ungültige Eingabe!");
        }
        else {
          if ( zug(cnt_spieler, cnt_figur, wurfel) == 1 ) {                      // zug ausführung
            erfolg = 1;
          }
        }
      }
    }
    else {
      System.out.println("Es ist kein zug möglich");
      cnt_figur = IOUtils.readInt();                                            // warte auf eingabe falls kein zug möglich
    }
  }
  
//------------------------------------------------------------------------------  

  public int zug(int cnt_spieler, int cnt_figur, int wurfel){
    int pos;
    int Gegner;
    int GegnerFigur;
    
    //System.out.println("\n  DEBUG: public int zug(int, int, int)\n");
    
    if ( cnt_spieler<1 || cnt_spieler>4 || cnt_figur<1 || cnt_figur>4 ) {
      System.out.println("ERROR ERROR ERROR in zug() !");
      return 0;
    }
    
    
    if (zugmoglich(cnt_spieler, cnt_figur, wurfel)==1 ) {
      
      pos = getpos(cnt_spieler, cnt_figur);
      
      if (pos==0) {                                     // setze eigene Figur
        SpielerX[cnt_spieler-1].setfigur(cnt_figur, 1);
      }
      else {
        SpielerX[cnt_spieler-1].setfigurwurfel(cnt_figur, wurfel);
      }   
      
      pos = getpos(cnt_spieler, cnt_figur);
      
      Gegner = gegneraufposX(pos, cnt_spieler);    // setze Figur des Gegners
      if (Gegner!=0) {
        GegnerFigur = figuraufposX(pos, Gegner);
        SpielerX[Gegner-1].setfigur(GegnerFigur, 0);
      }
      
      return 1;
      
    }
    return 0;
  }
  
//------------------------------------------------------------------------------ 
  
  public int zugmoglich(int cnt_spieler, int cnt_figur, int wurfel){
   
    //System.out.println("\n  DEBUG: public int zugmoglich(int, int, int)\n");
    
    int testpos = getpos(cnt_spieler, cnt_figur);
    
    if ( cnt_spieler<1 || cnt_spieler>4 || cnt_figur<1 || cnt_figur>4 ) {
      System.out.println("ERROR ERROR ERROR in zugmoglich() !");
      return 0;
    }
    
    cnt_spieler--;
    cnt_figur--;
    
    if ( testpos==0 && wurfel==6 && figuraufposX(cnt_spieler*10+1, cnt_spieler+1)==0 ) {
      return 1;
    }
      
    if ( testpos>0 && SpielerX[cnt_spieler].getfigur(cnt_figur+1)+wurfel<=44 && figuraufposX(testpos+wurfel, cnt_spieler+1)==0 ) {
      return 1;
    } 
    
    return 0;
  }  
  
//------------------------------------------------------------------------------ 
  
  public int gewonnen(int cnt_spieler) {
    int testpos;
    
    //System.out.println("\n  DEBUG: public int gewonnen(int)\n");
    
    if ( cnt_spieler<1 || cnt_spieler>4 ) {
      System.out.println("ERROR ERROR ERROR in gewonnen() !");
      return 0;
    }
  
    for (int cnt_figur=1; cnt_figur<=4 ; cnt_figur++) {
    
      testpos = getpos(cnt_spieler, cnt_figur);
      
      if ( testpos <= 40 ) {
        return 0;
      }
      
    }
    return 1;
  }
  
//------------------------------------------------------------------------------ 




//------------------------------------------------------------------------------ 

  public int getpos(int cnt_spieler, int cnt_figur) {
    int pos;
    
    //System.out.println("\n  DEBUG: public int getpos(int, int)\n");
    
    if ( cnt_spieler<1 || cnt_spieler>4 || cnt_figur<1 || cnt_figur>4 ) {
      System.out.println("ERROR ERROR ERROR in getpos() ! " + cnt_spieler + " " + cnt_figur + "\n");
      return 0;
    }
    
    pos = SpielerX[cnt_spieler-1].getfigur(cnt_figur);
    
    if (pos!=0 && pos<=40 ) {
      pos += (cnt_spieler-1)*10;
      if (pos>40) {
        pos = pos - 40;
      }
      
    }
    return pos;
  }

//------------------------------------------------------------------------------ 
  
  public int figuraufposX(int pos, int cnt_spieler){
    int testpos;
    
    //System.out.println("\n  DEBUG: public int figuraufposX(int, int)\n");
    
    if ( cnt_spieler<1 || cnt_spieler>4 ) {
      System.out.println("ERROR ERROR ERROR in figuraufposX() !");
      return 0;
    }
    
    for (int cnt_figur=1; cnt_figur<=4; cnt_figur++) {
    
      testpos = getpos(cnt_spieler, cnt_figur);
    
      if (testpos==pos) {
         return cnt_figur;
      }
      
    }
    return 0;
  }
  
//------------------------------------------------------------------------------ 
  
  public int spieleraufposX(int pos){
    int testpos;
    
    //System.out.println("\n  DEBUG: public int spieleraufposX(int)\n");
    
    if ( pos>=1 && pos<=40 ) {
      for (int cnt_spieler=1; cnt_spieler<=anzahlSpieler; cnt_spieler++) {
        for (int cnt_figur=1; cnt_figur<=4; cnt_figur++) {
        
          testpos = getpos(cnt_spieler, cnt_figur);
          
          if (testpos>=1 && testpos<=40 && testpos==pos) {
            return cnt_spieler;
          }
          
        }
      }
    }
    return 0;
  }
  
//------------------------------------------------------------------------------ 

  public int gegneraufposX(int pos, int cnt_spieler){
    int testpos;
    int cnt_gegner;
    int cnt_figur;
    //System.out.println("\n  DEBUG: public int spieleraufposX(int)\n");
    
    if ( pos>=1 && pos<=40 ) {
      for (cnt_gegner=1; cnt_gegner<=anzahlSpieler; cnt_gegner++) {
        if (cnt_gegner != cnt_spieler) {
          for (cnt_figur=1; cnt_figur<=4; cnt_figur++) {
            testpos = getpos(cnt_gegner, cnt_figur);        
            if (testpos>=1 && testpos<=40 && testpos==pos) {
              return cnt_gegner;
            }
          }
        }
      }
    }
    return 0;
  }
  
//------------------------------------------------------------------------------ 
  
  public void SpielfeldAusgabe(){
    int schritt;
    
    //System.out.println("\n  DEBUG: public void Spielfeldausgabe()\n");
    
    /*System.out.print("\nSpieler 1: ");
    System.out.print(SpielerX[0].figur[0] + " ");
    System.out.print(SpielerX[0].figur[1] + " ");
    System.out.print(SpielerX[0].figur[2] + " ");
    System.out.print(SpielerX[0].figur[3]);
    
    System.out.print("\nSpieler 2: ");
    System.out.print(SpielerX[1].figur[0] + " ");
    System.out.print(SpielerX[1].figur[1] + " ");
    System.out.print(SpielerX[1].figur[2] + " ");
    System.out.print(SpielerX[1].figur[3]);
    
    System.out.print("\nSpieler 3: ");
    System.out.print(SpielerX[2].figur[0] + " ");
    System.out.print(SpielerX[2].figur[1] + " ");
    System.out.print(SpielerX[2].figur[2] + " ");
    System.out.print(SpielerX[2].figur[3]);
    
    System.out.print("\nSpieler 4: ");
    System.out.print(SpielerX[3].figur[0] + " ");
    System.out.print(SpielerX[3].figur[1] + " ");
    System.out.print(SpielerX[3].figur[2] + " ");
    System.out.print(SpielerX[3].figur[3]);
    
    System.out.print("\n\n");*/
    
    // print Haus
    for (int cnt_spieler=1; cnt_spieler<=anzahlSpieler; cnt_spieler++) {
      for (int cnt_figur=1; cnt_figur<=4; cnt_figur++) {
        if (getpos(cnt_spieler, cnt_figur) == 0) {
          System.out.print(cnt_spieler);
        }
        else {
          System.out.print('#');
        }
      }
      System.out.print("      ");
    }
    System.out.print('\n');
    
    // print Spielfeld
    for (int cnt_schritte=1; cnt_schritte <= 40; cnt_schritte++) {
      schritt = 0;
      for (int cnt_spieler=1; cnt_spieler<=anzahlSpieler; cnt_spieler++) {
        for (int cnt_figur=1; cnt_figur<=4; cnt_figur++) {
          if ( getpos(cnt_spieler, cnt_figur) == cnt_schritte ) {
            schritt = cnt_spieler;
          }
        }
      }
      if (schritt==0) {
        System.out.print('*');  
      }
      else {
        System.out.print(schritt);
      }
    }
    System.out.print('\n');
    
    // print Ziel
    for (int cnt_spieler=1; cnt_spieler<=anzahlSpieler; cnt_spieler++) {
      for (int cnt_ziel=1; cnt_ziel<=4; cnt_ziel++) {
        schritt = 0;
        for (int cnt_figur=1; cnt_figur<=4; cnt_figur++) {
          if (getpos(cnt_spieler, cnt_figur) == 40+cnt_ziel) {
            schritt = cnt_spieler;
          }
        }
        if (schritt==0) {
          System.out.print('#');
        }
        else {
          System.out.print(schritt);
        }
      }
      System.out.print("      ");
    }
    System.out.print("\n\n");
  }
  
//------------------------------------------------------------------------------ 


}

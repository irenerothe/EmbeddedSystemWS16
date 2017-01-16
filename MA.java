
public class MA {
  
  public static void main(String[] args) {
    
    int cnt_spieler=1;
    int SpielEnde=0;
    
    SpielVerhalten Spiel = new SpielVerhalten();

    while (SpielEnde==0) {
      System.out.print("\n-----------------------------------------------\n\n"); 
      Spiel.SpielfeldAusgabe();
      Spiel.zugablauf(cnt_spieler);
      SpielEnde = Spiel.gewonnen(cnt_spieler);
      if ( SpielEnde == 0 ) {
        cnt_spieler++;
        if ( cnt_spieler>Spiel.anzahlSpieler ) {
          cnt_spieler = 1;
        }
      }
    }
    System.out.println("Spieler " + (cnt_spieler) + " hat gewonnen.");
    
    System.out.println ("\nENDE");
  }

}

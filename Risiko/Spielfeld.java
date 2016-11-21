import java.util.List;
import java.util.ArrayList;

class Spielfeld{
        
      Spieler[] spieler; 
      Kontinent[] kontinent;
      int[][] karte;
      int neue_truppen;
      
      private int anzahl_laender; 
      
      //List<Kontinent> kontinent = new ArrayList<Kontinent>();
      
       Spielfeld(int karte[][], int anzahl_spieler){
                erstelle_spieler(anzahl_spieler);
                erstelle_kontinent(anzahl_laender);
                truppen_zufaellig_setzen();
                this.karte = karte;
         }
      
       private void erstelle_spieler(int anzahl_spieler){
                    spieler = new Spieler[anzahl_spieler];    
       }
       
       private void erstelle_kontinent(int anzahl_laender){
       
       
       }
       
       private void truppen_zufaellig_setzen(){
       
       
       }
       
       int berechne_anzahl_neue_truppen(int spieler)
       {
          return neue_truppen;
       }
       
              void ausgabe_eigener_laender(int spieler)
       {
        
       }
       
       void truppen_setzen(int anzahl_truppen, int land)
       {
        
       }
}
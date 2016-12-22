import algds.IOUtils;

public class MenschAergerDichNicht{
  //Attribute
  private int Spieleranzahl=0;
  private Spielfeld spielfeld; 
  private Wuerfel wuerfel;   
  Spieler spieler[];
  
  //Konstruktor
  MenschAergerDichNicht(){
    do{
      System.out.println("Wie viele Spieler sollen teilnehmen? (1-4)");
      Spieleranzahl = IOUtils.readInt();
    }while((Spieleranzahl==0)||(Spieleranzahl>4));
    spielfeld = new Spielfeld();
    wuerfel = new Wuerfel();
    spieler = new Spieler[Spieleranzahl];
    for(int k=0;k<Spieleranzahl;k++){
      spieler[k]=new Spieler(k,spielfeld);
      for (int l=1;l<5 ;l++ ) {
        spieler[k].FigurSetzen(l,0);
        spielfeld.HausSetzen(k,l,spieler[k].FigurAbfragen(l));
      } // end of for
    }  
  }
  
  //Methoden
  public void Spielverlauf(){
    int i=0;
    boolean Siegbedingung=false;
    do{
      spielfeld.SpielfeldAusgeben();
      if (i==Spieleranzahl)  i=0;
      spieler[i].Spielzug(wuerfel);
      Siegbedingung = Siegpruefung(spieler[i]);
      i++;
    }while(Siegbedingung!=true);
    System.out.println("Spieler "+(--i)+" hat das Spiel gewonnen!");
    IOUtils.readChar(); 
  } 
    
  public boolean Siegpruefung(Spieler spieler){
    int i;
    for (i=1;i<5;i++ ) {
      if (spieler.FigurAbfragen(i).PositionAbfragen()<41)
        return false;
    } // end of for
    return true;
  }   
}
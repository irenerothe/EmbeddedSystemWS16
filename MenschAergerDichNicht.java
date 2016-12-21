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
    spielfeld = new Spielfeld(Spieleranzahl);
    wuerfel = new Wuerfel();
    spieler = new Spieler[Spieleranzahl];
    for(int k=0;k<Spieleranzahl;k++){
      spieler[k]=new Spieler(k);
      for (int l=1;l<5 ;l++ ) {
        spieler[k].FigurSetzen(l,0);
        spielfeld.HausSetzen(k,l,spieler[k].FigurAbfragen(l));
      } // end of for
    }  
  }
  
  //Methoden
  public void Starten(){
    spielfeld.SpielfeldAusgeben();
    int i=0;
    boolean Siegbedingung=false;
    do{
      spielfeld.SpielfeldAusgeben();
      if (i==Spieleranzahl) 
        i=0;
      Spielerzug(spieler[i]);
      Siegbedingung = Siegpruefung(spieler[i]);
      i++;
    }while(Siegbedingung!=true);
    System.out.println("Spieler "+(--i)+" hat das Spiel gewonnen!");
    IOUtils.readChar(); 
  }
  
  public void FigurVerruecken(Spieler spieler,int NrFigur, int Wert){
    //alte Position zurücksetzen
    int altePosition = spieler.FigurPositionAbfragen(NrFigur);
    Figur figur=spieler.FigurAbfragen(NrFigur);
    int SpielerNr= spieler.SpielernummerAbfragen();
    if (altePosition==0) {
        spielfeld.HausSetzen(SpielerNr,figur.NrAbfragen(),null);
    } 
    else if (altePosition<=40){
      spielfeld.FeldSetzen(altePosition,null);
    }
    else{
      spielfeld.ZielSetzen(SpielerNr,figur.NrAbfragen(),null);
    }
    
    //neue Position setzen
    spieler.FigurSetzen(NrFigur,Wert);
    if (Wert==0) {
        spielfeld.HausSetzen(SpielerNr,figur.NrAbfragen(),figur);
    } 
    else if (Wert<=40){
      spielfeld.FeldSetzen(Wert,spieler.FigurAbfragen(NrFigur));
    }
    else{
      spielfeld.ZielSetzen(SpielerNr,figur.NrAbfragen(),figur);
    }    
  }
  
  
  public boolean SpielerSetzen(Spieler spieler, int FigurNr, int Wuerfelwurf, boolean NurPruefen){ 
    int i;
    int Position=spieler.FigurPositionAbfragen(FigurNr);
    int SpielerNr=spieler.SpielernummerAbfragen();
    
    int Zielposition=0;
    int SpielerNrZielposition;
    Figur figurZielposition=null;
    Spieler besitzerFigurZielposition=null;
    if (Position==0) {
      //Startposition ist frei oder von Gegner besetzt
      if (Wuerfelwurf==6) {
        Zielposition=spieler.StartpositionAbfragen();
        figurZielposition = spielfeld.FeldAbfragen(Zielposition);
        if (figurZielposition == null) {
          if (NurPruefen==false) 
            this.FigurVerruecken(spieler,FigurNr,Zielposition);
          return true;
        }
        else {
          besitzerFigurZielposition = figurZielposition.Besitzer();
          SpielerNrZielposition = besitzerFigurZielposition.SpielernummerAbfragen();
          if (SpielerNrZielposition !=SpielerNr) {
            if (NurPruefen==false){ 
              this.FigurVerruecken(besitzerFigurZielposition,figurZielposition.NrAbfragen(),0);
              this.FigurVerruecken(spieler,FigurNr,Zielposition);
            }
            return true;
          } // end of if
        } // end of if-else
      } // end of if
    } 
    return false;
  }  
  
  public void Spielerzug(Spieler spieler){
    int i,j;
    int Wuerfelwurf=0; 
    for(i=0;i<3;i++){//3 mal Würfeln
      //Ausgaben Würfel
      if(i==0){
        System.out.println("Spieler "+spieler.SpielernummerAbfragen()+" bitte würfeln!");
        IOUtils.readChar();
      }
      else{
        System.out.println("Ergebnis: "+ Wuerfelwurf+"\nKein Zug möglich!\nSpieler "+spieler.SpielernummerAbfragen()+" bitte erneut würfeln!");
        IOUtils.readChar(); 
      }
      //Würfeln 
      Wuerfelwurf = spieler.Wuerfeln(wuerfel);
      //Prüfen, ob Zug möglich ist
      for (j=1;j<5 ;j++) {
        if (SpielerSetzen(spieler, j, Wuerfelwurf,true)) {
          i=3;
          j=5;
          System.out.print("Ergebnis: "+ Wuerfelwurf);
          SpielerSetzen(spieler,spieler.FigurWaehlen().NrAbfragen(), Wuerfelwurf,false);
        } // end of if 
      } // end of for
      if (i==2) {
          System.out.println("Ergebnis: "+ Wuerfelwurf+"\nKein Zug möglich!");
      } // end of if-else
    }
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

import algds.IOUtils;
 
class Spieler{
  //Attribute
  private Figur figur[];
  private Spielfeld spielfeld;
  private int Startposition;
  private int Zielposition;
  private int Spielernummer;
  
  //Konstruktor
  Spieler(int Spielernummer, Spielfeld spielfeld){
    this.Spielernummer=Spielernummer;
    this.spielfeld = spielfeld;
    figur = new Figur[4];
    int i;
    for (i=0;i<4 ;i++ ) {
      figur[i]=new Figur(this,i+1);
    } // end of for
    Startposition=Spielernummer*10+1;
    if(Startposition == 1)
      Zielposition=Spielernummer=40;
    else
      Zielposition=Startposition-1;
  }
  
  //Methoden
  public int SpielernummerAbfragen(){
  return Spielernummer;  
  }
  
  public int StartpositionAbfragen(){
  return Startposition;  
  }
  
  public int ZielpositionAbfragen(){
  return Zielposition;  
  }
  
  public int FigurWaehlen(){
    int Nummer=0;
    System.out.print("\nWelche Figur(1-4) möchten Sie setzen?\n");
    do{
      Nummer=IOUtils.readInt();
    }while ((Nummer<1)||(Nummer>4));
    return Nummer;
  }
  
  public Figur FigurAbfragen(int Nummer){
    return figur[Nummer-1];
  } 

  public void FigurSetzen(int Nummer, int Wert){
    figur[Nummer-1].PositionSetzen(Wert);
  } 
  
  public int FigurPositionAbfragen(int Nummer){
    return figur[Nummer-1].PositionAbfragen();
  } 
  
  public void Spielzug(Wuerfel wuerfel){
    int i,j;
    int Wuerfelwurf=0; 
    boolean nochmalwuerfeln=true;
    
    for(i=0;i<3;i++){//3 mal Würfeln
      //Ausgaben Würfel
      if(i==0){
        System.out.println("Spieler "+(Spielernummer+1)+" bitte würfeln!");
        IOUtils.readChar();
      }
      else{
        System.out.println("Ergebnis: "+ Wuerfelwurf+"\nKein Zug möglich!\nSpieler "+(Spielernummer+1)+" bitte erneut würfeln!");
        IOUtils.readChar(); 
      }
      //Würfeln 
      Wuerfelwurf=wuerfel.wurf();   
      //Prüfen, ob Zug möglich ist
      for (j=1;j<5 ;j++) {
        if (FigurSetzen(j, Wuerfelwurf,true)) {      //Prüfen, ob überhaupt ein Zug möglich ist
          i=3;
          j=5;
          System.out.print("Ergebnis: "+ Wuerfelwurf);
          FigurSetzen(FigurWaehlen(), Wuerfelwurf,false);           // wenn Zug möglich, dann ausführen, dabei wird auch gefragt, welche Figur gesetzt werden soll
          
          if (Wuerfelwurf ==6 && nochmalwuerfeln == true) {
            i = 2;
            nochmalwuerfeln = false;
          } // end of if
          
        } // end of if 
      } // end of for
      
      if (i==2) {
          System.out.println("Ergebnis: "+ Wuerfelwurf+"\nKein Zug möglich!");
      } // end of if-else
    }
  }
   
  public boolean FigurSetzen(int Nummer, int Wuerfelwurf, boolean NurPruefen){ 
    int i;
    int Position=FigurPositionAbfragen(Nummer);
    
    int Zielposition=0;
    int SpielerNrZielposition;
    Figur figurZielposition=null;
    Spieler besitzerFigurZielposition=null;
    
    if (Position==0) {  //Figur noch im Haus 
      //Startposition ist frei oder von Gegner besetzt
      if (Wuerfelwurf==6) {
        Zielposition=StartpositionAbfragen();
        figurZielposition = spielfeld.FeldAbfragen(Zielposition);
        }
      else{
        return false;
      }  
    }
    
    else if(Position<=40) {
      Zielposition = FigurPositionAbfragen(Nummer) + Wuerfelwurf;
      figurZielposition = spielfeld.FeldAbfragen(Zielposition);
    }
    
  
    if (figurZielposition == null) {
        if (NurPruefen==false) 
          FigurVerschieben(Nummer,Zielposition);
        return true;
        }
        else {
          besitzerFigurZielposition = figurZielposition.Besitzer();                   
          SpielerNrZielposition = besitzerFigurZielposition.SpielernummerAbfragen();
          if (SpielerNrZielposition !=Spielernummer) {
            if (NurPruefen==false){ 
              besitzerFigurZielposition.FigurVerschieben(figurZielposition.NrAbfragen(),0);
              FigurVerschieben(Nummer,Zielposition);
            }
            return true;
          } 
      } 
    return false;  
  } 

    


  public void FigurVerschieben(int Nummer, int Wert){
    //alte Position zurücksetzen
    int altePosition = FigurPositionAbfragen(Nummer);
    
    if (altePosition==0) {
        spielfeld.HausSetzen(Spielernummer,Nummer,null);
    } 
    else if (altePosition<=40){
      spielfeld.FeldSetzen(altePosition,null);
    }
    else{
      spielfeld.ZielSetzen(Spielernummer,Nummer,null);
    }
    
    //neue Position setzen
    FigurSetzen(Nummer,Wert);
    if (Wert==0) {
        spielfeld.HausSetzen(Spielernummer,Nummer,figur[Nummer-1]);
    } 
    else if (Wert<=40){
      spielfeld.FeldSetzen(Wert,figur[Nummer-1]);
    }
    else{
      spielfeld.ZielSetzen(Spielernummer,Wert%10,figur[Nummer-1]);
    }    
  } 
}
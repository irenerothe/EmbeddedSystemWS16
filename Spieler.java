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
    int i,j, z;
    int Wuerfelwurf=0;   
    boolean nochmalwuerfeln=false;
    
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
      for (j=1;j<5 ;j++) {
        if (FigurSetzen(j, Wuerfelwurf, Spielernummer, true)) {      //Prüfen, ob überhaupt ein Zug möglich ist
          i=3;
          j=5;
          System.out.print("Ergebnis: "+ Wuerfelwurf);
          FigurSetzen(FigurWaehlen(), Wuerfelwurf, Spielernummer, false);           // wenn Zug möglich, dann ausführen, dabei wird auch gefragt, welche Figur gesetzt werden soll
          
          if (Wuerfelwurf ==6) {
            nochmalwuerfeln=true;
            }
          else{
            nochmalwuerfeln=false;
            }
          
          } // end of if
          
        } // end of if 
      
      if (i==2) {
          System.out.println("Ergebnis: "+ Wuerfelwurf+"\nKein Zug möglich!");
      } 
      
      if (Wuerfelwurf ==6 && nochmalwuerfeln == true) {            //nochmal würfel bei 6
            spielfeld.SpielfeldAusgeben();
            System.out.println("Spieler "+(Spielernummer+1)+" bitte nochmal würfeln!");
            IOUtils.readChar(); 
            Wuerfelwurf=wuerfel.wurf();   
            System.out.print("Ergebnis: "+ Wuerfelwurf);
            for (z=1;z<5 ;z++) {
              if (FigurSetzen(z, Wuerfelwurf, Spielernummer, true)) {
                z=5; j=5; i=3;
                FigurSetzen(FigurWaehlen(), Wuerfelwurf, Spielernummer, false); 
                nochmalwuerfeln = false; 
              }
            }
            z=5;j=5;i=3;      
        } 
       

    }
  }
   
  public boolean FigurSetzen(int Nummer, int Wuerfelwurf,int Spielernummer, boolean NurPruefen){ 
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
    
    else if(Position<=40) {         //Figur auf dem Feld
    
      Zielposition = FigurPositionAbfragen(Nummer) + Wuerfelwurf;
      
      if (Spielernummer!=0 && Zielposition>40 && Position <=40) {          //Feldüberlauf
        Zielposition = Zielposition-40;  
      } // end of if
      
   
      if (Spielernummer==1 && Position <=10 && Zielposition >10 && Zielposition <15 ){
        Zielposition = Zielposition+30;
      } // end of if-else
      else if (Spielernummer==2 && Position <=20 && Zielposition >20 && Zielposition <25 ){
        Zielposition = Zielposition+20;
      } // end of if-else
      else if (Spielernummer==3 && Position <=30 && Zielposition >30 && Zielposition <35){
        Zielposition = Zielposition+10;
      } // end of if-else
      
      if (Zielposition < 45) {
           figurZielposition = spielfeld.FeldAbfragen(Zielposition);
      } // end of if-else
      else
      {
           return false;
           }   
        
  
    }     
    else if(Position>40 && Zielposition<45) {          //Figur im Ziel
      Zielposition = FigurPositionAbfragen(Nummer) + Wuerfelwurf;
      figurZielposition = spielfeld.ZielAbfragen(Spielernummer, Zielposition%10);

    } // end of if-else   
    else {
      return false;
    } // end of if-else
    
  
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
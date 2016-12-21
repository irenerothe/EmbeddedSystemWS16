import algds.IOUtils;
 
class Spieler{
  //Attribute
  private Figur figur[];
  private int Startposition;
  private int Zielposition;
  private int Spielernummer;
  //Konstruktor
  Spieler(int Spielernummer){
    this.Spielernummer=Spielernummer;
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
  
  public Figur FigurWaehlen(){
    int Nr=0;
    System.out.print("\nWelche Figur(1-4) möchten Sie setzen?\n");
    do{
      Nr=IOUtils.readInt();
    }while ((Nr<1)||(Nr>4));
    return figur[Nr-1];
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
  
  public int Wuerfeln(Wuerfel wuerfel){
    return wuerfel.wurf();
  }
}
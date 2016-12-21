class Figur{
  //Attribute
  private Spieler besitzer;
  private int FigurNr;
  private int Position; //0 - Haus/1 - 40 Felder/41 - 44 Ziel
  
  //Konstruktor
  Figur(Spieler spieler,int FigurNr){
    besitzer=spieler;
    this.FigurNr=FigurNr;
  }
  
  //Methoden
  public Spieler Besitzer(){
    return besitzer;
  }
  
  public int NrAbfragen(){
    return FigurNr;
  }
  
  public int PositionAbfragen(){
    return Position;
  }
  
  public void PositionSetzen(int Nummer){
    Position = Nummer;
  }
}
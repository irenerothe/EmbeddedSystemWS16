   class Spielfeld{
     private Figur Feld[];
     private Figur Haus[][];
     private Figur Ziel[][];
     
     Spielfeld(){
       Feld = new Figur[40];
       Haus = new Figur[4][4];
       Ziel = new Figur[4][4];
       int i,j;
       for (i=0;i<40 ;i++ ) {           //allgemeine Felder initialisieren
         Feld[i]=null;
       } // end of for
       for (i=0;i<4;i++) { 
         for(j=0;j<4;j++){
           Haus[i][j]=null;
           Ziel[i][j]=null;
         }
       } // end of for       
     }
     public void FeldSetzen(int Nummer, Figur figur){
       Feld[Nummer-1]=figur;  
     }
     
     public void HausSetzen(int Spielernummer,int Nummer, Figur figur){
       Haus[Spielernummer][Nummer-1]=figur;  
     }
     
     public void ZielSetzen(int Spielernummer,int Nummer, Figur figur){
       Ziel[figur.Besitzer().SpielernummerAbfragen()][Nummer-1]=figur;  
     }
     
     public Figur FeldAbfragen(int Nummer){
       return Feld[Nummer-1];  
     } 
     
     public Figur HausAbfragen(Spieler spieler, int Nummer){
       return Haus[spieler.SpielernummerAbfragen()][Nummer-1];  
     } 
     
     public Figur ZielAbfragen(Spieler spieler, int Nummer){
       return Ziel[spieler.SpielernummerAbfragen()][Nummer-1];  
     }    
     public void SpielfeldAusgeben(){
       int i,j;
       System.out.println("***********************************************************************************************");
       System.out.print(" H1                               H2                               H3                               H4\n");
       for (i=0;i<40;i++ ) {
       if(i==0 || i==10 || i==20 ||i==30 ||i==40){      //Kennzeichnung der Häuser
           System.out.print("|");
           
         if(Feld[i]==null)
           System.out.print("O  ");
         else
           System.out.print(((Feld[i].Besitzer().SpielernummerAbfragen()+1)*10+Feld[i].NrAbfragen())+" ");
           
           System.out.print("| ");
        }
        else {
         if(Feld[i]==null)
           System.out.print("O  ");
         else
           System.out.print(((Feld[i].Besitzer().SpielernummerAbfragen()+1)*10+Feld[i].NrAbfragen())+" ");
        
        } 
       } // end of for
       

       System.out.println("                                      |                                |                                |                                |");
       System.out.print("                              Z2                              Z3                               Z4                               Z1\n");       
       
       for (i=0;i<4;i++) { //Ausgabe der Spielerspezifischen Felder 
         System.out.print("\nHaus Spieler"+i+":\n");
         for(j=0;j<4;j++){
           if(Haus[i][j]==null)
             System.out.print("_ ");
           else
             System.out.print(((Haus[i][j].Besitzer().SpielernummerAbfragen()+1)*10+Haus[i][j].NrAbfragen())+" ");
         }
         System.out.print("\n                                                                Ziel Spieler"+i+":\n                                                                ");
         for(j=0;j<4;j++){
           if(Ziel[i][j]==null)
             System.out.print("_ ");
           else
             System.out.print(                                                               ((Ziel[i][j].Besitzer().SpielernummerAbfragen()+1)*10+Ziel[i][j].NrAbfragen())+" ");
         }
       } // end of for
       System.out.println("\n**********************************************************************************************");
     }
   }
public class TTTGame
{
  public static void main(String args[])
  {
    int dimension=3;  // Festlegen der Anzahl der Spalten und Zeilen
    int u=0;
    int v=0;
    int lines=dimension;
    int cols=dimension;
    int gewinn=0;

    int[][] gamearray = new int[lines][cols];    //Spielfeld anlegen
    
    for (u=0; u<lines; u++)
    {
      for (v=0; v<cols; v++)
      {
        gamearray[u][v] = 0; // Fuellen der Matrix mit "0"
      }
    }
  
    RealPlayer player1 = new RealPlayer(); // Erstellung von Spieler 1
    player1.setLines(lines);
    player1.setCols(cols);
    player1.setNumber(1); // die Zahl im Array von Player 1 wird auf 1 gesetzt
    player1.setName("Hans");
    player1.setSign("O"); // das Zeichen zur grafischen von Player 1 wird auf O gesetzt
    
    RealPlayer player2 = new RealPlayer(); // Erstellung von Spieler 1
    player2.setLines(lines);
    player2.setCols(cols);
    player2.setNumber(2); // die Zahl im Array von Player 1 wird auf 1 gesetzt
    player2.setName("Wurst");
    player2.setSign("X"); // das Zeichen zur grafischen von Player 1 wird auf X gesetzt
    
    CheckWinner checker = new CheckWinner();
    checker.setLines(lines);
    checker.setCols(cols);
    
    ShowGamearray showgame = new ShowGamearray();
    showgame.setLines(lines);
    showgame.setCols(cols);
    showgame.setPlayers(player1,player2);
    
    System.out.println("Spieler 1 heisst " + player1.getName() + " und benutzt das Zeichen " + player1.getSign() + "!");
    System.out.println("Spieler 2 heisst " + player2.getName() + " und benutzt das Zeichen " + player2.getSign() + "!");
    
    
    while (gewinn == 0) // while-Schleife so lange ausfuehren, bis jemand gewonnen hat (d.h. so lange bis "gewinn" auf "1" gesetzt wird)
    {
      if (gewinn == 0) // Player 1 ist an der Reihe
      {
        player1.set(gamearray);           // Setzen der Zahl 1, wenn Eingabe korrekt
        showgame.show(gamearray);         // Anzeigen des Spielfeldes
        gewinn=checker.check(gamearray,player1.getNumber());      // Überprüfung, ob Spieler 1 gewonnen hat nach Zug
        if (gewinn == 1)
        {
          System.out.println("Spieler " + player1.getName() + " hat gewonnen!!!");
        }
      }
      
      if (gewinn == 0) // Player 2 ist an der Reihe
      {
        player2.set(gamearray);
        showgame.show(gamearray);
        gewinn=checker.check(gamearray,player2.getNumber());
        if (gewinn == 1)
        {
          System.out.println("Spieler " + player2.getName() + " hat gewonnen!!!");
        }
      }
      
    }
    
  }
}

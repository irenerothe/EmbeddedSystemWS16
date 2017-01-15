public class CheckWinner
{
  private int lines;
  private int cols;
  
  public void setLines(int n)
  {
    lines = n;
  }
  
  public void setCols(int n)
  {
    cols = n;
  }

  public int check(int[][] array, int number)        // Zahl von Player wird geholt, der gespielt hat
  {
    int win=0;
    int count=0;
    int u=0;
    int v=0;
    
    // Teste jeweils eine Spalte (col):
    for (u=0; u<lines;u++)
    {
      for (v=0; v<cols; v++)                       //Spalten
      {
        if (array[u][v] == number)
        {
          count=count+1;                           // Anzahl der Werte des Players wird gezählt
        }
      }
      if (count == cols)                           // Wenn alle Spalten Werte vom Player enthalten --> gewonnen
      {
        win=1; // Wenn count der Dimension entspricht, d.h. alle Werte in einer Spalte vom gleichen Spieler gesetzt wurde => Spieler hat gewonnen!
      }
      else
      {
        count=0; // Counter zuruecksetzen
      }
    }
    
    // Teste jeweils eine Zeile (line):
    for (v=0; v<cols; v++)
    {
      for (u=0; u<lines;u++)
      {
        if (array[u][v] == number)
        {
          count=count+1;
        }
      }
      if (count == lines)
      {
        win=1; // Wenn count der Dimension entspricht, d.h. alle Werte in einer Zeile vom gleichen Spieler gesetzt wurde => Spieler hat gewonnen!
      }
      else
      {
        count=0; // Counter zuruecksetzen
      }
    }
    
    // Teste erste Diagonale:
    for (v=0; v<cols; v++)
    {
      if (array[v][v] == number)
      {
        count=count+1;
      }
    }
    if (count == cols)
    {
      win=1;
    }
    else
    {
      count=0; // Counter zuruecksetzen
    }
    
    // Teste zweite Diagonale:
    for (v=0; v<cols; v++)
    {
      if (array[v][cols-1-v] == number)
      {
        count=count+1;
      }
    }
    if (count == cols)
    {
      win=1;
    }
    else
    {
      count=0; // Counter zuruecksetzen
    }

    
    return win;
  }
}

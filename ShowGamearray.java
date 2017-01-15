public class ShowGamearray
{
  private int lines;
  private int cols;
  private Player player1;
  private Player player2;
  
  public void setLines(int n)
  {
    lines = n;
  }
  
  public void setCols(int n)
  {
    cols = n;
  }
  
  public void setPlayers(Player p1, Player p2)
  {
    player1=p1;
    player2=p2;
  }

  public void show(int[][] array)
  {
    int u=0;
    int v=0;
    
    for (u=0; u<lines;u++)
    {
      for (v=0; v<cols; v++)
      {
        if (array[u][v] == 0)
        {
          System.out.print("_" + " ");                           // wenn kein Wert in Array dann _
        }
        else if (array[u][v] == player1.getNumber())             // holt sich Zahl von Player 1 
        {
          System.out.print(player1.getSign() + " ");             // wenn Zahl von Player 1, dann X oder O wird in das Array geschrieben
        }
        else if (array[u][v] == player2.getNumber())
        {
          System.out.print(player2.getSign() + " ");
        }
      }
      System.out.println(" ");
    }
  }
    
}

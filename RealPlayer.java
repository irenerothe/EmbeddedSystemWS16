import java.util.Scanner;

public class RealPlayer extends Player
{
  public void set(int[][] array)
  {
    Scanner in = new Scanner(System.in); 
    int input_wrong = 1;
    while (input_wrong == 1)
    {
      System.out.println(name + ", bitte gebe eine Zeile ein: ");
      int line = in.nextInt();
      System.out.println(name + ", bitte gebe eine Spalte ein: ");
      int col = in.nextInt();
      int line_r=line-1;                    // da 0, 1, 2
      int col_r=col-1;                      // da 0, 1, 2
      if ( (line_r < lines) && (col_r < cols) && (array[line_r][col_r] == 0) )      //Prüfen, ob Spalte <3 und Zeile <3 und noch unbeschrieben (=0)
      {
        array[line_r][col_r]=number;     // Zahl des Player wird in das Array geschrieben
        input_wrong = 0; // Alle Eingaben richtig, while-Schleife beenden!
      }
      else
      {
        System.out.println("Fehlerhafte Eingabe!!!");
        input_wrong = 1;
      }
    }
  }
}

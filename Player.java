public abstract class Player // Abstrakt, da nur die Unterklasse z.B. VirtualPlayer oder RealPlayer, instanziiert werden darf
{
  protected String name;    // Variablen sind "protected",
  protected String sign;    // damit diese in allen abgeleiteten
  protected int number;   // Klassen, z.B. VirtualPlayer oder RealPlayer
  protected int lines;    // benutzt werden koennen.
  protected int cols;
  
  public void setLines(int n) // Set- und Get-Methoden sind public,
  {               // sodass die Variablen von aussen
    lines = n;          // gelesen und geschrieben werden koennen.
  }
  
  public void setCols(int n)
  {
    cols = n;
  }

  public void setName(String n)
  {
    name = n;
  }
  
  public String getName()
  {
    return name;
  }
  
  public void setSign(String s)
  {
    sign = s;
  }
  
  public String getSign()
  {
    return sign;
  }
  
  public void setNumber(int n)
  {
    number = n;
  }
  
  public int getNumber()
  {
    return number;
  }
  
  public void set(int[][] array)
  {
    // Wird in der jeweiligen Unterklasse, z.B. VirtualPlayer oder RealPlayer implementiert.
  }
}

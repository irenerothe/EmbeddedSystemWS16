import java.util.Random;

class Wuerfel{
  Random random;
  Wuerfel(){
    random = new Random();
  }
  public int wurf(){
    return (random.nextInt(6)+1);
  }
}
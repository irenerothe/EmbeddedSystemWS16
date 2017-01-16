
class Spieler {
  
    private int[] figur = {0,0,0,0};

//------------------------------------------------------------------------------  

// Constuctor
  Spieler() {
    figur[0] = 1;
    figur[1] = 0;
    figur[2] = 0;
    figur[3] = 0;
 }
  
//------------------------------------------------------------------------------ 

  public int getfigur(int cnt_figur)
  {
    if (cnt_figur<1 || cnt_figur>4) {
      System.out.println("ERROR ERROR ERROR in getfigur() !");
      return 0;
    }
    
    return figur[cnt_figur-1];
  }

//------------------------------------------------------------------------------ 

  public void setfigur(int cnt_figur, int pos)
  {
    if (cnt_figur<1 || cnt_figur>4 || pos<0 || pos>44) {
      System.out.println("ERROR ERROR ERROR in setfigur() !");
      return;
    }
    
    figur[cnt_figur-1] = pos;
  }

//------------------------------------------------------------------------------ 
  
  public void setfigurwurfel(int cnt_figur, int wurfel)
  {
    if (cnt_figur<1 || cnt_figur>4 || wurfel<1 || wurfel>6) {
      System.out.println("ERROR ERROR ERROR in setfigurwurfel() !");
      return;
    }
    
    figur[cnt_figur-1] += wurfel;
  }
  
  //------------------------------------------------------------------------------ 
  
}
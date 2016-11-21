import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 18.11.2016
  * @author 
  */

class Risiko_GUI extends JFrame {
  // Anfang Attribute
  
  private JLabel label_bildKarte, label_spielerzahl;
  private JButton btn_naechsteRunde;
  private JPanel panel_util, panel_map, panel_main;
  private JComboBox combox_anzahlSpieler;
  
  private GridLayout Layout = new GridLayout(3,0);
  
  Spielfeld spielfeld;
  
  // Ende Attribute
  
  public Risiko_GUI() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 800; 
    int frameHeight = 800;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Risiko");
    setResizable(true);
    //Container cp = getContentPane();
    //cp.setLayout(null);
    
    // Anfang Komponenten
    
    panel_main = new JPanel();
    panel_util = new JPanel();
    panel_map = new JPanel();
    
    panel_util.setLayout(Layout);
    
    Icon icon = new ImageIcon("Spielkarte.jpg");
    label_bildKarte = new JLabel(icon);
    panel_map.add(label_bildKarte);
    
    
    label_spielerzahl = new JLabel("Anzahl Spieler");
    panel_util.add(label_spielerzahl);
    
    String[] numofPlayers = {"2", "3", "4", "5" };
    combox_anzahlSpieler = new JComboBox(numofPlayers);
    panel_util.add(combox_anzahlSpieler);
    
    btn_naechsteRunde = new JButton("Fertig");
    //btn_naechsteRunde.setSize();
    panel_util.add(btn_naechsteRunde);
    
    panel_main.add(panel_map);
    panel_main.add(panel_util);  
     
    super.add(panel_main); 
    // Ende Komponenten
    super.pack();
    setVisible(true);
  } // end of public Risiko_GUI
  
  // Anfang Methoden
  // Ende Methoden
} // end of class Risiko_GUI

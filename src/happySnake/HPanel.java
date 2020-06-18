package happySnake;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class HPanel extends JPanel {
  public HPanel() {}
  public void paintComponent(Graphics g)
  {
	  super.paintComponent(g);
	  this.setBackground(Color.green);
  }
	
}

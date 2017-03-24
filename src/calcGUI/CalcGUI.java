package  calcGui;

import javax.swing.JFrame;

public class CalcGUI{
  public static void main(String[] args){
  CrazyCalcGUI crazy = new CrazyCalcGUI();
  crazy.setVisible(true);
  crazy.setSize(400,400);
  crazy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}

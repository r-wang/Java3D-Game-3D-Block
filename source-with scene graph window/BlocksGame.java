package blocksGame;

import javax.media.j3d.Transform3D;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * <p>Main class for the application</p>
 *
 * @author  Ruiqi Wang
 */

public class BlocksGame extends JFrame implements KeyListener
{
	 /**
     * Initializes the frame
     * 
     */

  public BlocksGame() 
  {
    super("Blocks Game");
    Container c = getContentPane();
    c.setLayout( new BorderLayout() );
    WrapBlocksGame w3d = new WrapBlocksGame();      
    c.add(w3d, BorderLayout.CENTER);

    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    pack();
    setResizable(false);    
    setVisible(true);
  } 


  /**
   * Main function for the application
   * 
   */
 
  public static void main(String[] args)
  { new BlocksGame(); }


public void keyPressed(KeyEvent ke) {
	// TODO Auto-generated method stub

	int xOffset = 0;
	int yOffset = 0;
	
    if(ke.getKeyCode() == KeyEvent.VK_UP)
            yOffset += 1;
    else if(ke.getKeyCode() == KeyEvent.VK_DOWN)
            yOffset -= 1;
    else if(ke.getKeyCode() == KeyEvent.VK_RIGHT)
            xOffset += 1;
    else if(ke.getKeyCode() == KeyEvent.VK_LEFT)
            xOffset -= 1;

    
}


public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}


public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

} // end of Checkers3D class

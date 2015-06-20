

/**
 * Write a description of class Window here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */




import graphics.ImageLoader;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
//JPanel class
public class Window extends JPanel
{

    int x=0;
    int y=0;
    private Game blah;

    private int screenWidth;
    private int screenHeight;
    private BufferedImage mainScreen;
    
    public Window(Game here)
    {
    	init();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int)(screenSize.getWidth()/2);
        screenHeight = (int)(screenSize.getHeight()/1.15);
        
        
        
        setLayout(null);
        blah = here;
        setBackground(new Color(0,0,0));

        setVisible(true);
        
        setKeys();

        
    }

    public void setKeys()
    {
        Action space = new AbstractAction()
        {
                public void actionPerformed(ActionEvent e)
                {
                    setVisible(false);
                    changeStart(blah);
                }
        };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "space");
        getActionMap().put("space",space);
    }

    public void changeStart(Game here)
    {
        here.setStart(true);
    }
    
    public void init(){
    	ImageLoader loader = new ImageLoader();
    	mainScreen = loader.load("/Window Pic.png");
    }
    public void paint(Graphics g){
    	//mainScreen.getScaledInstance((int)(mainScreen.getWidth()/800), (int)(mainScreen.getHeight()/800), Image.SCALE_FAST);
    	//g.drawImage(mainScreen, 100, 0, this);
    	g.drawImage(mainScreen, 0, 0, screenWidth, screenHeight, this);
    }
    

    	
}


    
    
    
    
    

 /*   public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        g.setColor(Color.getHSBColor(34,198,115));
        

        //g.fillRoundRect(180,100,440,600,20,20);

        g.fillRoundRect((int)(180*screenWidth/800),(int)(100*screenHeight/800),(int)(440*screenWidth/800),(int)(screenHeight/1.15),(int)(20*screenWidth/800),(int)(20*screenHeight/800));

        String hey = "ROAD_RAGE";
        Font stringFont = new Font("Arial Unicode", Font.BOLD, 48);
        g.setFont(stringFont);
        g.setColor(new Color(206,32,6));
        g.drawString(hey, (int)(235*screenWidth/800), (int)(200*screenHeight/800));

        String directions = "This is a two player";
        g.setFont(new Font("Arial Unicode MS", Font.BOLD, 35));

        g.drawString(directions, 250, 260);
        String directions2 = "racing game";
        g.drawString(directions2, 305, 285);
        g.setFont(new Font("Arial Unicode MS", Font.BOLD, 30));        
        String directions3 = "Each player is given three lives";
        g.drawString(directions3, 192, 330);
        g.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));        
        directions3 = "If you hit any BLUE ROCKS"; 
        g.drawString(directions3,250, 355);
        g.drawString("or GREEN SHRUBS,", 280, 380);
        g.drawString("you lose a life.", 310, 405);
        g.drawString("Going in the grass will slow you down.", 188, 435);
        directions3 = "The player to live the longest wins";
        g.drawString(directions3, 212, 650);
        directions3 = "Player 1 Will Use";
        g.drawString(directions3, 195, 470);
        directions3 = "Player 2 Will Use";
        g.drawString(directions3, 415, 470);
        directions3 = "A    S    D";
        g.setFont(new Font("Arial Unicode MS", Font.BOLD, 30));
        g.drawString(directions3, 225, 575);
        g.drawString("      W", 225, 540);
        g.drawString("       ⬆", 460, 540);
        directions3 = "⬅    ⬇   ➡";
        g.drawString(directions3, 455, 575);

        /*g.drawString(directions, (int)(250*screenWidth/800),(int)( 260*screenHeight/800));
        String directions2 = "racing game";
        g.drawString(directions2, (int)(305*screenWidth/800), (int)(285*screenHeight/800));
        g.setFont(new Font("Arial Unicode MS", Font.BOLD, 30));        
        String directions3 = "Each player is given three lives";
        g.drawString(directions3, (int)(192*screenWidth/800), (int)(330*screenHeight/800));
        g.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));        
        directions3 = "If you hit any BLUE ROCKS"; 
        g.drawString(directions3,(int)(250*screenWidth/800), (int)(355*screenHeight/800));
        g.drawString("or GREEN SHRUBS,", (int)(280*screenWidth/800), (int)(380*screenHeight/800));
        g.drawString("you lose a life.", (int)(310*screenWidth/800), (int)(405*screenHeight/800));
        g.drawString("Going in the grass will slow you down.", (int)(188*screenWidth/800), (int)(435*screenHeight/800));
        directions3 = "The player to live the longest wins";
        g.drawString(directions3, (int)(212*screenWidth/800), (int)(650*screenHeight/800));
        directions3 = "Player 1 Will Use";
        g.drawString(directions3, (int)(195*screenWidth/800), (int)(470*screenHeight/800));
        directions3 = "Player 2 Will Use";
        g.drawString(directions3, (int)(415*screenWidth/800), (int)(470*screenHeight/800));
        directions3 = "A    S    D";
        g.setFont(new Font("Arial Unicode MS", Font.BOLD, 30));
        g.drawString(directions3, (int)(225*screenWidth/800), (int)(575*screenHeight/800));
        g.drawString("      W", (int)(225*screenWidth/800), (int)(540*screenHeight/800));
        g.drawString("       ⬆", (int)(460*screenWidth/800), (int)(540*screenHeight/800));
        directions3 = "⬅    ⬇   ➡";
        g.drawString(directions3, (int)(455*screenWidth/800), (int)(575*screenHeight/800));

        
       
        
        directions3 = "Press the spacebar to begin";
        g.setFont(new Font("Arial Unicode MS", Font.BOLD, 35));

        g.drawString(directions3, 185, 685);

        g.drawString(directions3, (int)(185*screenWidth/800), (int)(685*screenHeight/800));

        setKeys();

           
            
           
    }*/

    

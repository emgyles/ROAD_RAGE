
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Game
{
    public boolean start;
    public Road road;
    public int here;

    public static int difficulty1;
    public static int difficulty2 = 3;
    public static String playerOneName;
    public String player1Name;
    public static String playerTwoName;
    public String player2Name;
    public static int car1Color;
    public static int car2Color;
    private int screenWidth;
    private int screenHeight;
    public Game()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int)(screenSize.getWidth()/2);
        screenHeight = (int)(screenSize.getHeight()/1.15);
        here = 1;
        

        
        JFrame test = new JFrame();
        test.setResizable(false);
        test.setSize(screenWidth,screenHeight);
        test.setLocationRelativeTo(null);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setTitle("Some sort of Racing Game");

        Window mainScreen = new Window(this);
        test.add(mainScreen);
        test.setVisible(true);
        start = false;
        
        while(!start){
        	try{
        		Thread.sleep(5);
        	}catch(Exception e){
        	}
        }
        test.remove(mainScreen);
        test.setVisible(false);
        
        playerOneName = JOptionPane.showInputDialog(null,"Player 1: Enter Name","Car 1", JOptionPane.PLAIN_MESSAGE);
        player1Name = playerOneName;
        //Shortcut
      if((player1Name.equals("buggy")))
        {
            difficulty1 = 2;
            difficulty2 = 2;
            player1Name = "Player 1";
            player2Name = "Player 2";
        }
      
      //Displays JOptionPanes to ask for names/ difficulties
        Object[] levels = {"Extremely Difficult", "Medium", "Easy"};
        Object[] carColors = {"Red", "Blue", "Green"};
        car1Color = JOptionPane.showOptionDialog(null, "What color car would you like?", "Car 1 Color", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, carColors, carColors[0]);
        difficulty1 = JOptionPane.showOptionDialog(null, "Player 1: Choose your Difficulty", "Car 1",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,null, levels, levels[0]);
        playerTwoName = JOptionPane.showInputDialog(null,"Player 2: Enter Name","Car 2", JOptionPane.PLAIN_MESSAGE);
        player2Name = playerTwoName;
        car2Color = JOptionPane.showOptionDialog(null, "What color car would you like?", "Car 2 Color", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, carColors, carColors[0]);
        difficulty2 = JOptionPane.showOptionDialog(null, "Player 2: Choose your Difficulty", "Car 2",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,null, levels, levels[0]);

        //Only creates road once the last question has been answered
        if(difficulty2 != 3){
       

        Car a = new Car(180*screenWidth/800, 670*screenHeight/800, player1Name, car1Color);
        Car b = new Car(580*screenWidth/800, 670*screenHeight/800, player2Name, car2Color);
        road = new Road(a, b, this);
        road.start();
        test.setVisible(true);
        test.add(road);
       
        for(;;)
        {
            while(here == 1)
            {
                road.update();
                road.repaint();

                for(Car c:Road.cars){
                    c.updatePosition();
                }
                try{
                    Thread.sleep(40); //50
                } catch (Exception e){

                }
            }
            
            try{
                Thread.sleep(500);
            }catch(Exception e){
            }
            Object[] yesNo = {"No", "Yes"};

            int endGame = JOptionPane.showOptionDialog(null, "Would you like to play again?", "Restart", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, yesNo, yesNo[0]);

            if(endGame == 1)
            {
                test.dispose();
                Game secondTime = new Game();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Thanks for Playing!", "Road_Rage", JOptionPane.PLAIN_MESSAGE);
                test.dispose();
                System.exit(0);
            }
            
        } 

    }
    }

    public void setStart(boolean a)
    {
        start = a;
    }

    public void setHere(int i)
    {
        here = i;
    }

    public double getScreenHeight()
    {
        return screenHeight;
    }

    public double getScreenWidth()
    {
        return screenWidth;
    }
}
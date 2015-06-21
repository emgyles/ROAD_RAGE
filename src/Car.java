
/**
 * Write a description of class Car here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.*;

public class Car //extends JPanel
{   
    //x and y coordinates of the "car"
    private int x;
    private int y;
    //"Speed" at which the car is moving
    private int xVelocity;
    private int yVelocity;
    private int lives;
    private Rectangle c;
    private String name;
    private int color;
    private int a;
    private int b;
    private int screenWidth;
    private int screenHeight;

    public Car(int x, int y, String name, int carColor)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int)(screenSize.getWidth()/2);
        screenHeight = (int)(screenSize.getHeight()/1.15);
        
        
        color = carColor;
        
        
        this.x = x;
        this.y = y;
        this.name = name;
        lives = 3;
        //a and b reset the car to original spot after death
        a = x;
        b = y;
        //c is the Rectangle Hitbox
        //c = new Rectangle(x, y, (50*screenWidth/800), (70*screenHeight/800));
        c = new Rectangle(x, y, (55*screenWidth/800), (109*screenHeight/800));
        
    }
    public int getColor()
    {
    	return color;	
    }
    public String getName()
    {
        return name;
    }
    
    
    public Rectangle getRecta()
    {   
        return c;
    }   

    public int getLives()
    {
        return lives;
    }

    public void setLives()
    {
        lives--;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void reset()
    {
        x = a;
        y = b;
    }

    public void updatePosition(){
        x += xVelocity;     
        y += yVelocity;
        updateHitbox();
    }

    public void setXVelocity(int i)
    {
        xVelocity = i;
    }

    public void setYVelocity(int i)
    {
        yVelocity = i;
    }
    
    public int getXVelocity()
    {
        return xVelocity;
    }

    public int getYVelocity()
    {
        return yVelocity;
    }
      
    public void updateHitbox()
    {
        c = new Rectangle(x, y, (55*screenWidth/800), (109*screenHeight/800));
    }
}
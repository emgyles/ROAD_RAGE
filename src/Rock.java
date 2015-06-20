    
/**
 * Write a description of class Rocks here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
public class Rock
{
    private int x;
    private int y;
    private Rectangle rock;
    private int alm;
    private int add;
    private int origY;
    private int screenWidth;
    private int screenHeight;
    public Rock(int random, int add, int y)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int)(screenSize.getWidth()/2);
        screenHeight = (int)(screenSize.getHeight()/1.15);
        
        
        
        
        
        this.alm = (int)(Math.random()*(170*screenWidth/800) + (100*screenWidth/800));
        this.add = add;
        this.x = random + add;
        this.y = y;
        origY = y;
        rock = new Rectangle(x, y, 40*screenWidth/800, 40*screenHeight/800);
    }
    
    public Rectangle getRecta()
    {
        return rock;
    }
       
    public void updateRocks()
    {
        y += 25;
    }
    public int getY()
    {
        return y;
    }
    public int getX()
    {
        return x;
    }
    public void updateAlm()
    {
        alm = (int)(Math.random()*(170*screenWidth/800) + (100*screenWidth/800));
    }
    public void setX()
    {
        updateAlm();
        x = alm + add;
    }
    public void setY()
    {
        y = origY;
    }
    public void updateHitbox(){
        rock = new Rectangle(x,y,40*screenWidth/800,40*screenHeight/800);
    }
    
}
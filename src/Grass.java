
/**
 * Write a description of class Grass_Clump here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
public class Grass
{
    private int x;
    private int y;
    private int alm;
    private int origY;
    private Rectangle grass;
    private int screenWidth;
    private int screenHeight;
    public Grass(int whichGrass, int i, int y)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int)(screenSize.getWidth()/2);
        screenHeight = (int)(screenSize.getHeight()/1.15);
        origY = y;
        if (i ==0)
        {
            if(whichGrass == 0)
            {
                x = (int)(Math.random()*(80*screenWidth/800));
            }
            else 
            {
                x = (int)(Math.random() * (80*screenWidth/800) +(300*screenWidth/800));                
            }
        }
        else
        {
            if(whichGrass == 0)
            {
                x = (int)(Math.random()*(80*screenWidth/800) + (400*screenWidth/800));
            }
            else 
            {
                x = (int)(Math.random() * (80*screenWidth/800) + (700*screenWidth/800));                
            }
        }
        this.y = y;
        alm = 0;
        grass = new Rectangle(x,y, 25*screenWidth/800, 25*screenHeight/800);
    }
    public int getX()
    {
        return x;
    }
    public void updateGrass()
    {
        y += 15;
    }
    public void updateAlm(int i, int whichBloop)
    {
        if (i ==0)
        {
            if(whichBloop == 0)
            {
                alm = (int)(Math.random()*(80*screenWidth/800));
            }
            else 
            {
                alm = (int)(Math.random() * (80*screenWidth/800) +(300*screenWidth/800));                
            }
        }
        else
        {
            if(whichBloop == 0)
            {
                alm = (int)(Math.random()*(80*screenWidth/800) + (400*screenWidth/800));
            }
            else 
            {
                alm = (int)(Math.random() * (80*screenWidth/800) + (700*screenWidth/800));                
            }
        }
    }
    public void setY()
    {
        y = origY;
    }
    public int getY()
    {
        return y;
    }
    public Rectangle getRecta()
    {
        return grass;
    }
    public int getOrigY()
    {
        return origY;
    }
    public void updateHitbox()
    {
        grass = new Rectangle(x,y, 25*screenWidth/800,25*screenHeight/800);
    }
    public void setX(int i, int whichBloop)
    {
        updateAlm(i, whichBloop);
        x = alm;
    }
}

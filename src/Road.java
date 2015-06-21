
/**
 * Write a description of class Road here.

 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import graphics.ImageLoader;
import javax.swing.*;
import java.awt.image.BufferedImage;

import java.awt.*;
import java.awt.event.*;
public class Road extends JPanel
{
    private int screenWidth;
    private int screenHeight;
    int x =0;
    int y =0;
    int y2 ;

    private long startTime;
    private String endTime;
    private String currentTime;
    

    private int SLOW_SPEED = 2;
    private int FAST_SPEED = 10;
    private final Rectangle[] SLOW_SPOTS;

    public Rock[] rocksLeft;
    public Rock[] rocksRight;
    
    public Grass grassLeft;
    public Grass grassRight;
    
    
    private BufferedImage cMoney;
    static Car[] cars;
    private Game game;
    
    
    public Road(Car a, Car b, Game g)
    {
    	game = g;
    	
        /*if(Game.player1Name.equals("buggy"))
        {
            rocksLeft = new Rock[1];
            rocksRight = new Rock[1];
        }*/
        if(Game.difficulty1 == 2)
        {
            rocksLeft = new Rock[1];
        }
        else if(Game.difficulty1 == 1)
        {
            rocksLeft = new Rock[4];
        }
        else
        {
            rocksLeft = new Rock[10];
        }
        
        if(Game.difficulty2 == 2)
        {
            rocksRight = new Rock[1];
        }
        else if(Game.difficulty2 == 1)
        {
            rocksRight = new Rock[4];
        }
        else
        {
            rocksRight = new Rock[10];
        }
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int)(screenSize.getWidth()/2);
        screenHeight = (int)(screenSize.getHeight()/1.15);
        SLOW_SPOTS  = new Rectangle[3];
        SLOW_SPOTS[0] = new Rectangle(0, 0, (100*screenWidth/800), screenHeight);
        SLOW_SPOTS[1] = new Rectangle((300*screenWidth/800), 0, (200*screenWidth/800), screenHeight*100);
        SLOW_SPOTS[2] = new Rectangle((700*screenWidth/800), 0, (100*screenWidth/800), screenHeight*100);
        y2 = screenHeight/2;
        
        
        
        
        setLayout(null);
        setSize(screenWidth,screenHeight);
        setBackground(new Color(72, 129, 48));
        cars = new Car[2];
        cars[0] = a;
        cars[1] = b;
        setUpKeys();
        int rando = (int)(Math.random()*(170*screenWidth/800) + (100*screenWidth/800));
        for(int i = 0; i < rocksLeft.length; i++)
        {
            rando = (int)(Math.random()*(170*screenWidth/800) + (100*screenWidth/800));
            rocksLeft[i] = new Rock(rando, 0, (-screenHeight / rocksLeft.length) * i);
        }
        for (int i = 0; i < rocksRight.length; i++)
        {
            rando = (int)(Math.random()*(170*screenWidth/800) + (100*screenWidth/800));
            rocksRight[i] = new Rock(rando, screenWidth/2, (-screenHeight / rocksRight.length) * i);
        }
        
        int whichGrass = (int)(Math.random()*2);
        grassLeft = new Grass(whichGrass, 0,-screenHeight/2);
        grassRight = new Grass(whichGrass, 1, -screenHeight/2);
        
        
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //Middle Line
        g.setColor(new Color(0,0,0));
        g.fillRect(screenWidth/2-(10*screenWidth/800),0,(10*screenWidth/800),screenHeight);
        //Two Roads
        g.setColor(new Color(59,59,59));
        g.fillRect(screenWidth/8,0,screenWidth/4,screenHeight);
        
        g.fillRect(5*screenWidth/8,0,screenWidth/4,screenHeight);
        lines(g);
        rocks(g);
        cars(g);
        lives(g);
        grass(g);
    }

     
    public void lives(Graphics g)
    {
        //CHECKS TO SEE IF CAR HITS ANY ROCKS
        boolean isHittingRockLeft = false;
        boolean isHittingRockRight = false;
        for (Rock rock : rocksLeft)
        {
            if (cars[0].getRecta().intersects(rock.getRecta()))
                isHittingRockLeft = true;
        }
        for (Rock rock : rocksRight)
        {
            if(cars[1].getRecta().intersects(rock.getRecta()))
                isHittingRockRight = true;
        }
        
        //CHECK TO SEE IF CAR IS HITTING GRASS SHRUBS
        boolean isHittingGrassLeft = false;
        if(cars[0].getRecta().intersects(grassLeft.getRecta())) 
             isHittingGrassLeft = true;
        boolean isHittingGrassRight = false;
        if(cars[1].getRecta().intersects(grassRight.getRecta()))
              isHittingGrassRight = true;
          
             
        //SEE IF CAR IS OUT OF BOUNDS      
        Rectangle top = new Rectangle(-50, -50 , screenWidth *3, 50);
        Rectangle bottom = new Rectangle(-50, screenHeight + (70*screenHeight/800), screenWidth *3, 50);
        Rectangle left = new Rectangle(-50, 0, 50, screenHeight);
        Rectangle right = new Rectangle(screenWidth, 0, 50, screenHeight);
        
        //PAINTS ENDSCREEN IF SOMEONE LOSES
        if (cars[1].getLives() == 0)
        {
            endScreen(g, 1);
        }
        if (cars[0].getLives() == 0)
        {
            endScreen(g, 0);
        }
        
        //RIGHT CAR LIVES
        if (cars[1].getRecta().intersects(right) || (cars[1].getX() == screenWidth/2) || (cars[1].getRecta().intersects(top)) || (cars[1].getRecta().intersects(bottom)))
        {
            cars[1].setLives();
            cars[1].reset();
        }
        if(isHittingRockRight)
        {
            cars[1].setLives();
            for (Rock rock : rocksRight)
            {
                rock.setX();
                rock.setY();
            }
            cars[1].reset();
        }
        if(isHittingGrassRight)
        {
            cars[1].setLives();
            cars[1].reset();
            grassRight.setX(1, (int)(Math.random()*2));
            grassRight.setY();
            grassRight.updateHitbox();
        }
        
        //LEFT CAR LIVES
        if ((cars[0].getX()+(55*screenWidth/800) == screenWidth/2) || cars[0].getRecta().intersects(left) ||  (cars[0].getRecta().intersects(top)) || (cars[0].getRecta().intersects(bottom)))
        {
            cars[0].setLives();
            cars[0].reset();
            grassLeft.updateHitbox();
        }
        if(isHittingRockLeft)
        {
            cars[0].setLives();
            for (Rock rock : rocksLeft)
            {
                rock.setX();
                rock.setY();
            }
            cars[0].reset();
        }
        if(isHittingGrassLeft)
        {
            cars[0].setLives();
            cars[0].reset();
            grassLeft.setX(0, (int)(Math.random()*2));
            grassLeft.setY();
            grassLeft.updateHitbox();
        }
        
        //PAINTING LIVES
        int x = 25*screenWidth/800;
        int x2 = 430*screenWidth/800;
        for (int i = 0; i < cars[0].getLives(); i++)
        {
            g.fillRect(x, 25*screenWidth/800, 25*screenHeight/800, 25*screenHeight/800);
            x += 30*screenWidth/800;
        }
        for (int i = 0 ; i < cars[1].getLives(); i++)
        {
            g.fillRect(x2, 25*screenWidth/800, 25*screenHeight/800, 25*screenHeight/800);
            x2 += 30*screenWidth/800;
        }
    }

    public void endtime(Graphics g, int i)
    {
        if( i ==1)
        {
            endTime = ((((System.currentTimeMillis() - startTime) / 1000) + "." + ((System.currentTimeMillis() - startTime) % 1000)));
            g.setFont(new Font("Courier New", Font.BOLD, 30));
            g.drawString("YOU LOST IN", (int)(500*screenWidth/800), (int)(370*screenHeight/800)); 
            g.drawString(endTime, (int)(5*screenWidth/8), (int)(4*screenHeight/8));
            g.drawString("SECONDS", (int)(52*screenWidth/80), (int)(43*screenHeight/80));
        }
        else
        {
            endTime = ((((System.currentTimeMillis() - startTime) / 1000) + "." + ((System.currentTimeMillis() - startTime) % 1000)));
            g.setFont(new Font("Courier New", Font.BOLD, 30));
            g.drawString("YOU LOST IN", screenWidth/8, (int)(370 *screenHeight/800)); 
            g.drawString(endTime, (int)(130*screenWidth/800),(int)(400*screenHeight/800));
            g.drawString("SECONDS", (int)(120*screenWidth/800), (int)(430*screenHeight/800));
        }
    }
    

    public void update()
    {
        y += 20;
        y2 += 20;
        for(Rock rock : rocksLeft)
        {
            rock.updateRocks();
            rock.updateHitbox();
        }
        for (Rock rock : rocksRight)
        {
            rock.updateRocks();
            rock.updateHitbox();
        }
        grassLeft.updateHitbox();
        grassLeft.updateGrass();
        grassRight.updateHitbox();
        grassRight.updateGrass();
        for (Car car : cars)
        {
            car.updateHitbox();
        }
    }

    public void start()
    {
        startTime = System.currentTimeMillis();
    }


    public void cars(Graphics g)
    {
        g.setColor(new Color(203, 24, 20));

        for (Car car : cars)
        {
            if (car.getXVelocity() < 0)
                car.setXVelocity(-getCarSpeed(car));
            else if (car.getXVelocity() > 0)
                car.setXVelocity(getCarSpeed(car));
            
            if (car.getYVelocity() < 0)
                car.setYVelocity(-getCarSpeed(car));
            else if (car.getYVelocity() > 0)
                car.setYVelocity(getCarSpeed(car));
                
            //g.fillRect(car.getX(), car.getY(), 50*screenWidth/800, 70*screenHeight/800);
            init();
            g.drawImage(cMoney, car.getX(), car.getY(),55*screenWidth/800, 109*screenHeight/800, null); 
            
        }
    }
    public void init(){
    	
    	ImageLoader loader = new ImageLoader();
    	cMoney = loader.load("/red car.png");
    }
    
    public void grass(Graphics g)
    {
        grassLeft.updateHitbox();
        grassRight.updateHitbox();
        if(grassLeft.getY() < screenHeight)
        {
            g.setColor(new Color(67, 240, 61));
            g.fillRect(grassLeft.getX(), grassLeft.getY(), 20*screenWidth/800, 20*screenHeight/800);
            g.fillRect(grassRight.getX(), grassRight.getY(), 20*screenWidth/800, 20*screenHeight/800);
        }
        else
        {
            grassLeft.setX(0, (int)(Math.random()*2));
            grassLeft.setY();
            grassRight.setX(1, (int)(Math.random()*2));
            grassRight.setY();
        }
    }

    public void rocks(Graphics g)
    {
        for(Rock rock : rocksLeft)
        {
            rock.updateHitbox();

            if(rock.getY() < screenHeight)
            {
                g.setColor(new Color(58,167,203));
                g.fillRect(rock.getX(),rock.getY(), 40*screenWidth/800,40*screenHeight/800);
            }
            else
            {
                rock.setX();
                rock.setY();
            }
        }
        for (Rock rock : rocksRight)
        {
            rock.updateHitbox();

            if(rock.getY() < screenHeight)
            {
                g.setColor(new Color(58,167,203));
                g.fillRect(rock.getX(),rock.getY(), 40*screenWidth/800,40*screenHeight/800);
            }
            else
            {
                rock.setX();
                rock.setY();
            }
        }
    }

    public void lines(Graphics g)
    {
        if(y < screenHeight && y2 < screenHeight)
        {
            g.setColor(new Color(230,188, 21));
            g.fillRect(192*screenWidth/800,y,16*screenWidth/800,40*screenHeight/800);
            g.fillRect(192*screenWidth/800,y2,16*screenWidth/800,40*screenHeight/800);
            g.fillRect(592*screenWidth/800,y,16*screenWidth/800,40*screenHeight/800);
            g.fillRect(592*screenWidth/800,y2,16*screenWidth/800,40*screenHeight/800);
        }
        else if (y < screenHeight && y2 >= screenHeight)
        {
            setY2(0);
        }
        else if (y >= screenHeight && y2 < screenHeight)
        {
            setY(0);
        }

    }

    public void setY(int i)
    {
        y = i;
    }

    public void setY2(int i)
    {
        y2 = i; 
    }

    public void endScreen(Graphics g, int i)
    {
        game.setHere(0);
        setBackground(new Color(0,0,0));
        g.setColor(new Color(255,255,255)); 
        g.fillRect(395*screenWidth/800, 0, 10*screenWidth/800, screenHeight);
        
        Font stringFont = new Font("Courier New", Font.BOLD, 28);
        g.setFont(stringFont);
        if (i == 1)
        {
           
            g.drawString("CONGRATULATIONS!", 75*screenWidth/800,100*screenHeight/800);
            g.drawString(cars[0].getName(), 120*screenWidth/800, 200*screenHeight/800);

            g.drawString("YOU WON.", 120*screenWidth/800, 370*screenHeight/800);
            g.drawString("SORRY.", 540*screenWidth/800, 100*screenHeight/800);
            g.drawString(cars[1].getName(), 520*screenWidth/800, 200*screenHeight/800);
            
            endtime(g, i);
        }
        else
        {
           
            g.drawString("CONGRATULATIONS!", 480*screenWidth/800,100*screenHeight/800);
            g.drawString(cars[1].getName(), 520*screenWidth/800, 200*screenHeight/800);
            g.drawString("YOU WON.", 520*screenWidth/800, 370*screenHeight/800);
            g.drawString("SORRY.", 160*screenWidth/800, 100*screenHeight/800);
            g.drawString(cars[0].getName(), 120*screenWidth/800, 200*screenHeight/800);
            
            endtime(g, i);
        }
        
        //g.drawString("Press SPACE to Play Again", 200, 700);
    }

    public void setUpKeys()
    {      
        // ******************PLAYER 1 BINDINGS- ADSW***************************

        //*********Left and Right*******
        Action left1 = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e){
                    cars[0].setXVelocity(-getCarSpeed(cars[0]));
                }
            };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "left1");
        getActionMap().put("left1", left1);

        Action left1R = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e){
                    cars[0].setXVelocity(0);
                }
            };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released A"), "left1R");
        getActionMap().put("left1R", left1R);

        Action right1 = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e){
                    cars[0].setXVelocity(getCarSpeed(cars[0]));
                }
            };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "right");
        getActionMap().put("right", right1);

        Action right1R = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e){
                    cars[0].setXVelocity(0);
                }
            };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released D"), "right1R");
        getActionMap().put("right1R", right1R);

        //**********Up and Down***********

        Action up1 = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e){
                    cars[0].setYVelocity(-getCarSpeed(cars[0]));
                }
            };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "up1");
        getActionMap().put("up1", up1);

        Action up1R = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e){
                    cars[0].setYVelocity(0);
                }
            };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released W"), "up1R");
        getActionMap().put("up1R", up1R);

        Action down1 = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e){
                    cars[0].setYVelocity(getCarSpeed(cars[0]));
                }
            };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "down1");
        getActionMap().put("down1", down1);

        Action down1R = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e){
                    cars[0].setYVelocity(0);
                }
            };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released S"), "down1R");
        getActionMap().put("down1R", down1R);

        //*****************************PLAYER 2 BINDINGS - Arrows********************
        //*****************Left and Right*************************
        Action left2 = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e){
                    cars[1].setXVelocity(-getCarSpeed(cars[1]));
                }
            };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "left2");
        getActionMap().put("left2", left2);

        Action left2R = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e){
                    cars[1].setXVelocity(0);
                }
            };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released LEFT"), "left2R");
        getActionMap().put("left2R", left2R);

        Action right2 = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e){
                    cars[1].setXVelocity(getCarSpeed(cars[1]));
                }
            };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "right2");
        getActionMap().put("right2", right2);

        Action right2R = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e){
                    cars[1].setXVelocity(0);
                }
            };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released RIGHT"), "right2R");
        getActionMap().put("right2R", right2R);

        //********************Up and Down********************

        Action up2 = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e){
                    cars[1].setYVelocity(-getCarSpeed(cars[1]));
                }
            };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "up2");
        getActionMap().put("up2", up2);

        Action up2R = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e){
                    cars[1].setYVelocity(0);
                }
            };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released UP"), "up2R");
        getActionMap().put("up2R", up2R);

        Action down2 = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e){
                    cars[1].setYVelocity(getCarSpeed(cars[1]));
                }
            };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "down2");
        getActionMap().put("down2", down2);

        Action down2R = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e){
                    cars[1].setYVelocity(0);
                }
            };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released DOWN"), "down2R");
        getActionMap().put("down2R", down2R);

        //******
        Action space = new AbstractAction()
            {
                public void actionPerformed(ActionEvent e)
                {
                   // Tester.ron2k15 = 1;
                   System.exit(0);
                }
            };
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "space");
        getActionMap().put("space",space);

    }

    private int getCarSpeed(Car car)
    {
        for(Rectangle i : SLOW_SPOTS)
        {
            if (car.getRecta().intersects(i))
                return SLOW_SPEED;
        }

        return FAST_SPEED;
    }
}
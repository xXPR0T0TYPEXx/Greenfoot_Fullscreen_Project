import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import lang.stride.Utility;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JPanel;
import greenfoot.core.WorldHandler;
/**
 * World environment
 * 
 * @author Ferhat
 * @version 1.0
 * @last updated 2019-02-27
 */
public class Sky extends World
{
    public static int widthScreenSize = 800;  // Default width size
    public static int heightScreenSize = 600;  // Default height size
    public static boolean fullscreenON = false;
    public boolean isMainMenu;
    public int spawnTimeClouds, spawnTimeSeals;
    public String getKey;
    public Fullscreen fullscreen;
    public boolean isGameOver = false;
    public Bar healthbar = new Bar("", "", 1, 1);
    public GreenfootImage image = new GreenfootImage(1,1);
    public JPanel panel = WorldHandler.getInstance().getWorldCanvas();
    public Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(image.getAwtImage(), new Point(0, 0), "");
    /**
     * Main constructor. Mainly used for the main menu of this game.
     */
    public Sky()
    {
        super(getWidthScreenSize(), getHeightScreenSize(), 1, false);  // set world edge to 'false' will create an infinite world
        isMainMenu = true;
        mainmenu();
        setBackground("blue_bg.jpg");
        for (final int clouds : lang.stride.Utility.makeRange(1, 4)) 
        {
            addObject( new Cloud("cloud.png", 0), Greenfoot.getRandomNumber(getHeight()), Greenfoot.getRandomNumber(getHeight()));
        }
        setPaintOrder(Label.class,Cloud.class);
    }

    /**
     * Initialize world.
     * 
     * @param 'bg': set world background from resource(images) e.g. "background.jpg"
     */
    public Sky(String bg)
    {
        super(getWidthScreenSize(), getHeightScreenSize(), 1, false); // set world edge to 'false' will create an infinite world
        isMainMenu = false;
        addObject( new FlyingFish("fish.png", "fish2.png", 7, true), getWidth()/2, getHeight()/2);
        addObject( healthbar, (getWidth()/2),(getHeight()*1/16));
        addObject( new Timer("", 30, new Color(100, 225, 255, 255), new Color(0, 0, 0, 0)), (getWidth()*1/8),(getHeight()*1/16));
        healthbar.setBarWidth(200); healthbar.setBarHeight(10); healthbar.setBreakPercent(2); healthbar.setMaximumValue(5);
        healthbar.setSafeColor(new Color(100, 225, 255, 255)); healthbar.setTextColor(new Color(100, 225, 255, 255));
        healthbar.setShowTextualUnits(false);
        setBackground(bg);
        for(Label l : getObjects(Label.class))
        {
            removeObject(l);
        }
        setPaintOrder(Bar.class,Label.class,Timer.class,FlyingFish.class,FlyingSeal.class,Ball.class,Cloud.class);
        for (final int clouds : lang.stride.Utility.makeRange(1, 7)) 
        {
            addObject( new Cloud("cloud.png", Greenfoot.getRandomNumber(2)+1), Greenfoot.getRandomNumber(getHeight()), Greenfoot.getRandomNumber(getHeight()));
        }
    }

    /**
     * Act - do whatever the Sky wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        this.getKey = Greenfoot.getKey();
        if(isMainMenu)
        {
            if("f".equals(getKey) && !getFullscreenON())
            {
                fullscreen = new Fullscreen();
                Greenfoot.setWorld(new Sky("blue_bg.jpg"));
                fullscreenON = true;
            } 
            else if("enter".equals(getKey))
            {
                isMainMenu = false;
                Greenfoot.setWorld(new Sky("blue_bg.jpg"));
            }
            else if("escape".equals(getKey))
            {
                System.exit(0);
            }
        }
        else
        {
            spawnClouds(2, 1, 5000);
            if(!getIsGameOver())
            {
                spawnFlyingSeals(2, 2, 2500);
                getFlyingFishHealth();
            }
            GameOver();
        }
    }

    /**
     * Initialize main menu
     */
    public void mainmenu()
    {
        addObject( new Label("Flying Fish!", 70, new Color(50,255,50,255), new Color(0,0,0,0)), getWidth()/2, getHeight()*1/6);
        if(!getFullscreenON())
        {
            addObject( new Label("Press:\n\n- Enter to play\n- F to pay respect. Just kidding lol. Press F to play in full screen\n - ESC to exit", 30, new Color(150,255,255,255), new Color(0,0,0,0)), getWidth()/2, getHeight()/2);
        }else addObject( new Label("Press:\n\n- Enter to play\n- ESC to exit", 35, new Color(150,255,255,255), new Color(0,0,0,0)), getWidth()/2, getHeight()/2);
    }

    /**
     * Spawns clouds from a specific amount of time.
     * 
     * @param 'additionalClouds': add more random amount of clouds to addCloud
     * @param 'addCloud': decides how many clouds should spawn
     * @param 'resetTime': reset spawn time. The lower reset time is the faster clouds being added to world
     */
    public void spawnClouds(int additionalClouds, int addCloud, int resetTime)
    {
        spawnTimeClouds += 15;
        if(spawnTimeClouds > 0 && spawnTimeClouds <= 16)
        {
            for (int cloud : Utility.makeRange(0, Greenfoot.getRandomNumber(additionalClouds)+addCloud)) 
            {               
                addObject( new Cloud("cloud.png", Greenfoot.getRandomNumber(2)+1), Greenfoot.getRandomNumber(getWidth()*1/4)+getWidth()+300, Greenfoot.getRandomNumber(getHeight()));
            }
        }
        else if (spawnTimeClouds > resetTime)
        {
            spawnTimeClouds = 0;
        }
    }

    /**
     * Spawns seals from a specific amount of time.
     * 
     * @param 'additionalSeals': add more random amount of seals to addSeal
     * @param 'addSeal': decides how many seals should spawn
     * @param 'resetTime': reset spawn time. The lower reset time is the faster seals being added to world
     */
    public void spawnFlyingSeals(int additionalSeals, int addSeal, int resetTime)
    {
        spawnTimeSeals += 15;
        if(spawnTimeSeals > 0 && spawnTimeSeals <= 16)
        {
            for (int cloud : Utility.makeRange(0, Greenfoot.getRandomNumber(additionalSeals)+addSeal)) 
            {               
                addObject( new FlyingSeal("seal.png", 3, Greenfoot.getRandomNumber(2)+1, "right"), -50, Greenfoot.getRandomNumber(getHeight()));
                addObject( new FlyingSeal("seal2.png", 3, Greenfoot.getRandomNumber(2)+1, "left"), getWidth()+50, Greenfoot.getRandomNumber(getHeight()));
            }
        }
        else if (spawnTimeSeals > resetTime)
        {
            spawnTimeSeals = 0;
        }
    }

    /**
     * Calls the current health points value from FlyingFish and sets it to health bar.
     */
    public void getFlyingFishHealth()
    {
        if(!getObjects(FlyingFish.class).isEmpty())
        {
            healthbar.setValue(getObjects(FlyingFish.class).get(0).getHealth());
            if(getObjects(FlyingFish.class).get(0).getHealth() == 4)
            {
                healthbar.setTextColor(new Color(50, 255, 50, 255));
                healthbar.setSafeColor(new Color(50, 255, 50, 255));
            }
            else if(getObjects(FlyingFish.class).get(0).getHealth() == 3)
            {
                healthbar.setTextColor(new Color(255, 255, 85, 255));
                healthbar.setSafeColor(new Color(255, 255, 85, 255));
            }
            else if(getObjects(FlyingFish.class).get(0).getHealth() == 2)
            {
                healthbar.setTextColor(new Color(255, 100, 0, 255));
                healthbar.setSafeColor(new Color(255, 100, 0, 255));
            }
            else if(getObjects(FlyingFish.class).get(0).getHealth() == 1)
            {
                healthbar.setTextColor(new Color(255, 0, 0, 255));
                healthbar.setSafeColor(new Color(255, 0, 0, 255));
            }
        }
        else
        {
            healthbar.setValue(0);
        }
    }

    /**
     * Being called if the player lost all health points.
     */
    public void GameOver()
    {
        if(getObjects(FlyingFish.class).isEmpty() && !getIsGameOver())
        {
            addObject( new Label("GAME OVER :(", 100, new Color(255,0,0,255), new Color(0,0,0,0)), getWidth()/2, getHeight()*1/6);
            addObject( new Label("Press:\n\n- Enter to replay\n- ESC to exit", 35, new Color(255,50,50,255), new Color(0,0,0,0)), getWidth()/2, getHeight()/2);
            for(FlyingSeal fs : getObjects(FlyingSeal.class))
            {
                removeObject(fs);
            }
            setIsGameOver(true);
        }
        if(getIsGameOver())
        {
            if("enter".equals(getKey))
            {
                Greenfoot.setWorld(new Sky("blue_bg.jpg"));
            }
            else if("escape".equals(getKey))
            {
                System.exit(0);
            }
        }
    }

    /**
     * set new width size
     */
    public static void setWidthScreenSize(int i)
    {
        widthScreenSize = i;
    }

    /**
     * set new height size
     */
    public static void setlHeightScreenSize(int i)
    {
        heightScreenSize = i;
    }

    /**
     * get current width size
     */
    public static int getWidthScreenSize()
    {
        return widthScreenSize;
    }

    /**
     * get current height size
     */
    public static int getHeightScreenSize()
    {
        return heightScreenSize;
    }

    /**
     * get current state of full screen mode
     */
    public static boolean getFullscreenON()
    {
        return fullscreenON;
    }

    /**
     * set if the game is over
     */
    public void setIsGameOver(boolean b)
    {
        isGameOver = b;
    }

    /**
     * get current game status.
     */
    public boolean getIsGameOver()
    {
        return isGameOver;
    }
}

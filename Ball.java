import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import org.junit.Assert;
/**
 * Allows the player to shoot some balls to it's enemies
 * 
 * @author Ferhat
 * @version 1.0
 * @last updated 2019-02-27
 */
public class Ball extends Actor
{
    public int speed;
    public String image;
    public boolean isPlayerViewRight;
    /**
     * Creates a new object of Ball to shoot around.
     * 
     * @param 'image': set picture from resource(images) e.g. "rocket.png"
     * @param 'speed': speed of ball in the environment
     * @param 'isPlayerViewRight': if true ball will move to right else to left
     */
    public Ball(String image, int speed, boolean isPlayerViewRight)
    {
        this.image = image;
        this.speed = speed;
        Assert.assertTrue("speed must be positive", speed > 0);  // Check first if speed is positive
        this.isPlayerViewRight = isPlayerViewRight;
        setImage(image);
    }

    /**
     * This method is called by the Greenfoot system when this object has been inserted into the world.
     */
    protected void addedToWorld(World world)
    {
        getIsPlayerViewRight();
    }

    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        movement();
        outOfWorldArea();
    }  

    /**
     * movement direction depends on player's perspective
     */
    public void movement()
    {
        if (getIsPlayerViewRight()) {
            this.move(speed);
        }
        else this.move(-speed);
    }

    /**
     * Removes the object from the world whenever a ball reaches the end of the world area.
     */
    public void outOfWorldArea()
    {
        if (getX() > getWorld().getWidth() + 150)
        {
            getWorld().removeObject(this);
        }
        else if (getX() < - 150)
        {
            getWorld().removeObject(this);
        }
        else if (getY() > getWorld().getHeight() + 150)
        {
            getWorld().removeObject(this);
        }
        else if (getY() < - 150)
        {
            getWorld().removeObject(this);
        }
    }

    /**
     * get current player view from FlyingFish
     */
    public boolean getIsPlayerViewRight()
    {
        return this.isPlayerViewRight;
    }
}

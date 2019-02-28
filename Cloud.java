import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Simple and friendly cloud objects in the environment.
 * 
 * @author Ferhat
 * @version 1.0
 * @last updated 2019-02-27
 */
public class Cloud extends Actor
{
    public int speed;
    /**
     * New Object of Cloud
     * 
     * @param 'image': picture from resource(images) e.g. "bear.png"
     * @param 'speed': speed of clouds in the environment
     */
    public Cloud(String image, int speed)
    {
        this.speed = speed;
        setImage(image);
    }

    /**
     * Act - do whatever the Cloud wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        movement();
        outOfWorldArea();
    }

    /**
     * Moves clouds from right to left of the screen
     */
    public void movement()
    {
        this.setLocation(getX() - this.speed, getY());
    }

    /**
     * Removes the object from the world whenever a cloud reaches the end of the world area.
     */
    public void outOfWorldArea()
    {
        if (getX() > getWorld().getWidth() + 1500)
        {
            getWorld().removeObject(this);
        }
        else if (getX() < - 1500)
        {
            getWorld().removeObject(this);
        }
        else if (getY() > getWorld().getHeight() + 1500)
        {
            getWorld().removeObject(this);
        }
        else if (getY() < - 1500)
        {
            getWorld().removeObject(this);
        }
    }
}

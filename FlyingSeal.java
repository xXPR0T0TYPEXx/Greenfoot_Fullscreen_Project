import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Hungry seals who'd like to bite flying fish
 * 
 * @author Ferhat
 * @version 1.0
 * @last updated 2019-02-27
 */
public class FlyingSeal extends Actor
{
    public int speed;
    public int health;
    public String image;
    public String perspetive;
    public boolean didBittenFish = false;
    /**
     * Create a new flying seal.
     * 
     * @param 'image': set picture from resource(images) e.g. "seal.png"
     * @param 'speed': set amount of health points
     * @param 'speed': speed of flying seal in the environment
     * @param 'perspetive': set perspective (left, right)
     */
    public FlyingSeal(String image, int health, int speed, String perspetive)
    {
        this.image = image;
        this.health = health;
        this.speed = speed;
        this.perspetive = perspetive;
        setImage(image);
    }

    /**
     * Act - do whatever the FlyingSeal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        movement();
        if (isTouching(Ball.class)) {
            damage(1);
            getWorld().removeObject(getOneIntersectingObject(Ball.class));
        }
        if (isTouching(FlyingFish.class) && !getDidBittenFish()) {
            getWorld().getObjects(FlyingFish.class).get(0).damage(1);
            setDidBittenFish(true);
            getWorld().removeObject(getOneIntersectingObject(Ball.class));
        }
        outOfWorldArea();
        if(getHealth() <= 0)
        {
            getWorld().addObject( new FloatInfo("+1", 30, new Color(100, 225, 255, 255), new Color(0, 0, 0, 0)), getX(), getY());
            getWorld().removeObject(this);
        }
    }   

    /**
     * allows the seals to move
     */
    public void movement()
    {
        switch(perspetive)
        {
            case "left":
            this.setLocation(getX() - speed, getY());
            break;

            case "right":
            this.setLocation(getX() + speed, getY());
            break;
        }
    }

    /**
     * Removes the object from the world whenever a seal reaches the end of the world area.
     */
    public void outOfWorldArea()
    {
        if (getX() > getWorld().getWidth() + 500)
        {
            getWorld().removeObject(this);
        }
        else if (getX() < - 500)
        {
            getWorld().removeObject(this);
        }
        else if (getY() > getWorld().getHeight() + 500)
        {
            getWorld().removeObject(this);
        }
        else if (getY() < - 500)
        {
            getWorld().removeObject(this);
        }
    }

    /**
     * decrease seal health points by d.
     * 
     * @param 'd': subtracts health points by d. the higher d is the more damage the seal gets
     */
    public void damage(int d)
    {
        health -= d;
    }

    /**
     * get current health points
     */
    public int getHealth()
    {
        return this.health;
    }

    /**
     * set if a seal did bite the player
     */
    public void setDidBittenFish(boolean b)
    {
        this.didBittenFish = b;
    }

    /**
     * get current bite state
     */
    public boolean getDidBittenFish()
    {
        return this.didBittenFish;
    }
}

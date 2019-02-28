import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main object - the player in this game.
 * 
 * Controll:
 * - up arrow key: fly up
 * - down arrow key: fly down
 * - left arrow key: change perspective to left
 * - right arrow key: change perspective to right
 * - space key: shooting
 * 
 * 
 * @author Ferhat
 * @version 1.0
 * @last updated 2019-02-27
 */
public class FlyingFish extends Actor
{
    public int speed;
    public int health = 5;
    private boolean shootKey = false;
    public String image, image2;
    public boolean isPlayerViewRight;
    /**
     * Create a new flying fish - the main character of this game
     * 
     * @param 'image': set picture from resource(images) e.g. "bear.png"
     * @param 'image2': set picture from resource(images) e.g. "bear2.png"
     * @param 'speed': speed of flying fish in the environment
     * @param 'isPlayerViewRight': set if player view (perspective) is right or not
     */
    public FlyingFish(String image, String image2, int speed, boolean isPlayerViewRight)
    {
        this.image = image;
        this.image2 = image2;
        setImage(image);
        this.isPlayerViewRight = isPlayerViewRight;
        this.speed = speed;
    }

    /**
     * Act - do whatever the FlyingFish wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        movement();
        shooting();
        worldAreaLimit();
        if(health <= 0)
        {
            getWorld().removeObject(this);
        }
    }

    /**
     * allows the player to move FlyingFish
     */
    private void movement()
    {
        if (Greenfoot.isKeyDown("up")) {
            this.setLocation(getX(), getY() - getSpeed());
        }
        if (Greenfoot.isKeyDown("down")) {
            this.setLocation(getX(), getY() + getSpeed());
        }
        if (Greenfoot.isKeyDown("left")) {
            setImage(image2);
            setIsPlayerViewRight(false);
        }
        if (Greenfoot.isKeyDown("right")) {
            setImage(image);
            setIsPlayerViewRight(true);
        }
    }

    /**
     * Let the player shoot by pressing 'space'
     */
    public void shooting()
    {
        if (!shootKey && Greenfoot.isKeyDown("space")) {
            getWorld().addObject( new Ball("ball.png", 35, getIsPlayerViewRight()), getX(), getY());
            shootKey = true;
        }
        else if (shootKey && ! Greenfoot.isKeyDown("space")) {
            shootKey = false;
        }
    }

    /**
     * Prevent the player not to leave the screen
     */
    public void worldAreaLimit()
    {
        if (getY() > getWorld().getHeight())
        {
            if (Greenfoot.isKeyDown("down")) {
                this.setLocation(getX(), getY() - getSpeed());
            }
        }
        if (getY() < 0)
        {
            if (Greenfoot.isKeyDown("up")) {
                this.setLocation(getX(), getY() + getSpeed());
            }
        }
    }

    /**
     * decrease player health points by d.
     * 
     * @param 'd': subtracts health points by d. the higher d is the more damage the player gets
     */
    public void damage(int d)
    {
        health -= d;
        getWorld().addObject( new FloatInfo("-" + String.valueOf(d) + " HP", 30, new Color(255, 0, 0, 255), new Color(0, 0, 0, 0)), getX(), getY());
    }

    /**
     * get current speed from FlyingFish
     */
    public int getSpeed()
    {
        return this.speed;
    }

    /**
     * get current health points from FlyingFish
     */
    public int getHealth()
    {
        return this.health;
    }

    /**
     * set player view boolean to true or false
     */
    public void setIsPlayerViewRight(boolean b)
    {
        this.isPlayerViewRight = b;
    }

    /**
     * get current player view from FlyingFish
     */
    public boolean getIsPlayerViewRight()
    {
        return this.isPlayerViewRight;
    }
}

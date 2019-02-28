import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A floating label object which disappears when it's transparent completely.
 * 
 * @author Ferhat
 * @version 1.0
 * @last updated 2019-02-27
 */
public class FloatInfo extends Label
{
    private boolean t = true;
    private int transparency = 255;
    /**
     * Create a new customizable floating label object.
     * 
     * @param 'text': a text string
     * @param 'size': size of text string
     * @param 'front': front color of text string
     * @param 'background': background color of text string
     */
    public FloatInfo(String text, int size, Color front, Color background)
    {
        super(text, size, front, background);
    }

    /**
     * Act - do whatever the FloatInfo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        getImage().setTransparency(this.transparency);
        floatMessageAnimation(1);
    }

    /**
     * Moves FloatInfo object uperwards and makes it's object more transparent from time to time
     * 
     * @param 'speed': speed of FloatInfo in the environment
     */
    public void floatMessageAnimation(int speed)
    {
        this.setLocation(getX(), getY() - speed);
        if (t){
            this.transparency = this.transparency - 3;
            if (transparency <= 15){
                t = false;
            }
        }
        else if (!t){
            getWorld().removeObject(this);
        }
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creates new text label string for the environment
 * 
 * @author Ferhat, Berthold 
 * @version 1.1
 * @last updated 2019-02-27
 */
public class Label extends Actor
{
    protected final int DEFAULTSIZE = 20;
    protected String text;
    protected final Color DEFAULTFRONT = new Color(255,255,255,255);
    protected final Color DEFAULTBACKGROUND = new Color(0,0,0,0);
    /**
     * Create a new customizable label
     * 
     * @param 'text': a text string
     * @param 'size': size of text string
     * @param 'front': front color of text string
     * @param 'background': background color of text string
     */
    public Label(String text, int size, Color front, Color background)
    {
        this.text = text;
        GreenfootImage g = new GreenfootImage(text, size, front, background);
        this.setImage(g);
    }

    /**
     * Create a new default label
     * 
     * @param 'text': a text string
     */
    public Label(String text)
    {
        this.text = text;
        GreenfootImage g = new GreenfootImage(text, DEFAULTSIZE, DEFAULTFRONT, DEFAULTBACKGROUND);
        this.setImage(g);
    }   
}

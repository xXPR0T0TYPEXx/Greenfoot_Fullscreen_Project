import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.concurrent.TimeUnit;
/**
 * A normal counter or timer which counts every second till the game is over.
 * 
 * @author Ferhat
 * @version 1.0
 * @last updated 2019-02-27
 */
public class Timer extends Actor
{
    private long secondLeft = 0;
    private long secondRight = 0;
    private long minuteLeft = 0;
    private long minuteRight = 0;
    private int size;
    private String text;
    private Color front;
    private Color background;
    /**
     * Create a new timer.
     * 
     * @param 'text': a text string
     * @param 'size': size of text string
     * @param 'front': front color of text string
     * @param 'background': background color of text string
     */
    public Timer(String text, int size, Color front, Color background)
    {
        this.size = size;
        this.text = text;
        this.front = front;
        this.background = background;
    }

    /**
     * Act - do whatever the Timer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(!((Sky)getWorld()).getIsGameOver())secondRight += System.currentTimeMillis()/1000000000; 
        if (secondRight >= 1000000){
            secondLeft += 1;
            secondRight = 0;
        }
        if (secondLeft == 6){
            minuteRight += 1;
            secondLeft = 0;
        }
        if (minuteRight >= 10){
            minuteLeft += 1;
            minuteRight = 0;
        }
        setImage(new GreenfootImage(text + this.minuteLeft + this.minuteRight + " : " + this.secondLeft + this.secondRight/100000, this.size, this.front, this.background));
    }    
}

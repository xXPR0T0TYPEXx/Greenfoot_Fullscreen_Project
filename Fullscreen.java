import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import greenfoot.core.WorldHandler;
import java.awt.Toolkit;
/**
 * Fullscreen class allows users to run their Greenfoot Projects in fullscreen mode without any loss
 * of image quality of objects or already existing Greenfoot methods. Fullscreen gets the JPanel
 * from WorldCanvas and puts it to a new JFrame window. Thereby Greenfoot window will lose it's JPanel,
 * so reboot Greenfoot to restore panel.
 * 
 * @author Ferhat
 * @version 1.0
 * @last updated 2019-02-27
 */
public class Fullscreen extends JFrame
{
    private GraphicsDevice vc;
    private JPanel panel = WorldHandler.getInstance().getWorldCanvas();  // Calls WorldCanvas from source
    /**
     * Fullscreen calls the current system screen resolution and sets it to a new JFrame window
     */
    public Fullscreen()
    {
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        vc = environment.getDefaultScreenDevice();
        Sky.setWidthScreenSize(Toolkit.getDefaultToolkit().getScreenSize().width);  // set new width from current width resolution
        Sky.setlHeightScreenSize(Toolkit.getDefaultToolkit().getScreenSize().height);  // set new height from current height resolution
        this.setUndecorated(true);
        this.setResizable(false);
        add(panel);
        vc.setFullScreenWindow(this);
    }
}

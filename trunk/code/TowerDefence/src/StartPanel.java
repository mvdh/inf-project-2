import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author Slayer
 */
public class StartPanel extends Panel
{

    private JLabel image;
    private ImageIcon startImage;


    public StartPanel(GameStats gameStats)
    {
        super(gameStats);
        URL url = getClass().getResource("images/screen_start.png");
        try
        {
            startImage = new ImageIcon(url);
        } catch (Exception e)
        {
        }
        image = new JLabel(startImage);
        image.setBounds(0, 0, 600, 600);
        add(image);
    }
}

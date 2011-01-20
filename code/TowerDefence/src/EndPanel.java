
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Slayer
 */
public class EndPanel extends Panel implements ActionListener
{

    private JLabel score;
    private JLabel wave;

    private JLabel image;
    private ImageIcon endImage;

    private Font statsFont = new Font("Arial", Font.BOLD, 18);


    public EndPanel(GameStats gameStats)
    {
        super(gameStats);

        score = new JLabel("" + (super.getGameStats().getPoints()));
        wave = new JLabel("" + (super.getGameStats().getWave() + 1));

        score.setFont(statsFont);
        wave.setFont(statsFont);

        score.setForeground(Color.white);
        wave.setForeground(Color.white);

        score.setBounds(222, 298, 160, 20);
        wave.setBounds(178, 259, 160, 20);

        add(score);
        add(wave);
        
        URL url = getClass().getResource("images/screen_end.png");
        try
        {
            endImage = new ImageIcon(url);
        } catch (Exception e)
        {
        }

        image = new JLabel(endImage);
        image.setBounds(0, 0, 600, 600);
        add(image);
    }
}

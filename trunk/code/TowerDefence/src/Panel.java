
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Slayer
 */
public abstract class Panel extends JPanel implements ActionListener
{

    private GameStats gameStats;
    private JButton start;
    private ImageIcon startButton;

    public Panel(GameStats gameStats)
    {
        URL url = getClass().getResource("images/start-button.png");
        try
        {
            startButton = new ImageIcon(url);
        } catch (Exception e)
        {
        }
        this.setFont(new Font("Arial", Font.BOLD, 16));
        setLocation(50, 50);
        setSize(600, 600);
        setVisible(true);
        setLayout(null);
        setBackground(Color.black);
        setGameStats(gameStats);
        
        start = new JButton(startButton);
        start.setBounds(163, 485, 274, 88);
        start.addActionListener(this);
        start.setBackground(Color.black);
        add(start);
    }

    public GameStats getGameStats()
    {
        return gameStats;
    }

    public void setGameStats(GameStats gameStats)
    {
        this.gameStats = gameStats;
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(start))
        {
            getGameStats().setStarted(true);
            setVisible(false);
        }
    }
}


import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;

/**
 *
 * @author Slayer
 */
public abstract class Panel extends JPanel
{

        private GameStats gameStats;

        public Panel(GameStats gameStats)
        {
                this.setFont(new Font("Arial", Font.BOLD, 16));
                setLocation(50, 50);
                setSize(600, 400);
                setVisible(true);
                setLayout(null);
                setBackground(Color.black);
                setGameStats(gameStats);
        }

        public GameStats getGameStats()
        {
                return gameStats;
        }

        public void setGameStats(GameStats gameStats)
        {
                this.gameStats = gameStats;
        }
}


import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Slayer
 */
public abstract class Panel extends JPanel{

    private GameStats gameStats;
    
    public Panel(GameStats gameStats){
        setLocation(40,40);
        setSize(600, 360);
        setVisible(true);
        setLayout(null);
        setBackground(Color.black);
        setGameStats(gameStats);
    }

    public GameStats getGameStats(){
        return gameStats;
    }

    public void setGameStats(GameStats gameStats){
        this.gameStats = gameStats;
    }

}

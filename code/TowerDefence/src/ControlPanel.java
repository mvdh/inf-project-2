import javax.swing.JPanel;

/**
 *
 * @author Maarten van den Hoek
 */
public class ControlPanel extends JPanel {

    private Field field;
    private GameStats gameStats;
    private Controller controller = null;

    public ControlPanel(GameStats gameStats, Field field) {
        setGameStats(gameStats);
        setLocation(50, 500);
        setSize(600, 150);
        setField(field);
        findType(field);
        setVisible(true);
        setLayout(null);
        //setBackground(null);
        this.setOpaque(false);
    }

    /**
     * Sets the field to the given field on wich the controlpanel is based
     * @param field the given field
     */
    public void setField(Field field){
        this.field = field;
    }

    public void setGameStats(GameStats gameStats){
        this.gameStats = gameStats;
    }

    /**
     * Returns the field on which the controlpanel is based
     * @return the field field
     */
    public Field getField(){
        return this.field;
    }

    public Controller getController()
    {
        return controller;
    }

    public GameStats getGameStats(){
        return this.gameStats;
    }

    public boolean hasController()
    {
        return controller != null;
    }
    
    /**
     * Determines of what instance the given field is
     * @param field the field the field on wich the controlpanel is based
     */
    public void findType(Field field){
        if(field instanceof Tower){
            controller = new TowerController(getGameStats(), (Tower) field);
        } else if(field instanceof Field){
            controller = new FieldController(getGameStats());
        } else{
            //DO NOTHING
        }
        
        if (controller != null)
        {
            add(controller);
		}
	}
}

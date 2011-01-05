import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Maarten van den Hoek
 */
public class ControlPanel extends JPanel {

    private Field field;
    private TowerData towerData;
    
    private Controller controller = null;

    public ControlPanel(TowerData towerData, Field field) {
        setLocation(0, 380);
        setSize(680, 180);
        setField(field);
        findType(field);
        setVisible(true);
        setTowerData(towerData);
        this.setLayout(null);
        setBackground(Color.white);
    }

    /**
     * Sets the field to the given field on wich the controlpanel is based
     * @param field the given field
     */
    public void setField(Field field){
        this.field = field;
    }

    public void setTowerData(TowerData towerData){
        this.towerData = towerData;
    }
    /**
     * Returns the field on which the controlpanel is based
     * @return the field field
     */
    public Field getField(){
        return this.field;
    }

    public TowerData getTowerData(){
        return this.towerData;
    }
    
    public Controller getController()
    {
        return controller;
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
            controller = new TowerController(getTowerData(), (Tower) field);
            System.out.println("Tower");
        } else if(field instanceof Field){
            controller = new FieldController(getTowerData());
            System.out.println("Field");
        } else{
            //DO NOTHING
        }
        
        if (controller != null)
        {
            add(controller);
        }
    }
}

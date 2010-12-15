import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

/**
 *
 * @author Maarten van den Hoek
 */
public class ControlPanel extends JPanel {

    private Field field;
    private TowerData towerData;

    public ControlPanel(TowerData towerData, Field field) {
        setLocation(0, 281);
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
     * Returns the field on wich the controlpanel is based
     * @return the field field
     */
    public Field getField(){
        return this.field;
    }

    public TowerData getTowerData(){
        return this.towerData;
    }

    /**
     * Determines of what instance the given field is
     * @param field the field the field on wich the controlpanel is based
     */
    public void findType(Field field){
        if(field instanceof Tower){
            TowerController towerController = new TowerController(getTowerData(), (Tower) field);
            add(towerController);
            System.out.println("Tower");
        } else if(field instanceof Field){
            FieldController fieldController = new FieldController();
            add(fieldController);
            System.out.println("Field");
        } else{
            //DO NOTHING
        }
    }
}

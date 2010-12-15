import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Maarten van den Hoek
 */
public class ControlPanel extends JPanel {

    private Field field;

    public ControlPanel(Field field) {
        setLocation(0, 281);
        setSize(680, 180);
        setField(field);
        findType(field);
        setVisible(true);
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

    /**
     * Returns the field on wich the controlpanel is based
     * @return the field field
     */
    public Field getField(){
        return this.field;
    }

    /**
     * Determines of what instance the given field is
     * @param field the field the field on wich the controlpanel is based
     */
    public void findType(Field field){
        if(field instanceof Tower){
            TowerController towerController = new TowerController((Tower) field);
            add(towerController);
            System.out.println("Tower");
        } else if(field instanceof Field){
            //FieldController fieldController = new FieldController(field);
        } else{
            //DO NOTHING
        }
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        System.out.println("how many times this one?");
    }
}

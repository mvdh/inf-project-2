
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

    private JButton restart;
    private JPanel description = new JPanel(new GridLayout(5, 1));
    private JLabel priceLbl = new JLabel();
    private JLabel hitPointsLbl = new JLabel();
    private JLabel rangeLbl = new JLabel();
    private JLabel speedLbl = new JLabel();
    private JLabel damageLbl = new JLabel();

    public EndPanel(GameStats gameStats)
    {
        super(gameStats);

    }

    public void actionPerformed(ActionEvent e)
    {
    }
}

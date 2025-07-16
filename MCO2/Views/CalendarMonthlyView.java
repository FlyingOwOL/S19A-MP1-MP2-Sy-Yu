package Views;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Color;

public class CalendarMonthlyView extends JPanel{

    private String[] dayNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Satureday"};

    public CalendarMonthlyView(){
        this.setPreferredSize(new Dimension(900, 400));
        this.setLayout(new GridLayout(6, 7));
        
        for (String n : dayNames){
            this.add(new JLabel(n));
            
        }
        for(int x = 0; x < 35; x++){
            this.add(new JButton());
        }
        this.setVisible(true);
    }
}

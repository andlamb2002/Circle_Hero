/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circleherosmall;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Andreas Lambropoulos
 */
public class SettingsPanel extends JPanel {
    
    private final int panelWidth = 960;
    private final int panelHeight = 720;
    
    public SettingsPanel(int x, int y) {
        
        this.setBounds(x, y, panelWidth, panelHeight);
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setMaximumSize(new Dimension(panelWidth, panelHeight));
        
        this.setLayout(null);
        //this.setBackground(Color.pink);
        
        
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circleherosmall;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author Andreas Lambropoulos
 */
public class BasicPanel extends JPanel {
    
    private int width;
    private int height;
    
    public BasicPanel(int x, int y, int w, int h) {
        
        this.setBounds(x, y, w, h);
        width = w;
        height = h;
        this.setPreferredSize(new Dimension(w, h));
        this.setMaximumSize(new Dimension(w, h));
        this.setMinimumSize(new Dimension(w, h));
        
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 0));
        //this.setBackground(Color.orange);
        //this.setOpaque(true);
        
    }
    
}

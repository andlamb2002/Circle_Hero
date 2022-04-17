/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circlehero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author Andreas Lambropoulos
 */
public class RightPanel extends JPanel{
    
    private int width;
    private int height;
    
    public RightPanel(int x, int y, int w, int h) {
        
        this.setBounds(x, y, w, h);
        width = w;
        height = h;
        this.setPreferredSize(new Dimension(w, h));
        this.setMaximumSize(new Dimension(w, h));
        this.setMinimumSize(new Dimension(w, h));
        
        //this.setBackground(Color.blue);
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circlehero;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Andreas Lambropoulos
 */
public class TopRightPanel extends JPanel{
    
    private int width;
    private int height;
    
    public TopRightPanel(int x, int y, int w, int h) {
        
        this.setBounds(x, y, w, h);
        width = w;
        height = h;
        this.setPreferredSize(new Dimension(w, h));
        this.setMaximumSize(new Dimension(w, h));
        this.setMinimumSize(new Dimension(w, h));
        
        //this.setBackground(Color.yellow);
        this.setLayout(null);
        
    }
    
}

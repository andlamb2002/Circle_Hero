/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circlehero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Andreas Lambropoulos
 */
public class Circle extends JPanel {
    
    private int circleSize;
    
    public Circle(int x, int y, int s) {
        
        circleSize = s;
        
        this.setBounds(x, y, circleSize, circleSize);
        this.setPreferredSize(new Dimension(circleSize, circleSize));
        this.setMaximumSize(new Dimension(circleSize, circleSize));
        
        //this.setOpaque(true);
        
    }
    
    public void setNewBounds(int x, int y) {
        this.setBounds(x, y, circleSize, circleSize);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(0, 0, circleSize, circleSize);
    }
    
    
}

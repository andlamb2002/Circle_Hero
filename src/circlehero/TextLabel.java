/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circlehero;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author Andreas Lambropoulos
 */
public class TextLabel extends JLabel {
    
    public TextLabel(String text, int size, Color color) {
        
        this.setText(text);
        
        this.setFont(new Font("VERDANA", Font.PLAIN, size));
        this.setForeground(color);
        this.setHorizontalTextPosition(JLabel.LEFT);
        this.setVerticalTextPosition(JLabel.CENTER);
        
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        
        //this.setBackground(Color.cyan);
        //this.setOpaque(true);
        
        
    }
    
}

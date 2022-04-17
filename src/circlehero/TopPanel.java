/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circlehero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Andreas Lambropoulos
 */
public class TopPanel extends JPanel {
    
    private TextLabel title;
    
    public TopPanel(int x, int y, int w, int h) {
        
        this.setBounds(x, y, w, h);
        this.setPreferredSize(new Dimension(w, h));
        this.setMaximumSize(new Dimension(w, h));
        this.setMinimumSize(new Dimension(w, h));
        
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        //this.setBackground(Color.green);
        
        title = new TextLabel("CIRCLE HERO", 75, Color.red);
        ImageIcon circleHeroLogo = new ImageIcon(new ImageIcon("circleHeroLogo.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        title.setIcon(circleHeroLogo);
        this.add(title);
        
    }
    
}

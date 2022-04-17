/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circleherosmall;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Andreas Lambropoulos
 */
public class GamePanel extends JPanel implements MouseListener {
    
    //GamePanel Size
    private final int panelWidth = 640;
    private final int panelHeight = 480;
    private final int endGamePanelWidth = 400;
    
    //Running
    private boolean running = false;
    private boolean easyMode = false;
    private boolean suddenDeathMode = false;
    
    //Default Values
    private final int defaultCircleSize = 80;
    private final int defaultTotalCircles = 50;
    
    //Circle Size
    private int circleSize = 80; //48, 60, 80, 120, 240
    private int gridUnits = (panelWidth * panelHeight) / circleSize;
    
    //3 Circles and their Bounds
    private Circle circle1;
    private Circle circle2;
    private Circle circle3;
    private int circle1X;
    private int circle1Y;
    private int circle2X;
    private int circle2Y;
    private int circle3X;
    private int circle3Y;
    
    //Clicks and Circle Quantities
    private int mouseClicks = 0;
    private int totalCircles = 50; 
    private int totalCirclesHit = 0;
    private double accuracy = 0;
    
    private double score = 0;
    private double highScore = 0;
    
    Timer timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds += 0.2;
            }
        });
    
    private double seconds = 0;
    
    private Robot tempRobot;
    
    public GamePanel(int x, int y) {
        
        this.setBounds(x, y, panelWidth, panelHeight);
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setMaximumSize(new Dimension(panelWidth, panelHeight));
        this.setLayout(null);
        
        this.setBackground(Color.lightGray);
        this.setFocusable(true);
        this.addMouseListener(this);
        this.addCircle1();
        this.addCircle2();
        this.addCircle3();
    }
    //Grid
    /*
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    
    public void draw(Graphics g) {
        
        for (int i = 0; i <= panelWidth/circleSize; i++) {
            g.drawLine(i * circleSize, 0, i * circleSize, panelHeight);
        }
        
        for (int i = 0; i <= panelHeight/circleSize; i++) {
            g.drawLine(0, i * circleSize, panelWidth, i * circleSize);
        }
        
    }
*/
    private double convertToTwoDecimal(double d) {
        d += .0001;
        double dMult = (d * 100);
        int iMult = (int) dMult;
        double dConverted = iMult / 100.0;
        return dConverted;
    }
    
    public double getHighScore() {
        return highScore;
    }
    
    public void setCircleSize(int s) {
        circleSize = s;
    }
    
    public void setTotalCircles(int c) {
        totalCircles = c;
    }
    
    public void setEasyMode(boolean e) {
        easyMode = e;
    }
    
    public void setSuddenDeathMode(boolean s) {
        suddenDeathMode = s;
    }
    
    public void setHighScore(double s) {
        highScore = s;
    }
    
    public boolean isRunning() {
        return running;
    }
    
    public void startGame() {
        running = true;
        timer.start();
        
    }
    
    public void endGame() { 
        timer.stop();
        seconds = convertToTwoDecimal(seconds);
        accuracy = (100.0 * totalCirclesHit) / mouseClicks;
        accuracy = convertToTwoDecimal(accuracy);
        score = (accuracy * totalCirclesHit)/seconds;
        score = convertToTwoDecimal(score);
        if (score > highScore) {
            highScore = score;
        }
        running = false;
        this.displayResults(MainFrame.rightPanel, MainFrame.extraWidth);
        this.displayHighScore(MainFrame.leftPanel, MainFrame.extraWidth);
        this.reset();
    }
    
    /*
    public void printInfo() {
        System.out.println("mouseClicks" + mouseClicks);
        System.out.println("accuracy" + accuracy);
        System.out.println("totalCircles" + totalCircles);
        System.out.println("totalCirclesHit" + totalCirclesHit);
        System.out.println("seconds" + seconds);
    }
*/
    
    public void displayResults(JPanel panel, int extraW) {
        
        panel.removeAll();
        
        TextLabel text1 = new TextLabel("Score: " + score, 20, Color.black);
        TextLabel text2 = new TextLabel("Circles Clicked: " + totalCirclesHit, 20, Color.black);
        TextLabel text3 = new TextLabel("Total Clicks: " + mouseClicks, 20, Color.black);
        TextLabel text4 = new TextLabel("Accuracy: " + accuracy + "%", 20, Color.black);
        
        BasicPanel panel1 = new BasicPanel(0, 0, extraW, 100);
        panel1.add(text1);
        BasicPanel panel2 = new BasicPanel(0, 0, extraW, 100);
        panel2.add(text2);
        BasicPanel panel3 = new BasicPanel(0, 0, extraW, 100);
        panel3.add(text3);
        BasicPanel panel4 = new BasicPanel(0, 0, extraW, 100);
        panel4.add(text4);
        
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        
        panel.setVisible(true);
    }
    
    public void displayCircleCounter(JPanel panel, int extraW) {
        
        panel.setVisible(false);
        panel.removeAll();
        
        TextLabel counter = new TextLabel("Counter: " + totalCirclesHit + "/" + totalCircles, 20, Color.black);
        
        BasicPanel counterPanel = new BasicPanel(0, 0, extraW, 100);
        counterPanel.add(counter);
        panel.add(counterPanel);
        
        panel.setVisible(true);
        
    }
    
    public void displayHighScore(JPanel panel, int extraW) {
        
        panel.setVisible(false);
        panel.removeAll();
        
        TextLabel text1 = new TextLabel("High Score: " + highScore, 20, Color.black);
        
        BasicPanel panel1 = new BasicPanel(0, 0, extraW, 100);
        panel1.add(text1);
        panel.add(panel1);
        
        panel.setVisible(true);
        
    }
    
    public void reset() {
        running = false;
        mouseClicks = 0;
        accuracy = 0;
        totalCirclesHit = 0;
        seconds = 0;
        score = 0;
        
        
        
        this.setCircleVisibility(false);
        
        int tempCircleX = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
        int tempCircleY = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;
        while((circle1X == tempCircleX && circle1Y == tempCircleY) || (circle2X == tempCircleX && circle2Y == tempCircleY) || (circle3X == tempCircleX && circle3Y == tempCircleY)) {
            tempCircleX = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
            tempCircleY = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;
        }
        circle1X = tempCircleX;
        circle1Y = tempCircleY;
        circle1.setNewBounds(circle1X, circle1Y);
        
        tempCircleX = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
        tempCircleY = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;
        while((circle1X == tempCircleX && circle1Y == tempCircleY) || (circle2X == tempCircleX && circle2Y == tempCircleY) || (circle3X == tempCircleX && circle3Y == tempCircleY)) {
            tempCircleX = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
            tempCircleY = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;
        }
        circle2X = tempCircleX;
        circle2Y = tempCircleY;
        circle2.setNewBounds(circle2X, circle2Y);
        
        tempCircleX = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
        tempCircleY = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;
        while((circle1X == tempCircleX && circle1Y == tempCircleY) || (circle2X == tempCircleX && circle2Y == tempCircleY) || (circle3X == tempCircleX && circle3Y == tempCircleY)) {
            tempCircleX = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
            tempCircleY = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;
        }
        circle3X = tempCircleX;
        circle3Y = tempCircleY;
        circle3.setNewBounds(circle3X, circle3Y);
        
    }
    
    public void addCircle1() {
        circle1X = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
        circle1Y = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;
        
        circle1 = new Circle(circle1X, circle1Y, circleSize);
        circle1.addMouseListener(this);
        this.add(circle1);
    }
    
    public void addCircle2() {
        circle2X = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
        circle2Y = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;
        
        while(circle2X == circle1X && circle2Y == circle1Y) {
            circle2X = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
            circle2Y = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;
        }
        
        circle2 = new Circle(circle2X, circle2Y, circleSize);
        circle2.addMouseListener(this);
        this.add(circle2);
    }
    
    public void addCircle3() {
        circle3X = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
        circle3Y = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;
        
        while(circle3X == circle1X && circle3Y == circle1Y) {
            circle3X = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
            circle3Y = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;
        }
        
        while(circle3X == circle2X && circle3Y == circle2Y) {
            circle3X = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
            circle3Y = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;
        }
        
        circle3 = new Circle(circle3X, circle3Y, circleSize);
        circle3.addMouseListener(this);
        this.add(circle3);
    }
    
    public void setCircleVisibility(boolean v) {
        circle1.setVisible(v);
        circle2.setVisible(v);
        circle3.setVisible(v);
    }
    
    public void removeAllCircles() {
        this.removeAll();
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }
    @Override
    public void mousePressed(MouseEvent e) {
        
        if (!easyMode) {
            if (running) {
                mouseClicks++;
            }

            if (e.getSource() == circle1) {

                try {

                    tempRobot = new Robot();

                    Point cursorPosition = MouseInfo.getPointerInfo().getLocation();
                    int cursorX = (int) cursorPosition.getX();
                    int cursorY = (int) cursorPosition.getY();

                    Color clickedColor = tempRobot.getPixelColor(cursorX, cursorY);

                    if (clickedColor.equals(Color.red)) {

                        if (!running) {
                            this.startGame();
                            displayCircleCounter(MainFrame.topLeftPanel, MainFrame.extraWidth);
                        }

                        else {
                            totalCirclesHit++;
                            displayCircleCounter(MainFrame.topLeftPanel, MainFrame.extraWidth);
                            if (totalCirclesHit == totalCircles) {
                                this.endGame();
                            }
                        }
                        int tempCircleX = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
                        int tempCircleY = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;

                        while((circle1X == tempCircleX && circle1Y == tempCircleY) || (circle2X == tempCircleX && circle2Y == tempCircleY) || (circle3X == tempCircleX && circle3Y == tempCircleY)) {
                            tempCircleX = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
                            tempCircleY = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;
                        }

                        circle1X = tempCircleX;
                        circle1Y = tempCircleY;
                        circle1.setNewBounds(circle1X, circle1Y);

                    }
                    
                    else if (suddenDeathMode) {
                        this.reset();
                        this.setCircleVisibility(true);
                    }
                    
                } 

                catch (AWTException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            if (e.getSource() == circle2) {

                try {

                    tempRobot = new Robot();

                    Point cursorPosition = MouseInfo.getPointerInfo().getLocation();
                    int cursorX = (int) cursorPosition.getX();
                    int cursorY = (int) cursorPosition.getY();

                    Color clickedColor = tempRobot.getPixelColor(cursorX, cursorY);

                    if (clickedColor.equals(Color.red)) {

                        if (!running) {
                            this.startGame();
                            displayCircleCounter(MainFrame.topLeftPanel, MainFrame.extraWidth);
                        }

                        else {
                            totalCirclesHit++;
                            displayCircleCounter(MainFrame.topLeftPanel, MainFrame.extraWidth);
                            if (totalCirclesHit == totalCircles) {
                                this.endGame();
                            }
                        }
                        int tempCircleX = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
                        int tempCircleY = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;

                        while((circle1X == tempCircleX && circle1Y == tempCircleY) || (circle2X == tempCircleX && circle2Y == tempCircleY) || (circle3X == tempCircleX && circle3Y == tempCircleY)) {
                            tempCircleX = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
                            tempCircleY = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;
                        }

                        circle2X = tempCircleX;
                        circle2Y = tempCircleY;
                        circle2.setNewBounds(circle2X, circle2Y);

                    }
                    
                    else if (suddenDeathMode) {
                        this.reset();
                        this.setCircleVisibility(true);
                    }

                } 

                catch (AWTException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            if (e.getSource() == circle3) {

                try {

                    tempRobot = new Robot();

                    Point cursorPosition = MouseInfo.getPointerInfo().getLocation();
                    int cursorX = (int) cursorPosition.getX();
                    int cursorY = (int) cursorPosition.getY();

                    Color clickedColor = tempRobot.getPixelColor(cursorX, cursorY);

                    if (clickedColor.equals(Color.red)) {

                        if (!running) {
                            this.startGame();
                            displayCircleCounter(MainFrame.topLeftPanel, MainFrame.extraWidth);
                        }

                        else {
                            totalCirclesHit++;
                            displayCircleCounter(MainFrame.topLeftPanel, MainFrame.extraWidth);
                            if (totalCirclesHit == totalCircles) {
                                this.endGame();
                            }
                        }

                        int tempCircleX = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
                        int tempCircleY = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;

                        while((circle1X == tempCircleX && circle1Y == tempCircleY) || (circle2X == tempCircleX && circle2Y == tempCircleY) || (circle3X == tempCircleX && circle3Y == tempCircleY)) {
                            tempCircleX = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
                            tempCircleY = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;
                        }

                        circle3X = tempCircleX;
                        circle3Y = tempCircleY;
                        circle3.setNewBounds(circle3X, circle3Y);

                    }
                    
                    else if (suddenDeathMode) {
                        this.reset();
                        this.setCircleVisibility(true);
                    }

                } 

                catch (AWTException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
            else if (suddenDeathMode && e.getSource() != circle1 && e.getSource() != circle2 && e.getSource() != circle3) {
                this.reset();
                this.setCircleVisibility(true);
            }
            
        }
        
        
        
        
        
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
        if(easyMode) {

            if (e.getSource() == circle1) {

                try {

                    tempRobot = new Robot();

                    Point cursorPosition = MouseInfo.getPointerInfo().getLocation();
                    int cursorX = (int) cursorPosition.getX();
                    int cursorY = (int) cursorPosition.getY();

                    Color clickedColor = tempRobot.getPixelColor(cursorX, cursorY);

                    while (!clickedColor.equals(Color.red)) {
                        cursorPosition = MouseInfo.getPointerInfo().getLocation();
                        cursorX = (int) cursorPosition.getX();
                        cursorY = (int) cursorPosition.getY();

                        clickedColor = tempRobot.getPixelColor(cursorX, cursorY);
                    }

                    if (!running) {
                        this.startGame();
                        displayCircleCounter(MainFrame.topLeftPanel, MainFrame.extraWidth);
                    }

                    else {
                        totalCirclesHit++;
                        mouseClicks++;
                        displayCircleCounter(MainFrame.topLeftPanel, MainFrame.extraWidth);
                        if (totalCirclesHit == totalCircles) {
                            this.endGame();
                        }
                    }

                    int tempCircleX = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
                    int tempCircleY = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;

                    while((circle1X == tempCircleX && circle1Y == tempCircleY) || (circle2X == tempCircleX && circle2Y == tempCircleY) || (circle3X == tempCircleX && circle3Y == tempCircleY)) {
                        tempCircleX = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
                        tempCircleY = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;
                    }

                    circle1X = tempCircleX;
                    circle1Y = tempCircleY;
                    circle1.setNewBounds(circle1X, circle1Y);
                }
                
                catch (AWTException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

            if (e.getSource() == circle2) {

                try {

                    tempRobot = new Robot();

                    Point cursorPosition = MouseInfo.getPointerInfo().getLocation();
                    int cursorX = (int) cursorPosition.getX();
                    int cursorY = (int) cursorPosition.getY();

                    Color clickedColor = tempRobot.getPixelColor(cursorX, cursorY);

                    while (!clickedColor.equals(Color.red)) {
                        cursorPosition = MouseInfo.getPointerInfo().getLocation();
                        cursorX = (int) cursorPosition.getX();
                        cursorY = (int) cursorPosition.getY();

                        clickedColor = tempRobot.getPixelColor(cursorX, cursorY);
                    }

                    if (!running) {
                        this.startGame();
                        displayCircleCounter(MainFrame.topLeftPanel, MainFrame.extraWidth);
                    }

                    else {
                        totalCirclesHit++;
                        mouseClicks++;
                        displayCircleCounter(MainFrame.topLeftPanel, MainFrame.extraWidth);
                        if (totalCirclesHit == totalCircles) {
                            this.endGame();
                        }
                    }

                    int tempCircleX = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
                    int tempCircleY = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;

                    while((circle1X == tempCircleX && circle1Y == tempCircleY) || (circle2X == tempCircleX && circle2Y == tempCircleY) || (circle3X == tempCircleX && circle3Y == tempCircleY)) {
                        tempCircleX = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
                        tempCircleY = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;
                    }

                    circle1X = tempCircleX;
                    circle1Y = tempCircleY;
                    circle2.setNewBounds(circle1X, circle1Y);
                }
                
                catch (AWTException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

            if (e.getSource() == circle3) {

                try {

                    tempRobot = new Robot();

                    Point cursorPosition = MouseInfo.getPointerInfo().getLocation();
                    int cursorX = (int) cursorPosition.getX();
                    int cursorY = (int) cursorPosition.getY();

                    Color clickedColor = tempRobot.getPixelColor(cursorX, cursorY);

                    while (!clickedColor.equals(Color.red)) {
                        cursorPosition = MouseInfo.getPointerInfo().getLocation();
                        cursorX = (int) cursorPosition.getX();
                        cursorY = (int) cursorPosition.getY();

                        clickedColor = tempRobot.getPixelColor(cursorX, cursorY);
                    }

                    if (!running) {
                        this.startGame();
                        displayCircleCounter(MainFrame.topLeftPanel, MainFrame.extraWidth);
                    }

                    else {
                        totalCirclesHit++;
                        mouseClicks++;
                        displayCircleCounter(MainFrame.topLeftPanel, MainFrame.extraWidth);
                        if (totalCirclesHit == totalCircles) {
                            this.endGame();
                        }
                    }

                    int tempCircleX = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
                    int tempCircleY = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;

                    while((circle1X == tempCircleX && circle1Y == tempCircleY) || (circle2X == tempCircleX && circle2Y == tempCircleY) || (circle3X == tempCircleX && circle3Y == tempCircleY)) {
                        tempCircleX = (int)(Math.random()*(panelWidth / circleSize)) * circleSize;
                        tempCircleY = (int)(Math.random()*(panelHeight / circleSize)) * circleSize;
                    }

                    circle1X = tempCircleX;
                    circle1Y = tempCircleY;
                    circle3.setNewBounds(circle1X, circle1Y);
                }
                
                catch (AWTException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                

            }
            
        }
            
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    
    
}

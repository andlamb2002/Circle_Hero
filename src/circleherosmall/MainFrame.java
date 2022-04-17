/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circleherosmall;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Andreas Lambropoulos
 */
public class MainFrame extends JFrame implements ActionListener, MouseListener, KeyListener, ChangeListener{
    
    //Variables for using/calculating bounds
    private int frameWidth;
    private int frameHeight;
    private final int mainPanelWidth = 640;
    private final int mainPanelHeight = 480;
    static int extraWidth;
    private int extraHeight;
    private final int topPanelHeights = 150;
    private final int selectionSettingPanelWidth = 350;
    
    //Default Values
    private final int defaultCircleSize = 80;
    private final int defaultTotalCircles = 50;
    private final String defaultGameMode = "Standard";
    
    private int circleSize = 80;
    private int totalCircles = 50;
    private String gameMode = "Standard";
    
    ImageIcon circleHeroLogo = new ImageIcon("circleHeroLogo.png");
    
    TopPanel topPanel;
    MainMenuPanel mainMenuPanel;
    static TopLeftPanel topLeftPanel;
    static LeftPanel leftPanel;
    TopRightPanel topRightPanel;
    static RightPanel rightPanel;
    GamePanel gamePanel;
    SettingsPanel settingsPanel;
    BasicPanel selectionSettingsPanel;
    
    JComboBox circleSizeComboBox;
    JSlider totalCirclesSlider;
    JLabel totalCirclesLabel;
    JComboBox gameModeComboBox;
    
    //Score Panels and Labels
    BasicPanel numCirclesPanel;
    BasicPanel accuracyPanel;
    
    //Buttons
    private JButton playButton;
    private JButton settingsButton;
    private JButton quitButton;
    
    private JButton saveButton;
    private JButton resetButton;
    
    public MainFrame() {
    
        //Main Frame
        this.setTitle("Circle Hero");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(mainPanelWidth, mainPanelHeight);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        
        this.setIconImage(circleHeroLogo.getImage());
        this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        
        //Calculating bounds
        Rectangle frameSize = this.getBounds();
        frameWidth = frameSize.width;
        frameHeight = frameSize.height;
        extraWidth = (int)((frameWidth - mainPanelWidth) / 2);
        extraHeight = (int)((frameHeight - mainPanelHeight) / 2);
       
        //Create and Add Panels
        topPanel = new TopPanel(extraWidth, 0, mainPanelWidth, extraHeight);
        mainMenuPanel = new MainMenuPanel(extraWidth, extraHeight);
        topLeftPanel = new TopLeftPanel(0, 0, extraWidth, topPanelHeights);
        topLeftPanel.setVisible(false);
        leftPanel = new LeftPanel(0, topPanelHeights, extraWidth, (frameHeight - topPanelHeights));
        topRightPanel = new TopRightPanel((extraWidth + mainPanelWidth), 0, extraWidth, topPanelHeights);
        rightPanel = new RightPanel((extraWidth + mainPanelWidth), topPanelHeights, extraWidth, (frameHeight - topPanelHeights));
        gamePanel = new GamePanel(extraWidth, extraHeight);
        gamePanel.setVisible(false);
        settingsPanel = new SettingsPanel(extraWidth, extraHeight);
        settingsPanel.setVisible(false);
        selectionSettingsPanel = new BasicPanel((mainPanelWidth - selectionSettingPanelWidth)/2, 0, selectionSettingPanelWidth, mainPanelHeight);
        selectionSettingsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 75));
        
        this.add(mainMenuPanel);
        this.add(topLeftPanel);
        this.add(leftPanel);
        this.add(topRightPanel);
        this.add(rightPanel);
        this.add(gamePanel);
        this.add(settingsPanel);
        this.add(topPanel);
        
        //Circle Size Combo Box
        selectionSettingsPanel.add(new BasicPanel(0, 0, 200, 100).add(new TextLabel("Circle Size: ", 20, Color.black)));
        Integer circleSizeList[] = {80, 40, 160};
        circleSizeComboBox = new JComboBox(circleSizeList);
        circleSizeComboBox.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXX");
        selectionSettingsPanel.add(circleSizeComboBox);
        
        //Total Circles Slider
        selectionSettingsPanel.add(new BasicPanel(0, 100, 200, 100).add(new TextLabel("Total: ", 20, Color.black)));
        totalCirclesSlider = new JSlider(20, 100, 50);
        totalCirclesSlider.setPreferredSize(new Dimension(175, 35));
        totalCirclesSlider.setPaintTrack(true);
        totalCirclesSlider.setMajorTickSpacing(10);
        totalCirclesSlider.setPaintLabels(true);
        totalCirclesSlider.addChangeListener(this);
        totalCirclesLabel = new JLabel();
        totalCirclesLabel.setText("  " + totalCirclesSlider.getValue());
        totalCirclesLabel.setFont(new Font("VERDANA", Font.PLAIN, 20));
        selectionSettingsPanel.add(totalCirclesSlider);
        selectionSettingsPanel.add(totalCirclesLabel);
        
        //Game Mode Combo Box
        selectionSettingsPanel.add(new BasicPanel(0, 0, 200, 100).add(new TextLabel("Game Mode: ", 20, Color.black)));
        String modeList[] = {"Standard", "Easy Mode", "Sudden Death"};
        gameModeComboBox = new JComboBox(modeList);
        gameModeComboBox.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXX");
        selectionSettingsPanel.add(gameModeComboBox);
        
        selectionSettingsPanel.setBackground(Color.lightGray);
        Border settingsBorder = BorderFactory.createLineBorder(Color.black, 3);
        selectionSettingsPanel.setBorder(settingsBorder);
        settingsPanel.add(selectionSettingsPanel);
        
        //Save Button
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        saveButton.setBounds(0, 0, 250, 150);/////
        saveButton.setFocusable(false);
        saveButton.setFont(new Font("VERDANA", Font.PLAIN, 20));
        saveButton.setForeground(Color.white);
        saveButton.setBackground(Color.green);
        selectionSettingsPanel.add(saveButton);
        
        //Reset Button
        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        resetButton.setBounds(0, 0, 250, 150);/////
        resetButton.setFocusable(false);
        resetButton.setFont(new Font("VERDANA", Font.PLAIN, 20));
        resetButton.setForeground(Color.white);
        resetButton.setBackground(Color.red);
        selectionSettingsPanel.add(resetButton);
        
        //Play Button
        playButton = new JButton("PLAY");
        playButton.addActionListener(this);
        playButton.setBounds(195, 65, 250, 150);
        playButton.setFocusable(false);
        playButton.setFont(new Font("VERDANA", Font.BOLD, 35));
        playButton.setForeground(Color.white);
        playButton.setBackground(Color.green);
        mainMenuPanel.add(playButton);
        
        //Settings Button
        settingsButton = new JButton("SETTINGS");
        settingsButton.addActionListener(this);
        settingsButton.setBounds(195, 265, 250, 150);
        settingsButton.setFocusable(false);
        settingsButton.setFont(new Font("VERDANA", Font.BOLD, 20));
        settingsButton.setForeground(Color.white);
        settingsButton.setBackground(Color.gray);
        mainMenuPanel.add(settingsButton);
        
        //Quit Button
        quitButton = new JButton("<-");
        quitButton.addActionListener(this);
        quitButton.setBounds((extraWidth - 150), 35, 100, 100);/////
        quitButton.setVisible(false);
        quitButton.setFocusable(false);
        quitButton.setFont(new Font("VERDANA", Font.BOLD, 20));
        quitButton.setForeground(Color.white);
        quitButton.setBackground(Color.green);
        topRightPanel.add(quitButton);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == playButton) {
            mainMenuPanel.setVisible(false);
            rightPanel.setVisible(false);
            quitButton.setVisible(true);
            gamePanel.setVisible(true);
            gamePanel.setCircleVisibility(true);
            if (gameMode.equals("Easy Mode")) {
                gamePanel.setEasyMode(true);
            }
            if (gameMode.equals("Sudden Death")) {
                gamePanel.setSuddenDeathMode(true);
            }
        }
        
        if(e.getSource() == settingsButton) {
            mainMenuPanel.setVisible(false);
            quitButton.setVisible(true);
            settingsPanel.setVisible(true);
            selectionSettingsPanel.setVisible(true);
        }
        
        if(e.getSource() == quitButton) {
            quitButton.setVisible(false);
            gamePanel.setVisible(false);
            settingsPanel.setVisible(false);
            topLeftPanel.setVisible(false);
            mainMenuPanel.setVisible(true);
            
            circleSizeComboBox.setSelectedItem(circleSize);
            totalCirclesSlider.setValue(totalCircles);
            gameModeComboBox.setSelectedItem(gameMode);
            
            gamePanel.reset();
            gamePanel.setCircleVisibility(true);
        }
        
        if(e.getSource() == saveButton) {
            circleSize = (int) circleSizeComboBox.getSelectedItem();
            totalCircles = totalCirclesSlider.getValue();
            gameMode = (String) gameModeComboBox.getSelectedItem();
            gamePanel.setCircleSize(circleSize);
            gamePanel.setTotalCircles(totalCircles);
            
            if (gameMode.equals("Standard")) {
                gamePanel.setEasyMode(false);
                gamePanel.setSuddenDeathMode(false);
            }
            
            if (gameMode.equals("Easy Mode")) {
                gamePanel.setSuddenDeathMode(false);
            }
            if (gameMode.equals("Sudden Death")) {
                gamePanel.setEasyMode(false);
            }
            
            gamePanel.setHighScore(0);
            leftPanel.setVisible(false);
            gamePanel.setCircleVisibility(false);
            gamePanel.addCircle1();
            gamePanel.addCircle2();
            gamePanel.addCircle3();
            
            quitButton.setVisible(false);
            settingsPanel.setVisible(false);
            mainMenuPanel.setVisible(true);
            
        }
        
        if(e.getSource() == resetButton) {
            circleSizeComboBox.setSelectedItem(defaultCircleSize);
            totalCirclesSlider.setValue(defaultTotalCircles);
            gameModeComboBox.setSelectedItem(defaultGameMode);
            
            circleSize = defaultCircleSize;
            totalCircles = defaultTotalCircles;
            gameMode = defaultGameMode;
            gamePanel.setCircleSize(circleSize);
            gamePanel.setTotalCircles(totalCircles);
            gamePanel.setEasyMode(false);
            
            gamePanel.setHighScore(0);
            leftPanel.setVisible(false);
            gamePanel.setCircleVisibility(false);
            gamePanel.addCircle1();
            gamePanel.addCircle2();
            gamePanel.addCircle3();
            
            quitButton.setVisible(false);
            settingsPanel.setVisible(false);
            mainMenuPanel.setVisible(true);
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyChar() == 'r' && gamePanel.isRunning()) {
            gamePanel.reset();
            gamePanel.setCircleVisibility(true);
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        totalCirclesLabel.setText("  " + totalCirclesSlider.getValue());
    }
    
}

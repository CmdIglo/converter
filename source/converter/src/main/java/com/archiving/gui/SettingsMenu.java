package com.archiving.gui;

import javax.swing.JFrame;

/**
 * Settings menu window class
 * 
 * @author Maxwell Leu
 */
public class SettingsMenu extends JFrame{
    
    /**
     * Class Cunstructor
     * @param width     The width of the window
     * @param height    The height of the window
     */
    public SettingsMenu(int width, int height) {
        this.setSize(width, height);
        this.setVisible(true);
    }

    /**
     * Gets the covers storage folder location set by user
     * @return  The covers storage location
     */
    public String getCovLoc() {
        return "";
    }

    /**
     * Gets the ebooks storage folder location set by user
     * @return  The eBooks storage location
     */
    public String getEbookLoc() {
        return "";
    }
}

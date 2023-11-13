package com.archiving;

import com.archiving.gui.MainWindow;

/**
 * Main Class
 *
 * @author Maxwell Leu
 */
public class App {

    public static void main( String[] args ) {
        MainWindow Win = new MainWindow(400, 400, "Konvertierer");
        Win.getWindow().setResizable(false);
        Win.getWindow().setVisible(true);
    }

}

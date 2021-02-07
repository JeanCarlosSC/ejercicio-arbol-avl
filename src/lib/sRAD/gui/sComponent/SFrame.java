package lib.sRAD.gui.sComponent;

import lib.sRAD.gui.component.MainBar;
import lib.sRAD.gui.component.Theme;
import lib.sRAD.gui.component.VentanaEmergente;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static lib.sRAD.gui.component.Resource.blackBorderTransparent;

public class SFrame extends JFrame {

    public void setProperties() {
        setProperties(1280, 720, Theme.bg3, true, blackBorderTransparent, null, true);
    }

    public void setProperties(int width, int height) {
        setProperties(width, height, Theme.bg3, true, blackBorderTransparent, null);
    }

    public void setProperties(int width, int height, Color background) {
        setProperties(width, height, background, true, blackBorderTransparent, null);
    }

    public void setProperties(int width, int height, Color background, Boolean undecorated, Border border, Component relativeLocation) {
        setSize(width, height);
        getContentPane().setBackground(background);
        setUndecorated(undecorated);
        rootPane.setBorder(border);
        setLocationRelativeTo(relativeLocation);
        setLayout(null);
    }

    public void setProperties(int width, int height, Color background, Boolean undecorated, Border border, Component relativeLocation,
                              Boolean visible) {
        setProperties(width, height, background, undecorated, border, relativeLocation);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(visible);
    }

    public void setMainBar(String title) {
        setMainBar(title, "resources/exampleLogo.png");
    }

    public void setMainBar(String title, String pathLogo) {
        MainBar mainBar = new MainBar(this);
        mainBar.setTitle(title);
        mainBar.setLogo(new ImageIcon(pathLogo));
        this.add(mainBar);
    }

}

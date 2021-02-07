package lib.sRAD.gui.sComponent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static lib.sRAD.gui.component.Resource.semiDarkGray2Border;
import static lib.sRAD.gui.component.Resource.DTII1;

public class SPanel extends JPanel {

    public SPanel(int x, int y, int width, int height) {
        setProperties(x, y, width, height);
    }

    public SPanel() {
        this(0, 0, 0, 0, DTII1, semiDarkGray2Border, null);
    }

    public SPanel(int x, int y, int width, int height, Color background, Border border) {
        setProperties(x, y, width, height, background, border, null);
    }

    public SPanel(int x, int y, int width, int height, Color background, Border border, LayoutManager layout) {
        setProperties(x, y, width, height, background, border, layout);
    }

    public void setSize(int width, int height) {
        super.setSize(width, height);
        setPreferredSize(new Dimension(width, height));
    }

    public void setProperties(int x, int y, int width, int height) {
        setProperties(x, y, width, height, DTII1, semiDarkGray2Border, null);
    }

    public void setProperties(int x, int y, int width, int height, Color background, Border border) {
        setProperties(x, y, width, height, background, border, null);
    }

    public void setProperties(int x, int y, int width, int height, Color background, Border border, LayoutManager layout) {
        this.setBounds(x, y, width, height);
        this.setBackground(background);
        this.setBorder(border);
        this.setLayout(layout);
    }

}

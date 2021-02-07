package lib.sRAD.gui.sComponent;

import lib.sRAD.gui.component.Theme;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static lib.sRAD.gui.component.Resource.fontText;
import static lib.sRAD.gui.component.Resource.semiDarkGray2Border;

public class STextField extends JTextField {
    //DEFAULT
    public STextField() {
        super();
    }

    //EMPTY
    public STextField(int x, int y, int width, int height) {
        setProperties(x, y, width, height);
    }

    public void setProperties(int x, int y, int width, int height) {
        setProperties(x, y, width, height, true, Theme.ft, Theme.bg2, fontText, semiDarkGray2Border, LEFT);
    }

    public void setProperties(int x, int y, int width, int height, Boolean editable, Color foreground, Color background, Font font,
                              Border border, int hAlignment) {
        this.setBounds(x, y, width, height);
        setEditable(editable);
        setForeground(foreground);
        setFont(font);
        setBackground(background);
        setCaretColor(foreground);
        setBorder(border);
        setHorizontalAlignment(hAlignment);
    }

}

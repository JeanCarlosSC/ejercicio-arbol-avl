package lib.sRAD.gui.sComponent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static lib.sRAD.gui.component.Resource.*;

public class STextArea extends JTextArea {

    public final static int SHOW_INFO_TYPE = 0;
    public final static int GET_INFO_TYPE = 1;

    /**
     * GET_INFO_TYPE
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public STextArea(int x, int y, int width, int height) {
        setProperties(x, y, width, height, true, true, "", darkWhite, darkGray, fontText, semiDarkGrayBlueBorder, LEFT_ALIGNMENT);
    }

    /**
     * SHOW_INFO_TYPE
     * @param x
     * @param y
     * @param width
     * @param height
     * @param text
     */
    public STextArea(int x, int y, int width, int height, String text) {
        setProperties(x, y, width, height, false, true, text, darkWhite, null, fontText, null, LEFT_ALIGNMENT);
    }

    public void setProperties(int x, int y, int width, int height, Boolean editable, Boolean lineWrap, String text, Color foreground, Color background,
                              Font font, Border border, Float hAlignment
    ) {
        this.setBounds(x, y, width, height);
        setText(text);
        setEditable(editable);
        setForeground(foreground);
        setFont(font);
        setBackground(background);
        setCaretColor(foreground);
        setBorder(border);
        setWrapStyleWord(lineWrap);
        setLineWrap(lineWrap);
        setAlignmentX(hAlignment);
    }

}

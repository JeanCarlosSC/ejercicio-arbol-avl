package lib.sRAD.gui.sComponent;

import lib.sRAD.gui.component.Theme;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static lib.sRAD.gui.component.Resource.*;

public class SButton extends JButton {

    /**
     * default button
     */
    public SButton() {
        super();
    }

//ICON BUTTON
    public SButton(int x, int y, Icon icon) {
        setProperties(x, y, icon, handCursor);
    }

    public SButton(int x, int y, Icon icon, Cursor cursor) {
        setProperties(x, y, icon, cursor);
    }

//TEXT BUTTON
    public SButton(int x, int y, int width, int height, String text) {
        this(x, y, width, height, text, handCursor, fontTitleMini, Theme.bbg, darkWhite, semiDarkGray2Border, semiDarkGray, semiDarkGray2Border);
    }
    public SButton(int x, int y, int width, int height, String text, Cursor cursor, Font font, Color background, Color foreground, Border border,
                   Color backgroundEntered, Border borderEntered) {
        setProperties(x, y, width, height, text, cursor, font, background, foreground, border, backgroundEntered, borderEntered);
    }

    public SButton(int x, int y, int width, int height, String text, Color background, Color backgroundEntered, Border borderEntered, Color foreground) {
        setProperties(x, y, width, height, text, handCursor, fontTitleMini, background, foreground, semiDarkGray2Border, backgroundEntered, borderEntered);
    }

    public void setProperties(int x, int y, int width, int height, String text, Cursor cursor, Font font, Color background, Color foreground,
                              Border border, Color backgroundEntered, Border borderEntered) {
        setProperties(x, y, width, height, cursor, background);
        setText(text);
        setFont(font);
        setForeground(foreground);
        setBorder(border);
        setHorizontalAlignment(SButton.CENTER);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(backgroundEntered);
                setBorder(borderEntered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(background);
                setBorder(border);
            }
        });
    }

    public void setProperties(int x, int y, int width, int height, Cursor cursor, Color background) {
        setLocation(x, y);
        setSize(width, height);
        setCursor(cursor);
        setBackground(background);
        setContentAreaFilled(true);
        setFocusPainted(false);
    }

    public void setProperties(int x, int y, Icon icon, Cursor cursor) {
        setLocation(x, y);
        setContentAreaFilled(false);
        setBorder(null);
        setCursor(cursor);
        setFocusable(false);
        setFocusPainted(false);
        if (icon != null) {
            this.setSize(icon.getIconWidth(), icon.getIconHeight());
            this.setIcon(icon);
        }
    }

    /**
     * text button filled with icon
     */
    public void setProperties(int x, int y, int width, int height, Cursor cursor, Color background, Boolean isSolid, Icon icon) {
        this.setProperties(x, y, icon, cursor);
        this.setSize(width, height);
        this.setBackground(background);
        this.setContentAreaFilled(isSolid);
    }

}

package lib.sRAD.gui.sComponent;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

import static lib.sRAD.gui.component.Resource.*;

public class SMenuBar extends JMenuBar {

    private Color generalBackground;
    private Color generalForeground;
    private Border generalBorder;

    public SMenuBar() {
        this(0, 29, 1280, 28, DTII1, darkWhite, semiDarkGrayBlueBorder);
    }

    public SMenuBar (int x, int y, int width, int height, Color background, Color foreground, Border border) {
        generalBackground = background;
        generalForeground = foreground;
        generalBorder = border;

        this.setBackground(generalBackground);
        this.setBorder(generalBorder);
        this.setBounds(x, y, width, height);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (MenuElement menuElement: getSubElements()) {
            JMenu menu = (JMenu) menuElement.getComponent();
            changeComponentColors(menu);
            menu.setOpaque(true);
            MenuElement[] menuElements = menu.getSubElements();
            for (MenuElement popupMenuElement: menuElements) {
                JPopupMenu popupMenu = (JPopupMenu) popupMenuElement.getComponent();
                popupMenu.setBorder(null);
                MenuElement[] menuItems = popupMenuElement.getSubElements();
                for (MenuElement menuItemElement: menuItems) {
                    JMenuItem menuItem = (JMenuItem) menuItemElement.getComponent();
                    changeComponentColors(menuItem);
                    menuItem.setOpaque(true);
                }
            }
        }
    }

    private void changeComponentColors(Component comp) {
        comp.setBackground(generalBackground);
        comp.setForeground(generalForeground);
    }

}

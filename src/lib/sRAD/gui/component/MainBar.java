package lib.sRAD.gui.component;

import lib.sRAD.gui.sComponent.SButton;
import lib.sRAD.gui.sComponent.SFrame;
import lib.sRAD.gui.sComponent.SLabel;
import lib.sRAD.gui.sComponent.SPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static lib.sRAD.gui.component.Resource.*;

public class MainBar extends SPanel {

    private final SPanel mainPanel;

    private final ImageIcon iBtExitOn = new ImageIcon("resources/mainBar/btExitOn.png");
    private final ImageIcon iBtExitOff = new ImageIcon("resources/mainBar/btExitOff.png");
    private final ImageIcon iBtMinOff = new ImageIcon("resources/mainBar/btMinOff.png");
    private final ImageIcon iBtMinOn = new ImageIcon("resources/mainBar/btMinOn.png");

    private final SLabel lLogo = new SLabel();
    private final SLabel lTitle = new SLabel();

    private final SButton btExit = new SButton();

    private int x0 = 0;
    private int y0 = 0;

    public MainBar(SFrame frame) {
        this(frame, 1280, true, Theme.bg2, DTII3);
    }

    public MainBar(SFrame frame, int screenWidth, Boolean move, Color backgroundColor, Color borderColor) {
        mainPanel = new SPanel(0, 0, screenWidth, 27, backgroundColor, null);

        if(move){
            mainPanel.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if(e.getSource() == mainPanel) {
                        frame.setLocation(e.getXOnScreen() - x0, e.getYOnScreen() - y0);
                    }
                }
                @Override
                public void mouseMoved(MouseEvent e) {
                    if(e.getSource() == mainPanel) {
                        x0 = e.getX();
                        y0 = e.getY();
                    }
                }
            });
        }
        add(mainPanel);

        SButton btMin = new SButton(screenWidth - 75, 0, iBtMinOff, defaultCursor);
        btMin.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setExtendedState(SFrame.ICONIFIED);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btMin.setIcon(iBtMinOn);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btMin.setIcon(iBtMinOff);
            }
        });
        mainPanel.add(btMin);

        btExit.setProperties(screenWidth - 48, 0, iBtExitOff, defaultCursor);
        btExit.addMouseListener (new MouseListener() {
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource() == btExit)
                    btExit.setIcon(iBtExitOff);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource() == btExit) {
                    if(JOptionPane.showConfirmDialog(null, "Desea salir?") == 0)
                        System.exit(0);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource() == btExit)
                    btExit.setIcon(iBtExitOn);
            }
        });
        mainPanel.add(btExit);

        mainPanel.add(lLogo);
        mainPanel.add(lTitle);

        setProperties(0, 0, screenWidth, 29, borderColor, null);
    }

    public void setLogo(ImageIcon icon) {
        ImageIcon iLogo = new ImageIcon(icon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
        lLogo.setProperties(5, 5, iLogo);
    }

    public void setTitle(String title) {
        lTitle.setProperties(350, 0, 570, 28, title, fontTitleMini, Theme.fmb, SwingConstants.CENTER);
    }

}

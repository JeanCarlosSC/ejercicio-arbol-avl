package lib.sRAD.gui.component;

import lib.sRAD.gui.sComponent.SFrame;

public class VentanaEmergente extends SFrame {

    private final SFrame frame;

    public VentanaEmergente(SFrame frame, int width, int height) {
        this.frame = frame;
        setProperties(width, height, Theme.vebg);
    }

    public void lanzar() {
        frame.setEnabled(false);
        setVisible(true);
    }

    public void cerrar() {
        frame.setEnabled(true);
        this.setVisible(false);
    }

}

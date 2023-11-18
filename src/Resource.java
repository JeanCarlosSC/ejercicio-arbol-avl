import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Clase para definir los colores, bordes, fuentes, etc.
 */
public class Resource {

//----------------------------------------COLOUR PALETTES---------------------------------------------------------------
    // blue
    public final static Color b1 = new Color(38, 133, 191);
    public final static Color b2 = new Color(61, 157, 217);
    public final static Color b3 = new Color(95, 182, 217);
    public final static Color b4 = new Color(148, 215, 242);
    public final static Color b5 = new Color(187, 232, 242);

    //basic
    public final static Color agca4 = new Color(63, 181, 212);

//--------------------------------------COLOR THEMES--------------------------------------------------------------------
    //tema amigable
    public final static Color ta6 = new Color(108, 142, 191);
// -----------------------------------------FONT------------------------------------------------------------------------
    //Windows standard
    public final static Font fontText = new Font("Arial", Font.PLAIN, 17);

//----------------------------------------BORDER----------------------------------------------------------------
    //tema amigable
    public final static Border ta6Border = BorderFactory.createLineBorder(ta6, 2, false);

    //azul gris celeste análogo
    public final static Border agca4Border = BorderFactory.createLineBorder(agca4, 2, false);

}

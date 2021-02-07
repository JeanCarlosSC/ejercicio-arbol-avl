package lib.sRAD.gui.component;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Resource {

//----------------------------------------COLOUR PALETTES---------------------------------------------------------------
    //basic
    public final static Color black = new Color(0, 0, 0);
    public final static Color red = new Color(255, 0, 0);
    public final static Color white = new Color(255, 255, 255);

    //green complementary cake
    public final static Color gcc1 = new Color(193, 255, 171);
    public final static Color gcc2 = new Color(122, 179, 102);
    public final static Color gcc3 = new Color(212, 255, 196);
    public final static Color gcc4 = new Color(179, 84, 167);
    public final static Color gcc5 = new Color(255, 171, 245);

    //azul gris celeste análogo
    public final static Color agca1 = new Color(74, 75, 246);
    public final static Color agca2 = new Color(63, 104, 212);
    public final static Color agca3 = new Color(82, 164, 235);
    public final static Color agca4 = new Color(63, 181, 212);
    public final static Color agca5 = new Color(74, 246, 235);

    //monochrome dark blue
    public final static Color mdb1 = new Color(46, 49, 107);
    public final static Color mdb2 = new Color(174, 177, 238);
    public final static Color mdb3 = new Color(101, 107, 235);
    public final static Color mdb4 = new Color(78, 80, 107);
    public final static Color mdb5 = new Color(79, 84, 184);

    //warning paleta
    public final static Color wp1 = new Color(72, 140, 3);
    public final static Color wp2 = new Color(132, 217, 4);
    public final static Color wp3 = new Color(242, 203, 5);
    public final static Color wp4 = new Color(242, 159, 5);
    public final static Color wp5 = new Color(242, 68, 5);

    //special palette 1
    public final static Color cyan = new Color(72, 206, 247);
    public final static Color darkCyan = new Color(70, 147, 171);
    public final static Color darkOcher = new Color(169, 105, 0);
    public final static Color mustard = new Color(255, 225, 0);
    public final static Color ocher = new Color(232, 145, 0);
    public final static Color darkBlueGray = new Color(58, 117, 181);
    public final static Color blueGray = new Color(82, 125, 181);

    //others and specials
    public final static Color blackTransparent = new Color(0F, 0F, 0F, 0.8F);
    public final static Color transparentMustard = new Color(1F, 1F, 0F, 0.5F);
    public final static Color transparent = new Color(0F, 0F, 0F, 0F);
    public final static Color superLightGray = new Color(120, 120, 120);
    public final static Color megaDarkWhite = new Color(180, 180, 180);
    public final static Color semiDarkWhite = new Color(210, 210, 210);

//--------------------------------------COLOR THEMES--------------------------------------------------------------------
    //tema amigable
    public final static Color ta1 = new Color(255, 242, 204);
    public final static Color ta2 = new Color(214, 182, 86);
    public final static Color ta3 = new Color(248, 206, 204);
    public final static Color ta4 = new Color(184, 84, 80);
    public final static Color ta5 = new Color(218, 232, 252);
    public final static Color ta6 = new Color(108, 142, 191);
    public final static Color ta7 = new Color(255, 89, 89);

    //Dark Theme based on Intellij Idea
    public final static Color darkGray = new Color(49, 51, 53); //second background
    public final static Color megaDarkGray = new Color(43, 43, 43); //console background
    public final static Color DTII1 = new Color(50, 53, 55); //main background
    public final static Color DTII2 = new Color(60, 63, 65);
    public final static Color DTII3 = new Color(81, 81, 81); //mainToolbar bottom border
    public final static Color DTII4 = new Color(85, 85, 85);//console border
    public final static Color gray = new Color(124, 123, 119); //comment font
    public final static Color semiDarkGray = new Color(114, 115, 122); //var names without use font
    public final static Color grayPurple = new Color(152, 118, 168); //var names in use font
    public final static Color lightGrayGreen = new Color(106, 135, 89); //string font
    public final static Color greenGray = new Color(98,148, 82); //javadoc font
    public final static Color orangeGray = new Color(199,118,50); //reserve word font
    public final static Color lightBlueGray = new Color(104,150,186); //numbers font
    public final static Color darkWhite = new Color(187,187,187); //main color font
// -----------------------------------------FONT------------------------------------------------------------------------
    //Windows standard
    public final static Font fontTitle = new Font("Arial", Font.BOLD, 28);
    public final static Font fontTitle1 = new Font("Gill Sans MT Condensed", Font.PLAIN, 64);
    public final static Font fontTitle2 = new Font("Arial", Font.BOLD, 18);
    public final static Font fontTitleMini = new Font("Arial", Font.PLAIN, 14); //used in title of mainBar, buttons
    public final static Font fontSubtitle = new Font("Gill Sans MT Condensed", Font.PLAIN, 32);
    public final static Font fontVersion = new Font("Arial", Font.PLAIN, 14);

    public final static Font fontText = new Font("Arial", Font.PLAIN, 17);
    public final static Font fontEcuation = new Font("Arial Narrow", Font.ITALIC, 20);
    public final static Font fontTextMini = new Font("Arial Narrow", Font.PLAIN, 14);
    public final static Font fontTextBig = new Font("Arial", Font.PLAIN, 18);
    public final static Font fontTextBold = new Font("Calibri", Font.BOLD, 18);

    //Linux standard
    public final static Font fontTextLinux = new Font("Liberation Serif", Font.PLAIN, 18);
    public final static Font fontTextBoldLinux = new Font("Liberation Serif", Font.BOLD, 18);

//----------------------------------------CURSORS-----------------------------------------------------------------------
//standard
    public final static Cursor defaultCursor = new Cursor(0);
    public final static Cursor crosshairCursor = new Cursor(1);
    public final static Cursor textCursor = new Cursor(2);
    public final static Cursor waitCursor = new Cursor(3);
    public final static Cursor nResizeCursor = new Cursor(8);
    public final static Cursor eResizeCursor = new Cursor(11);
    public final static Cursor handCursor = new Cursor(12);
    public final static Cursor moveCursor = new Cursor(13);

//----------------------------------------BORDER----------------------------------------------------------------
    //standard
    public final static Border blackBorder = BorderFactory.createLineBorder(black, 2, false);
    public final static Border transparentBorder = BorderFactory.createLineBorder(transparent, 2, false);
    public final static Border blackBorderTransparent = BorderFactory.createLineBorder(blackTransparent, 2, false);
    public final static Border semiDarkGrayBlueBorder = BorderFactory.createLineBorder(DTII1, 2, false);
    public final static Border grayBorder = BorderFactory.createLineBorder(gray, 2, false);
    public final static Border semiDarkGray2Border = BorderFactory.createLineBorder(DTII4, 2, false);
    public final static Border darkGrayBorder = BorderFactory.createLineBorder(darkGray, 2, false);

    //tema amigable
    public final static Border ta2Border = BorderFactory.createLineBorder(ta2, 2, false);
    public final static Border ta4Border = BorderFactory.createLineBorder(ta4, 2, false);
    public final static Border ta6Border = BorderFactory.createLineBorder(ta6, 2, false);

    //special palette 1
    public final static Border darkOcherBorder = BorderFactory.createLineBorder(darkOcher, 2, false);

    //azul gris celeste análogo
    public final static Border agca4Border = BorderFactory.createLineBorder(agca4, 2, false);

    //warning paleta
    public final static Border wp2Border = BorderFactory.createLineBorder(wp2, 2, false);

}

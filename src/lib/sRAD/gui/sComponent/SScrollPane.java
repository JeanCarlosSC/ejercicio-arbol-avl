package lib.sRAD.gui.sComponent;

import javax.swing.*;

import static lib.sRAD.gui.component.Resource.semiDarkGray2Border;
import static lib.sRAD.gui.component.Resource.DTII1;
import static lib.sRAD.gui.tool.AdvancedGraphBuilderKt.getCustomScroll;

public class SScrollPane extends JScrollPane {

    public SScrollPane(int x, int y, int width, int height) {
        setProperties(x, y, width, height);
    }

    public void setProperties(int x, int y, int width, int height){
        setBounds(x, y, width, height);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        verticalScrollBar.setUI(getCustomScroll());
        horizontalScrollBar.setUI(getCustomScroll());
        setBackground(DTII1);
        setBorder(semiDarkGray2Border);
    }

}

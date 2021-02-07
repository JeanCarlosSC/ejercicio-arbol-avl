package lib.sRAD.gui.sComponent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

import static lib.sRAD.gui.component.Resource.*;
import static lib.sRAD.gui.tool.AdvancedGraphBuilderKt.getCustomScroll;
import static lib.sRAD.gui.tool.AdvancedGraphBuilderKt.getCustomTable;

public class STable extends JScrollPane {

    public STable(int x, int y, int width, int height, ArrayList<ArrayList<String>> matriz) {
        this(x, y, width, height, matriz, 200, 30);
    }

    public STable(int x, int y, int width, int height, ArrayList<ArrayList<String>> matriz, int cellWidth, int cellHeight) {
        setProperties(x, y, width, height, matriz, cellWidth, cellHeight);
    }

    public void setProperties(int x, int y, int width, int height, ArrayList<ArrayList<String>> matriz, int cellWidth, int cellHeight) {
        Vector<String> placeholdes = new Vector<>();
        for(String element: matriz.get(0)) {
            placeholdes.add(element);
        }
        final DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(placeholdes);

        for (int i = 1; i < matriz.size(); i++) {
            modelo.addRow(new Vector<>());

            for (int j=0; j<matriz.get(0).size(); j++) {
                modelo.setValueAt(matriz.get(i).get(j), i-1, j);
            }
        }

        final JTable tabla = new JTable();
        setProperties(tabla, modelo, cellHeight);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setRowHeight(cellHeight);
        for (int i=0; i<matriz.get(0).size(); i++) {
            tabla.getColumnModel().getColumn(i).setPreferredWidth(cellWidth);
            tabla.getColumnModel().getColumn(i).setMaxWidth(cellWidth);
            tabla.getColumnModel().getColumn(i).setMinWidth(cellWidth);
        }
        tabla.setSize(cellWidth*matriz.get(0).size(), cellHeight*matriz.size());
        tabla.setPreferredSize(new Dimension(cellWidth*matriz.get(0).size(), cellHeight*(matriz.size()-1)));

        JTableHeader header = tabla.getTableHeader();
        header.setBackground(DTII4);
        header.setReorderingAllowed(false);
        header.setSize(cellWidth*matriz.get(0).size(), 30);
        header.setPreferredSize(new Dimension(cellWidth*matriz.get(0).size(), 30));
        header.setDefaultRenderer(getCustomTable(DTII4, null, null, white, fontText));

        setViewportView(tabla);
        setLocation(x, y);
        setSize(width, height);

        setBackground(DTII4);
        viewport.setBackground(DTII1);
        setBorder(semiDarkGray2Border);
        verticalScrollBar.setUI(getCustomScroll());
        horizontalScrollBar.setUI(getCustomScroll());
    }

    public JScrollPane getPanelBar(JTable table, int x, int y, int width, int height) {
        return getPanelBar(table, x, y, width, height, DTII1, null);
    }

    public JScrollPane getPanelBar(JTable table, int x, int y, int width, int height, Color background, Border border) {
        JScrollPane panelScroll = new JScrollPane(table);
        panelScroll.setLocation(x, y);
        panelScroll.setSize(width, height);
        panelScroll.getViewport().setBackground(background);
        panelScroll.setBorder(border);
        return panelScroll;
    }

    public void setProperties(JTable table, DefaultTableModel modelo) {
        setProperties(table, modelo, DTII4, 40);
    }

    public void setProperties(JTable table, DefaultTableModel modelo, int rowHeight) {
        setProperties(table, modelo, DTII4, rowHeight);
    }

    public void setProperties(JTable table, DefaultTableModel modelo, Color gridColor, int rowHeight) {
        table.setModel(modelo);
        table.setRowHeight(rowHeight);
        table.setDefaultRenderer(Object.class, getCustomTable());
        table.setGridColor(gridColor);
    }

}

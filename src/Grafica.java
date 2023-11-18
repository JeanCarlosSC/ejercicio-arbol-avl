import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class Grafica extends JScrollPane {
    Ventana ventana; // referencia de la ventana para enviarle mensajes desde la gráfica
    final ArbolAVL arbol = new ArbolAVL(); // árbol avl
    ArrayList<ArrayList<Integer>> lineas = new ArrayList<>(); // coordenadas de las líneas por dibujar

    private final JPanel pDibujo; // Panel contenedor de la gráfica

    public Grafica(Ventana v) {
        ventana = v; // guarda referencia

        pDibujo = new JPanel() {
            // sobreescribe el método paint para que pinte las líneas
            @Override
            public void paint(Graphics g) {
                super.paint(g); // llama al paint de la clase padre para que pinte los paneles
                g.setColor(Resource.b1); // cambia el color del pincel a azul

                if(!lineas.isEmpty()){ // dibuja si la lista de líneas no está vacía
                    for (ArrayList<Integer> linea: lineas) { // por cada línea en líneas
                        // dibuja línea a partir de dos puntos x1, y1, x2, y2
                        g.drawLine(linea.get(0), linea.get(1), linea.get(2), linea.get(3));
                    }
                }
            }
        };
        pDibujo.setBounds(0, 0, 706, 610);
        // cada vez que cambia el tamaño, hay que usar este método para que actualice las barras del ScrollPane
        pDibujo.setPreferredSize(new Dimension(706, 610));
        pDibujo.setLayout(null);
        pDibujo.setBackground(Resource.b5);
        setViewportView(pDibujo); // establecemos la vista de este panel para que muestre el dibujo

        setBounds(0, 0, 720, 624);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // muestra barra horizontal si es necesaria
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // muestra barra vertical si se necesita
        repaint(); // actualiza la vista para que refleje los cambios
    }

    /**
     * Elimina un nodo del árbol
     */
    public void eliminarNodo() {
        String value = JOptionPane.showInputDialog("Remueva un valor"); // pide valor con un cuadro de texto
        if(value.isEmpty()) { // si no ingresó nada muestra error
            JOptionPane.showMessageDialog(null, "Ingrese un valor", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        else if(Extension.isDouble(value)) {
            // si el valor es válido lo retira del árbol y actualiza los datos
            arbol.retirar(Extension.toDouble(value));
            actualizar();
        }
        else {
            // si ingresa algo que no es un número muestra error
            JOptionPane.showMessageDialog(null, "Ingrese valores válidos", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Inserta un nodo en el árbol
     */
    public void insertarNodo() {
        String value = JOptionPane.showInputDialog("Inserte un valor"); // pide valor con un cuadro de texto
        if(value.isEmpty()) { // si no ingresó nada muestra error
            JOptionPane.showMessageDialog(null, "Ingrese un valor", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        else if(Extension.isDouble(value)) { // si el valor es válido lo inserta en el  árbol y actualiza los datos
            arbol.insertar(Extension.toDouble(value));
            actualizar();
        }
        else {
            // si ingresa algo que no es un número muestra error
            JOptionPane.showMessageDialog(null, "Ingrese valores válidos", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Para hacer las rotaciones y mostrar los cambios en la interfaz
     */
    void actualizar() {
        arbol.organizar(); // balancea el árbol
        pDibujo.removeAll(); // elimina el dibujo existente
        lineas.clear(); // elimina las líneas de la lista anterior
        if(arbol.isNotEmpty()) { // si el árbol no está vacío lo dibuja
            pDibujo.setSize(706, 610);
            dibujar(arbol.raiz); // a partir del nodo raíz, es un método recursivo que llama a los nodos hijos
        }
        pDibujo.repaint();
        ventana.repaint();
        ventana.actualizarInformacion(); // actualiza panel derecho que contiene la información in-orden
    }

    /**
     * Dibuja el árbol AVL
     * @param nodo Recibe el nodo que será dibujado, también se dibujan sus hijos
     */
    private void dibujar(NodoAVL nodo) {
        if(nodo!=null) {
            int ancho = pDibujo.getWidth(); // ancho del dibujo
            int alto = pDibujo.getHeight(); // altura del dibujo

            // modifica tamaño del dibujo si no cabe algún nodo
            if(nodo.x+150>ancho) {
                ancho = nodo.x+150;
            }
            if(nodo.y+60>alto) {
                alto = nodo.y + 60;
            }
            pDibujo.setSize(ancho, alto);
            pDibujo.setPreferredSize(new Dimension(ancho, alto));

            // crea el nodo
            JPanel panel  = new JPanel();
            panel.setBounds(nodo.x, nodo.y, 124, 32);
            panel.setBackground(Resource.b4);
            panel.setBorder(Resource.agca4Border);
            panel.setLayout(null);

            // le pone el número al nodo
            JLabel lValor = new JLabel(nodo.valor+"");
            lValor.setBounds(2, 2, 120, 30);
            lValor.setHorizontalAlignment(JLabel.CENTER);
            lValor.setForeground(Color.BLACK);
            panel.add(lValor);

            pDibujo.add(panel); // agrega el nodo al dibujo

            //guarda líneas requeridas en caso de que tenga hijos
            if(nodo.left != null) {
                lineas.add(new ArrayList<>(Arrays.asList(nodo.x + 52, nodo.y + 30, nodo.left.x + 62, nodo.left.y)));
            }
            if(nodo.right != null) {
                lineas.add(new ArrayList<>(Arrays.asList(nodo.x + 72, nodo.y + 30, nodo.right.x + 62, nodo.right.y)));
            }

            //dibuja nodos hijos (llamada recursiva)
            dibujar(nodo.left);
            dibujar(nodo.right);
        }
    }
}
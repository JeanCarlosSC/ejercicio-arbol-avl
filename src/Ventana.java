import java.awt.Color;

import javax.swing.*;

public class Ventana extends JFrame {
    private final Grafica pGrafica; // Panel izquierdo
    private final JPanel pDerecho; // Panel derecho
    private final JButton bInsertar, bEliminar, bReiniciar; // Botones
    private final JTextArea taInorden; // Campo de texto donde se muestra salida in-orden

    public Ventana() {
        JPanel pIzquierdo = new JPanel();
        pIzquierdo.setBounds(30, 30, 721, 623); // coordenada y tamaño del elemento
        pIzquierdo.setBackground(Color.BLUE); // color de fondo
        pIzquierdo.setLayout(null); // Posicionamiento por coordenadas
        add(pIzquierdo); // se agrega a la ventana

        pGrafica = new Grafica(this); // Se envía la referencia de la ventana a la gráfica con this
        pIzquierdo.add(pGrafica); // se agrega pGrafica a pIzquierdo

        pDerecho = new JPanel();
        pDerecho.setBounds(770, 30, 465, 620);
        pDerecho.setBackground(Resource.b5);
        pDerecho.setLayout(null);
        pDerecho.setBorder(Resource.ta6Border); // Borde azul del panel derecho
        add(pDerecho);

        bInsertar = new JButton("Añadir"); // Se crea botón con el texto "añadir"
        bInsertar.setBounds(32, 32, 100, 32);
        bInsertar.setFont(Resource.fontText); // Se le cambia la fuente
        bInsertar.addActionListener(e -> pGrafica.insertarNodo()); // se le configura una acción al botón
        pDerecho.add(bInsertar);

        bEliminar = new JButton("Eliminar");
        bEliminar.setBounds(164, 32, 100, 32);
        bEliminar.setFont(Resource.fontText);
        bEliminar.addActionListener(e -> pGrafica.eliminarNodo());
        pDerecho.add(bEliminar);

        bReiniciar = new JButton("Reiniciar");
        bReiniciar.setBounds(296, 32, 100, 32);
        bReiniciar.setFont(Resource.fontText);
        bReiniciar.addActionListener(e -> {
            pGrafica.arbol.raiz = null; // Borrar hace que el nodo principal sea nulo
            pGrafica.actualizar(); // Se actualiza la gráfica
            actualizarInformacion(); // Se actualiza el panel derecho
        });

        taInorden = new JTextArea();
        taInorden.setBounds(32, 100, 400, 450);
        taInorden.setEditable(false); // No se puede editar el campo de in-orden
        taInorden.setLineWrap(true); // pasa a la siguiente línea si se llena horizontalmente de texto
        taInorden.setWrapStyleWord(true); // No corta palabras/números cuando salta de línea
        taInorden.setBackground(Resource.b5);
        pDerecho.add(taInorden);

        cargarPropiedades(); // carga las propiedades de la ventana
        actualizarInformacion(); // Actualiza panel derecho inicialmente
    }

    /**
     * Actualiza panel derecho por cada cambio
     */
    public void actualizarInformacion() {
        ArbolAVL arbol = pGrafica.arbol; // guarda el árbol que tiene la gráfica
        pDerecho.remove(bReiniciar); // elimina el botón de reiniciar (sólo lo muestra si hay nodos)

        if(arbol.isNotEmpty()) { // si el árbol no está vacío
            taInorden.setText("In-orden: "+arbol); // muestra el texto in-orden
            pDerecho.add(bReiniciar); // agrega botón para reiniciar
        }
        else {
            taInorden.setText("Por favor ingrese valores"); // si está vacío, pide el ingreso de valores
        }

        repaint();
    }

    /**
     * Propiedades de la ventana
     */
    public void cargarPropiedades() {
        setSize(1280, 720); // tamaño
        setTitle("Graficador de árboles AVL"); // título
        setLocationRelativeTo(null); // Ventana centrada
        setLayout(null); // Layout con sistema de coordenadas
        getContentPane().setBackground(Resource.b3); // fondo
        setVisible(true); // mostrar
    }
}
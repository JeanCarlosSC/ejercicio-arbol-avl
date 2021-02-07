package app

import lib.sRAD.gui.component.Resource.*
import lib.sRAD.gui.component.VentanaEmergente
import lib.sRAD.gui.sComponent.*
import lib.sRAD.logic.isDouble
import java.awt.Graphics
import javax.swing.JOptionPane

abstract class Grafica: SScrollPane(2, 2, 716, 620) {
    val arbol = ArbolAVL()
    private val lineas = mutableListOf<MutableList<Int>>()//coordenadas de las lineas por dibujar

    private val pInterno = object: SPanel(0, 0, 706, 610) {
        override fun paint(g: Graphics?) {
            super.paint(g)
            g!!.color = wp2

            if(lineas.isNotEmpty()){
                for (i in lineas) {
                    g.drawLine(i[0], i[1], i[2], i[3])
                }
            }
        }
    }//Panel contenedor de la gráfica

    init {
        pInterno.background = DTII2
        pInterno.border = null
        add(pInterno)
        setViewportView(pInterno)
    }

    fun removeVertice() {
        val ventana = VentanaEmergente(getCurrentFrame(), 500, 80)

        val lText = SLabel(30, 23, 240, 28,"Remueva un código ")
        ventana.add(lText)

        val taNum = STextField(235, 21, 100, 32)
        taNum.addActionListener {
            if(taNum.text.isNotEmpty()) {
                arbol.retirar(taNum.text.toDouble())
                actualizar()
            }
            else {
                JOptionPane.showMessageDialog(null, "Ingrese un número entero", "Error", JOptionPane.ERROR_MESSAGE)
            }
            ventana.cerrar()
        }
        ventana.add(taNum)

        val btCancelar = SButton(355, 21, 100, 32, "Cancelar")
        btCancelar.addActionListener {
            ventana.cerrar()
        }
        ventana.add(btCancelar)

        ventana.lanzar()
    }

    fun addVertice() {
        val ventana = VentanaEmergente(getCurrentFrame(), 500, 120)

        val lText = SLabel(50, 23, 240, 28,"Inserte el codigo")
        ventana.add(lText)

        val lText1 = SLabel(50, 64, 240, 28,"Inserte el nombre")
        ventana.add(lText1)

        val taNum = STextField(190, 21, 140, 32)
        ventana.add(taNum)

        val taName = STextField(190, 60, 140, 32)
        taName.addActionListener {
            if(taNum.text.isDouble() && taName.text.isNotEmpty()) {
                arbol.insertar(taNum.text.toDouble(), taName.text)
                actualizar()
            }
            else {
                JOptionPane.showMessageDialog(null, "Ingrese valores válidos", "Error", JOptionPane.ERROR_MESSAGE)
            }
            ventana.cerrar()
        }
        ventana.add(taName)

        val btCancelar = SButton(355, 21, 100, 32, "Cancelar")
        btCancelar.addActionListener {
            ventana.cerrar()
        }
        ventana.add(btCancelar)

        ventana.lanzar()
    }

    open fun actualizar() {
        arbol.organizar()
        pInterno.removeAll()
        lineas.clear()
        if(arbol.isNotEmpty()) {
            pInterno.setSize(706, 610)
            dibujar(arbol.raiz)
        }
        pInterno.repaint()
    }

    private fun dibujar(nodo: NodoAVL?) {
        if(nodo!=null) {
            //modifica tamaño del panel
            if(nodo.x+150>pInterno.width) {
                pInterno.setSize(nodo.x + 150, pInterno.height)
            }
            if(nodo.y+60>pInterno.height) {
                pInterno.setSize(pInterno.width, nodo.y + 60)
            }

            //dibuja nodo
            val panel  = SPanel(nodo.x, nodo.y, 124, 32, agca1, agca4Border)

            val lValor = SLabel(2, 2, 120, 30, "%11.0f".format(nodo.codigo))
            lValor.horizontalAlignment = SLabel.CENTER
            lValor.foreground = white
            panel.add(lValor)

            pInterno.add(panel)

            //guarda lineas
            if(nodo.left != null) {
                lineas.add(mutableListOf(nodo.x + 52, nodo.y + 30, nodo.left!!.x + 62, nodo.left!!.y))
            }
            if(nodo.right != null) {
                lineas.add(mutableListOf(nodo.x + 72, nodo.y + 30, nodo.right!!.x + 62, nodo.right!!.y))
            }

            //dibuja nodos hijos
            dibujar(nodo.left)
            dibujar(nodo.right)
        }
    }

    abstract fun getCurrentFrame(): SFrame

}
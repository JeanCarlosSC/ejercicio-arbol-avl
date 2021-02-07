package app

import lib.sRAD.gui.component.Resource.*
import lib.sRAD.gui.sComponent.*
import lib.sRAD.logic.isDouble

class App: SFrame() {
    private val pGrafica: Grafica
    private val pInformativo: SPanel
    private val pIzquierdo: SPanel
    private val bInsertar: SButton
    private val bRetirar: SButton
    private val bBorrar: SButton
    private val tfEsta: STextField //Valor para buscar si está en el árbol
    private val lEsta: SLabel //Label correspondiente al tfEsta
    private val taInorden: STextArea

    init {
        pIzquierdo = SPanel(30, 50, 719, 623)
        add(pIzquierdo)

        pGrafica = object: Grafica() {
            override fun getCurrentFrame(): SFrame {
                return this@App
            }
            override fun actualizar() {
                super.actualizar()
                actualizarInformacion()
            }
        }
        pIzquierdo.add(pGrafica)

        pInformativo = SPanel(770, 50, 484, 650)
        add(pInformativo)

        bInsertar = SButton(32, 32, 25, 25, "+", defaultCursor, fontTitle, null, wp1, null, DTII3, null)
        bInsertar.addActionListener { pGrafica.addVertice() }
        bInsertar.toolTipText = "Insertar valor en el árbol"

        bRetirar = SButton(57, 32, 25, 25, "-", defaultCursor, fontTitle, null, ta7, null, DTII3, null)
        bRetirar.addActionListener { pGrafica.removeVertice() }
        bRetirar.toolTipText = "Retirar valor del árbol"

        bBorrar = SButton(82, 32, 25, 25, "x", defaultCursor, fontTitle, null, ta6, null, DTII3, null)
        bBorrar.addActionListener { pGrafica.arbol.raiz = null; pGrafica.actualizar() }
        bBorrar.toolTipText = "Borrar el arbol actual"

        taInorden = STextArea(32, 100, 400, 400, "Recorrido in-orden:")

        lEsta = SLabel(32, 64, 430, 25, "")
        tfEsta = STextField(96, 64, 130, 28)
        tfEsta.addActionListener {
            lEsta.text = "Buscar                                ${
                if(tfEsta.text.isDouble() && pGrafica.arbol.buscar(pGrafica.arbol.raiz, tfEsta.text.toDouble()).isNotEmpty())
                    "${pGrafica.arbol.buscar(pGrafica.arbol.raiz, tfEsta.text.toDouble())}"
                else "No está en el árbol"
            }"
        }

        setMainBar("Ejercicio árbol AVL")
        setProperties()

        actualizarInformacion()
    }

    private fun actualizarInformacion() {
        val arbol = pGrafica.arbol

        pInformativo.removeAll()
        //carga informacion general
        pInformativo.add(bInsertar)
        pInformativo.add(bRetirar)

        if(arbol.isNotEmpty()) {
            taInorden.text = "In-orden:\n$arbol"
            pInformativo.add(taInorden)
            pInformativo.add(bBorrar)

            lEsta.text = "Buscar"
            pInformativo.add(lEsta)
            pInformativo.add(tfEsta)
        }

        repaint()
    }

}
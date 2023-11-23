import javax.swing.*;

import static java.lang.Math.max;

public class ArbolAVL {
    double minimo = 0;
    public NodoAVL raiz = null;

    /**
     * Inserta un valor en el árbol
     * @param valor para ingresar en el árbol
     */
    public void insertar(double valor) {
        //si no hay árbol
        if(raiz == null) {
            raiz = new NodoAVL(valor);
            return;
        }
        /*
        //si ya está en el árbol
        if(!buscar(raiz, valor).isEmpty()) {
            JOptionPane.showMessageDialog(null, "El código "+valor+" ya está en el árbol");
            return;
        }*/
        //si no está en el árbol
        NodoAVL p = raiz;
        NodoAVL q = null;
        NodoAVL nodo = new NodoAVL(valor);

        while (p != null) {
            if(valor < p.valor) {
                q = p;
                p = p.left;
            }
            else if(valor >= p.valor) {
                q = p;
                p = p.right;
            }
        }
        if(valor < q.valor) {
            q.left = nodo;
        }
        else {
            q.right = nodo;
        }

        //balancea árbol AVL
        calcularFactorBalance(raiz);
        do {
            balancear(raiz);
            calcularFactorBalance(raiz);
        }
        while(!estaBalanceado(raiz));
    }

    public void balancear(NodoAVL nodo) {
        //si el nodo es nulo
        if(nodo == null) {
            return;
        }
        //si el nodo esta desbalanceado
        if(nodo.balance == -2 && nodo.right.balance == -1) {
            rotacionSimpleIzquierda(nodo);
        }
        else if(nodo.balance == 2 && nodo.left.balance == 1) {
            rotacionSimpleDerecha(nodo);
        }
        else if(nodo.balance == -2 && nodo.right.balance == 1) {
            rotacionDobleIzquierda(nodo);
        }
        else if(nodo.balance == 2 && nodo.left.balance == -1) {
            rotacionDobleDerecha(nodo);
        }
        else if(nodo.balance == -2 && nodo.right.balance == 0) {
            rotacionSimpleIzquierda(nodo);
        }
        else if(nodo.balance == 2 && nodo.left.balance == 0) {
            rotacionSimpleDerecha(nodo);
        }
        else {
            //balancea los demás nodos
            balancear(nodo.left);
            balancear(nodo.right);
        }
    }

    public void retirar(double valor) {
        //El arbol tiene que existir y el valor tiene que estar en el árbol o el árbol no se modificará
        if(buscar(raiz, valor).isEmpty()) {
            return;
        }
        //si no tiene hijos, se elimina el nodo
        NodoAVL nodo = obtenerNodo(raiz, valor);
        if(nodo.left == null && nodo.right == null) {
            eliminar(raiz, nodo.valor);
        }
        else {
            //si no tiene sucesor
            NodoAVL sucesor = obtenerSucesorIn(nodo);
            NodoAVL antecesor = obtenerPariente(raiz, nodo);

            if (sucesor == null) {
                //si tiene antecesor
                if (antecesor != null) {
                    antecesor.right = nodo.left;
                } else {
                    raiz = nodo.left;
                }
            }
            else {
                //si existe pariente
                NodoAVL pariente = obtenerPariente(raiz, sucesor);

                if (pariente != null) {
                    //si el nodo es el pariente
                    if (pariente.valor == nodo.valor) {
                        nodo.right = sucesor.right;
                    } else {
                        //si el sucesor es antecesor
                        if (antecesor != null && sucesor.valor == antecesor.valor) {
                            antecesor.left = nodo.left;
                        } else {
                            //si el pariente es derecho
                            if (pariente.left != null && pariente.left.valor == sucesor.valor) {
                                pariente.left = sucesor.right;
                            } else {
                                pariente.right = sucesor.right;
                            }
                        }
                    }
                } else {
                    raiz.left = nodo.left;
                }
                nodo.valor = sucesor.valor;
            }
        }
        //balancea árbol AVL
        calcularFactorBalance(raiz);
        do {
            balancear(raiz);
            calcularFactorBalance(raiz);
        }
        while(!estaBalanceado(raiz));
    }

    public void rotacionSimpleIzquierda(NodoAVL nodo) {
        NodoAVL r = nodo.right.left;

        NodoAVL q = new NodoAVL(nodo.valor);
        q.left = nodo.left;
        q.right = r;

        nodo.valor = nodo.right.valor;

        nodo.left = q;
        nodo.right = nodo.right.right;
    }

    public void rotacionSimpleDerecha(NodoAVL nodo) {
        NodoAVL r = nodo.left.right;

        NodoAVL q = new NodoAVL(nodo.valor);
        q.right = nodo.right;
        q.left = r;

        nodo.valor = nodo.left.valor;

        nodo.right = q;
        nodo.left = nodo.left.left;
    }

    public void rotacionDobleIzquierda(NodoAVL nodo) {
        NodoAVL p = nodo.right;
        NodoAVL r = p.left;

        p.left = r.right;
        r.right = p;
        nodo.right = r;

        rotacionSimpleIzquierda(nodo);
    }

    public void rotacionDobleDerecha(NodoAVL nodo) {
        NodoAVL p = nodo.left;
        NodoAVL r = p.right;

        p.right = r.left;
        r.left = p;
        nodo.left = r;

        rotacionSimpleDerecha(nodo);
    }

    public String toString() {
        return inOrden(raiz);
    }

    public String buscar(NodoAVL raiz, double valor) {
        if(raiz == null) {
            return "";
        }
        if(raiz.valor == valor) {
            return raiz.valor+"";
        }
        return buscar(raiz.left, valor)+buscar(raiz.right, valor);
    }

    private int altura(NodoAVL nodo) {
        if(nodo == null)
            return 0;
        return max(altura(nodo.left), altura(nodo.right)) + 1;
    }

    private void calcularFactorBalance(NodoAVL nodo) {
        if(nodo == null)
            return;
        nodo.balance = altura(nodo.left)-altura(nodo.right);
        calcularFactorBalance(nodo.left);
        calcularFactorBalance(nodo.right);
    }

    private Boolean estaBalanceado(NodoAVL raiz) {
        if(raiz == null) {
            return true;
        }
        if(raiz.balance>1 || raiz.balance<-1) {
            return false;
        }
        return estaBalanceado(raiz.left) && estaBalanceado(raiz.right);
    }

    private NodoAVL obtenerNodo(NodoAVL nodo, double valor) {
        if(nodo == null) {
            return null;
        }
        if(nodo.valor == valor) {
            return nodo;
        }
        if(nodo.left != null && obtenerNodo(nodo.left, valor) != null) {
            return obtenerNodo(nodo.left, valor);
        }
        if(nodo.right != null && obtenerNodo(nodo.right, valor) != null) {
            return obtenerNodo(nodo.right, valor);
        }
        return null;
    }

    private void eliminar(NodoAVL nodo, double valor) {
        //verifica cualquier nodo nulo
        if (nodo == null) {
            return;
        }
        //verifica si el valor corresponde a la raiz
        if (raiz.valor==valor) {
            raiz = null;
            return;
        }
        //verifica el sub-árbol izquierdo
        if (nodo.left != null) {
            if(nodo.left.valor == valor) {
                nodo.left = null;
                return;
            }
            eliminar(nodo.left, valor);
        }
        //verifica el sub-árbol derecho
        if (nodo.right != null) {
            if (nodo.right.valor == valor) {
                nodo.right = null;
                return;
            }
            eliminar(nodo.right, valor);
        }
    }

    private NodoAVL obtenerPariente(NodoAVL raiz, NodoAVL nodo) {
        //si la raíz es nula
        if (raiz == null) {
            return null;
        }
        //si el que se busca es la raíz
        if (raiz.equals(nodo)) {
            return null;
        }
        //si la raíz no tiene hijos
        if (raiz.left == null && raiz.right == null) {
            return null;
        }
        //si la raíz dada es pariente del nodo
        if ((raiz.left != null && raiz.left.equals(nodo)) || (raiz.right != null && raiz.right.equals(nodo))){
            return raiz;
        }
        //else, busca en los sub-árboles
        if (raiz.left != null && obtenerPariente(raiz.left, nodo) != null) {
            return obtenerPariente(raiz.left, nodo);
        }
        return obtenerPariente(raiz.right, nodo);
    }

    private NodoAVL obtenerSucesorIn(NodoAVL nodo) {
        NodoAVL sucesor;
        if (nodo.right != null) {
            sucesor = nodo.right;
            while (sucesor.left != null) {
                sucesor = sucesor.left;
            }
            return sucesor;
        }
        //si tiene pariente derecho
        NodoAVL pariente = obtenerPariente(raiz, nodo);
        if (pariente != null && pariente.left != null && pariente.left.valor == nodo.valor) {
            return pariente;
        }
        //else
        return null;
    }

    /**
     * Calcula recorrido in-orden de forma recursiva
     * @param nodo Recibe nodo principal, se llaman los hijos de forma recursiva
     * @return cadena de texto con el recorrido in-orden del árbol
     */
    private String inOrden(NodoAVL nodo) {
        if(nodo != null) {
            return inOrden(nodo.left)+nodo.valor+",  "+inOrden(nodo.right);
        }
        return "";
    }

    // Desde aquì los métodos son para posicionar los nodos en el dibujo
    public void organizar() {
        minimo = 32; // en pixeles
        reestablecerCoordenadas(raiz);
        moverNodo(raiz, 32, 32);
        moverHaciaDerecha(raiz);
    }

    private void reestablecerCoordenadas(NodoAVL nodo) {
        if(nodo == null) {
            return;
        }
        nodo.x = 0;
        nodo.y = 0;
        reestablecerCoordenadas(nodo.left);
        reestablecerCoordenadas(nodo.right);
    }

    private void moverNodo(NodoAVL nodo, int x, int y) {
        if (nodo == null) {
            return;
        }
        if (x < minimo) {
            minimo = x;
        }
        if(isNotPosFree(raiz, x, y)) { // si la ubicacion está ocupada, acomoda los nodos
            var pariente = obtenerPariente(raiz, nodo);
            int xi = 1000;
            int yi = 1000;
            if(pariente != null) {
                xi = pariente.x+40;
                yi = pariente.y+60;
            }
            while (pariente != null) {
                pariente.x+=154;
                pariente = obtenerPariente(raiz, pariente);
            }
            moverNodo(nodo, xi, yi);
        }
        else { // si está disponible, acomoda los hijos de forma recursiva
            nodo.x = x;
            nodo.y = y;
            if (nodo.left != null) {
                moverNodo(nodo.left, nodo.x - 105, nodo.y + 60);
            }
            if (nodo.right != null) {
                moverNodo(nodo.right, nodo.x + 105, nodo.y + 60);
                if (nodo.left != null) {
                    nodo.x = (nodo.left.x + nodo.right.x)/2;
                }
            }
        }
    }

    private Boolean isNotPosFree(NodoAVL nodo, int x, int y) { // calcula si la posición está ocupada en el dibujo
        if (nodo == null) {
            return false;
        }
        if ((x >= nodo.x-104 && x <=nodo.x+104) && nodo.y == y) {
            return true;
        }
        return isNotPosFree(nodo.left, x, y) || isNotPosFree(nodo.right, x, y);
    }

    private void moverHaciaDerecha(NodoAVL nodo) { // mueve las coordenadas de los nodos a la derecha
        if(nodo != null) {
            nodo.x += (minimo-32)*-1;
            moverHaciaDerecha(nodo.left);
            moverHaciaDerecha(nodo.right);
        }
    }

    public Boolean isNotEmpty() {
        return raiz != null;
    } // `para saber si el árbol está vacío

}

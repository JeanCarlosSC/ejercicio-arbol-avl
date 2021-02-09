package app;

import javax.swing.*;

import static java.lang.Math.max;

public class ArbolAVL {
    double minimo = 0;
    NodoAVL raiz = null;

    public void insertar(double codigo, String nombre) {
        //si no hay árbol
        if(raiz == null) {
            raiz = new NodoAVL(codigo, nombre);
            return;
        }
        //si ya está en el árbol
        if(!buscar(raiz, codigo).isEmpty()) {
            JOptionPane.showMessageDialog(null, "El código "+codigo+" ya está en el árbol");
            return;
        }
        //si no está en el árbol
        NodoAVL p = raiz;
        NodoAVL q = null;
        NodoAVL nodo = new NodoAVL(codigo, nombre);

        while (p != null) {
            if(codigo < p.codigo) {
                q = p;
                p = p.left;
            }
            else if(codigo > p.codigo) {
                q = p;
                p = p.right;
            }
        }
        if(codigo < q.codigo) {
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

    public void retirar(double codigo) {
        //El arbol tiene que existir y el valor tiene que estar en el árbol o el árbol no se modificará
        if(buscar(raiz, codigo).isEmpty()) {
            return;
        }
        //si no tiene hijos, se elimina el nodo
        NodoAVL nodo = obtenerNodo(raiz, codigo);
        if(nodo.left == null && nodo.right == null) {
            eliminar(raiz, nodo.codigo);
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
                    if (pariente.codigo == nodo.codigo) {
                        nodo.right = sucesor.right;
                    } else {
                        //si el sucesor es antecesor
                        if (antecesor != null && sucesor.codigo == antecesor.codigo) {
                            antecesor.left = nodo.left;
                        } else {
                            //si el pariente es derecho
                            if (pariente.left != null && pariente.left.codigo == sucesor.codigo) {
                                pariente.left = sucesor.right;
                            } else {
                                pariente.right = sucesor.right;
                            }
                        }
                    }
                } else {
                    raiz.left = nodo.left;
                }
                nodo.codigo = sucesor.codigo;
                nodo.nombre = sucesor.nombre;
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

        NodoAVL q = new NodoAVL(nodo.codigo, nodo.nombre);
        q.left = nodo.left;
        q.right = r;

        nodo.nombre = nodo.right.nombre;
        nodo.codigo = nodo.right.codigo;

        nodo.left = q;
        nodo.right = nodo.right.right;
    }

    public void rotacionSimpleDerecha(NodoAVL nodo) {
        NodoAVL r = nodo.left.right;

        NodoAVL q = new NodoAVL(nodo.codigo, nodo.nombre);
        q.right = nodo.right;
        q.left = r;

        nodo.nombre = nodo.left.nombre;
        nodo.codigo = nodo.left.codigo;

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

    public String buscar(NodoAVL raiz, double codigo) {
        if(raiz == null) {
            return "";
        }
        if(raiz.codigo == codigo) {
            return raiz.nombre;
        }
        return buscar(raiz.left, codigo)+buscar(raiz.right, codigo);
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

    private NodoAVL obtenerNodo(NodoAVL nodo, double codigo) {
        if(nodo == null) {
            return null;
        }
        if(nodo.codigo == codigo) {
            return nodo;
        }
        if(nodo.left != null && obtenerNodo(nodo.left, codigo) != null) {
            return obtenerNodo(nodo.left, codigo);
        }
        if(nodo.right != null && obtenerNodo(nodo.right, codigo) != null) {
            return obtenerNodo(nodo.right, codigo);
        }
        return null;
    }

    private void eliminar(NodoAVL nodo, double codigo) {
        //verifica cualquier nodo nulo
        if (nodo == null) {
            return;
        }
        //verifica si el valor corresponde a la raiz
        if (raiz.codigo==codigo) {
            raiz = null;
            return;
        }
        //verifica el sub-árbol izquierdo
        if (nodo.left != null) {
            if(nodo.left.codigo == codigo) {
                nodo.left = null;
                return;
            }
            eliminar(nodo.left, codigo);
        }
        //verifica el sub-árbol derecho
        if (nodo.right != null) {
            if (nodo.right.codigo == codigo) {
                nodo.right = null;
                return;
            }
            eliminar(nodo.right, codigo);
        }
    }

    private NodoAVL obtenerPariente(NodoAVL raiz, NodoAVL nodo) {
        //si la raiz es nula
        if (raiz == null) {
            return null;
        }
        //si el que se busca es la raiz
        if (raiz.codigo == nodo.codigo) {
            return null;
        }
        //si la raiz no tiene hijos
        if (raiz.left == null && raiz.right == null) {
            return null;
        }
        //si la raiz dada es pariente del nodo
        if (raiz.left != null && raiz.left.codigo == nodo.codigo || raiz.right != null && raiz.right.codigo == nodo.codigo){
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
        if (pariente != null && pariente.left != null && pariente.left.codigo == nodo.codigo) {
            return pariente;
        }
        //else
        return null;
    }

    private String inOrden(NodoAVL nodo) {
        if(nodo != null) {
            return inOrden(nodo.left)+"%11.0f".formatted(nodo.codigo)+" "+nodo.nombre+"\n"+inOrden(nodo.right);
        }
        return "";
    }

    public void organizar() {
        minimo = 32;
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
        if(isNotPosFree(raiz, x, y)) {
            var pariente = obtenerPariente(raiz, nodo);
            final int xi = pariente.x+40;
            final int yi = pariente.y+60;
            while (pariente != null) {
                pariente.x+=154;
                pariente = obtenerPariente(raiz, pariente);
            }
            moverNodo(nodo, xi, yi);
        }
        else {
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

    private Boolean isNotPosFree(NodoAVL nodo, int x, int y) {
        if (nodo == null) {
            return false;
        }
        if ((x >= nodo.x-104 && x <=nodo.x+104) && nodo.y == y) {
            return true;
        }
        return isNotPosFree(nodo.left, x, y) || isNotPosFree(nodo.right, x, y);
    }

    private void moverHaciaDerecha(NodoAVL nodo) {
        if(nodo != null) {
            nodo.x += (minimo-32)*-1;
            moverHaciaDerecha(nodo.left);
            moverHaciaDerecha(nodo.right);
        }
    }

    public Boolean isNotEmpty() {
        return raiz != null;
    }

}

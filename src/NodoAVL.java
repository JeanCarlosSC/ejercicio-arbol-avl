public class NodoAVL {
    double valor; // valor del nodo
    int x=0; // coordenada x del nodo en el dibujo
    int y=0; // coordenada y del nodo en el dibujo

    int balance; //factor de balance

    NodoAVL left; // hijo izquierdo
    NodoAVL right; // hijo derecho

    public NodoAVL(double v) {
        // guarda los datos y por defecto establece los hijos como nulos
        valor = v;
        balance = 0;
        left = right = null;
    }

}

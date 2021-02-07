package app;

public class NodoAVL {
    double codigo;
    String nombre;
    int x=0;
    int y=0;

    int balance; //factor de balance

    NodoAVL left;
    NodoAVL right;

    public NodoAVL(double codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        balance = 0;
        left = right = null;
    }

}

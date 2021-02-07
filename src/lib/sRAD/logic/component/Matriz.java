package lib.sRAD.logic.component;

public class Matriz {

    public static String toString(double[][] matriz) {
        StringBuilder toString = new StringBuilder();
        for (double[] i: matriz) {
            for (double j: i) {
                toString.append("%12.10f ".formatted(j));
            }
            toString.append("\n");
        }
        return toString.toString();
    }

    public static String toString(double[] matriz) {
        StringBuilder toString = new StringBuilder();
        for (double i: matriz) {
            toString.append("%12.10f ".formatted(i));
        }
        return toString.toString();
    }

    public static double[] estadoEstable(double[][] matriz) {
        double[] estado = new double[matriz.length];
        estado[0] = 1;
        return estabilizar(estado, matriz);
    }

    private static double[] estabilizar (double[] estado, double[][] matriz) {
        double[] resultado = Matriz.multiplicar(estado, matriz);
        for (int i=0; i<estado.length; i++) {
            if (estado[i] != resultado[i]) {
                return estabilizar(resultado, matriz);
            }
        }
        return estado;
    }

    public static double[] multiplicar(double[] vector, double[][] matriz) {
        double[] resultado = new double[vector.length];
        for(int j=0; j<matriz[0].length; j++) {
            for(int i=0; i< matriz.length; i++) {
                resultado[j] += vector[i] * matriz[i][j];
            }
        }
        return resultado;
    }

    public static double[][] calcularTiempos(double[] estadoEstable, double[][] transicion) {
        int size = estadoEstable.length;
        double[][] tiempos = new double[size][size];
        //por cada columna establece un sistema de ecuaciones
        for (int col = 0; col < size; col++) {
            double[][] sistema = new double[size-1][size];
            for(int i=0; i<size; i++) {
                for (int j = 0; j < size; j++) {
                    if(j != col && i != col) {
                        final int colSistema = (j<col)? j : j-1;
                        final int filSistema = (i<col)? i : i-1;
                        sistema[filSistema][colSistema] = transicion[i][j]*-1;
                        if (filSistema == colSistema) {
                            sistema[filSistema][colSistema] += 1;
                        }
                    }
                }
                if (i<size-1)
                    sistema[i][size-1] = 1;
            }
            //resuelve el sistema
            double[] resultados  = Matriz.resolverSistemaLineal(sistema);
            //guarda los resultados de los calculos en la matriz de tiempos
            for (int i = 0; i < size; i++) {
                if(i == col) {
                    tiempos[i][col] = 1/estadoEstable[col];
                }
                else {
                    if (i < col) {
                        tiempos[i][col] = resultados[i];
                    }
                    else {
                        tiempos[i][col] = resultados[i-1];
                    }
                }
            }
        }

        return tiempos;
    }

    public static double[] resolverSistemaLineal(double[][] sistema) {
        try {
            // k is the index of row with max pivot
            int k;
            // swapTemp is the temporary row swapping variable
            double[] swapTemp;

            for(int i = 0; i < sistema.length; i++) {
                k = i;
                for(int j = i + 1; j < sistema.length; j++) {
                    if(sistema[j][i] > sistema[k][i]) {
                        k = j;
                    }
                }

                if(sistema[k][i] == 0) {
                    // Mark this column as free column
                    // Will solve later -> not now
                    System.out.println("\n----- Error 005 -----");
                    System.out.println("System of equations contains free variables");
                    System.exit(0);
                }

                // Swap the rows i and k
                swapTemp = sistema[i];
                sistema[i] = sistema[k];
                sistema[k] = swapTemp;

                // Iterate over all subsequent rows
                for(int j = 0; j < sistema.length; j++) {
                    // Reduce row by row
                    if(i != j && !reduceRow(sistema[i], sistema[j], i)) {
                        System.out.println("\n----- Error 006 -----");
                        System.out.println("System of equations cannot be solved by this program");
                        System.exit(0);
                    }
                }
            }
            //convierte a los pivotes en 1
            for (int i = 0; i < sistema.length; i++) {
                double valor = sistema[i][i];
                sistema[i][i] /= valor;
                sistema[i][sistema.length] /= valor;
            }

            double[] resultado = new double[sistema.length];
            for (int i=0; i< sistema.length; i++) {
                resultado[i] = sistema[i][sistema.length];
            }
            return resultado;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static boolean reduceRow(double[] r1, double[] r2, int pos) {
        try {
            if(r1.length != r2.length) return false;

            double factor = r2[pos] / r1[pos];
            for(int i = pos; i < r1.length; i++) {
                r2[i] -= factor * r1[i];
            }
            return true;
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("\n----- Error 004 -----");
            System.out.println("Array index out of range in function reduceRow()");
        }
        return false;
    }

}

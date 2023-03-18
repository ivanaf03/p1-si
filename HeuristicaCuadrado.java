package es.udc.sistemasinteligentes;

public class HeuristicaCuadrado extends  Heuristica{

    @Override
    public float evalua(Estado e) {
        ProblemaCuadradoMagico.EstadoCuadrado eC=(ProblemaCuadradoMagico.EstadoCuadrado) e;
        double v=Math.pow(eC.n, 2);
            int valorObjetivo = (int)(eC.n*v+1)/2; // valor objetivo de cada fila, columna y diagonal
            int diferenciaTotal = 0;

            // Evaluar filas
            for (int i = 0; i < eC.n; i++) {
                int sumaFila = 0;
                for (int j = 0; j < eC.n; j++) {
                    sumaFila += eC.cuadrado[i][j];
                }
                diferenciaTotal += Math.abs(sumaFila - valorObjetivo);
            }

            // Evaluar columnas
            for (int j = 0; j < eC.n; j++) {
                int sumaColumna = 0;
                for (int i = 0; i < eC.n; i++) {
                    sumaColumna += eC.cuadrado[i][j];
                }
                diferenciaTotal += Math.abs(sumaColumna - valorObjetivo);
            }

            // Evaluar diagonales
            int sumaDiagonal1 = 0;
            int sumaDiagonal2 = 0;
            for (int i = 0; i < eC.n; i++) {
                sumaDiagonal1 += eC.cuadrado[i][i];
                sumaDiagonal2 += eC.cuadrado[i][eC.n - i - 1];
            }
            diferenciaTotal += Math.abs(sumaDiagonal1 - valorObjetivo);
            diferenciaTotal += Math.abs(sumaDiagonal2 - valorObjetivo);

            return diferenciaTotal;
    }
}

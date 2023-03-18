package es.udc.sistemasinteligentes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main2 {

    public static void main(String[] args) throws Exception {
        int n = 4;
//        int[][] matriz = {
//            {2,0,0},
//            {0,0,0},
//            {0,0,0},
//        };
        int[][] matriz = {
                {2,8,15,9},
                {14,12,5,3},
                {11,13,4,6},
                {7,1,10,0},
        };
        ProblemaCuadradoMagico.EstadoCuadrado estadoInicial = new ProblemaCuadradoMagico.EstadoCuadrado(n,matriz);
        ProblemaBusqueda cuadrado = new ProblemaCuadradoMagico(estadoInicial);
        Heuristica heuristica = new HeuristicaCuadrado();

        EstrategiaBusquedaInformada buscador = new BusquedaAEstrella();

        Nodo[] solucion = buscador.soluciona(cuadrado, heuristica);
        ArrayList<Nodo> listaNodos = new ArrayList<>(Arrays.asList(solucion));
        Collections.reverse(listaNodos);
        for(Nodo i : listaNodos)
            System.out.println(i.toString());
    }
}

package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;
import java.util.Collections;

public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {
    }

    private Nodo[] reconstruyeSolucion(Nodo n){
        ArrayList<Nodo> nodos=new ArrayList<>();
        Nodo aux=n;
        while (aux.getAccion()!=null){
            nodos.add(aux);
            aux=aux.getPadre();
        }
        Collections.reverse(nodos);
        return nodos.toArray(new Nodo[0]);
    }
    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception{
        ArrayList<Estado> explorados = new ArrayList<>();
        Estado estadoActual = p.getEstadoInicial();
        explorados.add(estadoActual);
        Nodo nodoActual=new Nodo(estadoActual, null, null, null);

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!p.esMeta(estadoActual)){
            System.out.println((i++) + " - " + estadoActual + " no es meta");
            Accion[] accionesDisponibles = p.acciones(estadoActual);
            boolean modificado = false;
            for (Accion acc: accionesDisponibles) {
                Estado sc = p.result(estadoActual, acc);
                System.out.println((i++) + " - RESULT(" + estadoActual + ","+ acc + ")=" + sc);
                if (!explorados.contains(sc)) {
                    estadoActual = sc;

                    System.out.println((i++) + " - " + sc + " NO explorado");
                    explorados.add(estadoActual);
                    modificado = true;
                    System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                    nodoActual=new Nodo(estadoActual, nodoActual, acc, null);
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " ya explorado");
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
        }
        System.out.println((++i) + " - FIN - " + estadoActual);
        return reconstruyeSolucion(nodoActual);
    }

}



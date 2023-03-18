package es.udc.sistemasinteligentes;
import java.util.*;

public class BusquedaAEstrella implements EstrategiaBusquedaInformada {

    public BusquedaAEstrella() {
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p, Heuristica h) throws Exception {
        Queue<Nodo> frontera = new PriorityQueue<>(); //profundidad
        Nodo nodoActual = new Nodo(p.getEstadoInicial(),null, null, h);
        frontera.add(nodoActual);
        ArrayList<Estado> explorados = new ArrayList<>();

        int i = 1;
        int nCreados = 1;
        System.out.println((i++) + " - Empezando búsqueda en " + nodoActual.estado);

        Nodo nodo;
        while (true) {
            if (frontera.isEmpty())
                throw new Exception("No se ha podido encontrar una solución");
            nodoActual = frontera.remove(); //profundidad
            System.out.println((i++) + " ! Estado actual cambiado a " + nodoActual.estado);
            if (p.esMeta(nodoActual.estado)) break;
            else {
                System.out.println((i++) + " - " + nodoActual.estado + " no es meta");
                explorados.add(nodoActual.estado);
                Accion[] accionesDisponibles = p.acciones(nodoActual.estado);
                for (Accion acc : accionesDisponibles) {
                    Estado sc = p.result(nodoActual.estado, acc);
                    nodo = new Nodo(sc, nodoActual, acc, h);
                    nCreados++;
                    System.out.println((i++) + " - RESULT(" + nodoActual.estado + "," + acc + ")=" + sc);
                    if (!explorados.contains(sc)) {
                        if (!frontera.contains(nodo) && !explorados.contains(sc)) {
                            frontera.add(nodo);
                            System.out.println((i++) + " - " + sc + " NO explorado");
                            System.out.println((i++) + " - Nodo anadido a frontera" + nodo);
                        } else
                            System.out.println((i++) + " - " + sc + " ya explorado");
                    }
                }
            }
        }
        System.out.println((i) + " - FIN - " + nodoActual);
        System.out.println("Nodos expandidos: " + explorados.size());
        System.out.println("Nodos creados: " + nCreados);
        return reconstruye_sol(nodoActual);
    }

    /**
     * Calcula el camino para llegar a la solucion dado el nodo final
     * @param nodo nodo final
     * @return array con los nodos seguidos para llegar a la solucion
     */
    private Nodo[] reconstruye_sol(Nodo nodo) {
        ArrayList<Nodo> solucion = new ArrayList<>();
        Nodo actual = nodo;
        while(actual != null){
            solucion.add(actual);
            actual = actual.padre;
        }
        return solucion.toArray(new Nodo[0]);
    }
}

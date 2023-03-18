
package es.udc.sistemasinteligentes;
public class Nodo implements Comparable<Nodo>{
    Nodo padre;
    Estado estado;
    Accion accion;
    float coste;
    float f;

    /**
     * @param padre nodo padre
     * @param estado estado del nodo
     * @param accion accion previa
     * @param heuristica funcion heuristica
     */
    public Nodo(Estado estado, Nodo padre, Accion accion, Heuristica heuristica) {
        this.padre = padre;
        this.estado = estado;
        this.accion = accion;
        if(padre != null){
            this.coste = padre.coste + accion.getCoste();
            if(heuristica != null)
                this.f = this.coste + heuristica.evalua(estado);
        }
    }

    @Override
    public String toString() {
        return "(" + estado +
                ", " + accion +
                ", " + coste +
                ", " + f +
                ')';
    }

    @Override
    public int compareTo(Nodo nodo) {
        // el orden se basa en la funcion f
        return nodo.f < this.f ? 1 : -1;
    }

    public Accion getAccion(){
        return accion;
    }

    public Estado getEstado(){
        return estado;
    }

    public Nodo getPadre(){
        return padre;
    }

}
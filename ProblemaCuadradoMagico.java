package es.udc.sistemasinteligentes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {
    public ProblemaCuadradoMagico(Estado estadoInicial) {
        super(estadoInicial);
    }
    public static class EstadoCuadrado extends Estado{
        public int n;
        public int[][] cuadrado;

        public EstadoCuadrado(int n, int[][] cuadrado){
            this.n=n;
            this.cuadrado=cuadrado;
        }

        @Override
        public String toString() {
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    sb.append("|").append(cuadrado[i][j]).append("|");
                }
                sb.append("\n");
            }
            return sb.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if(this==obj){
                return true;
            }
            if(!(obj instanceof EstadoCuadrado estadoCuadrado)){
                return false;
            }
            return n==estadoCuadrado.n && Arrays.deepEquals(cuadrado, estadoCuadrado.cuadrado);
        }

        @Override
        public int hashCode() {
            return Objects.hash(n, Arrays.deepHashCode(cuadrado));
        }
    }

    public static class AccionCuadrado extends Accion{
        private final int a;
        private final int b;
        private final int value;

        public AccionCuadrado(int a, int b, int value){
            this.a=a;
            this.b=b;
            this.value=value;
        }
        @Override
        public String toString() {
            return "Adding "+value+" in ("+a+","+b+")";
        }

        @Override
        public boolean esAplicable(Estado es) {
            EstadoCuadrado estadoCuadradoAplicado=(EstadoCuadrado) aplicaA(es);
            EstadoCuadrado estadoCuadradoActual=(EstadoCuadrado) (es);

            if(estadoCuadradoActual.cuadrado[a][b]!=0){
                return false;
            }
            int result=(estadoCuadradoActual.n*(estadoCuadradoActual.n-1))/2;
            int sumd1 = 0,sumd2=0, sumf=0, sumc=0;

            for (int i = 0; i < estadoCuadradoAplicado.n; i++) {
                if (result < (sumd1 += estadoCuadradoAplicado.cuadrado[i][i])) return false;
                if (result < (sumd2 += estadoCuadradoAplicado.cuadrado[i][estadoCuadradoAplicado.n-1-i])) return false;
                if (result < (sumf += estadoCuadradoAplicado.cuadrado[a][i])) return false;
                if (result < (sumc += estadoCuadradoAplicado.cuadrado[i][b])) return false;
            }
            return true;
        }

        @Override
        public Estado aplicaA(Estado es) {
            EstadoCuadrado estadoCuadrado = ((EstadoCuadrado) es);
            int[][] aux=new int[estadoCuadrado.n][estadoCuadrado.n];
            for(int i=0; i<estadoCuadrado.n;i++){
                System.arraycopy(estadoCuadrado.cuadrado[i],0,aux[i],0, estadoCuadrado.n);
            }
            aux[a][b]=value;
            return new EstadoCuadrado(estadoCuadrado.n, aux);
        }
    }
    @Override
    public boolean esMeta(Estado es) {
        EstadoCuadrado estadoCuadrado = (EstadoCuadrado) es;
        int sumd1 = 0,sumd2=0;
        for (int i = 0; i < estadoCuadrado.n; i++) {
            sumd1 += estadoCuadrado.cuadrado[i][i];
            sumd2 += estadoCuadrado.cuadrado[i][estadoCuadrado.n-1-i];
        }
        if(sumd1!=sumd2){
            return false;
        }

        for (int i = 0; i < estadoCuadrado.n; i++) {
            int rowSum = 0, colSum = 0;
            for (int j = 0; j < estadoCuadrado.n; j++) {
                rowSum += estadoCuadrado.cuadrado[i][j];
                colSum += estadoCuadrado.cuadrado[j][i];
            }
            if (rowSum != colSum || colSum != sumd1){
                return false;
            }
        }
        return true;
    }

    @Override
    public Accion[] acciones(Estado es) {
        EstadoCuadrado estadoCuadrado = (EstadoCuadrado) es;
        ArrayList<Integer> listValues=new ArrayList<>();
        ArrayList<Integer> listPosibilities=new ArrayList<>();
        ArrayList<Accion> actions=new ArrayList<>();

        for(int i=0;i<estadoCuadrado.n;i++){
            for(int j=0;j<estadoCuadrado.n;j++){
                listValues.add(estadoCuadrado.cuadrado[i][j]);
            }
        }

        for(int i=1;i<(estadoCuadrado.n*estadoCuadrado.n);i++){
            if(!listValues.contains(i)){
                listPosibilities.add(i);
            }
        }

        for(int i=0; i<estadoCuadrado.n;i++){
            for(int j=0; j<estadoCuadrado.n;j++){
                if(estadoCuadrado.cuadrado[i][j]==0){
                    for(Integer posible: listPosibilities){
                        Accion a=new AccionCuadrado(i, j, posible);
                        if (a.esAplicable(estadoCuadrado)) {
                            actions.add(a);
                        }
                    }
                }
            }
        }
         return actions.toArray(new Accion[0]);
    }

}

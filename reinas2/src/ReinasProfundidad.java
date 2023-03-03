import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class ReinasProfundidad {
    static int tamañoTablero = 8;
    private static class Tablero{
        private final int[] posicionesReinas;

        public Tablero(int n){
            posicionesReinas = new int[n];
            Arrays.fill(posicionesReinas,0);
        }

        public Tablero(Tablero t){
            posicionesReinas = t.posicionesReinas.clone();
        }

        public ArrayList<Tablero> generarHijos(){
            ArrayList<Tablero> hijos = new ArrayList<>();
            int columnaLibre = 0;
            while (posicionesReinas[columnaLibre] != 0){
                columnaLibre++;
            }
            for(int fila = 1 ; fila <= tamañoTablero ; fila++){
                if(validarPosicion(columnaLibre,fila)){
                    Tablero hijo = new Tablero(this);
                    hijo.posicionesReinas[columnaLibre] = fila;
                    hijos.add(hijo);
                }
            }
            return hijos;
        }

        private boolean validarPosicion(int columna, int fila) {
            for (int i = 0; i < columna; i++) {
                int diferencia = Math.abs(posicionesReinas[i] - fila);
                if (diferencia == 0 || diferencia == columna - i) {
                    return false;
                }
            }
            return true;
        }
        public boolean esSolucion() {
            return posicionesReinas[posicionesReinas.length-1] != 0;
        }
    }

    public static void main(String[] args) {
        algoritmoDFS();
    }

    private static void algoritmoDFS() {
        int visitas = 0;
        ArrayList<Tablero> nodosVisitados = new ArrayList<>();
        Stack<Tablero> nodosFrontera = new Stack<>();
        Tablero nodoActual = new Tablero(tamañoTablero);
        nodosFrontera.push(nodoActual);
        while (!nodosFrontera.isEmpty()) {
            visitas++;
            nodoActual = nodosFrontera.pop();
            if (nodoActual.esSolucion()) {
                System.out.print("Solución: [");
                for (int reina : nodoActual.posicionesReinas) {
                    System.out.print(reina + " ");
                }
                System.out.println("]");
                System.out.println("Visitas totales: " + visitas);
                return;
            }
            nodosVisitados.add(nodoActual);
            for (Tablero hijo : nodoActual.generarHijos()) {
                if (!nodosVisitados.contains(hijo) && !nodosFrontera.contains(hijo)) {
                    nodosFrontera.push(hijo);
                }
            }
        }
    }
}
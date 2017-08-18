package arbolB;

public class Nodo {
    Nodo padre;
    Nodo[] hijos;
    int[] arreglo;
    private int w;
    private int contador;

    public Nodo(int orden) {
        padre = null;
        w = orden;
        arreglo = new int[w * 2 + 1];
        hijos = new Nodo[w * 2 + 1];
    }

    public int getData(int pos) {
        return arreglo[pos];
    }

    public void incrementarContador() {
        contador++;
    }

    public int getContador() {
        return contador;
    }

    public int getW() {
        return w;
    }

    public boolean estoyLleno() {
        if (contador == 2 * w + 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String s = "[";
        for (int data : arreglo) {
            if (data != 0)
                s += data + ", ";
        }
        return s.substring(0, s.length() - 2) + "]";
    }

}









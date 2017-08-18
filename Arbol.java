package arbolB;

import javax.swing.*;

public class Arbol {

    private final int w;
    private Nodo raiz;

    public Arbol(int orden) {
        w = orden;
        raiz = new Nodo(w);
    }

    public Nodo buscar(int dato) {
        return busquedaRecursiva(dato, raiz);
    }

    private Nodo busquedaRecursiva(int dato, Nodo nodo) {
        if (nodo != null) {
            for (int i = 0; i < nodo.getContador(); i++) {
                if (dato == nodo.arreglo[i]) {
                    return nodo;
                } else if (dato < nodo.arreglo[i]) {
                    return busquedaRecursiva(dato, nodo.hijos[i]);
                } else if (i == nodo.getContador() - 1 && dato > nodo.arreglo[i]) {
                    return busquedaRecursiva(dato, nodo.hijos[i + 1]);
                }
            }
        }
        return null;
    }

    public void agregar(int dato) {
        agregadoRecursivo(dato, raiz, 0);
    }

    private int agregadoRecursivo(int dato, Nodo nodo, int i) {
        if (i < nodo.getContador() && dato > nodo.arreglo[i]) {
            return agregadoRecursivo(dato, nodo, i + 1);
        } else if (i < nodo.getContador() && dato == nodo.arreglo[i]) {
            JOptionPane.showMessageDialog(null, "El dato ya existe");
            return -1;
        } else {
            if (nodo.hijos[i] != null) {
                return agregadoRecursivo(dato, nodo.hijos[i], 0);
            } else {
                System.arraycopy(nodo.arreglo, i, nodo.arreglo, i + 1, nodo.getContador() - i);

                nodo.arreglo[i] = dato;
                nodo.incrementarContador();
                if (nodo.estoyLleno()) {
                    dividir(nodo);
                }
                return i;
            }
        }
    }

    public void dividir(Nodo nodoLleno) {
        int w = nodoLleno.getW();
        Nodo[] residuos = {
                new Nodo(w),
                new Nodo(w)
        };

        System.arraycopy(nodoLleno.arreglo, 0, residuos[0].arreglo, 0, w);
        System.arraycopy(nodoLleno.arreglo, w + 1, residuos[1].arreglo, 0, w);

        for (Nodo n : residuos) {
            for (int i = 0; i < w; i++) {
                n.incrementarContador();
            }
        }

        Nodo padre = nodoLleno.padre;
        if (padre == null) {
            raiz = (padre = new Nodo(w));
        }
        for (int i = 0; i < padre.hijos.length; i++) {
            if (padre.hijos[i] == nodoLleno) {
                padre.hijos[i] = null;
            }
        }

        int mitad = nodoLleno.arreglo[w];
        int pos = agregadoRecursivo(mitad, padre, 0);
        System.arraycopy(padre.hijos, pos, padre.hijos, pos+1, (w*2)-pos);

        for (int i = 0; i < residuos.length; i++) {
            residuos[i].padre = padre;
            padre.hijos[i+pos] = residuos[i];

        }

        System.arraycopy(nodoLleno.hijos, 0, residuos[0].hijos, 0, w);
        System.arraycopy(nodoLleno.hijos, w, residuos[1].hijos, 0, w+1);
        for (Nodo residuo : residuos) {
            for (Nodo hijo : residuo.hijos) {
                if (hijo != null) {
                    hijo.padre = residuo;
                }
            }
        }
    }

    @Override
    public String toString() {
        return recursive2String(raiz, 0);
    }

    public String recursive2String(Nodo nodo, int level) {
        String str = "";
        int n, i;
        if(nodo != null){
            for(n=nodo.getContador();n>=0;n--){
                str += recursive2String(nodo.hijos[n],level+1);
                if(n>0){
                    for(i=0;i<level;i++){
                        str += "________";
                    }
                    str += nodo.arreglo[n-1]+"\n";
                }
            }
        }
        return str;
    }
    }


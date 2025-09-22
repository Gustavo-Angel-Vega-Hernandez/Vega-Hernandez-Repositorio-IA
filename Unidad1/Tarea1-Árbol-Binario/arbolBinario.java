class Nodo {
    int valor;
    Nodo izquierdo;
    Nodo derecho;

    Nodo(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }
}

class ArbolBinarioBusqueda {
    Nodo raiz;

    public ArbolBinarioBusqueda() {
        raiz = null;
    }
    
    public boolean vacio() {
        return raiz == null;
    }

    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    private Nodo insertarRec(Nodo actual, int valor) {
        if (actual == null) {
            return new Nodo(valor);
        }

        if (valor < actual.valor) {
            actual.izquierdo = insertarRec(actual.izquierdo, valor);
        } else if (valor > actual.valor) {
            actual.derecho = insertarRec(actual.derecho, valor);
        }
        return actual;
    }

    public Nodo buscarNodo(int valor) {
        return buscarNodoRec(raiz, valor);
    }

    private Nodo buscarNodoRec(Nodo actual, int valor) {
        if (actual == null || actual.valor == valor) {
            return actual;
        }
        return (valor < actual.valor)
                ? buscarNodoRec(actual.izquierdo, valor)
                : buscarNodoRec(actual.derecho, valor);
    }

    public void imprimirArbol() {
        recorridoInOrden(raiz);
        System.out.println();
    }

    private void recorridoInOrden(Nodo actual) {
        if (actual != null) {
            recorridoInOrden(actual.izquierdo);
            System.out.print(actual.valor + " ");
            recorridoInOrden(actual.derecho);
        }
    }
}

public class arbolBinario {
    public static void main(String[] args) {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(60);
        arbol.insertar(80);

        System.out.println("Recorrido en-orden:");
        arbol.imprimirArbol();

        Nodo nodoBuscado = arbol.buscarNodo(20);
        if (nodoBuscado != null) {
            System.out.println("Nodo encontrado: " + nodoBuscado.valor);
        } else {
            System.out.println("Nodo no encontrado");
        }
    }
}

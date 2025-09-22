import java.util.*;

class Arista {
    String destino;
    int peso;

    Arista(String destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }
}

class Grafo {
    private final Map<String, List<Arista>> listaDeAdyacencia = new HashMap<>();

    public void agregarNodo(String nodo) {
        listaDeAdyacencia.putIfAbsent(nodo, new ArrayList<>());
    }

    public void agregarArista(String origen, String destino, int peso) {
        listaDeAdyacencia.get(origen).add(new Arista(destino, peso));
    }

    public Map<String, Integer> dijkstra(String inicio) {
        Map<String, Integer> distancias = new HashMap<>();
        for (String nodo : listaDeAdyacencia.keySet()) {
            distancias.put(nodo, Integer.MAX_VALUE);
        }
        distancias.put(inicio, 0);

        PriorityQueue<Map.Entry<String, Integer>> colaDePrioridad = new PriorityQueue<>(Comparator.comparing(Map.Entry::getValue));

        colaDePrioridad.add(new AbstractMap.SimpleEntry<>(inicio, 0));

        while (!colaDePrioridad.isEmpty()) {
            String actual = colaDePrioridad.poll().getKey();

            for (Arista arista : listaDeAdyacencia.get(actual)) {
                int nuevaDist = distancias.get(actual) + arista.peso;
                if (nuevaDist < distancias.get(arista.destino)) {
                    distancias.put(arista.destino, nuevaDist);
                    colaDePrioridad.add(new AbstractMap.SimpleEntry<>(arista.destino, nuevaDist));
                }
            }
        }
        return distancias;
    }
}

public class Dijkstra {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        String[] nodos = {"A", "B", "C", "D", "E", "F"};
        for (String nodo : nodos) {
            grafo.agregarNodo(nodo);
        }

        grafo.agregarArista("A", "B", 4);
        grafo.agregarArista("A", "C", 2);
        grafo.agregarArista("B", "C", 1);
        grafo.agregarArista("B", "D", 5);
        grafo.agregarArista("C", "D", 8);
        grafo.agregarArista("C", "E", 10);
        grafo.agregarArista("D", "E", 2);
        grafo.agregarArista("D", "F", 6);
        grafo.agregarArista("E", "F", 3);

        Map<String, Integer> resultado = grafo.dijkstra("A");

        System.out.println("Distancias más cortas desde A:");
        for (Map.Entry<String, Integer> entrada : resultado.entrySet()) {
            System.out.println("A -> " + entrada.getKey() + ": " + entrada.getValue());
        }
    }
}

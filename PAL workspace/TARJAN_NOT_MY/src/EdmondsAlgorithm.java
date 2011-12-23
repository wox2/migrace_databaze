import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * Tarjanova implementace Edmondsova algoritmu se zapracovanymi Cameriniho
 * pripominkami. Hleda minimalni orientovanou kostru
 * Jedna se o implementaci pro ridke grafy, jejiz operace nejsou optimalni
 * ve smyslu clanku Roberta Tarjana "Finding optimum branchings", ale
 * k optimalite nemaji daleko.
 * @author Pavel Micka
 */
public class EdmondsAlgorithm {
    private Queue<Integer> roots; //silne komponenty, ktere jsou koreny v aktualnim grafu (nevede do nich zadny uzel)
    private BinomialHeap[] queues; //vstupni hrany do danych uzlu (razene dle priority)
    private Edge[] enter; //vstupni hrany do silnych komponent
    private List<Edge>[] h; //hrany, ktere mohou byt v kostre
    private List<Integer> rset; //rooti vyslednych stromu Tarjanovy implementace
    private DisjointSet s; //silne komponenty (reprezentanti)
    private DisjointSet w; //slabe komponenty (reprezentanti)
    private int[] min; //urcuje finalni korenove uzly orientovanych koster
    private int nodeCount; //pocet uzlu zadaneho grafu
    private List<Edge> fRoots; //koreny lesa F
    private List<Edge>[] cycles; //nalezene cykly
    private Edge[] lambda; //listy lesa F

    public EdmondsAlgorithm(int nodeCount) {
        roots = new LinkedList<Integer>();
        queues = new BinomialHeap[nodeCount];
        enter = new Edge[nodeCount]; //vstupni hrany silnych komponent
        h = new ArrayList[nodeCount]; //mnozina hran (optimalni kostra)
        rset = new ArrayList<Integer>(); //finalni koreny
        w = new DisjointSet(nodeCount);
        s = new DisjointSet(nodeCount);
        min = new int[nodeCount];

        this.fRoots = new ArrayList<Edge>();
        cycles = new ArrayList[nodeCount];
        lambda = new Edge[nodeCount];

        for (int i = 0; i < nodeCount; i++) {
            roots.add(i);
            queues[i] = new BinomialHeap(1400000); //kapacita, ktera by mela stacit pro dane pripady
            h[i] = new ArrayList<Edge>();
            enter[i] = new Edge(0, 0, Integer.MIN_VALUE);
            min[i] = i;
        }
    }

    /**
     * Demonstrace Edmondsova algorimu
     *
     * Na vstupu (standard in) jsou data ve tvaru
     *
     * 6
     * 0 1 50
     * 0 2 60
     * 1 3 50
     * 2 3 1
     * 3 5 1000
     * 0 4 2
     * 3 0 1
     * 0 3 30
     * 0 3 300
     * 0 0 0
     *
     * Kde prvni radek je pocet uzlu, nasledujici radky reprezentuji hrany
     * ve tvaru (z do vaha) a posledni radek (0 0 0) znaci konec vstupu
     *
     * Algoritmus vypise koren a hrany minimalni kostry
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("edmondsData/soubor.txt")), 9192); 

        String line = reader.readLine();
        int nodeCount = Integer.valueOf(line);

        EdmondsAlgorithm o = new EdmondsAlgorithm(nodeCount);
        o.readEdges(reader);
        ResultWrapper r = o.edmondsAlgorithm();

        for(Integer i : r.getRoots()){
            System.out.println(i);
        }
        for(Edge e : r.getEdges()){
            System.out.println(e);
        }
    }

    /**
     * Precte hrany ve tvaru
     * E1 E1 WEIGHT
     * ze zadaneho readeru
     * @param reader
     * @param nodeCount pocet uzlu
     * @throws IOException
     */
    private void readEdges(BufferedReader reader) throws IOException {
        String line = null;
        while ((line = reader.readLine()) != null) {
            StringTokenizer tokenizer = new StringTokenizer(line, " ");
            int from = Integer.valueOf(tokenizer.nextToken());
            int to = Integer.valueOf(tokenizer.nextToken());
            int weight = Integer.valueOf(tokenizer.nextToken());

            if (from == to) continue;

            Edge e = new Edge(from, to, weight);
            queues[to].insert(e);
        }
    }
    /**
     * Tarjanova implementace Edmonsova algoritmu
     * @return minimalni kostra orientovaneho grafu
     */
    private ResultWrapper edmondsAlgorithm() {
        while (roots.size() != 0) {
            int k = roots.poll();
            Edge edge = min(k);
            if (!(edge.getFrom() == 0 && edge.getTo() == 0)) {
                if (cycles[s.find(k)] == null || cycles[s.find(k)].size() == 1) {
                    lambda[k] = edge;
                } else if (s.find(edge.getFrom()) != s.find(edge.getTo())) {
                    for (Edge e : cycles[s.find(k)]) {
                        fRoots.remove(e); //je v cyklu, nemuze byt rootem
                        edge.getfChildren().add(e);
                        e.setfParent(edge);
                    }
                }
            }
            if (edge.getFrom() == 0 && edge.getTo() == 0) { //pokud je to dummy hrana
                rset.add(k); //pak uzel nema vstupni hranu (je root)
            } else if (s.find(edge.getFrom()) == k) { //nalezli jsme hranu v ramci silne komponenty
                roots.add(k); //uzel vratimem do fronty
            } else {
                h[edge.getFrom()].add(edge); //tato hrana je adeptem na hranu MST
                fRoots.add(edge); //tato hrana je korenem v lese F
                if (w.find(edge.getFrom()) != w.find(edge.getTo())) { //pocatecni a koncovy uzel jsou v ruznych slabych komponentach
                    w.union(w.find(edge.getFrom()), w.find(edge.getTo()));
                    enter[k] = edge; //edge je vstupni hranou do k
                } else { //pocatecni a koncovy uzel jsou ve stejne slabe komponente
                    Edge cycleEdge = edge;
                    int maxVal = Integer.MIN_VALUE;
                    int vertex = Integer.MIN_VALUE;

                    List<Edge> cycle = new ArrayList<Edge>();
                    while (!(cycleEdge.getFrom() == 0 && cycleEdge.getTo() == 0)) { //hledame hranu v cyklu s maximalni vahou (ktera nejvice poskodi cyklus)
                        cycle.add(cycleEdge);
                        if (cycleEdge.getWeight() > maxVal) {
                            maxVal = cycleEdge.getWeight();
                            vertex = s.find(cycleEdge.getTo());
                        }
                        cycleEdge = enter[s.find(cycleEdge.getFrom())];
                    }
                    cycles[s.find(k)] = cycle;
                    queues[k].decreaseAllKeys(edge.getWeight() - maxVal);
                    min[k] = min[vertex];

                    //collapse cycle
                    cycleEdge = enter[s.find(edge.getFrom())];
                    while (!(cycleEdge.getFrom() == 0 && cycleEdge.getTo() == 0)) {
                        queues[s.find(cycleEdge.getTo())].decreaseAllKeys(cycleEdge.getWeight() - maxVal); //pridame prichozim hranam hodnotu, ktera znaci, o kolik se vypustenim dane cyklove hrany a pridanim dane necyklove zhorsi reseni
                        queues[k].mergeHeaps(queues[s.find(cycleEdge.getTo())]);
                        s.union(k, s.find(cycleEdge.getTo()));
                        cycleEdge = enter[s.find(cycleEdge.getFrom())];
                    }
                    roots.add(k);
                }
            }
        }
        return leaf();
    }

    /**
     * Slouzi k identifikaci minimalni kostry dle Cameriniho pripominek
     * @return minimalni kostra grafu
     */
    private ResultWrapper leaf() {
        List<Edge> b = new ArrayList<Edge>();
        List<Integer> r = new ArrayList<Integer>();
        int fRootIndex = 0;
        OUTER:
        while (!rset.isEmpty() || fRootIndex < fRoots.size()) {
            int v = 0;
            if (!rset.isEmpty()) {
                v = min[rset.remove(0)];
                r.add(v);
            } else {
                Edge e = fRoots.get(fRootIndex);
                if (e.isDeleted()) {
                    fRootIndex++;
                    continue OUTER;
                }
                v = e.getTo();
                b.add(e);
                fRootIndex++;
            }
            Edge curr = lambda[v];
            while (curr != null && curr.isDeleted() == false) {
                curr.setDeleted(true);
                fRoots.addAll(curr.getfChildren());
                curr = curr.getfParent();
            }
        }
        return new ResultWrapper(r, b);
    }

    /**
     * Odstrani a vrati minimalni hranu vstupujici do daneho uzlu
     * @param k cislo uzlu
     * @return minimalni vstupni hrana, pokud hrana neexistuje - dummy hrana
     * z uzlu 0 do uzlu 0 s vahou Integer.MIN_VALUE
     */
    private Edge min(int k) {
        Edge e = queues[k].returnTop();
        if (e == null) e = new Edge(0, 0, Integer.MIN_VALUE);
        return e;
    }
}

/**
 * Binomialni halda
 * Radi prvky dle priority (mensi == vyssi priorita)
 * @author Pavel Micka
 */
class BinomialHeap {
    private Node<Edge>[] nodes;
    private Node<Edge> min;
    private int offset = 0;

    public BinomialHeap(double capacity) {
        min = null;
        nodes = new Node[((int) (Math.log(capacity) / Math.log(2))) + 2];
    }

    public void decreaseAllKeys(int ammout) {
        this.offset -= ammout;
    }

    /**
     * Vlozi prvek do haldy, pokud je mensi nez aktualni minimalni, tak
     * jej ulozi jako nejmensi prvek
     * @param e
     */
    public void insert(Edge e) {
        Node<Edge> n = new Node<Edge>(e);
        if (nodes[0] != null) merge(n, nodes[0]);
        else nodes[0] = n;

        if (min == null) min = n;
        else if (min.value.compareTo(n.value) == 1) min = n;
    }

    /**
     * Smaze a vrati uzel s nejvyssi prioritou
     * @return
     */
    public Edge returnTop() {
        if (min == null) return null;

        Edge tmp = (Edge) min.value;
        tmp.setWeight(tmp.getWeight() + this.offset);
        nodes[min.order] = null; //strom vyjmeme
        for (Node n : min.children) { //a z potomku udelame nove stromu
            n.parent = null;
            if (nodes[n.order] != null) merge(n, nodes[n.order]);
            else nodes[n.order] = n;
        }
        min.children.clear();
        Edge minVal = null;
        Node minNode = null;
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                if (minVal == null) {
                    minVal = nodes[i].value;
                    minNode = nodes[i];
                } else if (minVal.compareTo(nodes[i].value) == 1) {
                    minVal = nodes[i].value;
                    minNode = nodes[i];
                }
            }
        }
        min = minNode;
        return (Edge) tmp;
    }

    /**
     * Provede slouceni binomialnich stromu, je-li nutno provede slucovani
     * do vyssich radu
     * alespon jedna z hald musi byt bezpodminecne jiz soucasti haldy, obe haldy
     * musi byt stejneho radu
     * @param a halda 1
     * @param b halda 2
     * @return sloucena halda
     */
    private void merge(Node a, Node b) {
        if (a.order != b.order)
            throw new IllegalArgumentException("Haldy nejsou stejneho radu");
        int tmpOrder = a.order;
        nodes[tmpOrder] = null;
        Node newRoot = null;
        if (a.value.compareTo(b.value) < 0) {
            b.parent = a;
            a.children.add(b);
            a.order++;
            newRoot = a;
        } else {
            a.parent = b;
            b.children.add(a);
            b.order++;
            newRoot = b;
        }
        if (nodes[tmpOrder + 1] == null) nodes[tmpOrder + 1] = newRoot;
        else merge(newRoot, nodes[tmpOrder + 1]);
    }

    /**
     * Slouci haldy, pokud mergovana halda obsahuje mensi prvek, nez halda, na
     * ktere je volana operace, pak je tento prvek minimalni i v nove halde
     * (Toto lze napsat lepe)
     * @param heap
     */
    public void mergeHeaps(BinomialHeap heap) {
        for (int i = 0; i < heap.nodes.length; i++) {
            if (heap.nodes[i] != null) {
                decreaseWeights(this.offset - heap.offset, heap.nodes[i]);
                if (nodes[i] == null) nodes[i] = heap.nodes[i];
                else merge(nodes[i], heap.nodes[i]);
            }
        }
        if (this.min == null) this.min = heap.min;
        else if (this.min != null && heap.min != null && heap.min.value.getWeight() < min.value.getWeight())
            min = heap.min;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) builder.append(i + ": null\n");
            else builder.append(i + ":\n" + nodes[i].toString() + "\n");
        }
        return builder.toString();
    }

    private void decreaseWeights(int ammount, Node<Edge> node) {
        node.value.setWeight(node.value.getWeight() - ammount);
        for (Node n : node.children) {
            decreaseWeights(ammount, n);
        }
    }

    /**
     * Reprezentuje uzel binomialni haldy
     * @param <ENTITY>
     */
    private class Node<ENTITY extends Comparable> {

        Node<ENTITY> parent;
        ENTITY value;
        List<Node> children;
        int order; //rad binomialni haldy

        public Node(ENTITY value) {
            this.value = value;
            children = new ArrayList<Node>();
            order = 0;
        }

        @Override
        public String toString() {
            String s = "Node value: " + value + ", order: " + order + "\n";
            for (Node n : children) {
                s += n.toString();
            }
            return s;
        }
    }
}

/**
 * Hrana orientovaneho grafu
 * @author malejpavouk
 */
class Edge implements Comparable<Edge> {
    private Edge fParent; //rodic ve stromu F
    private List<Edge> fChildren; //deti ve stromu F
    private int from; //uzel z
    private int to; //uzel do
    private int weight; //vaha
    private int originalWeight; //puvodni vaha hrany
    private boolean deleted; //priznak smazani hrany

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.originalWeight = weight;
        this.deleted = false;

        this.fChildren = new ArrayList<Edge>();
    }

    public int compareTo(Edge o) {
        if (this.getWeight() < o.getWeight()) return -1;
        else if (this.getWeight() > o.getWeight()) return 1;
        return 0;
    }

    @Override
    public String toString() {
        return getFrom() + " " + getTo() + " " + getWeight();
    }

    /**
     * @return the fParent
     */
    public Edge getfParent() {
        return fParent;
    }

    /**
     * @param fParent the fParent to set
     */
    public void setfParent(Edge fParent) {
        this.fParent = fParent;
    }

    /**
     * @return the fChildren
     */
    public List<Edge> getfChildren() {
        return fChildren;
    }

    /**
     * @param fChildren the fChildren to set
     */
    public void setfChildren(List<Edge> fChildren) {
        this.fChildren = fChildren;
    }

    /**
     * @return the from
     */
    public int getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(int from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public int getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(int to) {
        this.to = to;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * @return the originalWeight
     */
    public int getOriginalWeight() {
        return originalWeight;
    }

    /**
     * @param originalWeight the originalWeight to set
     */
    public void setOriginalWeight(int originalWeight) {
        this.originalWeight = originalWeight;
    }

    /**
     * @return the deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * @param deleted the deleted to set
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

/**
 * Implementace Union-Find problemu pro Edmondsuv algoritmus
 * @author malejpavouk
 */
class DisjointSet {
    private Node[] nodes;

    public DisjointSet(int nodeCount) {
        nodes = new Node[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            nodes[i] = new Node();
            nodes[i].id = i;
        }
    }

    /**
     * Provede sjednoceni komponent, ve kterych jsou uzly "a" a "b"
     * Union provadi vzdy do A
     * @param a cislo uzlu a
     * @param b cislo uzlu b
     * @return cislo reprezentanta sjednocene komponenty
     */
    public int union(int a, int b) {
        Node repA = nodes[find(a)];
        Node repB = nodes[find(b)];

        repB.parent = repA;
        return repA.id;
    }

    /**
     * Vrati reprezentanta zadaneho uzlu
     * @param a cislo uzlu, jehoz reprezentanta hledame
     * @return cislo reprezentanta uzlu
     */
    public int find(int a) {
        Node n = nodes[a];
        int jumps = 0;
        while (n.parent != null) {
            n = n.parent;
            jumps++;
        }
        if(jumps > 1) repair(a, n.id);
        return n.id;
    }
    /**
     * Provede kontrakci cesty do korene
     * @param a list, ze ktereho se zacina kontrahovat
     * @param rootId id rootu
     */
    private void repair(int a, int rootId) {
        Node curr = nodes[a];
        while(curr.id != rootId){
            Node tmp = curr.parent;
            curr.parent = nodes[rootId];
            curr = tmp;
        }
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nodes.length; i++) {
            builder.append(find(i) + " ");
        }
        return builder.toString();
    }

    /**
     * Uzel n-arniho stromu
     */
    private static class Node {
        /**
         * Rodic
         */
        Node parent;
        /**
         * Identifikator uzlu
         */
        int id;
    }
}

/**
 * Prepravka na vysledek Edmondsova algoritmu
 * @author malejpavouk
 */
class ResultWrapper {
    private List<Integer> roots; //hrany minimalniho lesa
    private List<Edge> edges; //hrany minimalniho lesa

    public ResultWrapper(List<Integer> roots, List<Edge> edges) {
        this.roots = roots;
        this.edges = edges;
    }
    /**
     * @return the roots
     */
    public List<Integer> getRoots() {
        return roots;
    }

    /**
     * @return the edges
     */
    public List<Edge> getEdges() {
        return edges;
    }
}


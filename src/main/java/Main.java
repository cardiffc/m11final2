import java.util.HashSet;
import java.util.concurrent.ForkJoinPool;

public class Main {
    protected static HashSet<String> copyUrl = new HashSet<>();
    protected static final String ROOT = "https://skillbox.ru/";
    public static void main(String[] args) {
        Node root = new Node(ROOT);
        HashSet map = new ForkJoinPool().invoke(new Builder(root));
       // copyUrl.forEach(System.out::println);

    }
}

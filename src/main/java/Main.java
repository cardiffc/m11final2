import java.util.HashSet;
import java.util.concurrent.ForkJoinPool;

public class Main  {
    protected static HashSet<String> copyUrl = new HashSet<>();
    protected static final String ROOT = "https://skillbox.ru/";
    public static void main(String[] args) {
               Node root = new Node(ROOT);
               HashSet map = new ForkJoinPool().invoke(new Builder(root));
               map.add(ROOT);
               map.forEach(link -> {
                   int count = (int) link.toString().chars().filter(num -> num == '/').count();
                   String link2 = link.toString();
                   for (int i = 0; i < count - 3 ; i++) {
                       link2 = "\t".concat(link2);

                   }
                   System.out.println(link2);
               });

    }
}

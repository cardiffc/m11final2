import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;

public class Main  {
    protected static HashSet<String> copyUrl = new HashSet<>();
    protected static final String ROOT = "https://skillbox.ru/";
    public static void main(String[] args) {
        Node root = new Node(ROOT);
        TreeSet map = new ForkJoinPool().invoke(new Builder(root));
        map.add(ROOT);
        ArrayList<String> mapToFile = new ArrayList<>();
        map.forEach(link -> {
            int count = (int) link.toString().chars().filter(num -> num == '/').count();
            String link2 = link.toString();
            for (int i = 0; i < count - 3 ; i++) {
                link2 = "\t".concat(link2);
            }
            mapToFile.add(link2);
        });
        try {
            Files.write(Paths.get("data/out/sitemap.txt" ), mapToFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

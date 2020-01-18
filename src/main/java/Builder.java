import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Builder extends RecursiveTask<HashSet> {

    private Node node;

    public Builder(Node node) {
        this.node = node;
    }


    @Override
    protected HashSet compute() {
        String root = node.getRoot();
        Document doc = null;
        try {
            Thread.sleep(1000);
            doc = Jsoup.connect(root).maxBodySize(0).get();
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        Elements preChilds = doc.select("a");
        preChilds.forEach(pc -> {
            String child = pc.attr("abs:href");
            if (checkUrl(child) && addUrl(child)) {
                node.childs.add(new Node(child));
            }

        });
        HashSet<String> tempResult = new HashSet<>();
        for (Node child : node.getChilds()) {
            tempResult.add(child.getRoot());
        }
        List<Builder> tasks = new LinkedList<>();
        for (Node child2 : node.getChilds()) {
            Builder task = new Builder(child2);
            task.fork();
            tasks.add(task);
        }
        for (Builder task : tasks) {
            tempResult.addAll(task.join());
        }
        return tempResult;
    }
    private synchronized boolean addUrl (String url) {
        return Main.copyUrl.add(url);
    }

    private boolean checkUrl (String url) {
        return url.startsWith(Main.ROOT) && url.endsWith("/") && !url.equals("https://skillbox.ru/media/topic//")
             && !url.equals("https://skillbox.ru/media/authors/eugenya-sycheva/");
    }

}

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
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
            doc = Jsoup.connect(root).maxBodySize(0).get();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Elements preChilds = doc.select("a");
       // ArrayList<Node> n
        preChilds.forEach(pc -> {
            String child = pc.attr("abs:href");
            if (checkUrl(child) && addUrl(child)) {
                node.childs.add(new Node(child));
            }

        });
        node.getChilds().forEach(el -> System.out.println(el.getRoot()));



        return null;
    }

    private synchronized boolean addUrl (String url) {
        return Main.copyUrl.add(url);
    }

    private boolean checkUrl (String url) {
        return url.startsWith(Main.ROOT) && url.endsWith("/");
    }

}

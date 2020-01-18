import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Node {
    private String root;
    public List<Node> childs = new ArrayList<>();

    public Node(String root) {
        this.root = root;
    }

    public String getRoot() {
        return root;
    }

    public List<Node> getChilds() {
        return childs;
    }

    public void setChilds(List<Node> childs) {
        this.childs = childs;
    }
}

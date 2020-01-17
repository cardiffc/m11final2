import java.util.HashSet;

public class Node {
    private String root;
    public  HashSet<String> childs = new HashSet<>();

    public Node(String root) {
        this.root = root;
    }

    public String getRoot() {
        return root;
    }

    public HashSet<String> getChilds() {
        return childs;
    }

    public void setChilds(HashSet<String> childs) {
        this.childs = childs;
    }
}

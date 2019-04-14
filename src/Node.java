public class Node {

    int Key;
    int level;
    private String name;
    Node left;
    Node right;

    Node ( int key, String name) {
        this.Key = key;
        this.name = name;
        this.level = 1;
    }

    @Override
    public String toString () {
        return "Node{" +
                "Key=" + Key +
                ", name='" + name +
                '}';
    }
}

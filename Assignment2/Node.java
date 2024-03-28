package Assignment2;

public class Node <T>{
    //Taken from page 461
    private T info;
    private Node<T> left;
    private Node<T> right;

    public Node(T info){
        this.info = info;
        this.left = null;
        this.right = null;
    }

    public void setInfo(T info){
        this.info = info;
    }

    public T getInfo(){
        return info;
    }

    public void setLeft(Node<T> link){
        left = link;
    }
    public void setRight(Node<T> link){
        right = link;
    }

    public Node<T> getLeft(){
        return left;
    }

    public Node<T> getRight(){
        return right;
    }
}

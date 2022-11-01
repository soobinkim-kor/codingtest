package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class baek1991 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Node> nodes = new ArrayList<>();
        for(int i=0;i<n;i++){
            String[] info = br.readLine().split(" ");
            String root = info[0];
            String left = info[1];
            String right= info[2];
            Node node = new Node(root.charAt(0),left.charAt(0),right.charAt(0));
            nodes.add(node);
        }
        boolean[] visited = new boolean[26];
        visited[0]=true;
        preOrder(nodes,nodes.get(0),visited);

        visited = new boolean[26];
        visited[0]=true;
        System.out.println();
        inOrder(nodes,nodes.get(0),visited);

        visited = new boolean[26];
        visited[0]=true;
        System.out.println();
        postOrder(nodes,nodes.get(0),visited);
    }

    public static void preOrder(ArrayList<Node> nodes, Node node, boolean[] visited){

        char left = node.getLeft();
        char right = node.getRight();
        char root = node.getRoot();
        System.out.print(root);

        if(left!='.' && !visited[left-'A']){
            Node leftNode = getLeftNode(node,nodes);
            visited[left-'A']=true;
            preOrder(nodes,leftNode,visited);
        }

        if(right!='.' && !visited[right-'A']){
            Node rightNode = getRightNode(node,nodes);
            visited[right-'A']=true;
            preOrder(nodes,rightNode,visited);
        }
    }

    public static void inOrder(ArrayList<Node> nodes, Node node, boolean[] visited){

        char left = node.getLeft();
        char right = node.getRight();
        char root = node.getRoot();

        if(left!='.' && !visited[left-'A']){
            Node leftNode = getLeftNode(node,nodes);
            visited[left-'A']=true;
            inOrder(nodes,leftNode,visited);
        }

        System.out.print(root);

        if(right!='.' && !visited[right-'A']){
            Node rightNode = getRightNode(node,nodes);
            visited[right-'A']=true;
            inOrder(nodes,rightNode,visited);
        }
    }



    public static void postOrder(ArrayList<Node> nodes, Node node, boolean[] visited){
        char left = node.getLeft();
        char right = node.getRight();
        char root = node.getRoot();

        if(left!='.' && !visited[left-'A']){
            if(left!='.' && !visited[left-'A']){
                Node leftNode = getLeftNode(node,nodes);
                visited[left-'A']=true;
                postOrder(nodes,leftNode,visited);
            }
        }

        if(right!='.' && !visited[right-'A']){
            if(right!='.' && !visited[right-'A']){
                Node rightNode = getRightNode(node,nodes);
                visited[right-'A']=true;
                postOrder(nodes,rightNode,visited);
            }
        }
        System.out.print(root);
    }


    public static Node getLeftNode(Node root, ArrayList<Node> nodes){
        for(Node node : nodes){
            if(root.getLeft()==node.getRoot()){
                return node;
            }
        }
        return null;
    }
    public static Node getRightNode(Node root, ArrayList<Node> nodes){
        Node rightNode;
        for(Node node : nodes){
            if(root.getRight()==node.getRoot()){
                return node;
            }
        }
        return null;
    }

    public static void preOrder(ArrayList<Node> nodes, boolean[] visited){
        Stack<Node> nodeStack = new Stack<>();

        nodeStack.add(nodes.get(0));
        visited[0]=true;
        while(!nodeStack.isEmpty()){

            Node node = nodeStack.pop();

            char root = node.getRoot();
            char left = node.getLeft();
            char right = node.getRight();
            System.out.print(root);

            if(right!='.' && !visited[right-'A']){
                for(Node rightNode : nodes){
                    if(rightNode.getRoot()==right){
                        visited[right-'A']=true;
                        nodeStack.add(rightNode);
                    }
                }
            }

            if(left!='.' && !visited[left-'A']){
                for(Node leftNode : nodes){
                    if(leftNode.getRoot()==left){
                        visited[left-'A']=true;
                        nodeStack.add(leftNode);
                        break;
                    }
                }
            }
        }
    }



    public static class Node{
        private char root;
        private char left;
        private char right;

        public Node(char root, char left, char right){
            this.root=root;
            this.left=left;
            this.right=right;
        }

        public char getRoot(){
            return this.root;
        }

        public char getLeft(){
            return this.left;
        }

        public char getRight(){
            return this.right;
        }
    }
}

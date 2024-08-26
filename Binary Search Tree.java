// Project Title : Implementation of binary search tree and its application
// Date : 30/09/2023
// Subject : Data Structure(DS)

import java.util.*;

class BST{

    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
    }

    public static Node insert(Node root, int val){
        if(root == null){
            root = new Node(val);
            return root;
        }
        if(root.data > val ){
            //left subtree
            root.left = insert(root.left,val);
        }
        else{
            //right subtree
            root.right = insert(root.right,val);
        }
        return root;
    }

    public static void inorder(Node root){
        if(root == null){
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static boolean search(Node root, int val){
        if(root == null){
            return false;
        }
        if(root.data > val){
            return search(root.left,val);
        }
        else if(root.data == val){
            return true;
        }
        else{
            return search(root.right,val);
        }
    }

    public static Node delete(Node root, int val){
        if(root.data > val){
            root.left = delete(root.left,val);
        }
        else if(root.data < val){
            root.right = delete(root.right,val);
        }
        else{
            // case 1(leaf node):
            if(root.left == null && root.right == null){
                return null;
            }
            // case 2(single child):
            if(root.left == null){
                return root.right;
            }
            else if(root.right == null){
                return root.left;
            }
            // case 3(Two children):
            Node IS = inorderSuccessor(root.right);
            root.data = IS.data;
            root.right =  delete(root.right,IS.data);
        }
        return root;
    }

    public static Node inorderSuccessor(Node root){
        while(root.left != null){
            root = root.left;
        }
        return root;
    }

    public static void printRange(Node root, int x, int y){
        if(root == null){
            return;
        }
        if(root.data >= x && root.data <= y){
            printRange(root.left, x, y);
            System.out.print(root.data + " ");
            printRange(root.right, x, y);
        }
        else if(root.data >= y){
            printRange(root.left, x, y);
        }
        else {
            printRange(root.right, x, y);
        }
    }

    public static void printRootToLeaf(Node root, ArrayList<Integer> path){
        if(root == null){
            return;
        }
       
        path.add(root.data);

        if(root.left == null && root.right == null){
            printPath(path);
        }
        else{
            printRootToLeaf(root.left, path);
            printRootToLeaf(root.right, path);
        }
        path.remove(path.size()-1);
    }

    public static void printPath(ArrayList<Integer> path){
        for(int i=0; i<path.size(); i++){
            System.out.print(path.get(i)+" ---> ");
        }
        System.out.print("Null");
        System.out.println();
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int values[] = {50,40,30,35,60,55,15,57,75,80};
        Node root = null;

        for(int i=0; i<values.length; i++){
            root = insert(root, values[i]);
        }

        int choice;
        do{
            System.out.println();
            System.out.println("Choose from the following operations");
            System.out.println("1. To insert a node");
            System.out.println("2. To delete a node");
            System.out.println("3. To search in BST");
            System.out.println("4. To print nodes between the given range");
            System.out.println("5. To Print all the path from root node to leaf node");
            System.out.println("6. To print inorder traversal");
            System.out.println("7. To Exit");
            choice = sc.nextInt();
            System.out.println();
            switch(choice){

                case 1:
                    System.out.println("Following are the paths from Root Node to Leaf Node before insertion");
                    printRootToLeaf(root, new ArrayList<>());
                    System.out.println();
                    System.out.println("Enter the node value you want to insert");
                    int a = sc.nextInt();
                    System.out.println();
                    System.out.println("Following are the paths from Root Node to Leaf Node after insertion");
                    insert(root,a);
                    printRootToLeaf(root, new ArrayList<>());
                    break;

                case 2:
                    System.out.println("Following are the paths from Root Node to Leaf Node before deletion");
                    printRootToLeaf(root, new ArrayList<>());
                    System.out.println("Enter the node value you want to delete");
                    int b = sc.nextInt();
                    System.out.println();
                    System.out.println("Following are the paths from Root Node to Leaf Node after deletion");
                    delete(root,b);
                    printRootToLeaf(root, new ArrayList<>());
                    break;

                case 3:
                    System.out.println("Enter the node value you want to search in BST");
                    int c = sc.nextInt();
                    if(search(root, c)){
                        System.out.println("Value Found");
                    }
                    else{
                        System.out.println("Value not found");
                    }
                    break;

                case 4:
                    System.out.println("Enter the starting value of the range");
                    int d = sc.nextInt();
                    System.out.println("Enter the ending value of the range");
                    int e = sc.nextInt();
                    System.out.println("Node values between the specified range are");
                    printRange(root, d, e);
                    break;

                case 5:
                    System.out.println("Following are the paths from Root Node to Leaf Node");
                    printRootToLeaf(root, new ArrayList<>());
                    break;

                case 6:
                    System.out.println("The inorder traversal of BST is:");
                    inorder(root);
                    break;

                case 7:
                    System.out.println("Exiting the program");
                    break;

                default:
                    System.out.println("Enter from the given choices");
                    break;

            }
        }while(choice != 7);
    }
}
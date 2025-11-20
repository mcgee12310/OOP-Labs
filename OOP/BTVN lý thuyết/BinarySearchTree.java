import java.util.Scanner;

class TNode {
    int data;
    TNode leftChild, rightChild;

    public TNode(int data) {
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }
}

public class BinarySearchTree {

    // Create a new node
    private TNode makeNode(int data) {
        return new TNode(data);
    }

    // Insert a node into the BST
    public TNode insert(TNode root, int data) {
        if (root == null) {
            return makeNode(data);
        }
        if (data < root.data) {
            root.leftChild = insert(root.leftChild, data);
        } else if (data > root.data) {
            root.rightChild = insert(root.rightChild, data);
        }
        return root;
    }

    // Find a node in the BST
    public TNode find(TNode root, int data) {
        if (root == null) {
            System.out.println("Not found");
            return null;
        }
        if (data == root.data) {
            return root;
        }
        if (data < root.data) {
            return find(root.leftChild, data);
        } else {
            return find(root.rightChild, data);
        }
    }

    // Delete a node from the BST
    public TNode deleteNode(TNode root, int data) {
        if (root == null) {
            return null;
        }
        if (data < root.data) {
            root.leftChild = deleteNode(root.leftChild, data);
        } else if (data > root.data) {
            root.rightChild = deleteNode(root.rightChild, data);
        } else {
            // Node with only one child or no child
            if (root.leftChild == null) {
                return root.rightChild;
            } else if (root.rightChild == null) {
                return root.leftChild;
            }

            // Node with two children: get the inorder successor
            TNode succ = getMin(root.rightChild);
            root.data = succ.data;
            root.rightChild = deleteNode(root.rightChild, succ.data);
        }
        return root;
    }

    // Helper function to find the minimum node in a subtree
    private TNode getMin(TNode root) {
        while (root.leftChild != null) {
            root = root.leftChild;
        }
        return root;
    }

    // Pre-order traversal
    public void preOrder(TNode root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        preOrder(root.leftChild);
        preOrder(root.rightChild);
    }

    // In-order traversal
    public void inOrder(TNode root) {
        if (root == null) return;
        inOrder(root.leftChild);
        System.out.print(root.data + " ");
        inOrder(root.rightChild);
    }

    // Post-order traversal
    public void postOrder(TNode root) {
        if (root == null) return;
        postOrder(root.leftChild);
        postOrder(root.rightChild);
        System.out.print(root.data + " ");
    }

    // Free the tree (Java handles garbage collection automatically)
    public void freeTree(TNode root) {
        root = null;
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        TNode root = null;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.next();
            if (command.equals("#")) break;

            switch (command) {
                case "insert":
                    int insertValue = scanner.nextInt();
                    root = bst.insert(root, insertValue);
                    break;

                case "find":
                    int findValue = scanner.nextInt();
                    TNode foundNode = bst.find(root, findValue);
                    if (foundNode != null) {
                        System.out.println(foundNode.data);
                    }
                    break;

                case "delete":
                    int deleteValue = scanner.nextInt();
                    if (bst.find(root, deleteValue) != null) {
                        root = bst.deleteNode(root, deleteValue);
                        bst.inOrder(root);
                        System.out.println();
                    }
                    break;

                case "preorder":
                    bst.preOrder(root);
                    System.out.println();
                    break;

                case "postorder":
                    bst.postOrder(root);
                    System.out.println();
                    break;
            }
        }

        bst.preOrder(root);

        scanner.close();
        bst.freeTree(root);
    }
}

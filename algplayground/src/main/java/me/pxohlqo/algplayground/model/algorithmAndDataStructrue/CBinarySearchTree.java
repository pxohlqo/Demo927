package me.pxohlqo.algplayground.model.algorithmAndDataStructrue;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CBinarySearchTree<T extends Comparable<T>> {

    private int nodeCount = 0;

    private Node root = null;

    private class Node {
        T data;
        Node left, right;

        public Node(Node left, Node right, T elem) {
            this.data = elem;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return nodeCount;
    }

    public boolean add(T elem) {
        if (contains(elem)) return false;
        else {
            root = add(root, elem);
            nodeCount++;
            return true;
        }
    }

    private Node add(Node node, T elem) {
        if (node == null) {
            node = new Node(null, null, elem);
        } else {
            if (elem.compareTo(node.data) < 0) {
                node.left = new Node(null, null, elem);
            } else {
                node.right = new Node(null, null, elem);
            }
        }
        return node;
    }

    public boolean remove(T elem) {
        if (contains(elem)) {
            root = remove(root, elem);
            nodeCount--;
            return true;
        }
        return false;
    }

    private Node remove(Node node, T elem) {
        if (node == null) return null;
        int cmp = elem.compareTo(node.data);

        if (cmp < 0) {
            node.left = remove(node.left, elem);
        } else if (cmp > 0) {
            node.right = remove(node, elem);
        } else {
            if (node.left == null) {
                Node rightChild = node.right;
                node.right = null;
                node.data = null;
                return rightChild;
            } else if (node.right == null) {
                Node leftChild = node.left;
                node.left = null;
                node.data = null;
                return leftChild;
            } else {
                Node tmp = findMin(node.right);
                // Node tmp = findMax(node.left);
                node.data = tmp.data;
                node.right = remove(node.right, tmp.data);
                // node.left = remove(node.left, tmp.data);
            }
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node findMax(Node node) {
        while (node.right != null) node = node.right;
        return node;
    }

    public boolean contains(T elem) {
        return contains(root, elem);
    }

    private boolean contains(Node node, T elem) {
        if (node == null) return false;

        int cmp = elem.compareTo(node.data);
        if (cmp < 0) {
            return contains(node.left, elem);
        } else if (cmp > 0) {
            return contains(node.right, elem);
        } else {
            return true;
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public enum TreeTraversalOrder {
        PRE_ORDER, IN_ORDER, POST_ORDER, LEVEL_ORDER
    }

    public Iterator<T> traverse(TreeTraversalOrder order) {
        switch (order) {
            case PRE_ORDER:
                return preOrderTraversal();
            case POST_ORDER:
                return postOrderTraversal();
            case IN_ORDER:
                return inOrderTraversal();
            case LEVEL_ORDER:
                return levelOrderTraversal();
            default:
                return null;
        }
    }

    private Iterator<T> preOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final CStack<Node> stack = new CStack<>();
        stack.push(root);
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                Node node = stack.pop();
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private Iterator<T> inOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final CStack<Node> stack = new CStack<>();
        stack.push(root);

        return new Iterator<T>() {
            Node trav = root;

            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount!= nodeCount) throw new ConcurrentModificationException();
                while (trav != null && trav.left != null) {
                    stack.push(trav.left);
                    trav = trav.left;
                }

                Node node = stack.pop();
                if (node.right!=null){
                    stack.push(node.right);
                    trav = node.right;
                }

                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private Iterator<T> postOrderTraversal() {
        final int exceptedNodeCount = nodeCount;
        final CStack<Node> stack1 = new CStack<>();
        final CStack<Node> stack2 = new CStack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            Node node = stack1.pop();
            if (node != null) {
                stack2.push(node);
                if (node.left!=null) stack1.push(node.left);
                if (node.right!=null) stack1.push(node.right);
            }
        }
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (exceptedNodeCount!=nodeCount) throw new ConcurrentModificationException();
                return root!=null&&!stack2.isEmpty();
            }

            @Override
            public T next() {
                if(exceptedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return stack2.pop().data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private Iterator<T> levelOrderTraversal() {
        final int exceptedNodeCount = nodeCount;
        // final Queue<Node> queue = new LinkedList<>();
        final CQueue<Node> queue = new CQueue<>();
        queue.offer(root);

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (exceptedNodeCount!=nodeCount) throw new ConcurrentModificationException();
                return root!=null&&!queue.isEmpty();
            }

            @Override
            public T next() {
                if (exceptedNodeCount!=nodeCount)throw new ConcurrentModificationException();
                Node node = queue.poll();
                if (node.left!=null) queue.offer(node.left);
                if (node.right!=null) queue.offer(node.right);
                return node.data;
            }
        };
    }

}

package ru.nsu.izmailova.tree;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Generic tree class. Each node contains a value and a list of its children.
 *
 * @param <T> type of tree elements
 */
public class Tree<T> implements Iterable<T> {
    private T value;
    private Tree<T> parent;
    private final List<Tree<T>> children;
    private Search mode;
    private int modificationCount;

    /**
     * Create an empty tree with the given value.
     *
     * @param val the value to set
     * @throws NullPointerException if val is null
     */
    public Tree(T val) throws NullPointerException {
        if (val == null) {
            throw new NullPointerException();
        }

        value = val;
        parent = null;
        children = new ArrayList<>();
        mode = Search.BREADTH;
        modificationCount = 0;
    }

    /**
     * Returns an iterator over the elements in this tree in proper sequence.
     * Order of the elements will be defined by mode field;
     * use with() method to specify needed traverse option.
     *
     * @return an iterator over the elements
     */
    @Override
    public Iterator<T> iterator() {
        if (mode == Search.BREADTH) {
            return new BreadthIterator();
        } else if (mode == Search.DEPTH) {
            return new DepthIterator();
        } else {
            throw new IllegalStateException();
        }
    }

    private void update() {
        modificationCount++;
    }

    private void modify() {
        Tree<T> node = this;

        while (node.parent != null) {
            node.update();
            node = node.parent;
        }

        node.update();
    }

    /**
     * Change the traverse mode of tree.
     *
     * @param mode the algorithm to traverse tree
     * @return this
     * @throws NullPointerException if mode is null
     */
    public Tree<T> with(Search mode) throws NullPointerException {
        if (mode == null) {
            throw new NullPointerException();
        }

        modify();

        this.mode = mode;
        children.forEach(t -> t.with(mode));

        return this;
    }


    /**
     * Get the quantity of nodes in this tree, including the head.
     *
     * @return the quantity of nodes
     */
    public int size() {
        return children.stream().mapToInt(Tree::size).sum() + 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Tree<?> other = (Tree<?>) obj;

        if (!value.equals(other.value)) {
            return false;
        }

        if (children.size() != other.children.size()) {
            return false;
        }

        for (int i = 0; i < children.size(); i++) {
            if (!children.get(i).equals(other.children.get(i))) {
                return false;
            }
        }
        return true;
    }
    /**
     * Add a new value to the tree as a new branch.
     *
     * @param val a value to add
     * @return this
     * @throws NullPointerException if val is null
     */
    public Tree<T> add(T val) throws NullPointerException {
        if (val == null) {
            throw new NullPointerException();
        }

        modify();

        Tree<T> child = new Tree<>(val);
        child.parent = this;
        children.add(child);

        return this;
    }

    /**
     * Add an existing tree as a branch to this tree.
     *
     * @param branch a tree to add
     * @return this
     * @throws NullPointerException if branch is null
     */
    public Tree<T> add(Tree<T> branch) throws NullPointerException {
        if (branch == null) {
            throw new NullPointerException();
        }

        modify();

        branch.parent = this;
        children.add(branch);

        return this;
    }

    /**
     * Get the value in this node.
     *
     * @return the value in this node
     */
    public T getValue() {
        return this.value;
    }

    /**
     * Get a branch of this tree.
     *
     * @param index the number of a needed branch
     * @return a requested branch
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Tree<T> getBranch(int index) throws IndexOutOfBoundsException {
        return children.get(index);
    }

    /**
     * Get the parent of this tree.
     *
     * @return the parent
     */
    public Tree<T> getParent() {
        return parent;
    }

    /**
     * Set the value in this node.
     *
     * @param val value to set
     * @return this
     * @throws NullPointerException if val is null
     */
    public Tree<T> setValue(T val) throws NullPointerException {
        if (val == null) {
            throw new NullPointerException();
        }

        modify();

        value = val;
        return this;
    }

    /**
     * Remove a branch from this tree.
     *
     * @param index the number of a branch to remove
     * @return a removed branch
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Tree<T> remove(int index) throws IndexOutOfBoundsException {
        Tree<T> child = children.remove(index);
        child.parent = null;

        modify();

        return child;
    }

    private abstract class TreeIterator implements Iterator<T> {
        protected final ArrayDeque<Tree<T>> deque;
        private final Tree<T> root;
        private final int modificationState;

        private TreeIterator() {
            deque = new ArrayDeque<>();
            deque.add(Tree.this);
            root = Tree.this;
            modificationState = Tree.this.modificationCount;
        }

        @Override
        public boolean hasNext() {
            return !deque.isEmpty();
        }

        @Override
        public T next() throws ConcurrentModificationException {
            if (root.modificationCount != modificationState) {
                throw new ConcurrentModificationException();
            }

            return null;
        }
    }

    private class BreadthIterator extends TreeIterator {
        @Override
        public T next() throws ConcurrentModificationException, NoSuchElementException {
            super.next();

            Tree<T> polled = deque.poll();
            if (polled == null) {
                throw new NoSuchElementException();
            }

            deque.addAll(polled.children);

            return polled.value;
        }
    }

    private class DepthIterator extends TreeIterator {
        @Override
        public T next() throws ConcurrentModificationException, NoSuchElementException {
            super.next();

            Tree<T> popped;

            popped = deque.pop();

            for (int i = popped.children.size() - 1; i >= 0; i--) {
                deque.addFirst(popped.children.get(i));
            }

            return popped.value;
        }
    }

    /** Search algorithm options. */
    public enum Search {
        /** Use breadth-first search algorithm to traverse the tree. */
        BREADTH,

        /** Use depth-first search algorithm to traverse the tree. */
        DEPTH
    }

    public static void main(String[] args) {

    }
}




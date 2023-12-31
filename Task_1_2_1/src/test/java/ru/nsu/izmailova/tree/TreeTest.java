package ru.nsu.izmailova.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/** A set of tests for the Tree class. */
public class TreeTest {
    public static Tree<Integer> expected;

    public static Tree<Integer> oneTree;
    public static Tree<Integer> twoTree;
    public static Tree<Integer> branch;
    public static List<Integer> breadth =
            List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
    public static List<Integer> depth =
            List.of(1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 3, 12, 16, 17, 13, 14, 15);

    /** Initialize tree and branch. */
    @BeforeAll
    public static void initialization() {
        expected = new Tree<>(1);
        expected.add(2).getBranch(0).add(4).add(5).add(6).add(7).add(8).add(9).add(10).add(11);

        branch = new Tree<>(3);
        branch.add(12).getBranch(0).add(16).add(17).getParent().add(13).add(14).add(15);

        expected.add(branch);
    }

    @Test
    public void testBreadthTraverse() {
        List<Integer> actual = new ArrayList<>();
        expected.with(Tree.Search.BREADTH).iterator().forEachRemaining(actual::add);

        assertEquals(breadth, actual);
    }

    @Test
    public void testDepthTraverse() {
        List<Integer> actual = new ArrayList<>();
        expected.with(Tree.Search.DEPTH).iterator().forEachRemaining(actual::add);

        assertEquals(depth, actual);
    }

    @Test
    public void testEquals() {
        oneTree = new Tree<>(1);
        oneTree.add(2).add(3);
        twoTree = new Tree<>(1);
        twoTree.add(3).add(2);

        assertFalse(oneTree.equals(twoTree));

        oneTree = new Tree<>(1);
        oneTree.add(2).add(4).add(5).add(6).add(7).add(8).add(9).add(10).add(11);

        twoTree = new Tree<>(1);
        twoTree.add(2).add(4).add(5).add(6).add(7).add(8).add(9).add(10).add(11);

        assertEquals(oneTree, twoTree);
    }


    @Test
    public void testSize() {
        assertEquals(17, expected.size());
    }

    @Test
    public void testRelationsAndGetters() {
        assertNull(expected.getParent());
        assertEquals(expected, branch.getParent());

        assertEquals(1, expected.getValue());
        assertThrows(IndexOutOfBoundsException.class, () -> expected.getBranch(100));
    }

    @Test
    public void testSetter() {
        expected.setValue(100);
        assertEquals(100, expected.getValue());
        expected.setValue(1);
        assertEquals(1, expected.getValue());
    }

    @Test
    public void testNoSuchElementException() {
        assertThrows(
                NoSuchElementException.class,
                () -> {
                    Iterator<Integer> iterator = expected.with(Tree.Search.DEPTH).iterator();
                    while (true) {
                        iterator.next();
                    }
                });
    }

    @Test
    public void testConcurrentModificationException() {
        assertThrows(
                ConcurrentModificationException.class,
                () -> {
                    for (Integer integer : expected) {
                        expected.setValue(10).setValue(1);
                    }
                });
    }

    @Test
    public void testRemove() {
        Tree<Integer> removed = expected.remove(1);

        assertThrows(IndexOutOfBoundsException.class, () -> expected.remove(1));
        assertNull(removed.getParent());

        expected.add(removed);
    }
}
package ru.nsu.izmailova.substring;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.Channels;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SubstringSearcherTest {
    @Test
    public void simpleTest() throws IOException {
        try (InputStream stream =
                     getClass().getClassLoader().getResourceAsStream("simple.txt")) {
            SubstringSearcher alg = new SubstringSearcher(stream);
            List<Integer> expected = List.of(2);
            List<Integer> actual = alg.rabinKarp("dad");

            Assertions.assertEquals(expected, actual);
        }
    }

    @Test
    public void hugeTest() throws IOException {
        try (RandomAccessFile f =
                     new RandomAccessFile("./src/test/resources/HugeTest.txt", "rw")) {
            f.setLength(2000000L);
            f.seek(2341);
            f.writeBytes("test");
            f.seek(12312);
            f.writeBytes("test");
            f.seek(1558188);
            f.writeBytes("test");
            f.seek(0);

            SubstringSearcher alg = new SubstringSearcher(
                    Channels.newInputStream(f.getChannel()));
            String subline = "test";

            List<Integer> act = alg.rabinKarp(subline);
            List<Integer> exp = Arrays.asList(2341, 12312, 1558188);

            Assertions.assertEquals(exp, act);
        }
    }

    @Test
    public void repeatTest() throws IOException {
        try (InputStream stream =
                     getClass().getClassLoader().getResourceAsStream("repeatTest.txt")) {
            SubstringSearcher alg = new SubstringSearcher(stream);
            List<Integer> expected = List.of(0, 2, 4, 6, 8, 10);
            List<Integer> actual = alg.rabinKarp("ahaha");

            Assertions.assertEquals(expected, actual);
        }
    }

    @Test
    public void codingTest() throws IOException {
        try (InputStream stream =
                     getClass().getClassLoader().getResourceAsStream("codingTest.txt")) {
            SubstringSearcher alg = new SubstringSearcher(stream);
            List<Integer> expected = List.of(0, 8, 16);
            List<Integer> actual = alg.rabinKarp("ィ");

            Assertions.assertEquals(expected, actual);
        }
    }
}
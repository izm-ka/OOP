package ru.nsu.izmailova.substring;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
}
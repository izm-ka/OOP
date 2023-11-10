package ru.nsu.izmailova.substring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for searching substrings using the Rabin-Karp algorithm.
 * <p></p>
 * D is a number of accepted symbols,
 * Q is a prime number for mod operations,
 * p - hash of pattern,
 * t - hash of text
 */
public class SubstringSearcher {
    private static final int D = 256; // Number of characters in the input alphabet
    private static final int Q = 101; // A prime number for hashing

    private final InputStream stream; // Input stream to read from

    /**
     * Constructor for SubstringSearcher.
     *
     * @param stream text to find matches
     */
    public SubstringSearcher(InputStream stream) {
        this.stream = stream;
    }

    /**
     * Rabin-Karp algorithm.
     *
     * @param pattern the pattern to search for
     * @return result is a list of indexes in the text where patterns start
     * @throws IOException If an I/O error occurs
     */
    public List<Integer> rabinKarp(String pattern) throws IOException {
        try (Reader reader = new BufferedReader(
                new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            List<Integer> result = new ArrayList<>();
            int patLen = pattern.length(); // Length of the pattern
            int p = 0; // Hash value for pattern
            int t = -1; // Hash value for the text window
            int h = 1; // Hash multiplier
            int i;

            // Precompute the hash value of the pattern
            for (i = 0; i < patLen - 1; i++) {
                h = (h * D) % Q;
                p = (D * p + pattern.charAt(i)) % Q;
            }
            p = (D * p + pattern.charAt(i)) % Q;

            int nextChar;
            int start = 0; // Start index of the current text window
            int end = 0; // End index of the current text window
            int count = 0; // Counter for stream end handling
            StringBuilder text = new StringBuilder(); // Buffer for the text window

            // Iterate through the input stream to find matches
            while (true) {
                if ((nextChar = reader.read()) == -1) {
                    count++;
                    if (count == 2) {
                        break;
                    }
                }
                text.append((char) nextChar);
                end++;

                // If the text window is of the same length as the pattern, perform pattern matching
                if (end == patLen + 1) {
                    if (t == -1) {
                        // Calculate the hash value for the first text window
                        t = 0;
                        for (i = 0; i < patLen; i++) {
                            t = (D * t + text.charAt(i)) % Q;
                        }
                    }
                    // If hash values match, check for actual pattern match
                    if (p == t) {
                        int j;
                        for (j = 0; j < patLen; j++) {
                            if (text.charAt(j) != pattern.charAt(j)) {
                                break;
                            }
                        }
                        if (j == patLen) {
                            // Pattern found, add the start index to the result list
                            result.add(start);
                        }
                    }
                    // Update the hash value for the next text window
                    t = (D * (t - text.charAt(0) * h) + text.charAt(end - 1)) % Q;
                    if (t < 0) {
                        t = (t + Q);
                    }
                    // Slide the text window by deleting the first character and updating indices
                    text.deleteCharAt(0);
                    end = patLen;
                    start++;
                }
            }
            return result;
        }
    }
}

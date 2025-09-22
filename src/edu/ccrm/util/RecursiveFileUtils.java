package edu.ccrm.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Utility class with a recursive method. [cite: 33, 107]
 */
public class RecursiveFileUtils {

    /**
     * Recursively computes the total size of a directory.
     * @param path The directory path.
     * @return The total size in bytes.
     * @throws IOException If an I/O error occurs.
     */
    public static long calculateDirectorySize(Path path) throws IOException {
        try (Stream<Path> walk = Files.walk(path)) {
            return walk
                    .filter(Files::isRegularFile)
                    .mapToLong(p -> {
                        try {
                            return Files.size(p);
                        } catch (IOException e) {
                            return 0L;
                        }
                    })
                    .sum();
        }
    }

    // Demonstration of bitwise operators and a labeled jump [cite: 51, 37]
    public void demoControlFlow() {
        int x = 5; // 0101
        int y = 3; // 0011
        System.out.println("x & y = " + (x & y)); // Bitwise AND -> 0001 (1)
        System.out.println("x | y = " + (x | y)); // Bitwise OR  -> 0111 (7)

        OUTER_LOOP: // Labeled jump target
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    System.out.println("Breaking to outer loop from i=1, j=1");
                    break OUTER_LOOP; // Labeled break
                }
            }
        }
    }
}
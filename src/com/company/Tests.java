package com.company;

import org.junit.Test;
import org.assertj.core.api.Assertions;

public class Tests {

    private void check(String[][] array, String[][] rightArray, String name) {
        System.out.println(name);
        System.out.println("Input");
        for (String[] path : array) {
            for (String square : path) {
                System.out.print(square);
            }
            System.out.println();
        }
        Assertions.assertThat(Main.ProcessingData(array)).as("Result").isEqualTo(rightArray);
        System.out.println("Output");
        for (String[] path : rightArray) {
            for (String square : path) {
                System.out.print(square);
            }
            System.out.println();
        }
        System.out.println();
    }

    @Test
    public void testResult() throws Exception {
        check(new String[][]{
                        {"#", "s", "#", "#", "#"},
                        {"#", ".", ".", ".", "#"},
                        {".", ".", "#", ".", "#"},
                        {".", "#", ".", ".", "#"},
                        {"#", "#", "#", ".", "f"},
                },
                new String[][]{
                        {"#", "s", "#", "#", "#"},
                        {"#", "*", "*", "*", "#"},
                        {".", ".", "#", "*", "#"},
                        {".", "#", ".", "*", "#"},
                        {"#", "#", "#", "*", "f"},
                }, "Test 5x5. Good.");
        check(new String[][]{
                        {"#", "s", "#", "#", "#"},
                        {"#", ".", ".", ".", "#"},
                        {".", ".", "#", "#", "#"},
                        {".", ".", ".", ".", "#"},
                        {"#", "#", "#", ".", "f"},
                },
                new String[][]{
                        {"#", "s", "#", "#", "#"},
                        {"#", "*", ".", ".", "#"},
                        {".", "*", "#", "#", "#"},
                        {".", "*", "*", "*", "#"},
                        {"#", "#", "#", "*", "f"},
                }, "Test 5x5. Good.");
        check(new String[][]{
                        {"#", "s", "#", "#"},
                        {"#", ".", "#", "#"},
                        {".", ".", ".", "#"},
                        {"#", "#", ".", "f"},
                },
                new String[][]{
                        {"#", "s", "#", "#"},
                        {"#", "*", "#", "#"},
                        {".", "*", "*", "#"},
                        {"#", "#", "*", "f"},
                }, "Test 4x4. Good.");
        check(new String[][]{
                        {"#", "s", "."},
                        {".", ".", "#"},
                        {"#", ".", "f"},
                },
                new String[][]{
                        {"#", "s", "."},
                        {".", "*", "#"},
                        {"#", "*", "f"},
                }, "Test 3x3. Good.");
        check(new String[][]{
                        {"#", "s"},
                        {".", "."},
                        {"#", "f"},
                },
                new String[][]{
                        {"#", "s"},
                        {".", "."},
                        {"#", "f"},
                }, "Test 3x3. Not square.");
        check(new String[][]{
                        {"f", "#"},
                        {".", "s"},
                },
                new String[][]{
                        {"f", "#"},
                        {"*", "s"},
                }, "Test 2x2. Good.");
        check(new String[][]{
                        {"f", "#"},
                        {"#", "s"},
                },
                new String[][]{
                        {"f", "#"},
                        {"#", "s"},
                }, "Test 2x2. No passage.");
        check(new String[][]{
                        {".", "#"},
                        {".", "s"},
                },
                new String[][]{
                        {".", "#"},
                        {".", "s"},
                }, "Test 2x2. No 'f'.");
        check(new String[][]{
                        {"f", "."},
                        {"#", "."},
                },
                new String[][]{
                        {"f", "."},
                        {"#", "."},
                }, "Test 2x2. No 's'.");
        check(new String[][]{
                        {"f"},
                        {"#"},
                },
                new String[][]{
                        {"f"},
                        {"#"},
                }, "Test 2x2. Not square.");
    }
}
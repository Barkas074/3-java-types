package com.company;

import java.util.*;

public class Main {

    public static String[][] ProcessingData(String[][] field) {
        if (field.length != field[0].length)
            return field;
        Square[][] tempField = new Square[field.length][field.length];
        Square start = null;
        Square end = null;
        for (int i = 0; i < tempField.length; i++) {
            for (int j = 0; j < tempField[i].length; j++) {
                tempField[i][j] = new Square(field[i][j], i, j);
                if (tempField[i][j].IsStartWay()) start = tempField[i][j];
                if (tempField[i][j].IsEndWay()) end = tempField[i][j];
            }
        }
        if (start == null || end == null)
            return field;
        Square[][] newField = FindAWay(tempField, start, end);
        String[][] newFieldString = new String[newField.length][newField.length];
        for (int i = 0; i < newField.length; i++) {
            for (int j = 0; j < newField[i].length; j++) {
                newFieldString[i][j] = newField[i][j].toString();
            }
        }
        return newFieldString;
    }

    enum direction {top, right, down, left}

    private static Square[][] FindAWay(Square[][] field, Square start, Square end) {
        Square current = start;
//        Square[][] newField = field;
        List<Square> visited = new ArrayList<>();
        Stack<Square> stack = new Stack<>();
        visited.add(start);
        stack.push(start);
        while (current != end) {
            short counter = 0;
            for (direction dir : direction.values()) {
                boolean passage = ChoicePath(field, current, visited, stack, dir);
                if (passage) break;
                counter++;
            }
            if (counter == direction.values().length ) {
                visited.add(stack.pop());
            }
            if (stack.isEmpty())
                return field;
            current = stack.peek();
        }
        while (!stack.empty()) {
            Square square = stack.pop();
            if (square.IsPassage()) {
                square.SetProperty("*");
                field[square.GetX()][square.GetY()] = square;
            }
        }
        return field;
    }

    private static boolean ChoicePath(Square[][] field, Square current, List<Square> visited, Stack<Square> stack, direction dir) {
        Stack<Square> tempStack = (Stack<Square>) stack.clone();
        tempStack.pop();
        switch (dir) {
            case top:
                if (current.GetX() - 1 >= 0) {
                    current = field[current.GetX() - 1][current.GetY()];
                    if (!tempStack.isEmpty() && current == tempStack.peek())
                        return false;
                    return IsVisited(current, visited, stack);
                }
                break;
            case right:
                if (current.GetY() + 1 <= field.length - 1) {
                    current = field[current.GetX()][current.GetY() + 1];
                    if (!tempStack.isEmpty() && current == tempStack.peek())
                        return false;
                    return IsVisited(current, visited, stack);
                }

                break;
            case down:
                if (current.GetX() + 1 <= field.length - 1) {
                    current = field[current.GetX() + 1][current.GetY()];
                    if (!tempStack.isEmpty() && current == tempStack.peek())
                        return false;
                    return IsVisited(current, visited, stack);
                }
                break;
            case left:
                if (current.GetY() - 1 >= 0) {
                    current = field[current.GetX()][current.GetY() - 1];
                    if (!tempStack.isEmpty() && current == tempStack.peek())
                        return false;
                    return IsVisited(current, visited, stack);
                }
                break;
        }
        return false;
    }

    private static boolean IsVisited(Square current, List<Square> visited, Stack<Square> stack) {
        if (!visited.contains(current)) {
            if (current.IsWall()) {
                visited.add(current);
            } else {
                stack.push(current);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Введите размер поля: ");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        String[][] field = new String[size][size];
        for (int i = 0; i < field.length; i++) {
            System.out.println("Введите элементы " + (i + 1) + " строки: ");
            char[] charArray = sc.next().toCharArray();
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = String.valueOf(charArray[j]);
            }
        }
        sc.close();
        String[][] theFoundPath = ProcessingData(field);
        for (String[] path : theFoundPath) {
            for (String square : path) {
                System.out.print(square);
            }
            System.out.println();
        }
    }

}
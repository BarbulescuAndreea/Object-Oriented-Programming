package lab13.task2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // TODO: Initialize the calculator
        Calculator calculator = null;

        System.out.println(calculator.add(2d, 3d));
        System.out.println(calculator.divide(9d, 4d));
        System.out.println(calculator.average(List.of(1d, 2d, 3d, 4d)));

        // TODO: Test edge cases that would throw exceptions
    }
}

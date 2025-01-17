import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение (не забудьте пробелы):");

        String input = scanner.nextLine();
        String[] parts = input.split(" ");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Некорректный формат ввода");
        }

        int a, b;
        String num1 = parts[0];
        String operator = parts[1];
        String num2 = parts[2];

        if (num1.matches("\\p{Digit}+") && num2.matches("\\p{Digit}+")) {
            a = Integer.parseInt(num1);
            b = Integer.parseInt(num2);
        } else {
            a = convertToNumber(num1);
            b = convertToNumber(num2);
        }

        if ((a < 1 || a > 10) || (b < 1 || b > 10)) {
            throw new IllegalArgumentException("Числа должны быть от 1 до 10");
        }

        int result;
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("Некорректная арифметическая операция!");
        }

        System.out.println("Результат: " + (parts[0].matches("^[IVXLCDM]+$") && parts[2].matches("^[IVXLCDM]+$") ? convertToRoman(result) : result));
    }

    public static int convertToNumber(String roman) {
        switch (roman.toUpperCase()) {
            case "I": return 1;
            case "II": return 2;
            case "III": return 3;
            case "IV": return 4;
            case "V": return 5;
            case "VI": return 6;
            case "VII": return 7;
            case "VIII": return 8;
            case "IX": return 9;
            case "X": return 10;
            default: throw new IllegalArgumentException("Некорректное значение числа: " + roman);
        }
    }

    public static String convertToRoman(int number) {
        if (number < 1 || number > 100) {
            throw new IllegalArgumentException("Результат должен быть в диапазоне от 1 до 100 включительно!");
        }

        if (number == 100) {
            return "C";
        }

        StringBuilder sb = new StringBuilder();
        int[] decimalValues = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanNumerals = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < decimalValues.length; i++) {
            while (number >= decimalValues[i]) {
                sb.append(romanNumerals[i]);
                number -= decimalValues[i];
            }
        }

        return sb.toString();
    }
}
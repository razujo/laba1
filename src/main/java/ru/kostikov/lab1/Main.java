package ru.kostikov.lab1;

public class Main {

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
    }

    // Задание 1. fizz, buzz, fizzbuzz
    static void task1() {
        System.out.println("===== Задание 1 =====");
        String line = "";
        for (int i = 1; i <= 500; i++) {
            String s;
            if (i % 5 == 0 && i % 7 == 0) {
                s = "fizzbuzz";
            } else if (i % 5 == 0) {
                s = "fizz";
            } else if (i % 7 == 0) {
                s = "buzz";
            } else {
                s = "" + i;
            }
            // дополняем пробелами до 9 символов, чтобы получились ровные столбцы
            while (s.length() < 9) {
                s = s + " ";
            }
            line = line + s;
            // выводим по 10 чисел в строке
            if (i % 10 == 0) {
                System.out.println(line.trim());
                line = "";
            }
        }
        System.out.println();
    }

    // Задание 2. Строка наоборот
    static void task2() {
        System.out.println("===== Задание 2 =====");
        String str = "make install";
        String reversed = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed = reversed + str.charAt(i);
        }
        System.out.println("Исходная строка:   " + str);
        System.out.println("Строка наоборот:   " + reversed);
        System.out.println();
    }

    // Задание 3. Корни квадратного уравнения
    static void task3() {
        System.out.println("===== Задание 3 =====");
        solve(1, -3, 2);
        solve(1, -4, 4);
        solve(1, 2, 5);
        solve(2, 3, -2);
        System.out.println();
    }

    static void solve(double a, double b, double c) {
        System.out.print(equation(a, b, c) + "  ->  ");
        double d = b * b - 4 * a * c;
        if (d > 0) {
            double x1 = (-b - Math.sqrt(d)) / (2 * a);
            double x2 = (-b + Math.sqrt(d)) / (2 * a);
            System.out.println("x1 = " + num(x1) + ", x2 = " + num(x2));
        } else if (d == 0) {
            double x = -b / (2 * a);
            System.out.println("x = " + num(x));
        } else {
            System.out.println("нет вещественных корней");
        }
    }

    // красиво записываем уравнение, например: 2x^2 + 3x - 2 = 0
    static String equation(double a, double b, double c) {
        String s = "";
        if (a != 1) {
            s = s + num(a);
        }
        s = s + "x^2";
        if (b < 0) {
            s = s + " - " + num(-b) + "x";
        } else {
            s = s + " + " + num(b) + "x";
        }
        if (c < 0) {
            s = s + " - " + num(-c);
        } else {
            s = s + " + " + num(c);
        }
        return s + " = 0";
    }

    // число без ".0" на конце, если оно целое (2.0 -> 2)
    static String num(double x) {
        if (x == (int) x) {
            return "" + (int) x;
        }
        return "" + x;
    }

    // Задание 4. Сумма ряда 1 / (n^2 + n - 2), n начинается с 2
    static void task4() {
        System.out.println("===== Задание 4 =====");
        double eps = 0.000001;
        double sum = 0;
        int count = 0;
        int n = 2;
        double term = 1.0 / (n * n + n - 2);
        while (term >= eps) {
            sum = sum + term;
            count++;
            n++;
            term = 1.0 / ((double) n * n + n - 2);
        }
        System.out.println("Точность: члены ряда >= " + eps);
        System.out.println("Сложено членов: " + count);
        System.out.printf("Сумма ряда: %.6f%n", sum);
        // проверка: если разложить дробь, сумма ряда получается ровно 11/18
        System.out.printf("Для сравнения, точное значение 11/18 = %.6f%n", 11.0 / 18);
        System.out.println();
    }

    // Задание 5. Палиндром
    static void task5() {
        System.out.println("===== Задание 5 =====");
        check("шалаш");
        check("А роза упала на лапу Азора");
        check("Was it a car or a cat I saw?");
        check("make install");
    }

    static void check(String s) {
        if (isPalindrome(s)) {
            System.out.println("\"" + s + "\" -> палиндром");
        } else {
            System.out.println("\"" + s + "\" -> не палиндром");
        }
    }

    static boolean isPalindrome(String s) {
        // оставляем только буквы и цифры, всё в маленьком регистре
        String clean = "";
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                clean = clean + Character.toLowerCase(ch);
            }
        }
        // сравниваем первую букву с последней, вторую с предпоследней и т.д.
        for (int i = 0; i < clean.length() / 2; i++) {
            if (clean.charAt(i) != clean.charAt(clean.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}

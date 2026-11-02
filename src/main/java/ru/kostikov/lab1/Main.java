package ru.kostikov.lab1;

/**
 * Лабораторная работа №1. Основы языка Java: циклы, условия, строки.
 */
public class Main {
    public static void main(String[] args) {
        task1FizzBuzz();
        task2Reverse();
        task3Quadratic();
        task4Series();
        task5Palindrome();
    }

    /** Задание 1. Числа от 1 до 500: делится на 5 — fizz, на 7 — buzz, на 5 и 7 — fizzbuzz. */
    static void task1FizzBuzz() {
        System.out.println("===== Задание 1 =====");
        StringBuilder line = new StringBuilder();
        for (int i = 1; i <= 500; i++) {
            String value;
            if (i % 35 == 0) {
                value = "fizzbuzz";
            } else if (i % 5 == 0) {
                value = "fizz";
            } else if (i % 7 == 0) {
                value = "buzz";
            } else {
                value = String.valueOf(i);
            }
            line.append(String.format("%-9s", value));
            // по 10 значений в строке, чтобы вывод был компактным
            if (i % 10 == 0) {
                System.out.println(line.toString().stripTrailing());
                line.setLength(0);
            }
        }
        System.out.println();
    }

    /** Задание 2. Вывод строки наоборот с помощью charAt() и length(). */
    static void task2Reverse() {
        System.out.println("===== Задание 2 =====");
        String str = "make install";
        String reversed = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }
        System.out.println("Исходная строка:   " + str);
        System.out.println("Строка наоборот:   " + reversed);
        System.out.println();
    }

    /** Задание 3. Вещественные корни квадратного уравнения ax^2 + bx + c = 0. */
    static void task3Quadratic() {
        System.out.println("===== Задание 3 =====");
        double[][] examples = {
                {1, -3, 2},     // два корня
                {1, -4, 4},     // один корень
                {1, 2, 5},      // нет вещественных корней
                {2, 3, -2}
        };
        for (double[] e : examples) {
            System.out.printf("%sx^2 %s %sx %s %s = 0  ->  %s%n",
                    e[0] == 1 ? "" : fmt(e[0]), sign(e[1]), fmt(Math.abs(e[1])), sign(e[2]), fmt(Math.abs(e[2])),
                    solveQuadratic(e[0], e[1], e[2]));
        }
        System.out.println();
    }

    static String solveQuadratic(double a, double b, double c) {
        if (a == 0) {
            if (b == 0) {
                return c == 0 ? "x - любое число" : "нет вещественных корней";
            }
            return "x = " + fmt(-c / b) + " (линейное уравнение)";
        }
        double d = b * b - 4 * a * c;
        if (d < 0) {
            return "нет вещественных корней";
        }
        if (d == 0) {
            return "x = " + fmt(-b / (2 * a));
        }
        double sqrtD = Math.sqrt(d);
        return "x1 = " + fmt((-b - sqrtD) / (2 * a)) + ", x2 = " + fmt((-b + sqrtD) / (2 * a));
    }

    /** Задание 4. Сумма ряда 1 / (n^2 + n - 2) от n = 2, пока очередной член не меньше 1e-6. */
    static void task4Series() {
        System.out.println("===== Задание 4 =====");
        double eps = 1e-6;
        double sum = 0;
        int n = 2;
        double term = 1.0 / (n * n + n - 2);
        while (term >= eps) {
            sum += term;
            n++;
            term = 1.0 / ((double) n * n + n - 2);
        }
        System.out.println("Точность: члены ряда >= " + eps);
        System.out.println("Сложено членов: " + (n - 2));
        System.out.printf("Сумма ряда: %.6f%n", sum);
        // Точное значение: 1/(n^2+n-2) = 1/3 * (1/(n-1) - 1/(n+2)), сумма = 11/18
        System.out.printf("Для сравнения, точное значение 11/18 = %.6f%n", 11.0 / 18);
        System.out.println();
    }

    /** Задание 5. Проверка, является ли строка палиндромом (без учёта регистра, пробелов и знаков). */
    static void task5Palindrome() {
        System.out.println("===== Задание 5 =====");
        String[] examples = {"шалаш", "А роза упала на лапу Азора", "Was it a car or a cat I saw?", "make install"};
        for (String s : examples) {
            System.out.printf("\"%s\" -> %s%n", s, isPalindrome(s) ? "палиндром" : "не палиндром");
        }
    }

    static boolean isPalindrome(String s) {
        String clean = s.toLowerCase().replaceAll("[^\\p{L}\\p{N}]", "");
        int left = 0;
        int right = clean.length() - 1;
        while (left < right) {
            if (clean.charAt(left) != clean.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private static String fmt(double v) {
        return v == Math.rint(v) ? String.valueOf((long) v) : String.valueOf(v);
    }

    private static String sign(double v) {
        return v < 0 ? "-" : "+";
    }
}

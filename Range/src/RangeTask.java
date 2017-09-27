import java.util.Scanner;

class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getLength() {
        return (to - from);
    }

    public boolean isInside(double x) {
        return x >= from && x <= to;
    }
}

public class RangeTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите начальное число: ");
        double from = scanner.nextDouble();

        System.out.print("Введите конечное число: ");
        double to = scanner.nextDouble();

        if (to > from) {
            Range range = new Range(from, to);
            System.out.printf("Длина диапазона: %3f%n", range.getLength());

            System.out.print("Введите число: ");

            double x = scanner.nextDouble();
            if (range.isInside(x)) {
                System.out.print("Введенное число находится в диапазоне");
            } else {
                System.out.println("Введенное число находится вне диапазона");
            }
        } else {
            System.out.println("Конечное число меньше начального");
        }
    }
}

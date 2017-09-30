package Range.src.ru.nsu.mmf.g0917.meshcheryakov.rng;

public class RangeTask {
    public static void main(String[] args) {
        double from = 4; //для наглядности переменные объявлены сразу в коде
        double to = 8;

        Range range1 = new Range(from, to);

        from = 3;
        to = 5;

        Range range2 = new Range(from, to);

        Range crossRange = Range.getCrossRange(range1, range2);
        if (crossRange != null) {
            System.out.printf("Пересечение интервалов находится между %.3f и %.3f%n",
                    crossRange.getFrom(), crossRange.getTo());
        } else {
            System.out.println("Интервалы не пересекаются");
        }

        System.out.printf("Размер первого интервала: %.3f%n", range1.getLength());

        double x = 4;
        if (range1.isInside(x)) {
            System.out.println("Введенное число находится в первом интервале");
        } else {
            System.out.println("Введенное число находится вне первого интервала");
        }

        Range[] unionRange = Range.getUnion(range1, range2);
        System.out.println("Объединение двух интервалов:");
        for (int i = 0; i < unionRange.length; i++) {
            if (unionRange[i] == null) {
                break;
            }
            System.out.printf("от %.3f до %.3f%n", unionRange[i].getFrom(), unionRange[i].getTo());
        }

        Range[] differenceRange = Range.getDifference(range1, range2);
        System.out.println("Разность первого интервала от второго:");
        for (int i = 0; i < unionRange.length; i++) {
            if (differenceRange[i] == null) {
                break;
            }
            System.out.printf("от %.3f до %.3f%n", differenceRange[i].getFrom(), differenceRange[i].getTo());
        }
    }
}

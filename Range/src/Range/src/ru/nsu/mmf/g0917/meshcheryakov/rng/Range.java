package Range.src.ru.nsu.mmf.g0917.meshcheryakov.rng;

class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public double getLength() {
        return (to - from);
    }

    public boolean isInside(double x) {
        return x >= from && x <= to;
    }

    public static Range getCrossRange(Range range1, Range range2) {
        double crossFrom;
        double crossTo;
        if (range1.getFrom() > range2.getFrom()) {
            crossFrom = range1.getFrom();
        } else {
            crossFrom = range2.getFrom();
        }

        if (range1.getTo() > range2.getTo()) {
            crossTo = range2.getTo();
        } else {
            crossTo = range1.getTo();
        }

        if (crossFrom < crossTo) {
            Range crossRange = new Range(crossFrom, crossTo);
            return crossRange;
        }
        return null;
    }

    public static Range[] getUnion(Range range1, Range range2) {
        Range[] unionRange = new Range[2];

        if (range2.getFrom() > range1.getTo() || range1.getFrom() > range2.getTo()) {
            unionRange[0] = range1;
            unionRange[1] = range2;
        } else {
            double from = Math.min(range1.getFrom(), range2.getFrom());
            double to = Math.max(range1.getTo(), range2.getTo());

            unionRange[0] = new Range(from, to);
            unionRange[1] = null;
        }
        return unionRange;
    }

    public static Range[] getDifference(Range range1, Range range2) {
        Range[] differenceRange = new Range[2];

        if (range2.getFrom() > range1.getTo() || range1.getFrom() > range2.getTo() ||
                (range1.from > range2.from && range1.to < range2.to)) {  //не пересекаются либо 2й полностью включает 1й
            differenceRange[0] = range1;
            differenceRange[1] = null;
        } else if (range1.getFrom() < range2.getFrom() && range1.getTo() > range2.getTo()) { //1й полностью включает 2й
            differenceRange[0] = new Range(range1.getFrom(), range2.getFrom());
            differenceRange[1] = new Range(range2.getTo(), range1.getTo());
        } else if (range1.from < range2.from) { //2й частично перекрывает 1й справа или слева
            differenceRange[0] = new Range(range1.getFrom(), range2.getFrom());
            differenceRange[1] = null;
        } else {
            differenceRange[0] = new Range(range2.getTo(), range1.getTo());
            differenceRange[1] = null;
        }
        return differenceRange;
    }
}

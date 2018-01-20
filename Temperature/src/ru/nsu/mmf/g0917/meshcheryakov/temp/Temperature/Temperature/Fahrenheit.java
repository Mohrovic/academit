package ru.nsu.mmf.g0917.meshcheryakov.temp.Temperature.Temperature;

public final class Fahrenheit implements Scale {
    private final String NAME = "Фаренгейт";
    private double value;

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void checkVale() {
        final double MIN_F_TEMPERATURE = -459.688;
        final double MAX_F_TEMPERATURE = 1.8E32;

        if (value < MIN_F_TEMPERATURE || value > MAX_F_TEMPERATURE) {
            throw new IllegalArgumentException(
                    "Введенное значение температуры выходит за пределы теоретического");
        }
    }

    @Override
    public double convertToCelsius() {
        final double F_TO_C_DIVISOR = 1.8;
        final int F_TO_C_OFFSET = -32;

        return (value + F_TO_C_OFFSET) / F_TO_C_DIVISOR;
    }

    @Override
    public void convertFromCelsius(double value) {
        final double C_TO_F_MULTIPLIER = 1.8;
        final int C_TO_F_OFFSET = 32;

        this.value = value * C_TO_F_MULTIPLIER + C_TO_F_OFFSET;
    }
}

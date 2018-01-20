package ru.nsu.mmf.g0917.meshcheryakov.temp.Temperature.Temperature;

public final class Celsius implements Scale {
    private final String NAME = "Градусов Цельсия";
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
        final double MIN_C_TEMPERATURE = -273.16;
        final double MAX_C_TEMPERATURE = 1.41679E32;

        if (value < MIN_C_TEMPERATURE || value > MAX_C_TEMPERATURE) {
            throw new IllegalArgumentException(
                    "Введенное значение температуры выходит за пределы теоретического");
        }
    }

    @Override
    public double convertToCelsius() {
        return value;
    }

    @Override
    public void convertFromCelsius(double value) {
        this.value = value;
    }
}

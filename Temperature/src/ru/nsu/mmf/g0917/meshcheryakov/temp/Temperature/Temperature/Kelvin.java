package ru.nsu.mmf.g0917.meshcheryakov.temp.Temperature.Temperature;

public class Kelvin implements Scale {
    private final String NAME = "Кельвин";
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
        final double MIN_C_TEMPERATURE = 0;
        final double MAX_C_TEMPERATURE = 1E32;

        if (value < MIN_C_TEMPERATURE || value > MAX_C_TEMPERATURE) {
            throw new IllegalArgumentException(
                    "Введенное значение температуры выходит за пределы теоретического");
        }
    }

    @Override
    public double convertToCelsius() {
        final double K_TO_C_OFFSET = -273.16;

        return value + K_TO_C_OFFSET;
    }

    @Override
    public void convertFromCelsius(double value) {
        final double C_TO_K_OFFSET = 273.16;

        this.value = value + C_TO_K_OFFSET;
    }
}

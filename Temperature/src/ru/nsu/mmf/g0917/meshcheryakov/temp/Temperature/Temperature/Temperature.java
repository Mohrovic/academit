package ru.nsu.mmf.g0917.meshcheryakov.temp.Temperature.Temperature;

public class Temperature {

    private static void checkDataC(double tCelsius) {
        final double MIN_C_TEMPERATURE = -273.16;
        final double MAX_C_TEMPERATURE = 1.41679E32;

        if (tCelsius < MIN_C_TEMPERATURE || tCelsius > MAX_C_TEMPERATURE) {
            throw new IllegalArgumentException(
                    "Введенное значение температуры выходит за пределы теоретического");
        }
    }

    private static void checkDataF(double tFahrenheit) {
        final double MIN_F_TEMPERATURE = -459.688;
        final double MAX_F_TEMPERATURE = 1.8E32;

        if (tFahrenheit < MIN_F_TEMPERATURE || tFahrenheit > MAX_F_TEMPERATURE) {
            throw new IllegalArgumentException(
                    "Введенное значение температуры выходит за пределы теоретического");
        }
    }

    private static void checkDataK(double tKelvin) {
        final double MIN_C_TEMPERATURE = 0;
        final double MAX_C_TEMPERATURE = 1E32;

        if (tKelvin < MIN_C_TEMPERATURE || tKelvin > MAX_C_TEMPERATURE) {
            throw new IllegalArgumentException(
                    "Введенное значение температуры выходит за пределы теоретического");
        }
    }

    public static double convertCelsiusToFahrenheit(double tCelsius) {
        checkDataC(tCelsius);

        final double C_TO_F_MULTIPLIER = 1.8;
        final int C_TO_F_OFFSET = 32;

        return tCelsius * C_TO_F_MULTIPLIER + C_TO_F_OFFSET;
    }

    public static double convertFahrenheitToCelsius(double tFahrenheit) {
        checkDataF(tFahrenheit);

        final double F_TO_C_DIVISOR = 1.8;
        final int F_TO_C_OFFSET = -32;

        return (tFahrenheit + F_TO_C_OFFSET) / F_TO_C_DIVISOR;
    }

    public static double convertCelsiusToKelvin(double tCelsius) {
        checkDataC(tCelsius);

        final double C_TO_K_OFFSET = 273.16;

        return tCelsius + C_TO_K_OFFSET;
    }

    public static double convertKelvinToCelsius(double tKelvin) {
        checkDataK(tKelvin);

        final double K_TO_C_OFFSET = -273.16;

        return tKelvin + K_TO_C_OFFSET;
    }

    public static double convertFahrenheitToKelvin(double tFahrenheit) {
        checkDataF(tFahrenheit);

        final double F_TO_C_DIVISOR = 1.8;
        final int F_TO_C_OFFSET = -32;
        final double C_TO_K_OFFSET = 273.16;

        return (tFahrenheit + F_TO_C_OFFSET) / F_TO_C_DIVISOR + C_TO_K_OFFSET;
    }

    public static double convertKelvinToFahrenheit(double tKelvin) {
        checkDataK(tKelvin);

        final double C_TO_F_MULTIPLIER = 1.8;
        final int C_TO_F_OFFSET = 32;
        final double K_TO_C_OFFSET = -273.16;

        return (tKelvin + K_TO_C_OFFSET) * C_TO_F_MULTIPLIER + C_TO_F_OFFSET;
    }
}

package ru.nsu.mmf.g0917.meshcheryakov.temp.Temperature.Temperature;

public interface Scale {


    String getName();

    void setValue(double value);

    double getValue();

    void checkVale();

    double convertToCelsius();

    void convertFromCelsius(double value);
}

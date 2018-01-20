package ru.nsu.mmf.g0917.meshcheryakov.temp.Temperature.Interface;

import ru.nsu.mmf.g0917.meshcheryakov.temp.Temperature.Temperature.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class GUI extends JFrame {

    public GUI() {
        super("Конвертор температур");
        setSize(360, 100);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        String pattern = "##0.000";
        NumberFormat format = new DecimalFormat(pattern, otherSymbols);
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(6);

        JFormattedTextField fromField = new JFormattedTextField(format);
        fromField.setColumns(6);
        JTextField toField = new JTextField(6);
        JButton convertButton = new JButton("→");

        JComboBox<String> fromComboBox = new JComboBox<>();
        JComboBox<String> toComboBox = new JComboBox<>();

        ArrayList<Scale> scalesList = new ArrayList<>();

        scalesList.add(new Celsius());
        scalesList.add(new Fahrenheit());
        scalesList.add(new Kelvin());

        Scale[] scales = new Scale[scalesList.size()];
        scales = scalesList.toArray(scales);
        for (Scale scale : scales) {
            fromComboBox.addItem(scale.getName());
            toComboBox.addItem(scale.getName());
        }

        GridBagLayout gridBag = new GridBagLayout();
        JPanel pane = new JPanel(gridBag);
        GridBagConstraints constraints = new GridBagConstraints();
        pane.setLayout(gridBag);
        add(pane);

        constraints.weightx = 0.5;

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        pane.add(fromField, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 1;
        constraints.gridy = 0;
        pane.add(convertButton, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 2;
        constraints.gridy = 0;
        pane.add(toField, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 1;
        pane.add(fromComboBox, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 2;
        constraints.gridy = 1;
        pane.add(toComboBox, constraints);
        toField.setEditable(false);

        Scale[] finalScales = scales;

        ActionListener actionListener = e -> {
            int fromComboBoxIndex = fromComboBox.getSelectedIndex();
            int toComboBoxIndex = toComboBox.getSelectedIndex();

            finalScales[fromComboBoxIndex].setValue(Double.parseDouble(fromField.getText()));

            try {
                finalScales[fromComboBoxIndex].checkVale();

                final double universalValue = finalScales[fromComboBoxIndex].convertToCelsius();

                finalScales[toComboBoxIndex].convertFromCelsius(universalValue);

                toField.setText(String.valueOf(finalScales[toComboBoxIndex].getValue()));
            } catch (IllegalArgumentException iae) {
                toField.setText("Недопустимое значение");
            }
        };

        fromComboBox.addActionListener(actionListener);
        toComboBox.addActionListener(actionListener);
        convertButton.addActionListener(actionListener);
    }
}
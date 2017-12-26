package ru.nsu.mmf.g0917.meshcheryakov.temp.Temperature.Demo;

import ru.nsu.mmf.g0917.meshcheryakov.temp.Temperature.Temperature.Temperature;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;


public class TemperatureDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ковертор температур");
            frame.setSize(350, 100);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);

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
            fromComboBox.addItem("градусов Цельсия");
            fromComboBox.addItem("Фаренгейт");
            fromComboBox.addItem("Кельвин");

            JComboBox<String> toComboBox = new JComboBox<>();
            toComboBox.addItem("градусов Цельсия");
            toComboBox.addItem("Фаренгейт");
            toComboBox.addItem("Кельвин");

            GridBagLayout gridBag = new GridBagLayout();
            JPanel pane = new JPanel(gridBag);
            GridBagConstraints constraints = new GridBagConstraints();
            pane.setLayout(gridBag);
            frame.add(pane);

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

            ActionListener actionListener = e -> {
                int fromComboBoxIndex = fromComboBox.getSelectedIndex();
                int toComboBoxIndex = toComboBox.getSelectedIndex();

                if (fromComboBoxIndex == toComboBoxIndex) {
                    toField.setText(fromField.getText());
                } else {

                    int mode = fromComboBoxIndex * 10 + toComboBoxIndex;

                    switch (mode) {
                        case 1:
                            toField.setText(String.valueOf(
                                    Temperature.convertCelsiusToFahrenheit
                                            (Double.parseDouble(fromField.getText()))));
                            break;

                        case 2:
                            toField.setText(String.valueOf(
                                    Temperature.convertCelsiusToKelvin
                                            (Double.parseDouble(fromField.getText()))));
                            break;

                        case 10:
                            toField.setText(String.valueOf(
                                    Temperature.convertFahrenheitToCelsius
                                            (Double.parseDouble(fromField.getText()))));
                            break;

                        case 12:
                            toField.setText(String.valueOf(
                                    Temperature.convertFahrenheitToKelvin
                                            (Double.parseDouble(fromField.getText()))));
                            break;

                        case 20:
                            toField.setText(String.valueOf(
                                    Temperature.convertKelvinToCelsius
                                            (Double.parseDouble(fromField.getText()))));
                            break;

                        case 21:
                            toField.setText(String.valueOf(
                                    Temperature.convertKelvinToFahrenheit
                                            (Double.parseDouble(fromField.getText()))));
                            break;
                    }
                }
            };

            fromComboBox.addActionListener(actionListener);
            toComboBox.addActionListener(actionListener);
            convertButton.addActionListener(actionListener);
        });
    }
}

package GUI;

import minesweeper.Model;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {

    Model model;
    View view;

    public void addModel(Model model) {
        this.model = model;
    }

    public void addView(View view) {
        this.view = view;
    }

    public void initModel(int dimension, int minesTotal) {
        model.newGame(dimension, minesTotal);
    }

    public void waitCommand() throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        int command;

        try {
            command = scanner.nextInt();
        } catch (InputMismatchException e) {
            command = -1;
        }

        switch (command) {
            case 1: {
                model.showMessage("Введите координату по горизонтали:");
                int userYCoordinate;
                try {
                    userYCoordinate = scanner.nextInt();
                } catch (InputMismatchException e) {
                    userYCoordinate = -1;
                }
                userYCoordinate--;

                model.showMessage("Введите координату по вертикали:");
                int userXCoordinate;
                try {
                    userXCoordinate = scanner.nextInt();
                } catch (InputMismatchException e) {
                    userXCoordinate = -1;
                }
                userXCoordinate--;

                model.openCell(userXCoordinate, userYCoordinate);
            }
            break;

            case 2: {
                model.showMessage("Введите координату по горизонтали:");
                int userYCoordinate;
                try {
                    userYCoordinate = scanner.nextInt();
                } catch (InputMismatchException e) {
                    userYCoordinate = -1;
                }
                userYCoordinate--;

                model.showMessage("Введите координату по вертикали:");
                int userXCoordinate;
                try {
                    userXCoordinate = scanner.nextInt();
                } catch (InputMismatchException e) {
                    userXCoordinate = -1;
                }
                userXCoordinate--;

                model.setFlag(userXCoordinate, userYCoordinate);
            }

            break;

            case 3: {
                model.showMessage("Выберите уровень (1-новичок, 2-любитель, 3-профессионал, 4-особый):");
                newFieldSelect();
            }
            break;

            case 4: {
                model.exitGame();
            }
            break;

            default: {
                model.showMessage("Неизвестная команда. Введите команду:");
            }
        }
    }

    public void newFieldSelect() {
        Scanner scanner = new Scanner(System.in);
        int command;
        try {
            command = scanner.nextInt();
        } catch (InputMismatchException e) {
            command = -1;
        }

        int dimension = -1;
        int minesTotal = -1;

        switch (command) {
            case 1: {
                dimension = 9;
                minesTotal = 10;
            }
            break;
            case 2: {
                dimension = 16;
                minesTotal = 40;
            }
            break;
            case 3: {
                dimension = 22;
                minesTotal = 99;
            }
            break;
            case 4: {
                while (dimension < 9 || dimension > 30) {
                    model.showMessage("Введите размеры поля (от 9 до 30):");
                    try {
                        dimension = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        scanner.next();
                    }
                }

                int minesMax = (dimension - 1) * (dimension - 1);
                while (minesTotal < 10 || minesTotal > minesMax) {
                    String message = "Введите количество мин (от 10 до " + minesMax + "):";
                    model.showMessage(message);
                    try {
                        minesTotal = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        scanner.next();
                    }
                }

            }
            break;
            default: {
                dimension = 9;
                minesTotal = 10;
            }
        }
        model.newGame(dimension, minesTotal);
    }
}

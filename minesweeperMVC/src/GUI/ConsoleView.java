package GUI;

import minesweeper.Records;
import minesweeper.ViewListener;
import minesweeper.field.Field;

import java.text.SimpleDateFormat;
import java.util.*;

public class ConsoleView implements minesweeper.View {
    private final ArrayList<ViewListener> listeners = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    private final String MAIN_MENU;
    private int dimension;

    public ConsoleView() {
        MAIN_MENU = "Введите команду (1-открыть, 2-поставить флажок/вопрос, 3-открыть смежные, 4-сменить уровень, 5-выйти):";
    }

    @Override
    public void updateField(Field field) {
        dimension = field.getDimension();

        String notification = null;

        System.out.print("   |");
        for (int i = 1; i <= dimension; i++) {
            System.out.printf("%3d", i);
        }
        System.out.print("|");

        System.out.print(System.lineSeparator() + " --+");
        for (int i = 1; i <= dimension; i++) {
            System.out.print("---");
        }
        System.out.printf("|      | флажков осталось: %d |%n", field.getFlagsLeft());

        for (int x = 0; x < dimension; x++) {
            System.out.printf("%3d|", x + 1);
            for (int y = 0; y < dimension; y++) {
                switch (field.getMask(x, y)) {
                    case CLOSED: {
                        System.out.printf("   ");
                    }
                    break;

                    case OPENED: {
                        if (field.getCellObject(x, y) == Field.cellObject.EMPTY) {
                            System.out.print("  ·");
                        } else {
                            switch (field.getCellObject(x, y)) {
                                case C1:
                                    System.out.print("  1");
                                    break;
                                case C2:
                                    System.out.print("  2");
                                    break;
                                case C3:
                                    System.out.print("  3");
                                    break;
                                case C4:
                                    System.out.print("  4");
                                    break;
                                case C5:
                                    System.out.print("  5");
                                    break;
                                case C6:
                                    System.out.print("  6");
                                    break;
                                case C7:
                                    System.out.print("  7");
                                    break;
                                case C8:
                                    System.out.print("  8");
                                    break;
                                case MINE:
                                    System.out.print("  *");
                                    notification = "Game Over!";
                                    break;
                            }
                        }
                    }
                    break;

                    case FLAG: {
                        System.out.print("  P");
                    }
                    break;

                    case QUESTION_MARK: {
                        System.out.print("  ?");
                    }
                    break;
                }
            }
            System.out.print("|" + System.lineSeparator());

        }
        System.out.print(" ---");
        for (int i = 1; i <= dimension; i++) {
            System.out.print("---");
        }
        System.out.print("-" + System.lineSeparator());

        if (notification != null) {
            System.out.println(notification);
        }

        System.out.print(MAIN_MENU);
    }

    @Override
    public void startApplication() {
        boolean isRunning = true;

        while (isRunning) {
            int command;

            try {
                command = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next();
                command = -1;
            }

            final int MIN_COORDINATE = 1;
            switch (command) {
                case 1:
                    int userYCoordinate = getAndCheckInput("Введите координату по горизонтали",
                            MIN_COORDINATE, dimension);
                    userYCoordinate--;

                    int userXCoordinate = getAndCheckInput("Введите координату по вертикали",
                            MIN_COORDINATE, dimension);
                    userXCoordinate--;

                    for (ViewListener listener : listeners) {
                        listener.needOpenCell(userXCoordinate, userYCoordinate);
                    }
                    break;

                case 2:
                    userYCoordinate = getAndCheckInput("Введите координату по горизонтали",
                            MIN_COORDINATE, dimension);
                    userYCoordinate--;

                    userXCoordinate = getAndCheckInput("Введите координату по вертикали",
                            MIN_COORDINATE, dimension);
                    userXCoordinate--;

                    for (ViewListener listener : listeners) {
                        listener.needSetFlag(userXCoordinate, userYCoordinate);
                    }
                    break;

                case 3:
                    userYCoordinate = getAndCheckInput("Введите координату по горизонтали",
                            MIN_COORDINATE, dimension);
                    userYCoordinate--;

                    userXCoordinate = getAndCheckInput("Введите координату по вертикали",
                            MIN_COORDINATE, dimension);
                    userXCoordinate--;

                    for (ViewListener listener : listeners) {
                        listener.needOpenAdjacent(userXCoordinate, userYCoordinate);
                    }
                    break;

                case 4:
                    System.out.print("Выберите уровень (1-новичок, 2-любитель, 3-профессионал, 4-особый):");

                    try {
                        command = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        scanner.next();
                        command = -1;
                    }

                    final int MIN_MENU_ITEM = 1;
                    final int MAX_MENU_ITEM = 4;

                    if (command >= MIN_MENU_ITEM && command <= MAX_MENU_ITEM) {
                        if (command != 4) {
                            for (ViewListener listener : listeners) {
                                listener.needStartNewLevel(command);
                            }
                        } else {
                            final int MIN_DIMENSION = 9;
                            final int MAX_DIMENSION = 30;
                            int userDimension = getAndCheckInput("Введите размеры поля",
                                    MIN_DIMENSION, MAX_DIMENSION);

                            final int MIN_MINES = 10;
                            int maxMines = (userDimension - 1) * (userDimension - 1);
                            int userMinesTotal = getAndCheckInput("Введите количество мин",
                                    MIN_MINES, maxMines);

                            for (ViewListener listener : listeners) {
                                listener.needStartNewLevel(userDimension, userMinesTotal);
                            }
                        }
                    } else {
                        System.out.print("Такого уровня не существует. Введите число от 1 до 4:");
                    }
                    break;

                case 5:
                    isRunning = false;
                    break;

                default: {
                    System.out.printf("Неизвестная команда.%n%s", MAIN_MENU);
                }
            }
        }

    }

    @Override
    public void addViewListener(ViewListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeViewListener(ViewListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void close() throws Exception {
        listeners.clear();
        scanner.close();
    }

    void showRecords(Records record) {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        long timeConsumedMillis = record.getCurrentRecord();
        long previousRecordTimeMills = record.getPreviousRecord();
        Date previousRecordDate = record.getPreviousRecordDate();

        if (record.isNewRecord()) {
            System.out.printf("Новый рекорд: %s!%n", timeFormat.format(timeConsumedMillis));
            if (previousRecordDate != null) {
                System.out.printf("Прежний рекорд: %s (%s)%n%n", timeFormat.format(previousRecordTimeMills),
                        dateTimeFormat.format(previousRecordDate));
            }
        } else {
            System.out.printf("Победа! Все мины найдены за %s%n%n", timeFormat.format(timeConsumedMillis));
        }
    }

    private int getAndCheckInput(String message, int fromInt, int toInt) {
        int userInt;
        do {
            System.out.printf("%s от %d до %d:", message, fromInt, toInt);

            while (!scanner.hasNextInt()) {
                System.out.println("Это должно быть целое число");
                scanner.next();
            }

            userInt = scanner.nextInt();
        }
        while (userInt < fromInt || userInt > toInt);

        return userInt;
    }
}
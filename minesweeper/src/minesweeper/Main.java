package minesweeper;

import text.View;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int dimension = 9;
        int minesTotal = 10;
        int[] mines = new int[minesTotal];
        char[][] gameField = new char[dimension][dimension];

        int area = dimension * dimension;
        Random random = new Random();
        for (int i = 0; i < minesTotal; i++) {
            mines[i] = random.nextInt(area);
            int x = mines[i] / dimension;
            int y = mines[i] % dimension;

            if (gameField[x][y] == '*') {
                i--;
            }

            gameField[x][y] = '*';
        }

        //заполнение игрового поля
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                if (gameField[x][y] == '*') {
                    continue;
                }
                byte adjacentMines = 0;
                for (int x1 = x - 1; x1 <= x + 1; x1++) {
                    for (int y1 = y - 1; y1 <= y + 1; y1++) {
                        if ((x1 < 0 || x1 >= dimension) || (y1 < 0 || y1 >= dimension)) {
                            continue;
                        }
                        if (gameField[x1][y1] == '*') {
                            adjacentMines++;
                        }
                    }
                }
                gameField[x][y] = (char) (adjacentMines + 48);//Character.forDigit(adjacentMines, 10);
            }
        }

        //вывод в консоль временно
        /*System.out.print("   |");
        for (int i = 1; i <= dimension; i++) {
            System.out.printf("%3d", i);
        }
        System.out.print("\n --+");
        for (int i = 1; i <= dimension; i++) {
            System.out.print("---");
        }
        System.out.println();

        for (int x = 0; x < dimension; x++) {
            System.out.printf("%3d|", x + 1);
            for (int y = 0; y < dimension; y++) {
                System.out.printf("%3c", gameField[x][y]);
            }
            System.out.println();
        }*/

        //начало игры
        int flags = minesTotal;
        byte[][] mask = new byte[dimension][dimension]; //0-закрыто, 1-открыто, 2-флаг, 3-вопрос
        boolean isPlaying = true;

        View.printField(gameField, mask);

        long startTime = System.currentTimeMillis();

        while (isPlaying) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите команду (1-открыть, 2-поставить флажок/вопрос, 4-выйти):");
            int command = scanner.nextInt();

            switch (command) {
                case 1: {

                    System.out.print("Введите координату по горизонтали:");
                    int userYCoordinate = scanner.nextInt();
                    while (userYCoordinate < 1 || userYCoordinate > dimension) {
                        System.out.printf("Диапазон может быть от 1 до %d%nВведите координату по горизонтали:",
                                dimension);
                        userYCoordinate = scanner.nextInt();
                    }
                    userYCoordinate--;

                    System.out.print("Введите координату по вертикали:");
                    int userXCoordinate = scanner.nextInt();
                    while (userXCoordinate < 1 || userXCoordinate > dimension) {
                        System.out.printf("Диапазон может быть от 1 до %d%nВведите координату по горизонтали:",
                                dimension);
                        userXCoordinate = scanner.nextInt();
                    }
                    userXCoordinate--;

                    if (gameField[userXCoordinate][userYCoordinate] == '*') {
                        isPlaying = false;

                        for (int x = 0; x < dimension; x++) {
                            for (int y = 0; y < dimension; y++) {
                                if (gameField[x][y] == '*') {
                                    mask[x][y] = 1;
                                }
                            }
                        }
                        View.printField(gameField, mask);

                        System.out.println("game over!"); //TODO правильное открытие, если флажки стоят
                        break;
                    }

                    //открытие поля
                    mask[userXCoordinate][userYCoordinate] = 1;

                    if (gameField[userXCoordinate][userYCoordinate] == '0') {

                        ArrayList<Integer[]> emptyCellsStack = new ArrayList<>();
                        emptyCellsStack.add(new Integer[]{userXCoordinate, userYCoordinate});

                        while (!emptyCellsStack.isEmpty()) {
                            int emptyCellsStackPosition = emptyCellsStack.size() - 1;
                            int positionX = emptyCellsStack.get(emptyCellsStackPosition)[0];
                            int positionY = emptyCellsStack.get(emptyCellsStackPosition)[1];

                            for (int dx = -1; dx <= 1; dx++) {
                                for (int dy = -1; dy <= 1; dy++) {
                                    int currentX = positionX + dx;
                                    int currentY = positionY + dy;

                                    if ((currentX < 0 || currentX >= dimension) ||
                                            (currentY < 0 || currentY >= dimension)) {
                                        continue;
                                    }

                                    //if (dx == dy) { //todo если соседи-цифры, то открыть в углу
                                    //     continue;
                                    // }

                                    if (gameField[currentX][currentY] != '*') {
                                        if (gameField[currentX][currentY] == '0' && mask[currentX][currentY] == 0) {
                                            emptyCellsStack.add(new Integer[]{currentX, currentY});
                                        }
                                        mask[currentX][currentY] = 1;
                                    }
                                }
                            }
                            emptyCellsStack.remove(emptyCellsStackPosition);
                        }
                    }

                    //печатаем по маске
                    View.printField(gameField, mask);
                }
                break;

                case 2: {
                    System.out.print("введите координату x:");
                    int userYCoordinate = scanner.nextInt() - 1;

                    System.out.print("введите координату y:");
                    int userXCoordinate = scanner.nextInt() - 1;

                    switch (mask[userXCoordinate][userYCoordinate]) {
                        case 0: {
                            if (flags > 0) {
                                mask[userXCoordinate][userYCoordinate] = 2;

                                flags--;

                                View.printField(gameField, mask);

                                System.out.printf("Флажков осталось: %d%n", flags);
                            }

                            if (flags == 0) {
                                boolean overlap = false;
                                for (int x = 0; x < dimension; x++) {
                                    for (int y = 0; y < dimension; y++) {
                                        if (mask[x][y] == 2) {
                                            overlap = (gameField[x][y] == '*');
                                        }
                                    }
                                }

                                if (overlap) {
                                    long finishTime = System.currentTimeMillis();
                                    long timeConsumedMillis = finishTime - startTime;

                                    System.out.println("Победа!");

                                    isPlaying = false;

                                    Records.printAndSaveRecords(timeConsumedMillis);
                                } else {
                                    System.out.println("Флажки закончились");
                                }
                            }
                        }
                        break;

                        case 1: {
                            System.out.println("Эта ячейка уже открыта");
                        }
                        break;

                        case 2: {
                            mask[userXCoordinate][userYCoordinate] = 3;

                            flags++;

                            View.printField(gameField, mask);
                        }
                        break;

                        case 3: {
                            mask[userXCoordinate][userYCoordinate] = 0;
                        }
                        break;
                    }
                }
                break;
                case 3: {
                    long finishTime = System.currentTimeMillis();
                    long timeConsumedMillis = finishTime - startTime;

                    Records.printAndSaveRecords(timeConsumedMillis);
                }
                break;
                case 4: {
                    isPlaying = false;
                }
                break;
                default: {
                    System.out.println("Неизвестная команда");
                }
                break;
            }
        }
    }
}


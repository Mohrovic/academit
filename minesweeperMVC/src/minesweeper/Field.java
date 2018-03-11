package minesweeper;

import java.util.ArrayList;
import java.util.Random;

public class Field {
    private char[][] gameField;
    private byte[][] mask;

    private int dimension;
    private int flagsLeft;

    private int displayMode;
    private String notification;

    public Field(int dimension, int minesTotal) {
        this.dimension = dimension;
        gameField = new char[dimension][dimension];
        mask = new byte[dimension][dimension]; //0-закрыто, 1-открыто, 2-флаг, 3-вопрос

        flagsLeft = minesTotal;

        displayMode = 1; //1-показывать поле, 2 - показать сообщение, 3- показать меню с рекордами

        int area = dimension * dimension;
        Random random = new Random();

        // расстановка мин
        int[] mines = new int[minesTotal];
        for (int i = 0; i < minesTotal; i++) {
            mines[i] = random.nextInt(area);
            int x = mines[i] / dimension;
            int y = mines[i] % dimension;

            if (gameField[x][y] == '*') {
                i--;
            }

            gameField[x][y] = '*';
        }

        //заполнение цифрами игрового поля
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
                gameField[x][y] = (char) (adjacentMines + 48);
            }
        }
    }

    public boolean openCell(int userXCoordinate, int userYCoordinate) throws IllegalArgumentException {
        checkCoordinates(userXCoordinate, userYCoordinate);

        if (mask[userXCoordinate][userYCoordinate] != 0) { //открываем, только если ячейка закрыта
            return true;
        }

        if (gameField[userXCoordinate][userYCoordinate] == '*') {

            for (int x = 0; x < dimension; x++) {
                for (int y = 0; y < dimension; y++) {
                    if (gameField[x][y] == '*') {
                        mask[x][y] = 1;
                    }
                }
            }
            return false;
        } else {
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
        }
        return true;
    }

    private void checkCoordinates(int x, int y) throws IllegalArgumentException{
        if (x < 0 || x > dimension) {
            throw new IllegalArgumentException("Значение вертикальной координаты (x) выходит за пределы поля.");
        }

        if (y < 0 || y > dimension) {
            throw new IllegalArgumentException("Значение горизонтальной координаты (y) выходит за пределы поля.");
        }
    }

    public int getDimension() {
        return dimension;
    }

    public byte[][] getMask() {
        return mask;
    }

    public char[][] getGameField() {
        return gameField;
    }

    public void setNotification(String text) {
        notification = text;
    }

    public String getNotification() {
        return notification;
    }

    public void setDisplayMode(int mode) {
        displayMode = mode;
    }

    public int getDisplayMode() {
        return displayMode;
    }

    public void setFlagsLeft(int flags) {
        flagsLeft = flags;
    }

    public int getFlagsLeft() {
        return flagsLeft;
    }

}

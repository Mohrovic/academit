package minesweeper.field;

import java.util.ArrayList;
import java.util.Random;


public class Field {
    private Cell[][] cells;

    private int dimension;
    private int flagsLeft;

    public enum mask {CLOSED, OPENED, FLAG, QUESTION_MARK}

    public enum cellObject {EMPTY, MINE, C1, C2, C3, C4, C5, C6, C7, C8}

    public Field(int dimension, int minesTotal) {
        this.dimension = dimension;

        cells = new Cell[dimension][dimension];
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                cells[x][y] = new Cell();
            }
        }

        flagsLeft = minesTotal;

        int area = dimension * dimension;
        Random random = new Random();

        // расстановка мин
        int[] mines = new int[minesTotal];
        for (int i = 0; i < minesTotal; i++) {
            mines[i] = random.nextInt(area);
            int x = mines[i] / dimension;
            int y = mines[i] % dimension;

            if (cells[x][y].getCellObject() == cellObject.MINE) {
                i--;
            }

            cells[x][y].setCellObject(cellObject.MINE);
        }

        //заполнение цифрами игрового поля
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                if (cells[x][y].getCellObject() == cellObject.MINE) {
                    continue;
                }
                byte adjacentMines = 0;
                for (int x1 = x - 1; x1 <= x + 1; x1++) {
                    for (int y1 = y - 1; y1 <= y + 1; y1++) {
                        if ((x1 < 0 || x1 >= dimension) || (y1 < 0 || y1 >= dimension)) {
                            continue;
                        }

                        if (cells[x1][y1].getCellObject() == cellObject.MINE) {
                            adjacentMines++;
                        }
                    }
                }

                switch (adjacentMines) {
                    case 1:
                        cells[x][y].setCellObject(cellObject.C1);
                        break;
                    case 2:
                        cells[x][y].setCellObject(cellObject.C2);
                        break;
                    case 3:
                        cells[x][y].setCellObject(cellObject.C3);
                        break;
                    case 4:
                        cells[x][y].setCellObject(cellObject.C4);
                        break;
                    case 5:
                        cells[x][y].setCellObject(cellObject.C5);
                        break;
                    case 6:
                        cells[x][y].setCellObject(cellObject.C6);
                        break;
                    case 7:
                        cells[x][y].setCellObject(cellObject.C7);
                        break;
                    case 8:
                        cells[x][y].setCellObject(cellObject.C8);
                        break;
                }
            }
        }
    }

    public boolean openCell(int userXCoordinate, int userYCoordinate) throws IllegalArgumentException {
        checkCoordinates(userXCoordinate, userYCoordinate);

        if (cells[userXCoordinate][userYCoordinate].getMask() != mask.CLOSED) { //открываем, только если ячейка закрыта
            return true;
        }

        if (cells[userXCoordinate][userYCoordinate].getCellObject() == cellObject.MINE) {
            for (int x = 0; x < dimension; x++) {
                for (int y = 0; y < dimension; y++) {
                    if (cells[x][y].getCellObject() == cellObject.MINE) {
                        cells[x][y].setMask(mask.OPENED); //открываем все мины
                    }
                }
            }
            return false;
        } else {

            //открытие поля
            cells[userXCoordinate][userYCoordinate].setMask(mask.OPENED);

            if (cells[userXCoordinate][userYCoordinate].getCellObject() == cellObject.EMPTY) {
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

                            if (cells[currentX][currentY].getCellObject() != cellObject.MINE) {
                                if (cells[currentX][currentY].getCellObject() == cellObject.EMPTY
                                        && cells[currentX][currentY].getMask() == mask.CLOSED) {
                                    emptyCellsStack.add(new Integer[]{currentX, currentY});
                                }
                                cells[currentX][currentY].setMask(mask.OPENED);
                            }
                        }
                    }
                    emptyCellsStack.remove(emptyCellsStackPosition);
                }
            }
        }
        return true;
    }

    public boolean openAdjacent(int userXCoordinate, int userYCoordinate) {
        checkCoordinates(userXCoordinate, userYCoordinate);

        byte adjacentFlags = 0;

        //подсчет смежных флажков
        if (cells[userXCoordinate][userYCoordinate].getMask() == mask.OPENED) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    int currentX = userXCoordinate + dx;
                    int currentY = userYCoordinate + dy;

                    if ((currentX < 0 || currentX >= dimension) ||
                            (currentY < 0 || currentY >= dimension)) {
                        continue;
                    }

                    if (cells[currentX][currentY].getMask() == mask.FLAG) {
                        adjacentFlags++;
                    }
                }
            }
        } else {
            return true;
        }

        byte adjacentMines = 0;
        switch (cells[userXCoordinate][userYCoordinate].getCellObject()) {
            case C1:
                adjacentMines = 1;
                break;
            case C2:
                adjacentMines = 2;
                break;
            case C3:
                adjacentMines = 3;
                break;
            case C4:
                adjacentMines = 4;
                break;
            case C5:
                adjacentMines = 5;
                break;
            case C6:
                adjacentMines = 6;
                break;
            case C7:
                adjacentMines = 7;
                break;
            case C8:
                adjacentMines = 8;
                break;
        }

        //открытие смежных ячеек
        if (adjacentFlags == adjacentMines) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    int currentX = userXCoordinate + dx;
                    int currentY = userYCoordinate + dy;

                    if ((currentX < 0 || currentX >= dimension) ||
                            (currentY < 0 || currentY >= dimension)) {
                        continue;
                    }

                    if (cells[currentX][currentY].getCellObject() == cellObject.MINE
                            && cells[currentX][currentY].getMask() == mask.CLOSED) {
                        System.out.println("сработало");
                        //открываем все мины
                        for (int x = 0; x < dimension; x++) {
                            for (int y = 0; y < dimension; y++) {
                                if (cells[x][y].getCellObject() == cellObject.MINE) {
                                    cells[x][y].setMask(mask.OPENED);
                                }
                            }
                        }
                        return false;
                    } else {
                       if (cells[currentX][currentY].getMask() == mask.CLOSED) {
                           cells[currentX][currentY].setMask(mask.OPENED);
                       }
                    }
                }
            }
        }
        return true;
    }

    private void checkCoordinates(int x, int y) throws IllegalArgumentException {
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

    public void setFlagsLeft(int flags) {
        flagsLeft = flags;
    }

    public int getFlagsLeft() {
        return flagsLeft;
    }

    public cellObject getCellObject(int x, int y) {
        return cells[x][y].getCellObject();
    }

    public mask getMask(int x, int y) {
        return cells[x][y].getMask();
    }

    public void setMask(int x, int y, mask mask) {
        cells[x][y].setMask(mask);
    }
}
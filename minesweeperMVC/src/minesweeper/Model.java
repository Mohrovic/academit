package minesweeper;

import minesweeper.field.Field;

public class Model extends java.util.Observable {

    private Field field;
    private int flags;

    private long startTime;
    private int level;
    private int dimension;
    private int minesTotal;

    public Model() {
        level = 1;
        dimension = 9;
        minesTotal = 10;
    }

    public boolean openCell(int x, int y) throws IllegalArgumentException {
        return field.openCell(x, y);
    }

    public boolean setFlag(int userXCoordinate, int userYCoordinate) throws IllegalArgumentException {
        boolean isFlagsOverlapsMines = false;

        switch (field.getMask(userXCoordinate, userYCoordinate)) {
            case CLOSED:
                if (flags > 0) {
                    field.setMask(userXCoordinate, userYCoordinate, Field.mask.FLAG);

                    flags--;

                    field.setFlagsLeft(flags);

                    setChanged();
                    notifyObservers(field);
                }

                //проверяяем, не совпали ли все флажки с минами
                int dimension = field.getDimension();

                if (flags == 0) {
                    for (int x = 0; x < dimension; x++) {
                        for (int y = 0; y < dimension; y++) {
                            if (field.getMask(x, y) == Field.mask.FLAG) {
                                isFlagsOverlapsMines = (field.getCellObject(x, y) == Field.cellObject.MINE);
                            }
                        }
                    }
                }
                break;

            case OPENED:
                setChanged();
                notifyObservers(field);
                break;

            case FLAG:
                field.setMask(userXCoordinate, userYCoordinate, Field.mask.QUESTION_MARK);

                flags++;

                setChanged();
                notifyObservers(field);
                break;

            case QUESTION_MARK:
                field.setMask(userXCoordinate, userYCoordinate, Field.mask.CLOSED);

                setChanged();
                notifyObservers(field);
                break;
        }
        return isFlagsOverlapsMines; //true - победа, все флаги стоят над всеми минами
    }

    public boolean openAdjacent(int userXCoordinate, int userYCoordinate) throws IllegalArgumentException {
        return field.openAdjacent(userXCoordinate, userYCoordinate);
    }


    public Field startNewGame(int level) {
        switch (level) {
            case 1:
                dimension = 9;
                minesTotal = 10;
                break;

            case 2:
                dimension = 16;
                minesTotal = 40;
                break;

            case 3:
                dimension = 22;
                minesTotal = 99;
                break;

            case 4:
                //размеры и количество мин заданы через сеттеры

                if (dimension < 9 || dimension > 30) {
                    dimension = 9;
                }

                int minesMax = (dimension - 1) * (dimension - 1);
                if (minesTotal < 10 || minesTotal > minesMax) {
                    minesTotal = 10;
                }
                break;

            default:
                dimension = 9;
                minesTotal = 10;
        }

        field = new Field(dimension, minesTotal);
        flags = minesTotal;

        this.level = level;

        startTime = System.currentTimeMillis();

        return field;
    }


    public Field getField() {
        return field;
    }

    public long getTimeConsumedMills() {
        long finishTime = System.currentTimeMillis();
        return finishTime - startTime;
    }

    public int getLevel() {
        return level;
    }


    public void setDimension(int dimension) {
        if (dimension >= 1 && dimension <= 30) {
            this.dimension = dimension;
        } else {
            throw new IllegalArgumentException("Размер поля должен быть в пределах от 1 до 30 (передано" + dimension + ")");
        }
    }

    public void setMinesTotal(int minesTotal) {
        int minesMax = (dimension - 1) * (dimension - 1);
        if (minesTotal >= 10 && minesTotal <= minesMax) {
            this.minesTotal = minesTotal;
        } else {
            throw new IllegalArgumentException("Количество мин должно быть в пределах от 10 до "
                    + minesMax + " (передано" + minesTotal + ")");
        }
    }
}
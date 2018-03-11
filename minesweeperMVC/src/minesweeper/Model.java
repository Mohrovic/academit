package minesweeper;

public class Model extends java.util.Observable {

    private Field field;
    private int flags;

    private long startTime;
    private boolean isRunning;
    private boolean isGameOver;

    public void openCell(int x, int y) throws IllegalArgumentException {

        if (field.openCell(x, y)) {
            field.setNotification(null); // для консольной версии

            field.setDisplayMode(1); // для консольной версии

            setChanged();
            notifyObservers(field);

        } else {
            String notification = "Game over!";
            field.setNotification(notification);

            field.setDisplayMode(1);

            setChanged();
            notifyObservers(field);

            isGameOver = true;
        }
    }

    public void setFlag(int userXCoordinate, int userYCoordinate) throws IllegalArgumentException {
        char[][] gameField = field.getGameField();
        byte[][] mask = field.getMask();

        switch (mask[userXCoordinate][userYCoordinate]) {
            case 0: {
                if (flags > 0) {
                    mask[userXCoordinate][userYCoordinate] = 2;

                    flags--;

                    field.setFlagsLeft(flags);

                    field.setDisplayMode(1);

                    setChanged();
                    notifyObservers(field);
                }

                //проверяяем, не совпали ли все флажки с минами
                int dimension = field.getDimension();

                if (flags == 0) {
                    boolean isFlagsOverlapsMines = false;
                    for (int x = 0; x < dimension; x++) {
                        for (int y = 0; y < dimension; y++) {
                            if (mask[x][y] == 2) {
                                isFlagsOverlapsMines = (gameField[x][y] == '*');
                            }
                        }
                    }

                    if (isFlagsOverlapsMines) {
                        long finishTime = System.currentTimeMillis();
                        long timeConsumedMillis = finishTime - startTime;

                        String notification = "Победа!";
                        field.setNotification(notification);

                        field.setDisplayMode(2);

                        setChanged();
                        notifyObservers(field);

                        isRunning = false;

                        Records.printAndSaveRecords(timeConsumedMillis);
                    } else {
                        String notification = "Флажки закончились";
                        field.setNotification(notification);

                        field.setDisplayMode(2);

                        setChanged();
                        notifyObservers(field);
                    }
                }
            }
            break;

            case 1: {
                String notification = "Эта ячейка уже открыта";
                field.setNotification(notification);

                field.setDisplayMode(1);

                setChanged();
                notifyObservers(field);
            }
            break;

            case 2: {
                mask[userXCoordinate][userYCoordinate] = 3;

                flags++;

                field.setDisplayMode(1);

                setChanged();
                notifyObservers(field);
            }
            break;

            case 3: {
                mask[userXCoordinate][userYCoordinate] = 0;

                field.setDisplayMode(1);

                setChanged();
                notifyObservers(field);
            }
            break;
        }
    }

    public void newGame(int dimension, int minesTotal) {
        isRunning = true;
        isGameOver = false;

        field = new Field(dimension, minesTotal);
        flags = minesTotal;

        startTime = System.currentTimeMillis();

        String notification = "Новая игра:";
        field.setNotification(notification);

        field.setDisplayMode(1);

        setChanged();
        notifyObservers(field);
    }

    public void exitGame() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void showMessage(String message) {
        field.setNotification(message);

        field.setDisplayMode(2);

        setChanged();
        notifyObservers(field);
    }
}
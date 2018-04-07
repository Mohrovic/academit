package GUI;

import minesweeper.Model;
import minesweeper.Records;
import minesweeper.ViewListener;


public class Controller implements ViewListener {
    private Model model;
    private ConsoleView consoleView;

    public Controller(Model model, ConsoleView consoleView) {
        this.model = model;
        this.consoleView = consoleView;

        consoleView.updateField(model.startNewGame(model.getLevel()));
    }

    @Override
    public void needOpenCell(int x, int y) {
        boolean isGameOver = !model.openCell(x, y);
        consoleView.updateField(model.getField());
        if (isGameOver) {
            model.startNewGame(model.getLevel());
        }
    }

    @Override
    public void needSetFlag(int x, int y) {
        //проверка на выигрыш
        if (!model.setFlag(x, y)) {
            consoleView.updateField(model.getField());
        } else {
            Records record = new Records();
            if(record.updateRecord(model.getTimeConsumedMills(), model.getLevel())) {
                consoleView.showRecords(record);
            }

            consoleView.updateField(model.startNewGame(model.getLevel()));
        }
    }

    @Override
    public void needOpenAdjacent(int x, int y) {
        boolean isGameOver = !model.openAdjacent(x, y);
        consoleView.updateField(model.getField());
        if (isGameOver) {
            model.startNewGame(model.getLevel());
        }
    }

    @Override
    public void needStartNewLevel(int level) {
        consoleView.updateField(model.startNewGame(level));
    }

    @Override
    public void needStartNewLevel(int dimension, int minesTotal) {
        model.setDimension(dimension);
        model.setMinesTotal(minesTotal);

        final int SPECIAL_LEVEL = 4;
        consoleView.updateField(model.startNewGame(SPECIAL_LEVEL));
    }
}
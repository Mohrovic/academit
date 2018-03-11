package minesweeper;

import GUI.Controller;
import GUI.View;

public class RunConsole {

    public RunConsole() {

        Model myModel = new Model();
        View myView = new View();

        myModel.addObserver(myView);

        Controller myController = new Controller();
        myController.addModel(myModel);
        myController.addView(myView);

        int dimension = 9;
        int minesTotal = 10;
        myController.initModel(dimension, minesTotal);

        myView.addController(myController);

        while (myModel.isRunning()) {
            if (myModel.isGameOver()) {
                myController.initModel(dimension, minesTotal);
                continue;
            }

            try {
                myController.waitCommand();
            } catch (IllegalArgumentException e) {
                myModel.showMessage("Неверные координаты: " + e);
            }
        }
    }
}
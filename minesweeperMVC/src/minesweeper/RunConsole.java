package minesweeper;

import GUI.ConsoleView;
import GUI.Controller;

public class RunConsole {

    public RunConsole() {

        try (ConsoleView consoleView = new ConsoleView()) {
            Model model = new Model();

            Controller controller = new Controller(model, consoleView);

            consoleView.addViewListener(controller);

            consoleView.startApplication();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
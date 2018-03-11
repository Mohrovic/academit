package GUI;

import minesweeper.Field;

import java.util.Observable;

public class View implements java.util.Observer {

    private Controller controller;
    private final String MAIN_MENU;

    public View() {
        int dimension = 9; //первый запуск всегда будет с поля 9х9
        MAIN_MENU = "Введите команду (1-открыть, 2-поставить флажок/вопрос, 3-сменить уровень, 4-выйти):";
    }

    @Override
    public void update(Observable obs, Object obj) {
        Field field = (Field) obj;
        int dimension = field.getDimension();
        byte[][] mask = field.getMask();
        char[][] gameField = field.getGameField();
        int displayMode = field.getDisplayMode();

        switch (displayMode) {
            case 1: {
                System.out.print("   |");
                for (int i = 1; i <= dimension; i++) {
                    System.out.printf("%3d", i);
                }
                System.out.print("|");

                System.out.print("\n --+");
                for (int i = 1; i <= dimension; i++) {
                    System.out.print("---");
                }
                System.out.printf("|      | флажков осталось: %d |%n", field.getFlagsLeft());

                for (int x = 0; x < dimension; x++) {
                    System.out.printf("%3d|", x + 1);
                    for (int y = 0; y < dimension; y++) {

                        switch (mask[x][y]) {
                            case 0: {
                                System.out.printf("   ");
                            }
                            break;

                            case 1: {
                                if (gameField[x][y] == '0') {
                                    System.out.print("  ·");
                                } else {
                                    System.out.printf("%3c", gameField[x][y]);
                                }
                            }
                            break;

                            case 2: {
                                System.out.print("  P");
                            }
                            break;

                            case 3: {
                                System.out.print("  ?");
                            }
                            break;
                        }

                    }
                    System.out.print("|\n");

                }
                System.out.print(" ---");
                for (int i = 1; i <= dimension; i++) {
                    System.out.print("---");
                }
                System.out.print("-\n");

                String notification = field.getNotification();
                if (notification != null) {
                    System.out.println(notification);
                }

                System.out.print(MAIN_MENU);
            }
            break;

            case 2: {
                String notification = field.getNotification();
                if (notification != null) {
                    System.out.print(notification);
                }
            }
            break;
        }

    }

    public void addController(Controller controller) {
        this.controller = new Controller();
    }
}
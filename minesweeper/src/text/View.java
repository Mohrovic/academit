package text;

public class View {

    public static void printField(char[][] gameField, byte[][] mask) {
        int dimension = gameField[0].length;

        System.out.print("   |");
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
            System.out.println();
        }
    }
}

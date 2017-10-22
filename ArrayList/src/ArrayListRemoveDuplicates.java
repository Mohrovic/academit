import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListRemoveDuplicates {
    public static void main(String[] args) {
        ArrayList<Integer> integerList = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5));
        ArrayList<Integer> shortList = new ArrayList<>();

        for (int i = 0; i < integerList.size(); i++) {
            int currentInt = integerList.get(i);
            boolean isUnique = true;

            for (int j = i - 1; j >= 0; j--) {
                if (integerList.get(j) == currentInt) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                shortList.add(currentInt);
            }
        }
        System.out.println(shortList.toString());
    }
}

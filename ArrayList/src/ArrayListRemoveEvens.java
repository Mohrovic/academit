import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListRemoveEvens {
    public static void main(String[] args) {
        ArrayList<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 2, 2, 2, 3, 4, 5, 6, 7));
        for (int i = 0; i < integerList.size(); i++) {
            if (integerList.get(i) % 2 == 0) {
                integerList.remove(i);
                i--;
            }
        }
        System.out.println(integerList.toString());
    }
}

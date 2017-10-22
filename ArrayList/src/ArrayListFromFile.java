import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListFromFile {
    public static void main (String[] args){
        try (Scanner scanner = new Scanner(new FileInputStream("ArrayList/in.txt"))){
            ArrayList<String> list = new ArrayList<>();
            while (scanner.hasNext()){
                list.add(scanner.nextLine());
            }

            System.out.println(list.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

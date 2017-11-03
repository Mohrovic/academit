import java.util.Arrays;

public class DemoArray {
    public static void main(String[] args) {

        MyArray<String> names = new MyArray<>();

        names.add("a-b-c");
        names.add("1,2,3");
        System.out.println("Заполнили исходный массив:");
        System.out.println(Arrays.toString(names.toArray()));

        MyArray<String> names2 = new MyArray<>();
        names2.add("d-e-f");
        names2.add("4,5,6");
        names.add(names2);
        System.out.println("присоединили names2");
        System.out.println(Arrays.toString(names.toArray()));
        System.out.printf("Размер созданного списка = %d%n", names.size());

        names.remove(1);
        System.out.println("удалили элемент номер1");
        System.out.println(Arrays.toString(names.toArray()));
        System.out.printf("Размер созданного списка = %d", names.size());

        String insertingItem = "g-h-i";
        names.add(2, insertingItem);
        System.out.println("добавили String элемент");
        System.out.println(Arrays.toString(names.toArray()));

        System.out.printf("Содержит \"%s\": ", insertingItem);
        System.out.println(names.contains(insertingItem));

        MyArray<String> names3 = new MyArray<>();
        names3.add(names);

        System.out.printf("Список %s содержит все элементы %s: %s%n",
                Arrays.toString(names.toArray()), Arrays.toString(names3.toArray()), names.containsAll(names3));

        names3.remove(1);
        System.out.printf("Список %s содержит все элементы %s: %s%n",
                Arrays.toString(names.toArray()), Arrays.toString(names3.toArray()), names.containsAll(names3));

        names3.add("new string");
        System.out.printf("Список %s содержит все элементы %s: %s%n",
                Arrays.toString(names.toArray()), Arrays.toString(names3.toArray()), names.containsAll(names3));

        names.remove("g-h-i");
    }
}
public class Main {
    public static void main(String[] args) {
        // I
        // 1.
        MyArray<Integer> array = new MyArray<>();
        // 2.
        Integer[] defaultArray = new Integer[]{8, 5, 2, 6, 9, 3, 1, 4, 0, 7};
        MyArray<Integer> test = new MyArray<>(defaultArray);
        MyArray<Integer> test2 = new MyArray<>(defaultArray);
        MyArray<Integer> test3 = new MyArray<>(defaultArray);
        test.printToConsole();
        // II.
        // 1.1
        try {
            test.insert(3, 2);
            test.printToConsole();
            // 1.2
            test.add(142);
            test.printToConsole();
            // 2.
            test.remove(1);
            test.printToConsole();
            // 3.
            test.remove(new Integer(3));
            test.printToConsole();
            // 4.
            System.out.println(test.min());
            // 5.
            System.out.println(test.max());
            // 6.
            System.out.println(test.sumOfElements());
            // 7.
            System.out.println(test.productOfElements());
            // 8.
            System.out.println(test.find(3));
            System.out.println(test.find(2));
            // 9.
            System.out.println(test.has(3));
            System.out.println(test.has(2));
            // 10.
            test.printToConsole();
            test.bubbleSort();
            test.printToConsole();
            // 11.
            test2.printToConsole();
            test2.insertionSort();
            test2.printToConsole();
            // 12.
            test3.printToConsole();
            test3.selectionSort();
            test3.printToConsole();
            // 13.
            System.out.println(test3.get(2));
            // 14.
            test3.set(2, 24);
            // 15.
            test3.printToConsole();
            // 16.
            System.out.println(test3.getLength());
        } catch (MyArrayIndexException | MyArrayEmptyException e) {
            e.printStackTrace();
        }
    }
}

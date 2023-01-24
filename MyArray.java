public class MyArray<T extends Number & Comparable<T>> {

    // III.Так же класс должен содержать следующие приватные поля

    // 1.Сам массив произвольных данных
    private Object[] array;

    // 2.Длинну массива
    private int length;

    // I.Класс будет иметь следующие конструкторы

    // 1.Конструктор без параметров – конструктор по умолчанию, проводяющий базовую иницаилизацию массива
    public MyArray() {
        this.array = new Object[10];
        this.length = 0;
    }

    // 2.Конструктор с параметром T[] – конструктор, который проводит инициализацию
    // и заполняет массив данными, пришедшими из параметра
    public MyArray(T[] array) {
        this.length = array.length;
        this.array = new Object[length + 10];
        for (int i = 0; i < length; i++) {
            this.array[i] = array[i];
        }
    }

    // Вспомогательные методы для работы класса
    // Метод для проверки правильности предоставленного методам индекса
    private void checkIndex(int index, boolean isForAdding) throws MyArrayIndexException {
        if (index < 0) {
            throw new MyArrayIndexException("Индекс меньше нуля");
        }
        if (isForAdding) {
            if (index > length) {
                throw new MyArrayIndexException("Индекс для добавления больше чем последний имеющийся индекс плюс один");
            }
        } else {
            if (index > length - 1) {
                throw new MyArrayIndexException("Индекс больше индекса последнего элемента");
            }
        }
    }

    // Метод для увеличения максимально-ввозможного количества элементов для хранения
    private void expand() {
        Object[] newArray = new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, length);
        array = newArray;
    }

    // II.Так же класс будет иметь следующие публичные методы:
    // 1.1. Вставка в массив (в указанное место)
    public void insert(T element, int index) throws MyArrayIndexException {
        checkIndex(index, true);
        if (array.length == length) {
            expand();
        }
        if (index != length) {
            if (length - index >= 0) {
                System.arraycopy(array, index, array, index + 1, length - index);
            }
        }
        array[index] = element;
        length++;
    }

    //1.2. Вставка в массив (в конце)
    public void add(T element) {
        try {
            insert(element, length);
        } catch (MyArrayIndexException e) {
            e.printStackTrace();
        }
    }

    // 2. Удаление элемента по индексу
    public void remove(int index) throws MyArrayIndexException, MyArrayEmptyException {
        checkIndex(index, false);
        if (length == 0) {
            throw new MyArrayEmptyException("Массив уже пуст.");
        }
        if (index < length - 1) {
            if (length - (index + 1) >= 0) {
                System.arraycopy(array, index + 1, array, index + 1 - 1, length - (index + 1));
            }
        }
        length--;
    }

    // 3. Удаление всех элементов с заданным значением
    public void remove(T element) throws MyArrayEmptyException {
        if (length == 0) {
            throw new MyArrayEmptyException("Массив уже пуст.");
        }
        int matchCount = 0;
        for (int i = 0; i < length; i++) {
            if (array[i].equals(element)) {
                matchCount++;
            } else {
                array[i - matchCount] = array[i];
            }
        }
        length -= matchCount;
    }

    // 4. Поиск минимума
    public T min() throws MyArrayEmptyException {
        if (length == 0) {
            throw new MyArrayEmptyException("Нельзя найти минимум в пустом массиве.");
        }
        T minimum = (T) array[0];
        for (int i = 1; i < length; i++) {
            T temp = (T) array[i];
            if (minimum.compareTo(temp) > 0) {
                minimum = temp;
            }
        }
        return minimum;
    }

    // 5. Поиск максимума
    public T max() throws MyArrayEmptyException {
        if (length == 0) {
            throw new MyArrayEmptyException("Нельзя найти минимум в пустом массиве.");
        }
        T maximum = (T) array[0];
        for (int i = 1; i < length; i++) {
            T temp = (T) array[i];
            if (maximum.compareTo(temp) < 0) {
                maximum = temp;
            }
        }
        return maximum;
    }

    // 6. Поиск суммы элементов массива
    public double sumOfElements() throws MyArrayEmptyException {
        if (length == 0) {
            throw new MyArrayEmptyException("Нельзя найти сумму элементов в пустом массиве.");
        }
        double result = ((T) array[0]).doubleValue();
        for (int i = 1; i < length; i++) {
            result += ((T) array[i]).doubleValue();
        }
        return result;
    }

    // 7. Поиск произведения элементов массива
    public double productOfElements() throws MyArrayEmptyException {
        if (length == 0) {
            throw new MyArrayEmptyException("Нельзя найти произведение элементов в пустом массиве.");
        }
        double result = ((T) array[0]).doubleValue();
        for (int i = 0; i < length; i++) {
            result *= ((T) array[i]).doubleValue();
        }
        return result;
    }

    // 8. Поиск индекса заданного элемента в массиве, если такого элемента в массиве нет то возвращать -1
    public int find(T element) {
        for (int i = 0; i < length; i++) {
            if (element.equals((T) array[i])) {
                return i;
            }
        }
        return -1;
    }

    // 9. Проверка наличия элемента в массиве. Возвращает true, если элемент в массиве есть, false – нет.
    public boolean has(T element) {
        return find(element) != -1;
    }

    // 10. Пузырьковая сортировка
    public void bubbleSort() {
        for (int i = 0; i < length - 1; i++) {
            for (int j = 1; j < length - i; j++) {
                T next = (T) array[j];
                T previous = (T) array[j - 1];
                if (next.compareTo(previous) < 0) {
                    Object temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }

    // 11. Сортировка простыми вставками
    public void insertionSort() {
        for (int i = 1; i < length; i++) {
            int j = i;
            while (j > 0) {
                T jth = (T) array[j];
                T jMinusOneth = (T) array[j - 1];
                if (jMinusOneth.compareTo(jth) < 0) {
                    break;
                }
                Object temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
                j--;
            }
        }
    }

    // 12. Сортировка простым выбором
    public void selectionSort() {
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (((T) array[j]).compareTo((T) array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            Object temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    // 13. Получение элемента массива по индексу
    public T get(int index) throws MyArrayIndexException {
        checkIndex(index, false);
        return (T) array[index];
    }

    // 14. Задание значения элементу массива с заданным индексом
    public void set(int index, T element) throws MyArrayIndexException {
        checkIndex(index, false);
        array[index] = element;
    }

    // 15. Печать массива на экран
    public void printToConsole() {
        System.out.println(this);
    }

    // 16. Длинна массива
    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < length - 1; i++) {
            sb.append(array[i]);
            sb.append(", ");
        }
        sb.append(array[length - 1]);
        sb.append('}');
        return sb.toString();
    }
    //IV. Так же массив должен содержать следующие перегруженные операторы
    // Java не позволяет перегружать операторы
}

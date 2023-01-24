public class MyArrayIndexException extends Exception {
    public MyArrayIndexException() {}
    public MyArrayIndexException(String message) {
        super(String.format("Некорректный индекс: %s.", message));
    }
}

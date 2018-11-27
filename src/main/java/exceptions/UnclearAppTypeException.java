package exceptions;

public class UnclearAppTypeException extends Exception {

    public UnclearAppTypeException() {
        super("Unclear type of mobile app");
    }

    public UnclearAppTypeException(String detailedMassage) {
        super(detailedMassage);
    }
}

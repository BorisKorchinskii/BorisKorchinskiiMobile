package exceptions;

public class UnknownPatformException extends Exception{

    public UnknownPatformException(){
        super("Unknown mobile platform");
    }

    public UnknownPatformException(String detailedMassage){
        super(detailedMassage);
    }
}

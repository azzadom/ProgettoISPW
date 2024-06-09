package exception;

public class OperationFailedException extends Exception{

    public OperationFailedException(String message){
        super(message);
    }

    public OperationFailedException(){
        super("Unable to complete the operation.\n" +
                "If problem persists, contact support.");
    }

}

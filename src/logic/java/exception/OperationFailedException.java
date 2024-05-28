package exception;

public class OperationFailedException extends Exception{

    public OperationFailedException(String message){
        super(message);
    }

    public OperationFailedException(){
        super("Impossibile completare l'operazione.\n" +
                "Se il problema persiste contattare l'assistenza.");
    }

}

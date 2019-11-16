package exception;

public class RecordExistException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public RecordExistException() {
    }

    RecordExistException(Throwable e) {
        super(e);
    }

    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return "Scrip details already Exist";
    }

}

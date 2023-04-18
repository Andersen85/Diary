package diary.exception;

public class DiaryException extends Exception {
    private DiaryErrorCode err;

    public DiaryException(DiaryErrorCode err){
        this.err = err;
    }

    public DiaryErrorCode getErrorCode() {
        return err;
    }
}

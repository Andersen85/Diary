package diary.response;

import diary.exception.DiaryException;

public class ErrorResponse {
    private final String error;

    public ErrorResponse(DiaryException e){
        error = e.getErrorCode().getErrorString();
    }

    public String getError() {
        return error;
    }
}

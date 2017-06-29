package itesloscabos.com.hotelapp.Models.serverBroken;

/**
 * Created by croni on 22/06/2017.
 */

public class serverBroken {
    private String message;
    private String exceptionMessage;
    private String exceptionType;
    private String stackTrace;
    private innerException innerException;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public itesloscabos.com.hotelapp.Models.serverBroken.innerException getInnerException() {
        return innerException;
    }

    public void setInnerException(itesloscabos.com.hotelapp.Models.serverBroken.innerException innerException) {
        this.innerException = innerException;
    }
}

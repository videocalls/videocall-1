package kr.co.koscom.oppf.spt.direct.internal.exception;

@SuppressWarnings("serial")
public class InternalApiException extends RuntimeException  {

    private InternalApiExceptionCode exceptionCode = InternalApiExceptionCode.UNKNOWN_ERROR;
    private String[] args;
    
    public InternalApiException(InternalApiExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public InternalApiException(InternalApiExceptionCode exceptionCode, String...args ) {
        this.exceptionCode = exceptionCode;
        this.args = args;
    }

    public InternalApiException(InternalApiExceptionCode exceptionCode, Throwable cause, String...args) {
        super(cause);
        this.exceptionCode = exceptionCode;
        this.args = args;
    }
    
    public InternalApiExceptionCode getExceptionCode() {
        return this.exceptionCode;
    }

    public String[] getArgs() {
        return args;
    }
    
}

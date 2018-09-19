package kr.co.koscom.oppf.spt.direct.common.exception;

@SuppressWarnings("serial")
public class ApiException extends RuntimeException  {

    private ApiExceptionCode exceptionCode = ApiExceptionCode.UNKNOWN_ERROR;
    private String[] args;
    
    public ApiException(ApiExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public ApiException(ApiExceptionCode exceptionCode, String...args ) {
        this.exceptionCode = exceptionCode;
        this.args = args;
    }

    public ApiException(ApiExceptionCode exceptionCode, Throwable cause, String...args) {
        super(cause);
        this.exceptionCode = exceptionCode;
        this.args = args;
    }
    
    public ApiExceptionCode getExceptionCode() {
        return this.exceptionCode;
    }

    public String[] getArgs() {
        return args;
    }
    
}

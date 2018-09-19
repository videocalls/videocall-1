package kr.co.koscom.oppfm.cmm.exception;

/**
 * ExternalInterfaceException
 * <p>
 * Created by chungyeol.kim on 2017-05-17.
 */
public class ExternalInterfaceException extends AbstractErrorCodeException {

    private static final long serialVersionUID = 8219904766024419444L;

    private String errorCode;
    private String response;

    @Override
    public String getCode() {
        return this.errorCode;
    }

    public void setCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getResponse() {
        return this.response;
    }

    public ExternalInterfaceException(String errorCode, String[] args, String response) {
        super();
        this.errorCode = errorCode;
        this.response = response;
        this.args = args;
    }
}

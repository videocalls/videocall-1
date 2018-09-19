package kr.co.koscom.oppfm.cmm.exception;

/**
 * InternalInterfaceException
 * <p>
 * Created by chungyeol.kim on 2017-05-17.
 */
public class InternalInterfaceException extends AbstractErrorCodeException {

    private static final long serialVersionUID = -2929909607421188147L;

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

    public InternalInterfaceException(String errorCode, String[] args, String response) {
        super();
        this.errorCode = errorCode;
        this.response = response;
        this.args = args;
    }
}

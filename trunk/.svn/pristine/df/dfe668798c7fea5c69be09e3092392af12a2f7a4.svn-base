package kr.co.koscom.oppfm.cmm.exception;

/**
 * CommonException
 * <p>
 * Created by chungyeol.kim on 2017-05-17.
 */
public class CommonException extends AbstractErrorCodeException {

    private static final long serialVersionUID = 1332393860751563853L;

    private String errorCode;

    @Override
    public String getCode() {
        return this.errorCode;
    }

    public void setCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public CommonException(String errorCode, String[] args) {
        super();
        this.errorCode = errorCode;
        this.args = args;
    }
}

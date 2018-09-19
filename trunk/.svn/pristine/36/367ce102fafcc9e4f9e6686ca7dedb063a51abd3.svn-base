package kr.co.koscom.oppfm.cmm.exception;

/**
 * AbstractErrorCodeException
 * <p>
 * Created by chungyeol.kim on 2017-04-21.
 */
public abstract class AbstractErrorCodeException extends RuntimeException {

    private static final long serialVersionUID = -1663527949838322012L;

    protected Object[] args;

    public abstract String getCode();

    public AbstractErrorCodeException() {
        super();
    }

    public AbstractErrorCodeException(final Object... args) {
        super();
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }
}

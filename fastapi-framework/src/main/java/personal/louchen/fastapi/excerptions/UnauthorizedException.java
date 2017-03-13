package personal.louchen.fastapi.excerptions;

/**
 * 未认证错误
 * 
 * @author Eric.Lou
 *
 */
public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnauthorizedException(final String message) {
		super(message);
	}

	public UnauthorizedException(final Exception e) {
		super(e);
	}

}

package personal.louchen.fastapi.excerptions;

/**
 * 签名失败异常
 * 
 * @author Eric.Lou
 *
 */
public class UnsignException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnsignException(final String message) {
		super(message);
	}

	public UnsignException(final Exception e) {
		super(e);
	}

}

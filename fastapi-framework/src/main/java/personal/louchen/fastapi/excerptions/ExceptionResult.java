package personal.louchen.fastapi.excerptions;

/**
 * 异常返回值
 * 
 * @author Eric.Lou
 *
 */
public class ExceptionResult {

	private String message;

	public ExceptionResult() {

	}

	public ExceptionResult(final String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

}

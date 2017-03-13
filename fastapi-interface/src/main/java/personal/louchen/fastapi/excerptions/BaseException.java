package personal.louchen.fastapi.excerptions;

import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Collections;

/**
 * 公司异常框架的根异常。公司内定义的所有业务异常都应该继承自本类。
 *
 * @author Eric.Lou
 */
public abstract class BaseException extends RuntimeException {
	/**
	 * 下一步可以重试
	 */
	public static final Collection<String> NEXT_STEP_IDS_OF_RETRY = Collections.singleton("self");
	/**
	 * 下一步可以报告问题
	 */
	public static final Collection<String> NEXT_STEP_IDS_OF_REPORT_PROBLEM = Collections.singleton("reportProblem");
	/**
	 * 下一步可以重试或报告问题
	 */
	public static final Collection<String> NEXT_STEP_IDS_OF_RETRY_OR_REPORT_BUG = Sets
			.newHashSet("self", "reportProblem");
	/**
	 * 下一步可以去平台授权
	 */
	public static final Collection<String> NEXT_STEP_IDS_OF_PLAT_AUTHORIZE = Collections.singleton("platAuthorize");

	private static final long serialVersionUID = 4904242843495439482L;

	/**
	 * 攀爬异常藤，寻找是否有BaseException异常节点。
	 *
	 * @param throwable
	 * @return
	 */
	public static BaseException findBaseException(final Throwable throwable) {
		return ExceptionUtil.findCause(throwable, BaseException.class, true);
	}

	//----------------------------------------------------

	protected transient final Logger logger = LoggerFactory.getLogger(getClass());

	private final String id;
	private final Collection<String> nextStepIds;

	//----------------------------------------------------

	public BaseException(final String message) {
		super(message);
		id = buildId();
		nextStepIds = NEXT_STEP_IDS_OF_REPORT_PROBLEM;
	}

	public BaseException(final String message, final Collection<String> nextStepIds) {
		super(message);
		id = buildId();
		this.nextStepIds = Collections.unmodifiableCollection(nextStepIds);
	}

	public BaseException(final String message, final Throwable cause) {
		super(message, cause);
		if (cause instanceof BaseException) {
			final BaseException baseException = (BaseException) cause;
			id = baseException.getId();
		} else {
			id = buildId();
		}
		nextStepIds = Collections.emptyList();
	}

	//----------------------------------------------------

	private String buildId() {
		final Thread thread = Thread.currentThread();
		try {
			final InetAddress inetAddress = InetAddress.getLocalHost();
			return thread.getId() + "_" + System.currentTimeMillis() + '_' + inetAddress.getHostAddress();
		} catch (final UnknownHostException e) {
			logger.warn("不应该发生", e);
			return thread.getId() + "_" + System.currentTimeMillis();
		}
	}

	public String getId() {
		return id;
	}

	public BaseException(final String message, final Throwable cause, final Collection<String> nextStepIds) {
		super(message, cause);
		if (cause instanceof BaseException) {
			final BaseException baseException = (BaseException) cause;
			id = baseException.getId();
		} else {
			id = buildId();
		}
		this.nextStepIds = Collections.unmodifiableCollection(nextStepIds);
	}

	public Collection<String> getNextStepIds() {
		return nextStepIds;
	}
}

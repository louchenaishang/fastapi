package personal.louchen.fastapi.utils;

/**
 * @author Eric.Lou
 * @version $Id: NullUtil.java, v 0.1 2015-07-16 14:29
 */
public class NullUtil {

    /**
     * @param obj
     * @return boolean
     * @method 判断参数obj是否为null，如果obj为null,返回false；不为null，返回true
     * @author eric.lou
     * @date 2015年7月15日 上午10:25:53
     */
    public static boolean isNotNull(Object obj) {
        if (null == obj) {
            return false;
        }
        return true;
    }

    /**
     * @param obj
     * @return boolean
     * @method 判断参数obj是否为null，如果obj为null,返回true；不为null，返回false
     * @author eric.lou
     * @date 2015年7月15日 上午10:26:12
     */
    public static boolean isNull(Object obj) {
        if (null == obj) {
            return true;
        }
        return false;
    }

}
 
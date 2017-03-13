package personal.louchen.fastapi.utils;

import org.apache.commons.collections.CollectionUtils;
import personal.louchen.fastapi.excerptions.UnsignException;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * Created by louchen on 16/9/6.
 */
public class ApiSecurityUtil {

    private final static String SIGN = "sign";
    private final static String SIGN_CONDITION = "sign=";
    private final static String NONCESTR = "noncestr";
    private final static String NONCESTR_CONDITION = "noncestr=";
    private final static Integer NONCESTR_LENGTH = 32;

    /**
     * APISERVER,APICLIENT生成签名
     * @param queryMap
     * @param key
     * @return
     */
    public static String createSign(SortedMap<Object,Object> queryMap,String key){
        StringBuffer sb = new StringBuffer();
        Set es = queryMap.entrySet();
        if(CollectionUtils.isEmpty(es)){
            throw new UnsignException("");
        }
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            if(v!=null
                    && !"".equals(v)
                    && !"sign".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        String sign = MD5Util.md5(sb.toString()).toUpperCase();
        return sign;
    }

    /**
     * APISERVER,APICLIENT生成签名
     * @param query
     * @param key
     * @return
     */
    public static String createSign(String query,String key){
        return createSign(MapUtil.queryStringToMap(query),key);
    }

    /**
     * APISERVER,APICLIENT生成签名
     * @param request
     * @param key
     * @return
     */
    public static String createSign(HttpServletRequest request, String key){
        return createSign(MapUtil.queryStringToMap(request.getQueryString()),key);
    }

    /**
     * APISERVER 验证签名
     * @param query
     * @param key
     * @throws UnsignException
     */
    public static void validateSign(String query, String key){
        if(query==null){
            throw new UnsignException("");
        }
        if(!query.contains(NONCESTR_CONDITION)){
            throw new UnsignException("");
        }
        if(!query.contains(SIGN_CONDITION)){
            throw new UnsignException("");
        }
        SortedMap<Object, Object> queryMap = MapUtil.queryStringToMap(query);
        String sign = (String)queryMap.get(SIGN);
        String noncestr = (String)queryMap.get(NONCESTR);
        if(sign==null){
            throw new UnsignException("");
        }
        if(noncestr==null){
            throw new UnsignException("");
        }
        if(noncestr.length()!=NONCESTR_LENGTH.intValue()){
            throw new UnsignException("");
        }
        queryMap.remove(SIGN);
        if(!sign.equals(createSign(queryMap,key))){
            throw new UnsignException("");
        }
    }

    public static void main(String[] args) {
        System.out.println(createSign("who=abc&noncestr=12345678123456781234567812345678"
                ,"ceshiapidemiyaohahahaahYIdingshi32weiaa"));
    }

}

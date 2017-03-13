package personal.louchen.fastapi.utils.net;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * Created by louchen on 16/7/29.
 */
public class HttpUtil {

    public static final String DEFAULT_CHARSET = "utf-8";

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static SSLConnectionSocketFactory sslsf = null;

    private static PoolingHttpClientConnectionManager connectionManager = null;

    static{
        TrustManager[] tm = {new MyX509TrustManager()};
        SSLContext sslContext = null;
        try{
            sslContext = SSLContext.getInstance("SSL","SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            sslsf = new SSLConnectionSocketFactory(sslContext);
        }catch (Exception e){
            logger.error("",e);
        }

        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(400);
        connectionManager.setDefaultMaxPerRoute(20);
    }

    /**
     * 获取http客户端连接池
     * @return
     */
    public static PoolingHttpClientConnectionManager getConnectionManager(){
        return connectionManager;
    }

    /**
     * https客户端
     * @return
     */
    public static CloseableHttpClient createSSLClientDefault(){
        try{
            return HttpClients.custom()
                    .setConnectionManager(connectionManager)
                    .setSSLSocketFactory(sslsf)
                    .build();
        }catch (Exception e){
            logger.error("",e);
            return HttpClients.createDefault();
        }
    }

    /**
     * http客户端
     * @return
     */
    public static CloseableHttpClient createClientDefault(){
        try{
            return HttpClients.custom()
                    .setConnectionManager(connectionManager)
                    .build();
        }catch (Exception e){
            logger.error("",e);
            return HttpClients.createDefault();
        }
    }

    /**
     * 执行get请求
     * @param https
     * @param url     url链接
     * @param params  请求参数
     * @param charset 字符编码
     * @return
     * @throws Exception
     */
    public static String doGet(boolean https,String url, Map<String, ?> params, String charset) throws Exception {
        if (params != null && !params.isEmpty()) {
            if (!url.endsWith("?")) {
                url += "?";
            }
            url += buildQuery(params, Collections.<String>emptyList(), charset);
        }

        HttpGet httpGet = null;
        CloseableHttpClient httpclient = null;
        String dataStr = null;
        try{
            httpGet = new HttpGet(url);

            //设置超时
            httpGet.setConfig(RequestConfig.custom()
                    .setConnectionRequestTimeout(2000)//the timeout in milliseconds used when requesting a connection from the connection manager.
                    .setConnectTimeout(2000)//the timeout in milliseconds until a connection is established.
                    .setSocketTimeout(2000)//the socket timeout (SO_TIMEOUT) in milliseconds, which is the timeout for waiting for data or, put differently, a maximum period inactivity between two consecutive data packets).
                    .build());

            httpclient = https?HttpUtil.createSSLClientDefault():HttpUtil.createClientDefault();
            HttpResponse response = httpclient.execute(httpGet);
            if (response.getStatusLine ().getStatusCode () != 200) {
                httpGet.abort();
                return null;
            }
            HttpEntity entity = response.getEntity();
            dataStr = EntityUtils.toString(entity, charset);
        }catch (Exception e){
            logger.error("",e);
        }finally {
            try{
                if(httpGet!=null){
                    httpGet.abort();
                }
            }catch (Exception e){
                //ignore
            }
        }
        return dataStr;
    }

    /**
     * 拼接url请求参数（&key=value形式）
     *
     * @param params         参数集合
     * @param exclusiveParas 排除的参数
     * @param charset        字符编码
     * @return
     * @throws Exception
     */
    public static String buildQuery(Map<String, ?> params, List<String> exclusiveParas, String charset)
            throws Exception {
        StringBuilder content = new StringBuilder();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            Object value = params.get(key);
            if (StringUtils.isBlank(key) || value == null || value.equals("") || exclusiveParas.contains(key)) {
                continue;
            }
            content.append((index == 0 ? "" : "&") + key + "=" + URLEncoder.encode(value.toString(), charset));
            index++;
        }
        return content.toString();
    }

    //################### post

    /**
     * 执行post请求
     *
     * @param https
     *
     * @param url
     *            url链接
     * @param content
     *            请求参数内容
     * @param charset
     *            字符编码
     * @param contentType
     *            可null
     * @return
     * @throws Exception
     */
    public static String doPost(boolean https, String url, String content, String charset,String contentType) throws Exception {
        HttpPost httpPost = null;
        CloseableHttpClient httpclient = null;
        String dataStr = null;
        try{
            httpPost = new HttpPost(url);
            StringEntity contentEntity = new StringEntity(content, charset);
            httpPost.setEntity(contentEntity);

            if(contentType!=null){
                httpPost.addHeader("Content-Type", contentType);
            }

            //设置超时
            httpPost.setConfig(RequestConfig.custom()
                    .setConnectionRequestTimeout(2000)//the timeout in milliseconds used when requesting a connection from the connection manager.
                    .setConnectTimeout(2000)//the timeout in milliseconds until a connection is established.
                    .setSocketTimeout(2000)//the socket timeout (SO_TIMEOUT) in milliseconds, which is the timeout for waiting for data or, put differently, a maximum period inactivity between two consecutive data packets).
                    .build());

            httpclient = https?HttpUtil.createSSLClientDefault():HttpUtil.createClientDefault();
            HttpResponse response = httpclient.execute(httpPost);
            if (response.getStatusLine ().getStatusCode () != 200) {
                httpPost.abort();
                return null;
            }
            HttpEntity entity = response.getEntity();
            dataStr = EntityUtils.toString(entity, charset);
        }catch (Exception e){
            logger.error("",e);
        }finally {
            try{
                if(httpPost!=null){
                    httpPost.abort();
                }
            }catch (Exception e){
                //ignore
            }
        }
        return dataStr;
    }

    public static String doJsonPost(boolean https, String url, String jsonString, String charset) throws Exception {
        return doPost(https,url,jsonString,charset,"application/json");
    }

    public static String doXmlPost(boolean https, String url, String xmlString, String charset) throws Exception {
        return doPost(https,url,xmlString,charset,"application/xml");
    }

}

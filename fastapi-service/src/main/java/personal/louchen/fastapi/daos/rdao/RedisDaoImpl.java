package personal.louchen.fastapi.daos.rdao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * redis操作类 实现
 * @author ljjOF
 *
 */

@Repository("redisDao")
public class RedisDaoImpl implements RedisDao,Serializable,InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(RedisDaoImpl.class);

    private static final long serialVersionUID = 1L;

    @Resource(name = "connectionFactory")
    private JedisConnectionFactory jedisConnectionFactory;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.debug("redisDao initialized");
    }

    /**
     * 通过连接池获取一个jedis 客户端
     * @return
     */
    private Jedis getJedis() {
        Jedis jedis = jedisConnectionFactory.getConnection().getNativeConnection();
        return jedis;
    }

    /**
     * 检查是否连接成功
     * @return
     */
    public String ping(){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            String result = jedis.ping();
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * 通过key删除（字节）
     * @param key
     */
    public void del(byte [] key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.del(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
    /**
     * 通过key删除
     * @param key
     */
    public void del(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.del(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 添加key value 并且设置存活时间(byte)
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(byte [] key,byte [] value,int liveTime) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        expire(key, liveTime);
    }

    /**
     * 添加key value 并且设置存活时间
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(String key,String value,int liveTime) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        expire(key, liveTime);
    }

    /**
     * 添加key value
     * @param key
     * @param value
     */
    public void set(String key,String value){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
    /**添加key value (字节)(序列化)
     * @param key
     * @param value
     */
    public void set(byte [] key,byte [] value){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
    /**
     * 获取redis value (String)
     * @param key
     * @return
     */
    public String get(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            String result = jedis.get(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * key的失效时间
     * @param key
     * @return 秒
     */
    public Long ttl(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            Long result = jedis.ttl(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 加锁
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    @Override
    public String setex(String key, String value, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            String result = jedis.setex(key, expireTime, value);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long setnx(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            Long result = jedis.setnx(key, value);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 获取redis value (byte [] )(反序列化)
     * @param key
     * @return
     */
    public byte[] get(byte [] key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            byte[] result = jedis.get(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 通过正则匹配keys
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            Set<String> result = jedis.keys(pattern);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 检查key是否已经存在
     * @param key
     * @return
     */
    public boolean exists(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            boolean result = jedis.exists(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 清空redis 所有数据
     * @return
     */
    public String flushDB(){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            String result = jedis.flushDB();
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 查看redis里有多少数据
     */
    public long dbSize(){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            long result = jedis.dbSize();
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 追加key value
     *
     * @param key
     * @param value
     */
    @Override
    public void append(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.append(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 追加key value (字节)(序列化)
     *
     * @param key
     * @param value
     */
    @Override
    public void append(byte[] key, byte[] value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.append(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public void sadd(byte[] key, byte[] value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.sadd(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * lpushx
     */
    @Override
    public void plull(byte[] key, byte[] value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.lpushx(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public void rpush(byte[] key, byte[] value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.rpush(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<byte[]> lrange(byte[] key, int start, int end) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            List<byte[]> result = jedis.lrange(key, start, end);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long expire(String key, int seconds) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            Long result = jedis.expire(key, seconds);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long expire(byte[] key,  int seconds) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            Long result = jedis.expire(key, seconds);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**添加key value (字节)(序列化)
     * @param key
     * @param value
     */
    @Override
    public void set(String key,ArrayList value){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key.getBytes(), ListTranscoder.serialize(value));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    //###########################
    static class ListTranscoder{
        public static byte[] serialize(Object value) {
            if (value == null) {
                throw new NullPointerException("Can't serialize null");
            }
            byte[] rv=null;
            ByteArrayOutputStream bos = null;
            ObjectOutputStream os = null;
            try {
                bos = new ByteArrayOutputStream();
                os = new ObjectOutputStream(bos);
                os.writeObject(value);
                os.close();
                bos.close();
                rv = bos.toByteArray();
            } catch (IOException e) {
                throw new IllegalArgumentException("Non-serializable object", e);
            } finally {
                close(os);
                close(bos);
            }
            return rv;
        }

        public static Object deserialize(byte[] in) {
            Object rv=null;
            ByteArrayInputStream bis = null;
            ObjectInputStream is = null;
            try {
                if(in != null) {
                    bis=new ByteArrayInputStream(in);
                    is=new ObjectInputStream(bis);
                    rv=is.readObject();
                    is.close();
                    bis.close();
                }
            } catch (IOException e) {
                logger.warn("Caught IOException decoding %d bytes of data",
                        in == null ? 0 : in.length, e);
            } catch (ClassNotFoundException e) {
                logger.warn("Caught CNFE decoding %d bytes of data",
                        in == null ? 0 : in.length, e);
            } finally {
                close(is);
                close(bis);
            }
            return rv;
        }
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                logger.info("Unable to close %s", closeable, e);
            }
        }
    }

}


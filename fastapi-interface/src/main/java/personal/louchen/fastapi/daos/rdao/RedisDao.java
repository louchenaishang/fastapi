package personal.louchen.fastapi.daos.rdao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * redis操作类
 */
public interface RedisDao {

	/**
	 * 检查是否连接成功
	 *
	 * @return
	 */
	public String ping() throws Exception;

	/**
	 * 通过key删除（字节）
	 *
	 * @param key
	 */
	public void del(byte[] key) throws Exception;

	/**
	 * 通过key删除
	 *
	 * @param key
	 */
	public void del(String key) throws Exception;

	/**
	 * 添加key value 并且设置存活时间(byte)
	 *
	 * @param key
	 * @param value
	 * @param liveTime
	 */
	public void set(byte[] key, byte[] value, int liveTime) throws Exception;

	/**
	 * 添加key value 并且设置存活时间
	 *
	 * @param key
	 * @param value
	 * @param liveTime
	 */
	public void set(String key, String value, int liveTime) throws Exception;

	/**
	 * 添加key value
	 *
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) throws Exception;

	/**
	 * 添加key value
	 *
	 * @param key
	 * @param value
	 */
	public void set(String key, ArrayList value) throws Exception;

	/**
	 * 添加key value (字节)(序列化)
	 *
	 * @param key
	 * @param value
	 */
	public void set(byte[] key, byte[] value) throws Exception;

	/**
	 * 获取redis value (String)
	 *
	 * @param key
	 * @return
	 */
	public String get(String key) throws Exception;

	/**
	 * key的失效时间
	 * @param key
	 * @return 秒
	 */
	public Long ttl(String key) throws Exception;

	/**
	 * 加锁
	 * @param key
	 * @param value
	 * @param expireTime
	 * @return
	 */
	public String setex(String key, String value, int expireTime);

	/**
	 * 加锁
	 * @param key
	 * @param value
	 * @return
	 */
	public Long setnx(String key, String value);

	/**
	 * 获取redis value (byte [] )(反序列化)
	 *
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key) throws Exception;

	/**
	 * 通过正则匹配keys
	 *
	 * @param pattern
	 * @return
	 */
	public Set<String> keys(String pattern) throws Exception;

	/**
	 * 检查key是否已经存在
	 *
	 * @param key
	 * @return
	 */
	public boolean exists(String key) throws Exception;

	/**
	 * 清空redis 所有数据
	 *
	 * @return
	 */
	public String flushDB() throws Exception;

	/**
	 * 查看redis里有多少数据
	 */
	public long dbSize() throws Exception;

	/**
	 * 追加key value
	 *
	 * @param key
	 * @param value
	 */
	public void append(String key, String value) throws Exception;

	/**
	 * 追加key value (字节)(序列化)
	 *
	 * @param key
	 * @param value
	 */
	public void append(byte[] key, byte[] value) throws Exception;

	/**
	 * 向一个集合中添加一个元素 key value (字节)(序列化)
	 *
	 * @param key
	 * @param value
	 */
	public void sadd(byte[] key, byte[] value) throws Exception;

	/**
	 * 向一个集合中添加一个元素 key value (字节)(序列化)
	 *
	 * @param key
	 * @param value
	 */
	public void plull(byte[] key, byte[] value) throws Exception;


	public void rpush(byte[] key, byte[] value) throws Exception;

	public List<byte[]> lrange(byte[] key, int start, int end) throws Exception;

	/**
	 * 设置key超时
	 * @param key
	 * @param seconds
	 * @throws Exception
	 * @return
	 */
	public Long expire(String key, int seconds) throws Exception;

	public Long expire(byte[] key, int seconds) throws Exception;

}

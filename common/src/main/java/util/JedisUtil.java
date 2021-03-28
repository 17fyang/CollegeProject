package util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/1/3 16:19
 * @Description:
 */
public class JedisUtil {
    public static int DB = 5;
    private static final JedisPool jedisPool;

    static {
        jedisPool = new JedisPool("127.0.0.1", 6379);
    }


    public static String get(String key) {
        return safeRun((r) -> r.get(key));
    }


    public static String set(String key, String value) {
        return safeRun((r) -> r.set(key, value));
    }


    public static long expire(String key, Integer second) {
        return safeRun((r) -> r.expire(key, second));
    }

    public static void setAdd(String key, String... nums) {
        safeRun((r) -> r.sadd(key, nums));
    }

    public static void setDB(int db) {
        DB = db;
    }

    public static boolean setContains(String key, String value) {
        return safeRun((r) -> r.sismember(key, value));
    }

    public static long del(String key) {
        return safeRun((r) -> r.del(key));
    }

    public static boolean hasKey(String key) {
        return safeRun((r) -> r.exists(key));
    }

    private static <T> T safeRun(SingleRun<T> func) {
        Jedis resource = jedisPool.getResource();
        resource.select(DB);
        T result = func.run(resource);
        resource.close();
        return result;
    }

    interface SingleRun<T> {
        T run(Jedis resource);
    }
}
import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2017/11/10.
 */
public class redisdemo {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.136.120",6379);
        jedis.set("heloword","jedis");
        String value = jedis.get("heloword");
        System.out.println("连接redi获取heloword的key的值ֵ:"+value);
    }

}

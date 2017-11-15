import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 *zhangjp
 * jedis良好调用的风格
 */
public class demoTryCatch {
    private static Logger logger = LoggerFactory.getLogger(demoTryCatch.class);
    public static void main(String[] args) {
        Jedis jedis = null;
        try{
            jedis = new Jedis("192.168.2.101",6379);
            String value = jedis.get("heloword");
            System.out.println("连接redi获取heloword的key的值ֵ:"+value);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
    }

}

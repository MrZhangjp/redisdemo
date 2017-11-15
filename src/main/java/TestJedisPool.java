import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.io.IOException;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhangjp
 * \* Date: 2017/11/15
 * \* Time: 20:42
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class TestJedisPool {
    public static void main(String[] args) {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        JedisPool jedisPool = new JedisPool(poolConfig ,"192.168.2.101" ,6379);
        Jedis jedis = null;
        Pipeline pipeline = null;
        try{
            jedis = jedisPool.getResource();
            pipeline = jedis.pipelined();
            pipeline.del("test");
            pipeline.del("k1");
            pipeline.sync();

            pipeline.set("test1","test1");
            pipeline.incr("counter");
            List<Object> resultList = pipeline.syncAndReturnAll();
            for (Object o :  resultList){
                System.out.println(o);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            if(jedis != null){
                jedis.close();
                try {
                    if(pipeline != null){
                        pipeline.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
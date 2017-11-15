import redis.clients.jedis.Jedis;

import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhangjp
 * \* Date: 2017/11/15
 * \* Time: 20:15
 * \* To change this template use File | Settings | File Templates.
 * \* Description:测试序列化和反序列化
 * \
 */
public class TestSerializer {
    public static void main(String[] args) {
        Jedis jedis = null;
        try {
            ProtostuffSerializer protostuffSerializer = new ProtostuffSerializer();
            jedis = new Jedis("192.168.2.101",6379);
            String key = "club:1";
            Club club = new Club(1,"AC","米兰",new Date(),1);
            //club序列化
            byte[] clubBtyes = protostuffSerializer.serialize(club);
            jedis.set(key.getBytes(),clubBtyes);
            //club反序列化
            byte[] resultsBtyes = jedis.get(key.getBytes());
            Club resultClub = protostuffSerializer.deserialize(resultsBtyes);
            System.out.println(resultClub.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            jedis.close();
        }


    }
}
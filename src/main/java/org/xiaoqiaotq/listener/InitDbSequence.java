package x.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/8/18
 */
@Component
public class InitDbSequence implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private EntityManager em;

    /**
     * e.g. 1 江苏省
     */
    @Value("${x.mcu.regionId}")
    private int regionId;
    /**
     * regionId 后多少位，初始为1
     * init    1 0000 0001
     */
    private int suffixNum=8;

    private static AtomicInteger atomicInteger;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Query nativeQuery = em.createNativeQuery("CREATE TABLE IF NOT EXISTS DISTRIBUTED_SEQUENCE(id INT(11) PRIMARY KEY )");
        nativeQuery.executeUpdate();
        Query getLastMaxId = em.createNativeQuery("select max(i) from(\n" +
                "select max(id) i from app_user where id like :param UNION\n" +
                "select max(region_id) i from region where region_id like :param UNION\n" +
                "select max(id) i from member where id like :param UNION\n" +
                "\n" +
                "select max(id) i from micro_control_unit where id like :param UNION\n" +
                "select max(id) i from recorder where id like :param UNION\n" +
                "select max(id) i from tv_wall where id like :param UNION\n" +
                "select max(id) i from template where id like :param UNION\n" +
                "select max(id) i from log where id like :param UNION\n" +
                "select max(id) i from ip where id like :param UNION\n" +
                "select max(id) i from department where id like :param \n" +
                ") max_tbl");
        getLastMaxId.setParameter("param", generateParam());
        Integer lastMaxId =(Integer) getLastMaxId.getSingleResult();
        if(lastMaxId==null){
            lastMaxId=initId();
        }
        atomicInteger = new AtomicInteger(lastMaxId);
        Query dml = em.createNativeQuery("insert into DISTRIBUTED_SEQUENCE(id) values (?)");
        dml.setParameter(1, lastMaxId);
        dml.executeUpdate();
    }
    private int initId(){
        return (int) (regionId*(Math.pow(10,suffixNum))+1);
    }

    private String generateParam() {
        StringBuffer sb = new StringBuffer();
        sb.append(regionId);
        for (int i = 0; i < suffixNum; i++) {
            sb.append("_");
        }
        return sb.toString();
    }
    public static int getKey() {
        return atomicInteger.incrementAndGet();
    }
}
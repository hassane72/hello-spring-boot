package org.xiaoqiaotq.repository;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xiaoqiaotq.domain.geom.StreetTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/10/15
 */
@Repository
public class StreetRepositoryImpl implements StreetRepositoryCustom {
    @Autowired
    private EntityManager em;

    public void importStreet(List<StreetTest> streetList, String tblName){
        try {
            //create table postgrep
            Query nativeQuery = em.createNativeQuery("CREATE TABLE if not EXISTS "+tblName+" ( LIKE street_test INCLUDING DEFAULTS INCLUDING CONSTRAINTS INCLUDING INDEXES )");
            nativeQuery.executeUpdate();
            //insert data
            Class<StreetTest> streetClass = StreetTest.class;
            Field[] fields = streetClass.getDeclaredFields();
            String columns = Arrays.stream(streetClass.getDeclaredFields()).map(f-> ImprovedNamingStrategy.INSTANCE.columnName(f.getName())).collect(Collectors.joining(",", "(", ")"));
            for (int i = 0; i < streetList.size(); i++) {
                StreetTest street = streetList.get(i);
                String values = Arrays.stream(streetClass.getDeclaredFields()).map(f -> ":" + f.getName()).collect(Collectors.joining(",", "(", ")"));
//
                String sql = "insert into "+ tblName+ " "+ columns+ " values "+ values;
                Query saveQuery = em.createNativeQuery(sql);
                for (int j = 0; j <fields.length; j++) {
                    fields[j].setAccessible(true);
                    Object value = fields[j].get(street);
                    saveQuery.setParameter(fields[j].getName(), value);
                }
                saveQuery.executeUpdate();
                if ( i % 50 == 0 ) {
                    em.flush();
                    em.clear();
                }
            }
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }
    public List<StreetTest> findStreetByTblName(String tblName){
        Query nativeQuery = em.createNativeQuery("SELECT * FROM "+tblName,StreetTest.class);
        nativeQuery.setMaxResults(20);
        nativeQuery.setFirstResult(0);
        return nativeQuery.getResultList();
    }
}

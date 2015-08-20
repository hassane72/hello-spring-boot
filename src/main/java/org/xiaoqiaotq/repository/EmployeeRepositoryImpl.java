package org.xiaoqiaotq.repository;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/7/31
 */
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {
    @Autowired
    private EntityManager em;
    @Override
    public boolean exitsName(String name, Long id) {
        String hql = "SELECT CASE WHEN COUNT(1) > 0 THEN 'true' ELSE 'false' END FROM Employee as e WHERE e.name = :name";
        if(id != null){
            hql += " AND e.id != :id";
        }
        Query query = em.createQuery(hql);
        query.setParameter("name", name);
        if (id != null) {
            query.setParameter("id", id );
        }
        return Boolean.valueOf(String.valueOf(query.getSingleResult()));
    }
}

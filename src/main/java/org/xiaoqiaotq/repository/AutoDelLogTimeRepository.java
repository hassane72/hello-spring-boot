package org.xiaoqiaotq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.xiaoqiaotq.domain.AutoDelLogTimeEntity;
import org.xiaoqiaotq.domain.tree.CustomUserGroup;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/5/19.
 */
@Repository
public interface AutoDelLogTimeRepository extends JpaRepository<AutoDelLogTimeEntity,Long>{
    @Query(" from AutoDelLogTimeEntity t")
    AutoDelLogTimeEntity findTime();
}

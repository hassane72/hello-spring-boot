package org.xiaoqiaotq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xiaoqiaotq.domain.geom.StreetTest;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/10/15
 */
@Repository
public interface StreetRepository extends JpaRepository<StreetTest,Long>,StreetRepositoryCustom {
}

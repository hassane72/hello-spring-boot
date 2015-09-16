package org.xiaoqiaotq.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.xiaoqiaotq.domain.User;
import org.xiaoqiaotq.domain.tree.CustomUserGroup;

import java.util.List;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/5/19.
 */
@Repository
public interface CustomUserGroupRepository extends JpaRepository<CustomUserGroup,Long>,JpaSpecificationExecutor<CustomUserGroup>{

}

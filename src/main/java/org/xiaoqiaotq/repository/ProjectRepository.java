package org.xiaoqiaotq.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.xiaoqiaotq.domain.ManyToMany.Project;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/7/3.
 */
@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {
}

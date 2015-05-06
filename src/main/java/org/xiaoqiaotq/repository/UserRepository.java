package org.xiaoqiaotq.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.xiaoqiaotq.domain.User;

@Repository
public interface UserRepository extends JpaSpecificationExecutor<User>,PagingAndSortingRepository<User, Long> {
	Page<User> findByUsername(String username, Pageable pageable);
	
	@Query("select u from User u where u.username=:username")
	User isExist(@Param("username") String username);
	
	@Query("select u from User u where u.username=:username and u.pass=:pass")
	User isExist(@Param("username") String username, @Param("pass") String pass);
	
	@Modifying
	@Query("update User u set u.pass=?1 where u.id=?2")
	int resetPass(String newPass, Long uid);
}

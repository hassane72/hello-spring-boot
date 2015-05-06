package org.xiaoqiaotq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xiaoqiaotq.domain.User;
import org.xiaoqiaotq.repository.UserRepository;
import org.xiaoqiaotq.util.persistence.DynamicSpecifications;
import org.xiaoqiaotq.util.persistence.SearchFilter;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	public void save(User user){
		userRepository.save(user);
	}
	public boolean isExist(Long openid){
		return userRepository.findOne(openid)!=null;
	}
	public boolean isExist(String username){
		return userRepository.isExist(username)!=null;
	}
	public User login(User user){
		return userRepository.isExist(user.getUsername(),user.getPass());
	}
	public int resetPass(String newPass,Long uid){
		return userRepository.resetPass(newPass, uid);
	}
	public List<User> findAll(){
		return (List<User>) userRepository.findAll();
	}
	public User find(Long id){
		return userRepository.findOne(id);
	}
	public void remove(Long id){
		 userRepository.delete(id);;
	}
	
	public Page<User> queryUser( Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<User> spec = buildSpecification( searchParams);
		return userRepository.findAll(spec, pageRequest);
	}

	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<User> buildSpecification( Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
//		filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<User> spec = DynamicSpecifications.bySearchFilter(filters.values(), User.class);
		return spec;
	}
	
}
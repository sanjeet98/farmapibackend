/**
 * 
 */
package com.secureuser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secureuser.entity.User;

/**
 * @author kmahendr
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	public User findByLoginName(String loginName);
	public User findByUserId(Long userId);
	public List<User> findByRole(String role);

}

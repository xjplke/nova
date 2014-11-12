package me.itsoho.aaa.repo;


import me.itsoho.aaa.model.Radreply;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RadreplyRepository extends CrudRepository<Radreply, Long> {
	@Modifying(clearAutomatically = true)
	@Query("delete from Radreply a where a.username = :username") 
	public void deleteRadreplyByUsername(@Param("username")String account);
}

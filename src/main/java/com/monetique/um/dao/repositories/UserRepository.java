package com.monetique.um.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monetique.um.dao.entities.User;



public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT u FROM User u order by u.userName asc ")
    public List<User> getAllUser();

	
	@Query("SELECT u FROM User u WHERE u.userName=:userName ")
    public User findByuserName(@Param("userName") String userName);
	@Query("SELECT u FROM User u WHERE u.userName=:userName ")
    public User findByuserNameIgnoreCase(@Param("userName") String userName);
    
    @Query("SELECT u FROM User u WHERE u.email=:email ")
    public User findByEmail(@Param("email") String email);

    @Query("SELECT COUNT(u) FROM User u WHERE u.email=:email ")
    public long existsEmail(@Param("email") String email);

    @Query("SELECT COUNT(u) FROM User u WHERE u.userName=:userName ")
    public long existsuserName(@Param("userName") String userName);

    @Query("SELECT COUNT(u) FROM User u WHERE u.email=:email AND u.id<>:id")
    public long existsEmail(@Param("email") String email, @Param("id") Long idUser);

    @Query("SELECT COUNT(u) FROM User u WHERE u.userName=:userName  AND u.id<>:id")
    public long existsuserName(@Param("userName") String userName, @Param("id") Long idUser);
}

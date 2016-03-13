package com.gaohuan.biz.dao;

import com.gaohuan.biz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by gh on 2016/3/4 0004.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * 根据姓名查询用户信息
     *
     * @param username
     * @return
     */
    @Query("select u from User  u where  u.firstname=?1")
    public User findByUsername(String username);

    /**
     * 根据emailAddress查询
     *
     * @param emailAddress
     * @return
     */
    @Query(value = "SELECT * FROM USER WHERE EMAIL_ADDRESS=?1 ", nativeQuery = true)
    public List<User> findByEmailAddress(String emailAddress);

    /**
     * 模糊查询
     *
     * @param name
     * @return
     */
    @Query("select  u from User  u where  u.firstname like %?1")
    List<User> findByNameEndsWith(String name);

    /**
     * 查询
     *
     * @param lastname
     * @param firstname
     * @return
     */
    @Query("select u from User u where u.firstname =:firstname or u.lastname = :lastname")
    List<User> findByLastnameOrFirstname(@Param("lastname") String lastname, @Param("firstname") String firstname);

    @Query("select u from #{#entityName} u where u.lastname=?1")
    List<User> findByLastname(String lastname);
}

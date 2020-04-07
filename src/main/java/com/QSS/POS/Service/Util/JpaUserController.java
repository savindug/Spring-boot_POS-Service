package com.QSS.POS.Service.Util;


import com.QSS.POS.Service.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserController extends JpaRepository<Users, Integer> {

    @Query(value = "select * from users u where (u.username = ?1 and u.pwd = ?2) or (u.email = ?1 and u.pwd = ?2)", nativeQuery = true)
    Users findByUsernameOrEmailAndPwd(String username, String pwd);

    @Query(value = "select * from users u where u.username = ?1 or u.email = ?2", nativeQuery = true)
    Users findByUsernameOrEmail(String username, String email);
}

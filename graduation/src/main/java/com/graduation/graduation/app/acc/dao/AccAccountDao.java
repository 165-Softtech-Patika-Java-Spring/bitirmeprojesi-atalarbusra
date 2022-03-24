package com.graduation.graduation.app.acc.dao;

import com.graduation.graduation.app.acc.entity.AccAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccAccountDao extends JpaRepository<AccAccount, Long> {


    AccAccount findByUserName(String userName);

}

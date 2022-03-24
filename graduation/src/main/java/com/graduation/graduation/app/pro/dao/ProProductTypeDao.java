package com.graduation.graduation.app.pro.dao;

import com.graduation.graduation.app.pro.entity.ProProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProProductTypeDao extends JpaRepository<ProProductType, Long> {

}

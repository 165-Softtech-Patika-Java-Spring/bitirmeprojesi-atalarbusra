package com.graduation.graduation.app.pro.service.entityService;

import com.graduation.graduation.app.gen.service.BaseEntityService;
import com.graduation.graduation.app.pro.dao.ProProductTypeDao;
import com.graduation.graduation.app.pro.entity.ProProductType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProProductTypeEntityService extends BaseEntityService<ProProductType, ProProductTypeDao> {
    public ProProductTypeEntityService(ProProductTypeDao dao) {
        super(dao);
    }

    public void deleteAll(){
        getDao().deleteAll();
    }



}

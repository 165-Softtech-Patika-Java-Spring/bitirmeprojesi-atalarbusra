package com.graduation.graduation.app.acc.service.entityservice;

import com.graduation.graduation.app.acc.dao.AccAccountDao;
import com.graduation.graduation.app.acc.entity.AccAccount;
import com.graduation.graduation.app.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccAccountEntityService extends BaseEntityService<AccAccount, AccAccountDao> {

    public AccAccountEntityService(AccAccountDao dao) {
        super(dao);
    }


    public AccAccount findByUserName(String userName){
        return getDao().findByUserName(userName);
    }

}

package com.graduation.graduation.app.pro.service.entityService;

import com.graduation.graduation.app.gen.service.BaseEntityService;
import com.graduation.graduation.app.pro.dao.ProProductDao;
import com.graduation.graduation.app.pro.dto.ProProductDetails;
import com.graduation.graduation.app.pro.entity.ProProduct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProProductEntityService extends BaseEntityService<ProProduct, ProProductDao> {


    public ProProductEntityService(ProProductDao dao) {
        super(dao);
    }

    public List<ProProduct> findProductByTypeId(Long productTypeId){
        return getDao().findByProductTypeId(productTypeId);
    }
    public List<ProProduct> findProductWithPrice(double minPrice, double maxPrice){
        return getDao().findProductWithPrice(minPrice, maxPrice);
    }

    public List<ProProductDetails> getProductDetails(){
        return getDao().getProductDetails();
    }
}

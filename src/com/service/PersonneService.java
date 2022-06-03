package com.service;

import com.dao.DaoObject;
import com.model.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "PersonneService")
public class PersonneService {


    private DaoObject daoObject;

    @Autowired
    public PersonneService(DaoObject dao) {
        this.daoObject=dao;
    }

    public void setDaoObject(DaoObject daoObject) {
        this.daoObject = daoObject;
    }

    public void  save(BaseModel baseModel)
    {
        System.out.println(daoObject);

        daoObject.save(baseModel);
    }
}

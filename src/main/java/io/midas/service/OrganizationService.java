package io.midas.service;

import io.midas.model.Organization;
import io.midas.repository.OrganizationDao;
import io.midas.repository.OrganizationDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {
    @Autowired
    OrganizationDao dao;

    public boolean save(Organization o)
    {
        return dao.save(o);
    }

}

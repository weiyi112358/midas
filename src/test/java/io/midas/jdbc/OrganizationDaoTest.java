package io.midas.jdbc;

import io.midas.model.Organization;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class OrganizationDaoTest {

    private OrganizationDao organizationDao;

    @Test
    public void getOrganizationsTest()
    {
        organizationDao = new OrganizationDao();
        List<Organization> organizations = organizationDao.getOrganizations();
        int expectedNum = 1;
        Assert.assertEquals(expectedNum,organizations.size());

    }

    @Before
    public void setUp()
    {
        organizationDao = new OrganizationDao();
        Organization s = new Organization();
        s.setName("testStudent");

        organizationDao.save(s);
    }

    @After
    public  void tearDown()
    {
        organizationDao = new OrganizationDao();
        Organization s = new Organization();
        s.setName("testStudent");

        organizationDao.delete(s);

    }


}

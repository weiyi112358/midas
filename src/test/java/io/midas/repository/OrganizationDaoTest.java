package io.midas.repository;
import io.midas.ApplicationBoot;
import io.midas.model.Organization;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBoot.class)
public class OrganizationDaoTest {
    private static OrganizationDao organizationDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeClass
    public static void init(){
        organizationDao = new OrganizationDaoImpl();
    }

    @Before
    public void setUp()
    {
        Organization s = new Organization();
        s.setName("test");
        s.setArea("china");
        organizationDao.save(s);
    }

    @Test
    public void getOrganizations()
    {
        List<Organization> organizations = organizationDao.getOrganizations();
        int expected = 2;
        Assert.assertEquals(expected,organizations.size());
    }

    @Test
    public void update()
    {
        Organization res = organizationDao.getOrganizationByName("test");
        res.setArea("united state");
        organizationDao.update(res);
        Organization newRes = organizationDao.getOrganizationByName("test");
        Assert.assertEquals(newRes.getArea(),"united state");
    }

//    @Test
//    public void testManyToOne()
//    {
//        List<Organization> organizations = organizationDao.getOrganizationsWithChildren();
//        int expected = 1;
//        Assert.assertEquals(expected,organizations.size());
//
//
//    }

    @After
    public void delete()
    {
        organizationDao.delete("test");
    }

}

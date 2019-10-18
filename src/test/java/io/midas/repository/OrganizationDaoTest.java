package io.midas.repository;
import io.midas.model.Organization;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


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
        int expected = 1;
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

    @After
    public void delete()
    {
        organizationDao.delete("test");
    }

}

package practice_05;

import org.demo.home_03.model.*;
import org.demo.home_03.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.junit.*;

import java.util.List;

/**
 * @author dshvedchenko on 5/30/16.
 */
public class TestOrm1 {
    static SessionFactory sessionFactory;
    Session session;

    @BeforeClass
    public static void InitSessions() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Before
    public void init() {
        session = sessionFactory.openSession();
    }

    @After
    public void disposeTest() {
        session.close();
    }

    @AfterClass
    public static void releaseFactory() {
        sessionFactory.close();
    }


    @Test
    public void testSimpleSqlQUery() {
        List arrayList = session.createSQLQuery("select * from OFFICES").addScalar("OFFICE", StandardBasicTypes.INTEGER).list();
        System.out.println(arrayList);
    }

    @Test
    public void testDecardForTwoTables() {
        List list = session.createSQLQuery("SELECT  {o.*}, {f.*} FROM OFFICES o, OFFICES f")
                .addEntity("o", Office.class)
                .addEntity("f", Office.class)
                .list();

        Assert.assertEquals(25, list.size());
    }


    public static class OfficeDTO {
        private int id;
        private String city;

        public String getCity() {
            return city;
        }
    }

    @Test
    public void testDTOGetAndTrans() {
        List<OfficeDTO> list = session.createSQLQuery("select office as id, CITY as city FROM OFFICES")
                .setResultTransformer(Transformers.aliasToBean(OfficeDTO.class))
                .list();

          Assert.assertNotNull(list);
        Assert.assertEquals("New York", list.get(0).getCity());


    }

    @Test
    public void simpleCriteriaTest() {
        List<Office> list = session.createCriteria(Office.class)
                .add(Restrictions.like("city", "Chi%"))
                .add(Restrictions.between("office", 1, 16))
                .list();

        Assert.assertNotNull(list);
        Assert.assertEquals("Chicago", list.get(0).getCity());
    }

    @Test
    public void simpleCriteriaTestSqlRestriction() {
        List<Office> list = session.createCriteria(Office.class)
                .add(Restrictions.sqlRestriction("lower(CITY) like lower(?)", "Chi%", StandardBasicTypes.STRING)  )
                .add(Restrictions.between("office", 1, 16))
                .list();

        Assert.assertNotNull(list);
        Assert.assertEquals("Chicago", list.get(0).getCity());
    }

}

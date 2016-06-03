package practice_05;

import org.demo.home_03.dao.DaoFacade;
import org.demo.home_03.dao.SessionHolder;
import org.demo.home_03.model.*;
import org.demo.home_03.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.junit.*;

import java.sql.SQLException;
import java.util.List;

/**
 * @author dshvedchenko on 5/30/16.
 */
public class TestOrm1 {
    static SessionFactory sessionFactory;
    SessionHolder sessionHolder;
    DaoFacade daoFacade;

    @BeforeClass
    public static void initSuite() throws SQLException {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @AfterClass
    public static void closeSuite() {
        sessionFactory.close();
    }

    @Before
    public void initRes() throws SQLException {
        sessionHolder = new SessionHolder(sessionFactory.openSession());
        daoFacade = new DaoFacade(sessionHolder);
    }

    @After
    public void releaseRes() throws SQLException {
        sessionHolder.obtainSession().close();
    }

    @Test
    public void testSimpleSqlQUery() {
        List arrayList = sessionHolder.obtainSession().createSQLQuery("select * from OFFICES").addScalar("OFFICE", StandardBasicTypes.INTEGER).list();
        System.out.println(arrayList);
    }

    @Test
    public void testDecardForTwoTables() {
        List list = sessionHolder.obtainSession().createSQLQuery("SELECT  {o.*}, {f.*} FROM OFFICES o, OFFICES f")
                .addEntity("o", Office.class)
                .addEntity("f", Office.class)
                .list();

        Assert.assertEquals(25, list.size());
    }

    @Test
    public void testDTOGetAndTrans() {
        List<OfficeDTO> list = sessionHolder.obtainSession().createSQLQuery("select office as id, CITY as city FROM OFFICES")
                .setResultTransformer(Transformers.aliasToBean(OfficeDTO.class))
                .list();

          Assert.assertNotNull(list);
        Assert.assertEquals("New York", list.get(0).getCity());


    }

    @Test
    public void simpleCriteriaTest() {
        List<Office> list = sessionHolder.obtainSession().createCriteria(Office.class)
                .add(Restrictions.like("city", "Chi%"))
                .add(Restrictions.between("office", 1, 16))
                .list();

        Assert.assertNotNull(list);
        Assert.assertEquals("Chicago", list.get(0).getCity());
    }

    @Test
    public void simpleCriteriaTestSqlRestriction() {
        List<Office> list = sessionHolder.obtainSession().createCriteria(Office.class)
                .add(Restrictions.sqlRestriction("lower(CITY) like lower(?)", "Chi%", StandardBasicTypes.STRING)  )
                .add(Restrictions.between("office", 1, 16))
                .list();

        Assert.assertNotNull(list);
        Assert.assertEquals("Chicago", list.get(0).getCity());
    }

    public static class OfficeDTO {
        private int id;
        private String city;

        public String getCity() {
            return city;
        }
    }

}

package practice_05;

import org.demo.home_03.model.Office;
import org.demo.home_03.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.*;

import java.util.List;

/**
 * @author dshvedchenko on 5/30/16.
 */
public class Test119_148 {
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
    public void simpleCriteriaTest() {
        List<Office> list = session.createCriteria(Office.class)
                .add(Restrictions.like("city", "Chi%"))
                .add(Restrictions.between("office", 1, 16))
                .list();

        Assert.assertNotNull(list);
        Assert.assertEquals("Chicago", list.get(0).getCity());
    }


}

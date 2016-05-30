package home_03;

import org.demo.home_03.model.Office;
import org.demo.home_03.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    public void getOfficesTest() {
        List<Office> office = session.createCriteria(Office.class).list();
        System.out.println(office);
    }

}

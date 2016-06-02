package practice_05;

import org.demo.home_03.dao.OfficeDao;
import org.demo.home_03.dao.SalesrepDao;
import org.demo.home_03.dto.OfficeDTO;
import org.demo.home_03.dto.SalesrepDTO;
import org.demo.home_03.model.Office;
import org.demo.home_03.model.Salesrep;
import org.demo.home_03.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.*;

import java.math.BigDecimal;
import java.util.Date;
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
    public void simpleP119() {
        List<OfficeDTO> list = new OfficeDao(session).getQP119();

        Assert.assertNotNull(list);
        Assert.assertEquals("New York", list.get(0).getCity());
    }

    @Test
    public void simpleP1201() {
        List<OfficeDTO> list = new OfficeDao(session).getQP1201();
        Assert.assertNotNull(list);
        Assert.assertEquals("New York", list.get(0).getCity());
    }

    @Test
    public void simpleP1202() {
        List<OfficeDTO> list = new OfficeDao(session).getQP1202();
        Assert.assertNotNull(list);
        Assert.assertEquals("Atlanta", list.get(0).getCity());
    }

    @Test
    public void simpleP122() {
        List list = new SalesrepDao(session).getQP122();
        Assert.assertNotNull(list);
        Object[] r = (Object[]) list.get(0);
        Assert.assertEquals("Dan Roberts", (String)r[0] );
    }

    @Test
    public void simpleP123() {
        List list = new SalesrepDao(session).getQP123();
        Assert.assertNotNull(list);
        Object[] r = (Object[]) list.get(0);
        Assert.assertEquals("Nancy Angelli", (String)r[0] );
        Assert.assertEquals( "300000.00", ((BigDecimal)r[1]) .toString() );
    }

    @Test
    public void simpleP1241() {
        List list = new SalesrepDao(session).getQP1241();
        Assert.assertNotNull(list);
        Object r =  list.get(0);
        Assert.assertEquals("289353.200000", ((BigDecimal)r).toString() );
    }

    @Test
    public void simpleP1242() {
        List list = new SalesrepDao(session).getQP1242();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        Object[] r =  (Object[])list.get(0);
        Assert.assertEquals("Dan Roberts", ((String)r[0]) );
        Assert.assertEquals("2004-10-20", ((Date)r[1]).toString() );
    }

    @Test
    public void simpleP1243() {
        List list = new SalesrepDao(session).getQP1243();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
        Object[] r =  (Object[])list.get(0);
        Assert.assertEquals("Dan Roberts", ((String)r[0]) );
        Assert.assertEquals("300000.00", ((BigDecimal)r[1]).toString() );
        Assert.assertTrue(104 == (Integer)r[2] );
    }
}


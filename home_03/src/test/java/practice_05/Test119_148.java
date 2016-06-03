package practice_05;

import org.demo.home_03.dao.OfficeDao;
import org.demo.home_03.dao.OrderDao;
import org.demo.home_03.dao.ProductDao;
import org.demo.home_03.dao.SalesrepDao;
import org.demo.home_03.dto.OfficeDTO;
import org.demo.home_03.dto.OrderDTO;
import org.demo.home_03.dto.SalesrepDTO;
import org.demo.home_03.model.Office;
import org.demo.home_03.model.Order;
import org.demo.home_03.model.Salesrep;
import org.demo.home_03.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.*;
import static org.junit.Assert.*;

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

        assertNotNull(list);
        assertEquals("New York", list.get(0).getCity());
    }

    @Test
    public void simpleP1201() {
        List<OfficeDTO> list = new OfficeDao(session).getQP1201();
        assertNotNull(list);
        assertEquals("New York", list.get(0).getCity());
    }

    @Test
    public void simpleP1202() {
        List<OfficeDTO> list = new OfficeDao(session).getQP1202();
        assertNotNull(list);
        assertEquals("Atlanta", list.get(0).getCity());
    }

    @Test
    public void simpleP122() {
        List list = new SalesrepDao(session).getQP122();
        assertNotNull(list);
        Object[] r = (Object[]) list.get(0);
        assertEquals("Dan Roberts", (String)r[0] );
    }

    @Test
    public void simpleP123() {
        List list = new SalesrepDao(session).getQP123();
        assertNotNull(list);
        Object[] r = (Object[]) list.get(0);
        assertEquals("Nancy Angelli", (String)r[0] );
        assertEquals( "300000.00", ((BigDecimal)r[1]) .toString() );
    }

    @Test
    public void simpleP1241() {
        List list = new SalesrepDao(session).getQP1241();
        assertNotNull(list);
        Object r =  list.get(0);
        assertEquals("289353.200000", ((BigDecimal)r).toString() );
    }

    @Test
    public void simpleP1242() {
        List list = new SalesrepDao(session).getQP1242();
        assertNotNull(list);
        assertTrue(list.size() > 0);
        Object[] r =  (Object[])list.get(0);
        assertEquals("Dan Roberts", ((String)r[0]) );
        assertEquals("2004-10-20", ((Date)r[1]).toString() );
    }

    @Test
    public void simpleP1243() {
        List list = new SalesrepDao(session).getQP1243();
        assertNotNull(list);
        assertTrue(list.size() > 0);
        Object[] r =  (Object[])list.get(0);
        assertEquals("Dan Roberts", ((String)r[0]) );
        assertEquals("300000.00", ((BigDecimal)r[1]).toString() );
        assertTrue(104 == (Integer)r[2] );
    }

    @Test
    public void simpleP126() {
        List list = new OfficeDao(session).getQP126();
        assertNotNull(list);
        assertTrue(list.size() > 0);
        Object[] r =  (Object[])list.get(0);
        assertEquals("New York", ((String)r[0]) );
        assertEquals("Eastern", ((String)r[1]) );
        assertEquals("117637.00", ((BigDecimal)r[2]).toString() );
    }

    @Test
    public void simpleP1262() {
        List list = new ProductDao(session).getQP1262();
        assertNotNull(list);
        assertTrue(list.size() > 0);
        Object[] r =  (Object[])list.get(0);
        assertEquals("ACI", ((String)r[0]) );
        assertEquals("41001", ((String)r[1]) );
        assertEquals("Size 1 Wiget", ((String)r[2]) );
        assertEquals("15235.00", ((BigDecimal)r[3]).toString() );
    }

    @Test
    public void sompleP135() {
        List<Order> orders = new OrderDao(session).getSimpleP135();
        assertNotNull(orders);
        assertTrue(orders.size() > 0);
        assertEquals(Integer.valueOf(112961), orders.get(0).getOrderNum());
    }

    @Test
    public void simpleP1362() {
        List<OrderDTO> orders = new OrderDao(session).getSimple1362();
        assertNotNull(orders);
        assertTrue(orders.size() > 0);
        assertEquals(Integer.valueOf(112987), orders.get(0).getOrderNum());
        assertEquals(new BigDecimal("27500.00"), orders.get(0).getAmount());
    }

    @Test
    public void simpleP1373() {
        List<SalesrepDTO> salesrepDTOs = new SalesrepDao(session).getP1373();
        assertNotNull(salesrepDTOs);
        assertTrue(salesrepDTOs.size() > 0);
        assertEquals("Dan Roberts", salesrepDTOs.get(0).getName());
    }

    @Test
    public void simpleP138() {
        List<OrderDTO> orderDTOs = new OrderDao(session).getP138();
        assertNotNull(orderDTOs);
        assertTrue(orderDTOs.size() > 0);
        assertEquals(Integer.valueOf(112961), orderDTOs.get(0).getOrderNum());
    }


}


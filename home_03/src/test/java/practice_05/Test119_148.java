package practice_05;

import org.demo.home_03.dao.DaoFacade;
import org.demo.home_03.dao.SessionHolder;
import org.demo.home_03.dto.OfficeDTO;
import org.demo.home_03.dto.OrderDTO;
import org.demo.home_03.dto.SalesrepDTO;
import org.demo.home_03.model.Order;
import org.demo.home_03.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author dshvedchenko on 5/30/16.
 */
public class Test119_148 {
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
    public void simpleP119() {
        List<OfficeDTO> list = daoFacade.getOfficeDao().getQP119();

        assertNotNull(list);
        assertEquals("New York", list.get(0).getCity());
    }

    @Test
    public void simpleP1201() {
        List<OfficeDTO> list = daoFacade.getOfficeDao().getQP1201();
        assertNotNull(list);
        assertEquals("New York", list.get(0).getCity());
    }

    @Test
    public void simpleP1202() {
        List<OfficeDTO> list = daoFacade.getOfficeDao().getQP1202();
        assertNotNull(list);
        assertEquals("Atlanta", list.get(0).getCity());
    }

    @Test
    public void simpleP122() {
        List list = daoFacade.getSalesrepDao().getQP122();
        assertNotNull(list);
        Object[] r = (Object[]) list.get(0);
        assertEquals("Dan Roberts", (String) r[0]);
    }

    @Test
    public void simpleP123() {
        List list = daoFacade.getSalesrepDao().getQP123();
        assertNotNull(list);
        Object[] r = (Object[]) list.get(0);
        assertEquals("Nancy Angelli", (String) r[0]);
        assertEquals("300000.00", ((BigDecimal) r[1]).toString());
    }

    @Test
    public void simpleP1241() {
        List list = daoFacade.getSalesrepDao().getQP1241();
        assertNotNull(list);
        Object r = list.get(0);
        assertEquals("289353.200000", ((BigDecimal) r).toString());
    }

    @Test
    public void simpleP1242() {
        List list = daoFacade.getSalesrepDao().getQP1242();
        assertNotNull(list);
        assertTrue(list.size() > 0);
        Object[] r = (Object[]) list.get(0);
        assertEquals("Dan Roberts", ((String) r[0]));
        assertEquals("2004-10-20", ((Date) r[1]).toString());
    }

    @Test
    public void simpleP1243() {
        List list = daoFacade.getSalesrepDao().getQP1243();
        assertNotNull(list);
        assertTrue(list.size() > 0);
        Object[] r = (Object[]) list.get(0);
        assertEquals("Dan Roberts", ((String) r[0]));
        assertEquals("300000.00", ((BigDecimal) r[1]).toString());
        assertTrue(104 == (Integer) r[2]);
    }

    @Test
    public void simpleP126() {
        List list = daoFacade.getOfficeDao().getQP126();
        assertNotNull(list);
        assertTrue(list.size() > 0);
        Object[] r = (Object[]) list.get(0);
        assertEquals("New York", ((String) r[0]));
        assertEquals("Eastern", ((String) r[1]));
        assertEquals("117637.00", ((BigDecimal) r[2]).toString());
    }

    @Test
    public void simpleP1262() {
        List list = daoFacade.getProductDao().getQP1262();
        assertNotNull(list);
        assertTrue(list.size() > 0);
        Object[] r = (Object[]) list.get(0);
        assertEquals("ACI", ((String) r[0]));
        assertEquals("41001", ((String) r[1]));
        assertEquals("Size 1 Wiget", ((String) r[2]));
        assertEquals("15235.00", ((BigDecimal) r[3]).toString());
    }

    @Test
    public void sompleP135() {
        List<Order> orders = daoFacade.getOrderDao().getSimpleP135();
        assertNotNull(orders);
        assertTrue(orders.size() > 0);
        assertEquals(Integer.valueOf(112961), orders.get(0).getOrderNum());
    }

    @Test
    public void simpleP1362() {
        List<OrderDTO> orders = daoFacade.getOrderDao().getSimple1362();
        assertNotNull(orders);
        assertTrue(orders.size() > 0);
        assertEquals(Integer.valueOf(112987), orders.get(0).getOrderNum());
        assertEquals(new BigDecimal("27500.00"), orders.get(0).getAmount());
    }

    @Test
    public void simpleP1373() {
        List<SalesrepDTO> salesrepDTOs = daoFacade.getSalesrepDao().getP1373();
        assertNotNull(salesrepDTOs);
        assertTrue(salesrepDTOs.size() > 0);
        assertEquals("Dan Roberts", salesrepDTOs.get(0).getName());
    }

    @Test
    public void simpleP138() {
        List<OrderDTO> orderDTOs = daoFacade.getOrderDao().getP138();
        assertNotNull(orderDTOs);
        assertTrue(orderDTOs.size() > 0);
        assertEquals(Integer.valueOf(112961), orderDTOs.get(0).getOrderNum());
    }


}


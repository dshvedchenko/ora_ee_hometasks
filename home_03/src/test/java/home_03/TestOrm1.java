package home_03;

import org.demo.home_03.dao.DaoFacade;
import org.demo.home_03.dao.SessionHolder;
import org.demo.home_03.model.*;
import org.demo.home_03.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.junit.*;

import java.math.BigDecimal;
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
    public void getOfficeTest() {
        Office office = sessionHolder.obtainSession().get(Office.class, 11);
        Assert.assertNotNull(office);
        Assert.assertEquals("New York", office.getCity());
        Assert.assertEquals("Eastern", office.getRegion());
        Assert.assertNotNull(office.getManager());
        Assert.assertEquals("Sam Clark", office.getManager().getName());
    }

    @Test
    public void getSalesRep() {
        Salesrep salesrep = sessionHolder.obtainSession().get(Salesrep.class, 101);
        Assert.assertNotNull(salesrep);
        Assert.assertEquals("Dan Roberts", salesrep.getName());
    }

    @Test
    public void getProduct() {
        Product product = sessionHolder.obtainSession().get(Product.class, new ProductPK("ACI", "41001"));
        Assert.assertNotNull(product);
    }

    @Test
    public void getOrder() {
        Order order = sessionHolder.obtainSession().get(Order.class, 112961);
        Assert.assertNotNull(order);
        Assert.assertEquals("2007-12-17 00:00:00.0", order.getOrderDate().toString());
        Assert.assertEquals("J.P. Sinclair", order.getCust().getCompany());
    }

    @Test
    public void getCustomer() {
        Customer customer = sessionHolder.obtainSession().get(Customer.class, 2101);
        Assert.assertNotNull(customer);
        Assert.assertEquals("Jones Mfg.", customer.getCompany());
        Assert.assertEquals(0, customer.getCreditLimit().compareTo(new BigDecimal(65000)));
    }

    @Test
    public void getSalesRepsInNewYork() {
        Criteria criteria = sessionHolder.obtainSession().createCriteria(Salesrep.class);
        criteria.add(Restrictions.eq("repOffice", sessionHolder.obtainSession().get(Office.class, 11)));
        List<Salesrep> salesrepList = criteria.list();
        Assert.assertEquals(2, salesrepList.size());


    }

    @Test
    public void testSimpleSqlQUery() {
        List arrayList = sessionHolder.obtainSession().createSQLQuery("select * from OFFICES").addScalar("OFFICE", StandardBasicTypes.INTEGER).list();
        System.out.println(arrayList);
    }
}

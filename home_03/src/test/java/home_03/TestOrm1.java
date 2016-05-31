package home_03;

import org.demo.home_03.model.*;
import org.demo.home_03.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.junit.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public void getOfficeTest() {
        Office  office = session.get(Office.class,11);
        Assert.assertNotNull(office);
        Assert.assertEquals("New York", office.getCITY());
        Assert.assertEquals("Eastern", office.getREGION());
        Assert.assertNotNull(office.getMGR());
        Assert.assertEquals("Sam Clark", office.getMGR().getNAME());
    }

    @Test
    public void getSalesRep() {
        Salesrep salesrep = session.get(Salesrep.class, 101);
        Assert.assertNotNull(salesrep);
        Assert.assertEquals("Dan Roberts", salesrep.getNAME());
    }

    @Test
    public void getProduct() {
        Product product = session.get(Product.class, new ProductPK("ACI","41001"));
        Assert.assertNotNull(product);
    }

    @Test
    public void getOrder() {
        Order order = session.get(Order.class, 112961);
        Assert.assertNotNull(order);
        Assert.assertEquals("2007-12-17",order.getORDER_DATE());
        Assert.assertEquals("J.P. Sinclair", order.getCUST().getCOMPANY());
    }

    @Test
    public void getCustomer() {
        Customer customer = session.get(Customer.class,2101);
        Assert.assertNotNull(customer);
        Assert.assertEquals("Jones Mfg.", customer.getCOMPANY());
        Assert.assertEquals(0,customer.getCREDIT_LIMIT().compareTo(new BigDecimal(65000)));
    }

    @Test
    public void getSalesRepsInNewYork() {
        Criteria criteria = session.createCriteria(Salesrep.class);
        criteria.add(Restrictions.eq("REP_OFFICE", session.get(Office.class, 11) ));
        List<Salesrep> salesrepList = criteria.list();
        Assert.assertEquals(2, salesrepList.size());


    }

    @Test
    public void testSimpleSqlQUery() {
        List arrayList = session.createSQLQuery("select * from OFFICES").addScalar("OFFICE", StandardBasicTypes.INTEGER).list();
        System.out.println(arrayList);
    }
}

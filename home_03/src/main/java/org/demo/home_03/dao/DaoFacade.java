package org.demo.home_03.dao;

/**
 * @author dshvedchenko on 6/3/16.
 */
public class DaoFacade {
    final SessionHolder sessionHolder;
    private OfficeDao officeDao;
    private OrderDao orderDao;
    private ProductDao productDao;
    private SalesrepDao salesrepDao;

    public DaoFacade(SessionHolder sessionHolder) {
        this.sessionHolder = sessionHolder;
    }

    public OfficeDao getOfficeDao() {
        if (officeDao == null) officeDao = new OfficeDao(sessionHolder);
        return officeDao;
    }

    public OrderDao getOrderDao() {
        if (orderDao == null) orderDao = new OrderDao(sessionHolder);
        return orderDao;
    }

    public ProductDao getProductDao() {
        if (productDao == null) productDao = new ProductDao(sessionHolder);
        return productDao;
    }

    public SalesrepDao getSalesrepDao() {
        if (salesrepDao == null) salesrepDao = new SalesrepDao(sessionHolder);
        return salesrepDao;
    }


}

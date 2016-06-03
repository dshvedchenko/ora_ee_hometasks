package org.demo.home_03.dao;

import org.demo.home_03.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author dshvedchenko on 6/3/16.
 */
public class SessionHolder {

    private Session session;

    public SessionHolder(Session session) {
        this.session = session;
    }

    public Session obtainSession() {
        return session;
    }
}

package mantis.appmanager;

import mantis.model.AccountData;
import mantis.model.Accounts;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Accounts accounts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<AccountData> result = session.createQuery("from AccountData").list();
        for ( AccountData account : result ) {
            System.out.println(account);
        }
        session.getTransaction().commit();
        session.close();
        return new Accounts(result);
    }
}

package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.UserData;

import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper(ApplicationManager applicationManager) {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public int userId(String username) {
        int id = 0;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery(String.format( "from UserData where username='%s'", username )).list();
        for ( UserData user : result ) {
            id = user.getId();
        }
        session.getTransaction().commit();
        session.close();
        return id;
    }
}

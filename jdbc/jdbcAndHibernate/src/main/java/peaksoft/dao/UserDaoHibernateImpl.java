package peaksoft.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import peaksoft.model.User;
import peaksoft.util.Util;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try {
            Session session = Util.getSession().openSession();
            session.beginTransaction();
            session.getTransaction().commit();
            session.close();
            System.out.println("table created successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = Util.getSession().openSession();
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Успешное удаление всех пользователей");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try {
            Session session = Util.getSession().openSession();
            session.beginTransaction();
            session.save(user);
            System.out.println(new User(name, lastName, age));
            session.getTransaction().commit();
            session.close();
            System.out.println("Users successfully saved!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = Util.getSession().openSession();
            session.beginTransaction();
            session.createSQLQuery("delete from users where id = ?").executeUpdate();
            session.getTransaction().commit();
            System.out.println(id + " " + "User have been deleted with id");
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try {
            Session session = Util.getSession().openSession();
            session.beginTransaction();
            userList = session.createQuery("from User").list();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = Util.getSession().openSession();
            session.beginTransaction();
            Query query = session.createQuery("delete from User");
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Cleaned");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
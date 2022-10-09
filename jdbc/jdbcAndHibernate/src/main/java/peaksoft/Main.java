package peaksoft;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
//        userDaoJdbc.createUsersTable();
//        userDaoJdbc.saveUser("Elaman", "Rakhmanov", (byte) 22);
//        userDaoJdbc.saveUser("Bektur", "Mergenov", (byte) 22);
//        userDaoJdbc.saveUser("Asyl", "Avazov", (byte) 21);
//        userDaoJdbc.saveUser("Maksat", "Kanybekov", (byte) 21);
//        userDaoJdbc.getAllUsers();
//        userDaoJdbc.cleanUsersTable();
//        userDaoJdbc.dropUsersTable();

        UserDao userDao = new UserDaoHibernateImpl();

        //userDao.createUsersTable();
        userDao.saveUser("Elaman","Rakhmanov",(byte) 23);

        userDao.saveUser("Елон","Маск",(byte) 40);

        userDao.saveUser("Нурбек","Исламов",(byte) 21);

        //Util.buildSessionFactory();
    }
}

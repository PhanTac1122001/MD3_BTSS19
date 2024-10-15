package com.btss19.model.dao.user;

import com.btss19.model.entity.Role;
import com.btss19.model.entity.User;
import com.btss19.model.entity.constants.RoleName;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean register(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(findByRoleName(RoleName.USER));
        user.setStatus(true);
        user.setRoles(roles);
        user.setPassWord(BCrypt.hashpw(user.getPassWord(), BCrypt.gensalt(10)));
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public User fingUserByEmail(String email) {
        Session session = sessionFactory.openSession();

        try {
            String hql = "from User u where u.email=: _email";
            return session.createQuery(hql, User.class).setParameter("_email", email).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    private Role findByRoleName(RoleName roleName) {
        Session session = sessionFactory.openSession();

        try {
            String hql = "from Role r where r.roleName = :_roleName";
            return session.createQuery(hql, Role.class).
                    setParameter("_roleName", roleName).getSingleResult();
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}

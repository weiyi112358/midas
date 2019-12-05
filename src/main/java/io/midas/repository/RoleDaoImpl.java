package io.midas.repository;

import io.midas.model.Role;
import io.midas.model.Student;
import io.midas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Stack;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Override
    public Role getRoleByName(String name){
        String hql = "FROM Role as s where s.name=:name";
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            Query<Role> query = session.createQuery(hql);
            query.setParameter("name",name);
            return query.uniqueResult();
        }
    }
}

package com.infoshareacademy.dreamteam.dao;

import com.infoshareacademy.dreamteam.cdi.RoleType;
import com.infoshareacademy.dreamteam.domain.entity.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Stateless
public class RoleDaoBean {

    @PersistenceContext
    private EntityManager entityManager;


    public Optional<Role> findByRoleType(RoleType roleType) {
        Query query = entityManager.createNamedQuery("Role.findByRoleType");
        query.setParameter("roleType", roleType);
        return query.getResultList().stream().findFirst();
    }

}

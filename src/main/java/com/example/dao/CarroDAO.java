package com.example.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.domain.Carro;

public class CarroDAO implements ICarroDAO {
    @Override
    public void save(Carro carro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(carro);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Carro carro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(carro);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Carro carro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(carro);
        transaction.commit();
        session.close();
    }

    @Override
    public Carro findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Carro> cq = cb.createQuery(Carro.class);
        Root<Carro> root = cq.from(Carro.class);
        cq.select(root).where(cb.equal(root.get("id"), id));
        TypedQuery<Carro> query = session.createQuery(cq);
        Carro carro = null;
        try {
            carro = query.getSingleResult();
            Hibernate.initialize(carro.getAcessorios());
        } catch (Exception e) {
            System.out.println("Nenhum resultado encontrado para a query");
        } finally {
            session.close();
        }
        return carro;
    }

    @Override
    public List<Carro> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Carro> cq = cb.createQuery(Carro.class);
        Root<Carro> root = cq.from(Carro.class);
        cq.select(root);
        TypedQuery<Carro> query = session.createQuery(cq);
        List<Carro> carros = query.getResultList();
        session.close();
        return carros;
    }
}
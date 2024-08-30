package com.example.dao;

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
        Carro carro = session.get(Carro.class, id);
        session.close();
        return carro;
    }
}
package com.example.computerormthymeleaf.service;

import com.example.computerormthymeleaf.model.Computer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class ComputerService implements IComputerService { //hibernate
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Computer> findAll() {
        String queryStr = "SELECT c FROM Computer AS c";
        TypedQuery<Computer> query = entityManager.createQuery(queryStr, Computer.class);
        return query.getResultList();
    }

    @Override
    public void save(Computer computer) {
        Transaction transaction = null;
        Computer origin;
        if (computer.getId() == 0) {
            origin = new Computer();
        } else {
            origin = findById(computer.getId());
        }
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            origin.setComputerCode(computer.getComputerCode());
            origin.setComputerName(computer.getComputerName());
            origin.setManufacturer(computer.getManufacturer());
            origin.setPrice(computer.getPrice());
            origin.setImg(computer.getImg());
            session.saveOrUpdate(origin);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Computer findById(int id) {
        String queryStr = "SELECT c FROM Computer AS c WHERE c.id = :id";
        TypedQuery<Computer> query = entityManager.createQuery(queryStr, Computer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void update(int id, Computer computer) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();

            // Retrieve the existing Computer object by its ID
            Computer existingComputer = session.get(Computer.class, id);

            if (existingComputer != null) {
                // Update the fields of the existing Computer with the new values
                existingComputer.setComputerCode(computer.getComputerCode());
                existingComputer.setComputerName(computer.getComputerName());
                existingComputer.setManufacturer(computer.getManufacturer());
                existingComputer.setPrice(computer.getPrice());
                existingComputer.setImg(computer.getImg());

                // Save the updated Computer object
                session.update(existingComputer);

                // Commit the transaction
                transaction.commit();
            } else {
                // If no computer is found, you might want to handle this case (e.g., throw an exception)
                System.out.println("Computer with ID " + id + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    @Override
    public void remove(int id) {
        Computer computer = findById(id);
        if (computer != null) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.remove(computer);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

    @Override
    public Computer findByName(String name) {
        String queryStr = "SELECT c FROM Computer AS c WHERE c.computerName = :name";
        TypedQuery<Computer> query = entityManager.createQuery(queryStr, Computer.class);
        query.setParameter("name", name);
        List<Computer> results = query.getResultList();

        if (results.isEmpty()) {
            return null; // or throw an exception if you prefer
        } else {
            return results.get(0); // Return the first match (assuming names are unique)
        }
    }

    @Override
    public List<Computer> findByPriceRange(double minPrice, double maxPrice) {
        String queryStr = "SELECT c FROM Computer AS c WHERE c.price BETWEEN :minPrice AND :maxPrice";
        TypedQuery<Computer> query = entityManager.createQuery(queryStr, Computer.class);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        return query.getResultList();
    }


}

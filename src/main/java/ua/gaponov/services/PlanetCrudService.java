package ua.gaponov.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.gaponov.entities.Planet;
import ua.gaponov.utils.HibernateUtil;

public class PlanetCrudService {

  public String create(Planet planet) {
    if (planet == null) {
      return "";
    }

    try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
      Transaction transaction = session.beginTransaction();
      session.persist(planet);
      transaction.commit();
      return planet.getId();
    }
  }

  public Planet get(String id) {
    try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
      Query<Planet> query = session.createQuery(
          "from Planet where id = :id",
          Planet.class
      );
      query.setParameter("id", id);
      return query.stream().findFirst().orElse(null);
    }
  }

  public String update(Planet planet) {
    if (planet == null) {
      return "";
    }

    try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession();) {
      Transaction transaction = session.beginTransaction();
      session.merge(planet);
      transaction.commit();
      return planet.getId();
    }
  }

  public String delete(Planet planet) {
    if (planet == null) {
      return "";
    }

    try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession();) {
      Transaction transaction = session.beginTransaction();
      session.remove(planet);
      transaction.commit();
      return planet.getId();
    }
  }
}

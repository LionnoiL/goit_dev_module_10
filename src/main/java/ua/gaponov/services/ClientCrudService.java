package ua.gaponov.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.gaponov.entities.Client;
import ua.gaponov.utils.HibernateUtil;

public class ClientCrudService {

  public long create(Client client) {
    if (client == null) {
      return -1;
    }

    try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
      Transaction transaction = session.beginTransaction();
      session.persist(client);
      transaction.commit();
      return client.getId();
    }
  }

  public Client get(long id) {
    try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
      Query<Client> query = session.createQuery(
          "from Client where id = :id",
          Client.class
      );
      query.setParameter("id", id);
      return query.stream().findFirst().orElse(null);
    }
  }

  public long update(Client client) {
    if (client == null) {
      return -1;
    }

    try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession();) {
      Transaction transaction = session.beginTransaction();
      session.merge(client);
      transaction.commit();
      return client.getId();
    }
  }

  public long delete(Client client) {
    if (client == null) {
      return -1;
    }

    try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession();) {
      Transaction transaction = session.beginTransaction();
      session.remove(client);
      transaction.commit();
      return client.getId();
    }
  }
}

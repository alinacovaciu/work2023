package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public abstract class WithSessionAndTransaction<T> {

	private static SessionFactory sessionFactory;
	private T returnValue;

	public T getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(T returnValue) {
		this.returnValue = returnValue;
	}

	public T run() {
		try {
			if (sessionFactory == null) {
				setUp();
			}

			Session session = sessionFactory.openSession();
			session.getTransaction().begin();

			doAction(session);

			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	public abstract void doAction(Session session);

	protected void setUp() throws Exception {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
			e.printStackTrace();
		}
	}
}

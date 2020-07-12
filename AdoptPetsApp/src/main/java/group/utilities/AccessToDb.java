package group.utilities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import group.exception.ErrorInAccessToDb;

public class AccessToDb {
	private static final String PERSISTENCE_UNIT_NAME = "AdoptPets";
	private static EntityManagerFactory factory;
	private static EntityManager em;

	// Create Entity manager for access to DB
	public static EntityManager createFactory() throws ErrorInAccessToDb {
		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();
			getFactory();
			return em;
		} catch (Exception e) {
			throw new ErrorInAccessToDb("Error In Access to DB");
		}
	}

	// Open transaction to DB
	public static EntityManager getFactory() throws ErrorInAccessToDb {
		try {
			if (!em.getTransaction().isActive())
				em.getTransaction().begin();
			return em;
		} catch (Exception e) {
			throw new ErrorInAccessToDb("Error In Access to DB");
		}
	}

	// Commit data
	public static void commitFactory() throws ErrorInAccessToDb {
		try {
			if (em.getTransaction().isActive()) {
				em.getTransaction().commit();
			}
		} catch (Exception e) {
			throw new ErrorInAccessToDb("Error In Access to DB");
		}
	}

	// Close Entity manager
	public static void closeFactory() {
		if (em != null)
			em.close();
	}
}

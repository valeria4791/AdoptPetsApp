package group.dataAccess;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import group.entities.PetLove;
import group.exception.ErrorInProcessPetLove;
import group.utilities.AccessToDb;

public class PetLoveDAO {
	public PetLoveDAO() {
	}

	// Create new pet love
	public PetLove create(PetLove newPetLove) throws ErrorInProcessPetLove {
		try {
			// access to DB
			EntityManager em = AccessToDb.createFactory();

			em.persist(newPetLove);

			// Save and close
			AccessToDb.commitFactory();
			return newPetLove;
		} catch (Exception e) {
			throw new ErrorInProcessPetLove("Error in process pet love data");
		} finally {
			AccessToDb.closeFactory();
		}
	}

	// Update pet love
	public PetLove update(PetLove updatePetLove) throws ErrorInProcessPetLove {
		try {
			// access to DB
			EntityManager em = AccessToDb.createFactory();

			em.merge(updatePetLove);

			// Save and close
			AccessToDb.commitFactory();
			return updatePetLove;
		} catch (Exception e) {
			throw new ErrorInProcessPetLove("Error in process pet love data");
		} finally {
			AccessToDb.closeFactory();
		}
	}

	// Delete pet love
	public void remove(int idPPetLove) throws ErrorInProcessPetLove {
		try {
			// access to DB
			EntityManager em = AccessToDb.createFactory();

			em.remove(getPetLove(idPPetLove));

			// Save and close
			AccessToDb.commitFactory();
		} catch (Exception e) {
			throw new ErrorInProcessPetLove("Error in process pet love data");
		} finally {
			AccessToDb.closeFactory();
		}
	}

	// Get pet love
	public PetLove getPetLove(int idPetLove) throws ErrorInProcessPetLove {
		try {
			// access to DB
			EntityManager em = AccessToDb.createFactory();

			PetLove petLove = em.find(PetLove.class, idPetLove);

			return petLove;
		} catch (Exception e) {
			throw new ErrorInProcessPetLove("Error in process pet love data");
		} finally {
			AccessToDb.closeFactory();
		}
	}
}

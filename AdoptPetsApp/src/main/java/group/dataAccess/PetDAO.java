package group.dataAccess;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import group.entities.Pet;
import group.exception.ErrorInProcessPetData;
import group.utilities.AccessToDb;
import group.utilities.Category;
import group.utilities.Gender;
import group.utilities.PetSize;

public class PetDAO {
	public PetDAO() {
	}

	// Create new pet
	public Pet create(Pet newPet) throws ErrorInProcessPetData {
		try {
			// access to DB
			EntityManager em = AccessToDb.createFactory();

			em.persist(newPet);

			// Save and close
			AccessToDb.commitFactory();
			return newPet;
		} catch (Exception e) {
			throw new ErrorInProcessPetData("Error in process pet data");
		} finally {
			AccessToDb.closeFactory();
		}
	}

	// Update pet
	public Pet update(Pet updatePet) throws ErrorInProcessPetData {
		try {
			// access to DB
			EntityManager em = AccessToDb.createFactory();

			em.merge(updatePet);

			// Save and close
			AccessToDb.commitFactory();
			return updatePet;
		} catch (Exception e) {
			throw new ErrorInProcessPetData("Error in process pet data");
		} finally {
			AccessToDb.closeFactory();
		}
	}

	// Delete pet
	public void remove(int idPPet) throws ErrorInProcessPetData {
		try {
			// access to DB
			EntityManager em = AccessToDb.createFactory();

			em.remove(getPet(idPPet));

			// Save and close
			AccessToDb.commitFactory();
		} catch (Exception e) {
			throw new ErrorInProcessPetData("Error in process pet data");
		} finally {
			AccessToDb.closeFactory();
		}
	}

	// Get pet
	public Pet getPet(int idPPet) throws ErrorInProcessPetData {
		try {
			// access to DB
			EntityManager em = AccessToDb.createFactory();

			Pet pet = em.find(Pet.class, idPPet);

			// Close
			return pet;
		} catch (Exception e) {
			throw new ErrorInProcessPetData("Error in process pet data");
		} finally {
			AccessToDb.closeFactory();
		}
	}

	// Get all pets in DB
	public List<Pet> getAllPets() throws ErrorInProcessPetData {

		try {
			// access to DB
			EntityManager em = AccessToDb.createFactory();

			List<Pet> results = em.createNamedQuery("AllPets", Pet.class).getResultList();

			return results;
		} catch (Exception e) {
			throw new ErrorInProcessPetData("Error in process pet data");
		} finally {
			AccessToDb.closeFactory();
		}
	}

	// Get pets by criteria
	public List<Pet> getPetsByCriteria(Category petCategory, int petAge, PetSize petSize, Gender petGender)
			throws ErrorInProcessPetData {
		try {
			// access to DB
			EntityManager em = AccessToDb.createFactory();

			// Create Dynamic Query
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Pet> query = cb.createQuery(Pet.class);
			Root<Pet> pet = query.from(Pet.class);

			// Create criteria for where
			List<Predicate> predicates = new ArrayList<>();

			// Category
			if (petCategory != null)
				predicates.add(cb.and(cb.equal(pet.get("category"), petCategory)));

			// Pet Age
			if (petAge != 0)
				predicates.add(cb.and(cb.equal(pet.get("petAge"), petAge)));

			// Pet size
			if (petSize != null)
				predicates.add(cb.and(cb.equal(pet.get("petSize"), petSize)));

			// Pet Gender
			if (petGender != null)
				predicates.add(cb.and(cb.equal(pet.get("gender"), petGender)));

			// Set where
			query.select(pet).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

			return em.createQuery(query).getResultList();
		} catch (

		Exception e) {
			throw new ErrorInProcessPetData("Error in process pet data");
		} finally {
			AccessToDb.closeFactory();
		}
	}
}

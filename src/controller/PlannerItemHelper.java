package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.PlannerItem;

public class PlannerItemHelper {
	
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Planner");


	public void deleteItem(PlannerItem toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PlannerItem> typedQuery = em.createQuery("select pi from PlannerItem pi where pi.course = :selectedCourse and pi.assignment = :selectedAssignment", PlannerItem.class);
		
		// Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedCourse", toDelete.getCourse());
		typedQuery.setParameter("selectedAssignment", toDelete.getAssignment());
		
		// we only want one result
		typedQuery.setMaxResults(1);
		
		// get the result and save it into a new list item 
		PlannerItem result = typedQuery.getSingleResult();
		
		// remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}


	public void insertItem(PlannerItem pi) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(pi);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<PlannerItem> showAllItems() {
		EntityManager em = emfactory.createEntityManager();
		List<PlannerItem> allItems = em.createQuery("SELECT i FROM PlannerItem i").getResultList();
		return allItems;
	}

	public List<PlannerItem> searchForItemByCourse(String courseName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PlannerItem> typedQuery = em.createQuery("select pi from PlannerItem pi where pi.course = :selectedCourse", PlannerItem.class);
		typedQuery.setParameter("selectedCourse", courseName);
		
		List<PlannerItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public List<PlannerItem> searchForItemByAssignment(String assignmentName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction();
		TypedQuery<PlannerItem> typedQuery = em.createQuery("select pi from PlannerItem pi where pi.assignment = :selectedAssignment", PlannerItem.class);
		typedQuery.setParameter("selectedAssignment", assignmentName);
		
		List<PlannerItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public PlannerItem searchForItemById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();;
		PlannerItem found = em.find(PlannerItem.class, idToEdit);
		em.close();
		return found;
	}

	public void updateAssignment(PlannerItem toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public void cleanUp() {
		// close it
		emfactory.close();
	}

}

import java.util.List;
import java.util.Scanner;

import controller.PlannerItemHelper;
import model.PlannerItem;

public class StartProgram {

	static Scanner in = new Scanner(System.in);
	static PlannerItemHelper lih = new PlannerItemHelper();
	
	public static void runMenu() {
		// setup run value to be true so it runs the menu the first time at least
		boolean runAgain = true;
		
		// print the welcome message to the user
		System.out.println("--- Welcome to your school planner! ---");
		
		// while loop to run the menu
		while(runAgain) {
			System.out.println("*  Select an option:");
			System.out.println("*  1 -- Add an item");
			System.out.println("*  2 -- Edit an item");
			System.out.println("*  3 -- Delete an item");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Exit the planner");
			System.out.print("*  Your selection: ");
			
			// gather the selection from the user using the scanner
			int selection = in.nextInt();
			in.nextLine();

			// determine what to do given the users answer
			if (selection == 1) {
				addAnItem();
			} else if (selection == 2) {
				editAnItem();
			} else if (selection == 3) {
				deleteAnItem();
			} else if (selection == 4) {
				viewTheList();
			} else {
				// clean the program properly and display a goodbye message
				lih.cleanUp();
				System.out.println("   Have a nice day!   ");
				runAgain = false;
			}
		}
	}
	
	public static void main(String[] args) {
		// call the runMenu class to display the menu
		runMenu();
	}

	private static void viewTheList() {
		List<PlannerItem> allItems = lih.showAllItems();
		
		for(PlannerItem singleItem : allItems) {
			System.out.println(singleItem.returnPlannerDetails());
		}
	}

	private static void deleteAnItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter the class to delete: ");
		String course = in.nextLine();
		System.out.print("Enter the assignment to delete: ");
		String assignment = in.nextLine();
		PlannerItem toDelete = new PlannerItem(course, assignment);
		lih.deleteItem(toDelete);
	}

	private static void editAnItem() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Course");
		System.out.println("2 : Search by Assignment");
		int searchBy = in.nextInt();
		in.nextLine();
		List<PlannerItem> foundItems;
		if (searchBy == 1) {
			System.out.print("Enter the course name: ");
			String courseName = in.nextLine();
			foundItems = lih.searchForItemByCourse(courseName);
		} else {
			System.out.print("Enter the assignment: ");
			String assignmentName = in.nextLine();
			foundItems = lih.searchForItemByAssignment(assignmentName);
		}

		if (!foundItems.isEmpty()) {
			System.out.println("Found Results.");
			for (PlannerItem l : foundItems) {
				System.out.println(l.getId() + " : " + l.returnPlannerDetails());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			PlannerItem toEdit = lih.searchForItemById(idToEdit);
			System.out.println("Retrieved " + toEdit.getAssignment() + " from " + toEdit.getCourse());
			System.out.println("1 : Update Course");
			System.out.println("2 : Update Assignment");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Course: ");
				String newCourse = in.nextLine();
				toEdit.setCourse(newCourse);
			} else if (update == 2) {
				System.out.print("New Assignment: ");
				String newAssignment = in.nextLine();
				toEdit.setAssignment(newAssignment);
			}

			lih.updateAssignment(toEdit);

		} else {
			System.out.println("---- No results found");
		}
	}

	private static void addAnItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter a class: ");
		String store = in.nextLine();
		System.out.print("Enter an assignment: ");
		String item = in.nextLine();
		PlannerItem toAdd = new PlannerItem(store, item);
		lih.insertItem(toAdd);
	}
}

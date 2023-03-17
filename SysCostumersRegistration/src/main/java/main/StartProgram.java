package main;

import java.util.List;
import java.util.Scanner;

import dao.CustomerDao;
import dao.PhoneDao;
import model.Customer;
import model.Phone;

public class StartProgram {

	// Creating Scanner Object to read input
	private static Scanner inputString = new Scanner(System.in);
	private static String readText;

	public static void main(String[] args) {
		
		int option = 0; // Creating option integer variable
        
		do { // Do - While loop
			
			menu(); //Display Menu
	        option = getOnlyNumber();
	        System.out.println();
			 
            // Switch case
			switch (option) {
				
				// Customer manager  ------------------------------------
				case 1: // Display Customer
					display();
					break;
	
				case 2: // Search By Id Customer
					searchById();
					break;

				case 3: // Search By Name Customer
					searchByName();
					break;
					
				case 4: // Add customer
					add();
					break;
					
				case 5: // Update Customer
					update();
					break;
					
				case 6: // Delete Customer
					delete();
					break;
					
				// Phone manager  ------------------------------------
				case 7: // Display Customer phone
					displayPhone();
					break;
				
				case 8: // Add Customer phone
					addPhone();
					break;
				
				case 9: // Delete Customer phone
					deletePhone();
					break;
					
					
				case 10: // Exit
					// to prevent enter in default option
					break;
	
				// If none above case executes
				default:
					// Print statement
					System.out.println("\n  --  Invalid input  --\n");
					
					break;
			}
			
			pause();
			clearConsole();
			
		} while (option != 10); // Checking condition
		System.out.println("xx  Program finished  xx");
		System.exit(0);
	}
	
	
	// Display Menu  /////////////////////////////////////////////////////////
	public static void menu() {
		// Printing statements displaying menu on console
		System.out.println("##  Customer Registration System  ##");
		System.out.println();
		System.out.println("- MENU");
		System.out.println("1: Display Customer");
		System.out.println("2: Search Customer by id");
		System.out.println("3: Search Customer by name");
		System.out.println("4: Add Customer");
		System.out.println("5: Update Customer");
		System.out.println("6: Delete Customer");
		System.out.println("");
		System.out.println("7: Display Customer phone");
		System.out.println("8: Add Customer phone");
		System.out.println("9: Delete Customer phone");
		System.out.println("");
		System.out.println("10: Exit program");
		System.out.print("Enter your selection : ");
	}
	
	// Get Only Number - to prevent null point exception and alphabetical entry /
	static int getOnlyNumber() {
		int intNumber;
		readText = inputString.nextLine(); // Read input data		
		readText = readText.replaceAll("[^0-9]", "");  // Filter only the numbers
		readText = readText.substring(0, Math.min(readText.length(), 9) );// Get the first 9 characters so you don't overflow the integer 2147483647(999999999)
		intNumber = (readText.isEmpty()) ? 0 : Integer.parseInt(readText); //	Convert string to int, if empty returns 0
		return intNumber;
	}
	
	// Display Customer  /////////////////////////////////////////////////////
	public static void display() {
		System.out.println("##  Display customer list  ##\n");
		try {
			CustomerDao dao = new CustomerDao();
			List<Customer> customers = dao.list();
			for (Customer customer : customers) {
				System.out.println(customer);
				System.out.println("----------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Search By Id  /////////////////////////////////////////////////////////
	static void searchById(){
		System.out.println("##  Search customer by id  ##\n");
		System.out.print("What is the customer id number? \nType 0 to abort or customer Id: ");
		int i = getOnlyNumber();
		if (i == 0) {
			return;
		}
		try {
			CustomerDao dao = new CustomerDao();
			Customer customer = dao.searchById(Integer.valueOf(i).longValue());
			if (customer.getId()==null){
				System.out.println("This customer not exist!");
				return;
			}
			System.out.println(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	static boolean searchByIdPrintName(int i){
		try {
			CustomerDao dao = new CustomerDao();
			Customer customer = dao.searchById(Integer.valueOf(i).longValue());
			if (customer.getId()==null){
				System.out.println("This customer not exist!");
				return false;
			}
			System.out.println("Customer: "+customer.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	// Search By Name  ///////////////////////////////////////////////////////
	static void searchByName(){
		System.out.println("##  Search customer by name  ##\n");
		try {
			System.out.print("What is the customer part name? \nType part name: ");
			
			CustomerDao dao = new CustomerDao();
			List<Customer> customers = dao.searchByNameList(inputString.nextLine());
			if (customers.isEmpty()){
				System.out.println("There is no customer with this name!");
				return;
			}
			for (Customer customer : customers) {
				System.out.println(customer);
				System.out.println("----------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Add Customer  /////////////////////////////////////////////////////////
		static void add() {
			System.out.println("##  Add customer  ##\n");
			
	        System.out.print("What is the customer name? ");
	        String name = inputString.nextLine();
	        
	        System.out.print("What is the customer email? ");
	        String email = inputString.nextLine();
	        
	        // Create record object and pass constructor
	        // parameters.
	        CustomerDao dao = new CustomerDao();
			Customer customer = new Customer();
			customer.setName(name);
			customer.setEmail(email);
			dao.add(customer);
			System.out.println("New customer added!");
		}
	
	// Update  ///////////////////////////////////////////////////////////////
	static void update(){
		System.out.println("##  Update customer  ##\n");
		System.out.print("What is the Customer id number to update? \nType 0 to abort or customer Id: ");
		try {
			int i = getOnlyNumber();
			if (i == 0) {
				System.out.println("\n  Update action aborted!");
				return;
			}
			CustomerDao dao = new CustomerDao();
			Customer customer = dao.searchById(Integer.valueOf(i).longValue());
			
			// Check if customer exist
			if (customer.getId()==null) {
				System.out.println("\n  There is no customer with that id!");
				return;
			}
			
			System.out.println("Type enter to skip or new name to update "+customer.getName()+" : ");
			String name = inputString.nextLine();
			
			System.out.println("Type enter to skip or new email to update "+customer.getEmail()+" : ");
	        String email = inputString.nextLine();
	        
	        customer.setName((name.intern() != "".intern())? name : customer.getName());
			customer.setEmail((email.intern() != "".intern())? email : customer.getEmail());
			dao.update(customer);
			
			System.out.println("\n  Successful customer update!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Delete  ///////////////////////////////////////////////////////////////
	static void delete(){
		System.out.println("##  Delete customer by id  ##\n");
        System.out.print("What is the customer id number to delete?");
        int i =getOnlyNumber();
        
        CustomerDao dao = new CustomerDao();
        
        try {
			Customer customer = dao.searchById(Integer.valueOf(i).longValue());
			if (customer.getId()==null){
				System.out.println("This customer not exist!");
				return;
			}
			System.out.println("Customer: "+customer.getName());
			
			dao.delete(Integer.valueOf(i).longValue());
			System.out.println("\n  Successful customer delete!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// Phone --------------------------------
	// Display phone
	static void displayPhone() {
		try {

			System.out.print("What is the customer id number to display phone? ");
			int i = getOnlyNumber();
			
			if (searchByIdPrintName(i) == false) {
				return;
			}
			
			PhoneDao dao = new PhoneDao();
			List<Phone> phones = dao.listByCustomerId(Integer.valueOf(i).longValue());
			for (Phone phone : phones) {
				System.out.println(phone.toStringSimple());
				System.out.println("----------------------------------------");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Add phone
	static void addPhone() {

		try {

			System.out.print("What is the customer id number to add phone? ");
			int i = getOnlyNumber();
			
			if (searchByIdPrintName(i) == false) {
				return;
			}		
			System.out.print("What is the new phone number? ");
	        String phone = inputString.nextLine();
	        
	        System.out.print("What is the new phone type?(home, office, cell) ");
	        String type = inputString.nextLine();
	        
	        Phone p = new Phone();
			p.setNumber(phone);
			p.setType(type);
			p.setCustomerId(Integer.valueOf(i).longValue());
			
			PhoneDao dao = new PhoneDao();
			dao.add(p);
			System.out.println("New phone added!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// Delete phone
	static void deletePhone() {
		try {

			System.out.print("What is the customer id number to delete phone? ");
			int i = getOnlyNumber();
			
			if (searchByIdPrintName(i) == false) {
				return;
			}
			
			System.out.print("What is the id phone to delete? ");
	        i = getOnlyNumber();
	        
	        PhoneDao dao = new PhoneDao();
	        Phone phone = dao.searchById(Integer.valueOf(i).longValue());
	        if(phone.getId() == null) {
	        	System.out.println("  number not found\n");
	        	return;
	        }

	        dao.delete(Integer.valueOf(i).longValue());
			System.out.println("The phone "+phone.getNumber()+" has been deleted!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Clear Console - trick to clear the console ////////////////////////////
	public final static void clearConsole() {
	for (int i = 0; i < 100; ++i)  
	       System.out.println();
	}

	// Pause Console - trick to pause the console ////////////////////////////
	public final static void pause() {
		System.out.println("\nType enter to continue!");
		readText = inputString.nextLine();		
	}

}

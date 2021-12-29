import java.sql.SQLException;
import java.util.Scanner;

public class ATMBank {

	Scanner sc = new Scanner(System.in);
	ConnectToDataBase ctD;

	// Constructor
	public ATMBank() throws SQLException {
		ctD = new ConnectToDataBase(); // We are creating object
	}

	public void Login_Customer() throws SQLException  {
		System.out.print("Enter customer name: ");
		String name = sc.next();
		System.out.print("Enter customer password: ");
		String password = sc.next();
		boolean flag = ctD.login(name, password);
		if (flag) {
			System.out.println("Login is successful!");
			display_Menu();
		} else {
			System.out.println("Login failed, try again");
			Login_Customer();
		}
	}

	public void display_Menu() throws SQLException {
		System.out.println("******************Menu*******************");
		System.out.println("1. Check Balance");
		System.out.println("2. Withdraw Money");
		System.out.println("3. Deposit Money");
		System.out.println("4. Logout");
		System.out.println("*****************************************");
		System.out.print("Select your choice: ");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			check_Balance();
			break;
		case 2:
			withdraw_Money();
			break;
		case 3:
			deposit_Money();
			break;
		case 4:
			log_out();
			break;
		default:
			System.out.println("Please select valid choice 1-4");
			break;
		}
	}

	public void check_Balance() throws SQLException {
		int balance = ctD.getBalance(ctD.CustomerID);
		System.out.println("Your total balance in your Account is: " + balance);
		display_Menu();
	}

	public void withdraw_Money() throws SQLException {
		System.out.println("Enter amount you want to withdraw: ");
		int amount = sc.nextInt();
		boolean flag = ctD.withDrawMoney(ctD.CustomerID, amount);
		if (flag) {
			System.out.println("Take your money: " + amount);
			display_Menu();
		} else {
			System.out.println("Not enough money in your account");
			display_Menu();
		}
	}

	public void deposit_Money() throws SQLException {
		System.out.println("Enter amount to deposit:");
		int amount = sc.nextInt();
		boolean flag = ctD.depositMoney(ctD.CustomerID, amount);
		if (flag) {
			System.out.println("Succesfully deposit money.");
			display_Menu();
		}
	}

	public void log_out() {
		System.out.println("Goodbye!");
		System.exit(0);
	}

	public static void main(String[] args) throws SQLException {
		ATMBank at1 = new ATMBank();
		at1.Login_Customer();
	}
}

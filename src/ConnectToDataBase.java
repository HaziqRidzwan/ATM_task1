import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToDataBase {

	Statement stmt = null;
	ResultSet rs = null;
	Connection con = null;
	int CustomerID = 0;
	int Balance_Money = 0;

	// constructor--> special function whose name is same as class name and have no
	// return type

	public ConnectToDataBase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "Sayasukamakan1@");
			System.out.println("Success!!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean login(String name, String password) throws SQLException {
		stmt = con.createStatement();
		rs = stmt.executeQuery(
				"select * from customer where CustomerName= '" + name + "' and CustomerPassword= '" + password + "'");
		if (rs.next()) {
			CustomerID = rs.getInt("CustomerID");
			return true;
		} else {
			return false;
		}
	}

	public int getBalance(int id) throws SQLException {
		rs = stmt.executeQuery("select BalanceAmount from Account where CustomerID=" + id + "");
		if (rs.next()) {
			Balance_Money = rs.getInt("BalanceAmount");
			return Balance_Money;
		} else {
			return 0;
		}
	}

	public boolean withDrawMoney(int id, int amount) throws SQLException {
		if (amount > Balance_Money) {
			return false;
		} else {
			stmt.executeUpdate(
					"update Account set BalanceAmount=" + (Balance_Money - amount) + " where customerid=" + id);
			return true;
		}
	}

	public boolean depositMoney(int id, int amount) throws SQLException {

		int a = stmt.executeUpdate(
				"update Account set BalanceAmount=" + (Balance_Money + amount) + " where customerid=" + id);
		if (a == 1) {
			return true;
		} else {
			return false;
		}

	}

}

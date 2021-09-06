import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2ConnectionTests {


	public static void main(String[] args) throws Exception {
		Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "root", "root");
		String schema = connection.getSchema();
		if (!"PUBLIC".equals(schema)){
			throw new Exception("connection was not successful");
		}
		System.out.println(schema);




	}

}

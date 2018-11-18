package person;

import java.sql.*;
import java.util.*;

public class PersonSQLiteImpl implements PersonDao {

    private Connection conn;

    public PersonSQLiteImpl( String dbFile ) {
        // open a connection with proper jdbc url
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
        } catch (SQLException | ClassNotFoundException e) {
            throw new Error("Unable to open SQLite file: " + dbFile, e);
        }
    }


    @Override
    public List<Person> listAll() {
        String query = "SELECT * FROM persons";
        List<Person> persons = new ArrayList<>();
        try {
            // get all persons and assign each to a list
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                Person p = new Person();
                p.setId(rs.getInt("id"));
                p.setFirstName(rs.getString("firstname"));
                p.setLastName(rs.getString("lastname"));
                p.setAge(rs.getInt("age"));
                persons.add(p);
            }
        } catch (SQLException e) {
            throw new Error("Unable to execute query: " + query, e);
        }

        return persons;
    }

}

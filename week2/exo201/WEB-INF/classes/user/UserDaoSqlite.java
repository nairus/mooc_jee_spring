package user;

import java.sql.*;

public class UserDaoSqlite implements UserDao {

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new Error(e);
        }
    }

    protected Connection conn;
    public UserDaoSqlite( String userFilePath ) throws SQLException {
        // initialize a connection.
        String jdbcUrl = "jdbc:sqlite:" + userFilePath;
        conn = DriverManager.getConnection(jdbcUrl);
    }

    @Override
    public void add(User user, String password) throws SQLException {
        // define query
        String query = "INSERT INTO users (`firstname`, `lastname`, `email`, `password`)" +
                    "VALUES (?, ?, ?, ?)";

        // prepare statement
        PreparedStatement pst = this.conn.prepareStatement(query);

        try {
            // set parameters
            pst.setString(1, user.getFirstname());
            pst.setString(2, user.getLastname());
            pst.setString(3, user.getEmail());
            pst.setString(4, password);

            // execute query
            int affectedLines = pst.executeUpdate();
        } catch (SQLException e) {
            // re-throw the exception.
            throw e;
        } finally {
            // free statement resource in any case.
            pst.close();
        }
    }

    @Override
    public void update(User user, String password) throws SQLException {
        // define query
        String query = "UPDATE users SET " +
                        "`firstname` = ?, `lastname` = ?, " +
                        "`email` = ?, `password` = ?" +
                    "WHERE id = ?";

        // prepare statement
        PreparedStatement pst = this.conn.prepareStatement(query);

        try {
            // set parameters
            pst.setString(1, user.getFirstname());
            pst.setString(2, user.getLastname());
            pst.setString(3, user.getEmail());
            pst.setString(4, password);
            pst.setLong(5, user.getId());

            // execute query
            int affectedLines = pst.executeUpdate();
        } catch (SQLException e) {
            // re-throw the exception.
            throw e;
        } finally {
            // free statement resource in any case.
            pst.close();
        }
    }

    @Override
    public User find(long id) throws SQLException {
        // Define query
        String query = "SELECT * FROM users WHERE id = ?";

        // prepare statement
        PreparedStatement pst = this.conn.prepareStatement(query);

        // set parameter
        pst.setLong(1, id);

        // execute the query and populate the user
        User user = this.populateUser(pst.executeQuery());

        // free the statement
        pst.close();

        return user;
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        // Define query
        String query = "SELECT * FROM users WHERE email = ?";

        // prepare statement
        PreparedStatement pst = this.conn.prepareStatement(query);

        // set parameter
        pst.setString(1, email);

        // execute the query and populate the user
        User user = this.populateUser(pst.executeQuery());

        // free the statement
        pst.close();

        return user;
    }

    @Override
    public long checkPassword(String email, String password) throws SQLException {
        // Define query
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        // prepare statement
        PreparedStatement pst = this.conn.prepareStatement(query);

        // set parameters
        pst.setString(1, email);
        pst.setString(2, password);

        ResultSet result = pst.executeQuery();
        User user = this.populateUser(result);

        // free the statement
        pst.close();

        // user not found
        if (null == user) {
            return -1;
        }

        return 1;
    }

    @Override
    public void delete(long id) throws SQLException {
        // Define query
        String query = "DELETE FROM users WHERE id = ?";

        // prepare statement
        PreparedStatement pst = this.conn.prepareStatement(query);

        // set parameter
        pst.setLong(1, id);

        // execute statement
        pst.executeUpdate();

        // free the statement
        pst.close();
    }

    @Override
    public long exists(String email) throws SQLException {
        User user = this.findByEmail(email);

        // user not found
        if (null == user) {
            return -1;
        }

        return 1;
    }

    private User populateUser(ResultSet result) throws SQLException {
        if (!result.next()) {
            return null;
        }

        // create the user
        User user = new User();
        user.setId(result.getLong("id"));
        user.setFirstname(result.getString("firstname"));
        user.setLastname(result.getString("lastname"));
        user.setEmail(result.getString("email"));

        // free the resultset
        result.close();

        return user;
    }

}

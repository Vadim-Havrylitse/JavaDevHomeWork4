package databaseconnector;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MySQLConnector implements DatabaseConnector {
    private String url;
    private String username;
    private String password;

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}

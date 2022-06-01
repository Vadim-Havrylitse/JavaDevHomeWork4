package databaseconnector;

import dbenum.DBEnum;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnector {
    Connection getConnection() throws SQLException;

    static DatabaseConnector initDB(DBEnum db){
        return AbstractDatabaseConnector.initDbWithConfigAndMigrate(db);
    }



}

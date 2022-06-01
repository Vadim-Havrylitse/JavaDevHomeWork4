package databaseconnector;

import dbenum.DBEnum;
import org.flywaydb.core.Flyway;
import util.ReadConfigDb;

class AbstractDatabaseConnector {

    private AbstractDatabaseConnector() {
    }

    protected static DatabaseConnector initDbWithConfigAndMigrate(DBEnum db) {
        DatabaseConnector result;
        switch (db) {
            case MYSQL:
                MySQLConnector mySQLConnector = reedConfigFile(db, new MySQLConnector());
                doDatabaseMigrate(mySQLConnector.getUrl(), mySQLConnector.getUsername(), mySQLConnector.getPassword());
                result = mySQLConnector;
                break;
            case H2:
                H2Connector h2Connector = reedConfigFile(db, new H2Connector());
                doDatabaseMigrate(h2Connector.getUrl(), null, null);
                result = h2Connector;
                break;
            default:
                throw new RuntimeException("Your input database not exist with this configuration!");
        }
        return result;
    }


    private static <R extends DatabaseConnector> R reedConfigFile (DBEnum db, R dbConnectorIml){
        ReadConfigDb<R> readMySqlConfigDb = new ReadConfigDb<>(db);
        return readMySqlConfigDb.getObjectFromConfigFile(dbConnectorIml);
    }

    private static void doDatabaseMigrate (String url, String username, String password){
        Flyway flyway = Flyway.configure().dataSource(url, username, password).load();
        flyway.migrate();
    }
}

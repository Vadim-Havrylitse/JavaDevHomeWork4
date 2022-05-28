package databaseconnector;

import dbenum.DBEnum;
import util.ReadConfigDb;

class AbstractDatabaseConnector {

    private AbstractDatabaseConnector() {
    }

    protected static DatabaseConnector initDbWithConfig(DBEnum db) {

        switch (db) {
            case MYSQL:
                return reedConfigFile(db, new MySQLConnector());
            case H2:
                return reedConfigFile(db, new H2Connector());
        }
        throw new RuntimeException("Your input database not exist with this configuration!");
    }


    private static <R extends DatabaseConnector> DatabaseConnector reedConfigFile (DBEnum db, R dbConnectorIml){
        ReadConfigDb<R> readMySqlConfigDb = new ReadConfigDb<>(db);
        return (DatabaseConnector)readMySqlConfigDb.getObjectFromConfigFile(dbConnectorIml);
    }
}

package databaseservice;

import dbenum.DBEnum;

import java.sql.SQLException;

public interface DataBaseService {

    void create(String inputSqlQuery) throws SQLException;
    void readData(String inputSqlQuery) throws SQLException;
    void updateData(String inputSqlQuery) throws SQLException;
    void delete(String inputSqlQuery) throws SQLException;

    static DataBaseService of(DBEnum db){
        return new DataBaseServiceImpl(db);
    }
}

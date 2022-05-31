package databaseservice;

import dbenum.DBEnum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface DataBaseService {

    void create(String inputSqlQuery) throws SQLException;
    ResultSet readData(String inputSqlQuery) throws SQLException;
    void readAndPrintData(String inputSqlQuery) throws SQLException;
    void updateData(String inputSqlQuery) throws SQLException;
    void delete(String inputSqlQuery) throws SQLException;
    Map<Integer,String> getMapOfColumnsName (String tableName) throws SQLException;

    static DataBaseService of(DBEnum db){
        return new DataBaseServiceImpl(db);
    }
}

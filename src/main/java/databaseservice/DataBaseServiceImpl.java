package databaseservice;

import databaseconnector.DatabaseConnector;
import dbenum.DBEnum;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class DataBaseServiceImpl implements DataBaseService{
    private final String MASSAGE_OF_SUCCESSFUL = "You sql query is execute successfully. Congratulation!";
    private final Statement statement;

    @SneakyThrows
    public DataBaseServiceImpl(DBEnum db){
        this.statement = DatabaseConnector.initDB(db).getConnection().createStatement();
    }

    @Override
    public void create(String inputSqlQuery) throws SQLException {
        statement.execute(inputSqlQuery);
        System.out.println(MASSAGE_OF_SUCCESSFUL);
    }

    @Override
    public void readData(String inputSqlQuery) throws SQLException {
        ResultSet resultSet = statement.executeQuery(inputSqlQuery);
        printQueryResult(resultSet);
    }

    @Override
    public void updateData(String inputSqlQuery) throws SQLException {
        statement.executeUpdate(inputSqlQuery);
        System.out.println(MASSAGE_OF_SUCCESSFUL);
    }

    @Override
    public void delete(String inputSqlQuery) throws SQLException {
        statement.execute(inputSqlQuery);
        System.out.println(MASSAGE_OF_SUCCESSFUL);
    }

    private void printQueryResult(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        StringBuilder stringBuilderForElement = new StringBuilder();
        StringBuilder stringBuilderSecondLabelLine = new StringBuilder();
        int width = 0;
        boolean temp = true;

        while(resultSet.next()){
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                int columnDisplaySize = metaData.getColumnDisplaySize(i);
                String columnLabel = metaData.getColumnLabel(i);
                if (columnDisplaySize < columnLabel.length()){
                    columnDisplaySize = columnLabel.length();
                }
                if (temp){
                    stringBuilderSecondLabelLine.append(
                            String.format("| %-"+columnDisplaySize+"s", columnLabel.toUpperCase(Locale.ROOT))
                    );
                    width += columnDisplaySize + 2;
                }
                stringBuilderForElement.append(String.format("| %-"+columnDisplaySize+"s", resultSet.getString(i)));

            }
            stringBuilderForElement.append("|\n");
            temp = false;
        }
        String continuousLabelLine = "=".repeat(++width);
        String secondLabelLine = stringBuilderSecondLabelLine + "|";
        System.out.println(continuousLabelLine);
        System.out.println(secondLabelLine);
        System.out.println(continuousLabelLine);
        System.out.println(stringBuilderForElement);

    }
}

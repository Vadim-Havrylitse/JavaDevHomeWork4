package states;

import application.Application;

import java.sql.SQLException;

public class ReadQueryState extends CRUDState{

    private static final String MESSAGE_FOR_GET_TABLE_NAME = "Write name of table for select data:";
    private static final String MESSAGE_FOR_GET_COLUMNS_SELECT = "Write number of column for select in line (multiselect):";
    private static final String MESSAGE_FOR_GET_AVAILABILITY_CONDITION = "Add condition to your query? Y/N:";
    private static final String MESSAGE_FOR_GET_CONDITION_COLUMN ="Choose one column for condition WHERE:";
    private static final String MESSAGE_FOR_GET_CONDITION_VALUE ="Write value for condition WHERE:";

    private static final String queryForSelectWithCondition = "SELECT %s " +
            "FROM %s " +
            "WHERE %s = '%s';";
    private static final String queryForSelectAll = "SELECT %s " +
            "FROM %s;";

    public ReadQueryState(Application app) {
        super(app);
    }

    @Override
    public void doWhenPress (){
        String nameTable;
        String columnsForSelect;
        String columnForCondition = null;
        String valueForCondition = null;
        String resultQuery;

        try {
            System.out.println(MESSAGE_FOR_GET_TABLE_NAME);
            nameTable = scanner.nextLine();
            columnsForSelect = chooseColumnFromUserAndReturnNameOfColumn(nameTable, MESSAGE_FOR_GET_COLUMNS_SELECT);

            if (checkAvailableCondition()){
                columnForCondition = chooseColumnFromUserAndReturnNameOfColumn(nameTable, MESSAGE_FOR_GET_CONDITION_COLUMN);
                System.out.println(MESSAGE_FOR_GET_CONDITION_VALUE);
                valueForCondition = scanner.nextLine();
            }

            resultQuery = normalizeQuery(nameTable, columnsForSelect, columnForCondition, valueForCondition);

            System.out.println(resultQuery);
            dataBaseService.readAndPrintData(resultQuery);
        } catch (SQLException e) {
            printMessageWhenSQLExceptionCatch(e.getMessage());
        }
        finally {
            returnToCRUDState();
        }
    }

    private String normalizeQuery (String nameTable, String columnsForSelect, String columnForCondition, String valueForCondition){
        String query;
        if (columnForCondition == null){
            query = queryForSelectAll;
        }else {
            query = queryForSelectWithCondition;
        }
        return String.format(query,columnsForSelect, nameTable, columnForCondition, valueForCondition);
    }

    private boolean checkAvailableCondition (){
        System.out.println(MESSAGE_FOR_GET_AVAILABILITY_CONDITION);
        String str = scanner.nextLine();
        return str.equalsIgnoreCase("y");
    }
}

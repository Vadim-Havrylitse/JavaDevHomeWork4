package states;

import application.Application;

import java.sql.SQLException;

public class DeleteQueryState extends CRUDState{

    private static final String MESSAGE_FOR_GET_TABLE_NAME = "Write table name for DELETE:";
    private static final String MESSAGE_FOR_GET_COLUMNS_CONDITION = "Choose column for condition WHERE (single choice):";
    private static final String MESSAGE_FOR_GET_VALUES_CONDITION = "Write the values for condition WHERE:";

    private static final String queryForDelete = "DELETE FROM %s WHERE %s = '%s';";

    public DeleteQueryState(Application app) {
        super(app);
    }

    @Override
    public void doWhenPress () {
        String nameTable;
        String columnForCondition;
        String valueForCondition;
        String resultQuery;

        try {
            System.out.println(MESSAGE_FOR_GET_TABLE_NAME);
            nameTable = scanner.nextLine().trim().replaceAll("\\W+", "");
            columnForCondition = chooseColumnFromUserAndReturnNameOfColumn(nameTable, MESSAGE_FOR_GET_COLUMNS_CONDITION);
            System.out.println(MESSAGE_FOR_GET_VALUES_CONDITION);
            valueForCondition = scanner.nextLine().trim().replaceAll("\\W+$|^\\W+", "");

            resultQuery = normalizeQuery(nameTable, columnForCondition, valueForCondition);

            System.out.println(resultQuery);
            dataBaseService.delete(resultQuery);
        }catch (SQLException e) {
            printMessageWhenSQLExceptionCatch(e.getMessage());
        }finally {
            returnToCRUDState();
        }
    }

    private String normalizeQuery (String nameTable, String columnForCondition, String valueForCondition){
        return String.format(queryForDelete, nameTable, columnForCondition, valueForCondition);
    }
}

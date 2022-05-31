package states;

import application.Application;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UpdateQueryState extends CRUDState{
    private static final Map<Integer, String[]> cashForUpdateQuery = new HashMap<>();
    static {
        cashForUpdateQuery.put(1, new String[]
                {"UPDATE DATA", "UPDATE %s SET %s = '%s' WHERE %s='%s';"});
        cashForUpdateQuery.put(2, new String[]
                {"ADD COLUMN", "ALTER TABLE %s ADD COLUMN %s %s;"});
        cashForUpdateQuery.put(3, new String[]
                {"DROP COLUMN", "ALTER TABLE %s DROP COLUMN %s;"});
        cashForUpdateQuery.put(4, new String[]
                {"MODIFY COLUMN", "ALTER TABLE %s MODIFY COLUMN %s %s;"});
        cashForUpdateQuery.put(5, new String[]
                {"ADD PRIMARY KEY", "ALTER TABLE %s ADD CONSTRAINT PRIMARY KEY (%s);"});
        cashForUpdateQuery.put(6, new String[]
                {"ADD FOREIGN KEY (CASCADE)", "ALTER TABLE %s ADD CONSTRAINT FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE CASCADE;"});
        cashForUpdateQuery.put(7, new String[]
                {"ADD FOREIGN KEY (SET NULL)", "ALTER TABLE %s ADD CONSTRAINT FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE SET NULL;"});
    }

    private static final String MESSAGE_FOR_GET_UPDATE_TYPE = "Choose one of the next type of column:";
    private static final String MESSAGE_FOR_GET_TABLE_NAME = "Write table name for update:";
    private static final String MESSAGE_FOR_GET_COLUMN_NAME = "Choose column for modify/update (single choice):";
    private static final String MESSAGE_FOR_GET_COLUMN_NAME_ADD = "Write column name for ADD:";
    private static final String MESSAGE_FOR_GET_VALUE = "Write value:";
    private static final String MESSAGE_FOR_GET_COLUMNS_CONDITION = "Choose column for condition WHERE (single choice):";
    private static final String MESSAGE_FOR_GET_VALUE_CONDITION = "Write the value for condition WHERE:";
    private static final String MESSAGE_FOR_GET_DATATYPE = "Write datatype:";
    private static final String MESSAGE_FOR_GET_TABLE_REFERENCE = "Write table name for reference:";
    private static final String MESSAGE_FOR_GET_COLUMNS_REFERENCE = "Choose column for reference (single choice):";
    public UpdateQueryState(Application app) {
        super(app);
    }

    @Override
    public void doWhenPress (){
        String tableName;
        String columnName;
        String datatypeOrValue;
        String tableName2;
        String columnName2;
        String resultQuery = null;

        try{
            System.out.println(MESSAGE_FOR_GET_UPDATE_TYPE);
            cashForUpdateQuery.forEach((a1, a2) -> System.out.println("press "+a1+" -> "+a2[0]));
            int inputType = Integer.parseInt(scanner.nextLine());
            if (!cashForUpdateQuery.containsKey(inputType)){
                return;
            }

            System.out.println(MESSAGE_FOR_GET_TABLE_NAME);
            tableName = scanner.nextLine();
            if (cashForUpdateQuery.get(inputType)[0].equals("ADD COLUMN")) {
                System.out.println(MESSAGE_FOR_GET_COLUMN_NAME_ADD);
                columnName = scanner.nextLine().trim().replaceAll("\\W+$|^\\W+", "");
            }else {
                columnName = chooseColumnFromUserAndReturnNameOfColumn(tableName, MESSAGE_FOR_GET_COLUMN_NAME);
            }

            switch (inputType) {
                case 1:
                    System.out.println(MESSAGE_FOR_GET_VALUE);
                    datatypeOrValue = scanner.nextLine().trim().replaceAll("\\W+$|^\\W+", "");
                    columnName2 = chooseColumnFromUserAndReturnNameOfColumn(tableName, MESSAGE_FOR_GET_COLUMNS_CONDITION);
                    System.out.println(MESSAGE_FOR_GET_VALUE_CONDITION);
                    String valueForCondition = scanner.nextLine().trim().replaceAll("\\W+$|^\\W+", "");
                    resultQuery = normalizeQuery(inputType, tableName, columnName, datatypeOrValue, columnName2, valueForCondition);
                    break;
                case 2:
                case 4:
                    System.out.println(MESSAGE_FOR_GET_DATATYPE);
                    datatypeOrValue = scanner.nextLine();
                    resultQuery = normalizeQuery(inputType, tableName, columnName, datatypeOrValue);
                    break;
                case 3:
                case 5:
                    resultQuery = normalizeQuery(inputType, tableName, columnName);
                    break;
                case 6:
                case 7:
                    System.out.println(MESSAGE_FOR_GET_TABLE_REFERENCE);
                    tableName2 = scanner.nextLine();
                    columnName2 = chooseColumnFromUserAndReturnNameOfColumn(tableName2, MESSAGE_FOR_GET_COLUMNS_REFERENCE);
                    resultQuery = normalizeQuery(inputType,  tableName, columnName, tableName2, columnName2);
                    break;
                }
            System.out.println(resultQuery);
            dataBaseService.updateData(resultQuery);
        }catch (SQLException e){
            printMessageWhenSQLExceptionCatch(e.getMessage());
        }
        finally {
            returnToCRUDState();
        }
    }

    // number of size String[] = max count of symbol '%s' in any Query in Map<Integer,String> cashForUpdateQuery
    private String normalizeQuery (Integer inputType, String ... arg){
        String[] arguments = new String[5];
        for (int i = 0; i < arguments.length; i++) {
            if (i <= (arg.length-1)){
                arguments[i] = arg[i];
            }else {
                arguments[i] = null;
            }
        }
        return String.format(cashForUpdateQuery.get(inputType)[1], arguments[0], arguments[1], arguments[2], arguments[3], arguments[4]);
    }
}

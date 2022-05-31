package states;

import application.Application;

import java.sql.SQLException;

public class CreateQueryState extends CRUDState{
    private static final String MESSAGE_FOR_GET_TABLE_NAME = "Write table name for INSERT:";

    private static final String MESSAGE_FOR_GET_COLUMNS_NAME = "Choose column for INSERT in line (multiselect):";

    private static final String MESSAGE_FOR_GET_VALUES = "Write the values for next columns:";

    private static final String queryForInsert = "INSERT INTO %s (%s) VALUES %s;";

    public CreateQueryState(Application app) {
        super(app);
    }

    @Override
    public void doWhenPress (){
        System.out.println(MESSAGE_FOR_GET_TABLE_NAME);
        String nameTable = scanner.nextLine();
        try {
            String nameColumns = chooseColumnFromUserAndReturnNameOfColumn(nameTable, MESSAGE_FOR_GET_COLUMNS_NAME);
            String insertValues = getFromUserInsertValues(nameColumns);
            String resultQuery = normalizeQuery(nameTable, nameColumns, insertValues);
            System.out.println(resultQuery);
            dataBaseService.create(resultQuery);
        } catch (SQLException e) {
            printMessageWhenSQLExceptionCatch(e.getMessage());
        }
        finally {
            returnToCRUDState();
        }
    }

    private String getFromUserInsertValues(String nameColumns){
        System.out.println(MESSAGE_FOR_GET_VALUES);
        StringBuilder builder = new StringBuilder();
        String str;
        String answerForContinue;
        do {
            builder.append("(");
            for(String element : nameColumns.split(",")){
                System.out.print("input " + element + " -> ");
                str = scanner.nextLine().trim().replaceAll("\\W+$|^\\W+", "");
                builder.append("'").append(str).append("'").append(", ");
            }
            int indexComa = builder.lastIndexOf(",");
            builder.delete(indexComa,indexComa+2);
            builder.append("),");
            System.out.println("Continue write values? Y/N");
            answerForContinue = scanner.nextLine();
        } while (answerForContinue.equalsIgnoreCase("y"));
        int indexComa2 = builder.lastIndexOf(",");
        builder.delete(indexComa2,indexComa2 + 2);
        return builder.toString();
    }

    private String normalizeQuery (String nameTable, String columnsForInsert, String insertValues){
        return String.format(queryForInsert, nameTable, columnsForInsert, insertValues);
    }

}

package states;

import application.Application;

import java.util.Arrays;
import java.util.Scanner;

public abstract class State {
    protected final Scanner scanner;
    protected Application app;

    public State (Application app){
        this.app = app;
        this.scanner = new Scanner(System.in);
    }

    public abstract void doWhenPress ();

    protected int checkCorrectInputAndReturnNumber(String[] massage) {
        String str;
        do {
            printMessage(massage);
            str = scanner.nextLine();
        } while (str.isBlank() || !str.matches("[0-"+(massage.length-2)+"]"));
        return Integer.parseInt(str);
    }

    private void printMessage (String[] massage){
        Arrays.stream(massage).forEach(System.out::println);
    }

    protected void returnToStartState(){
        app.changeState(new StartState(app));
    }

    protected void exitApp (){
        System.exit(+100500);
    }

    protected void printMessageWhenSQLExceptionCatch(String error){
        System.err.println("-- SOMETHING WRONG WITH SQL QUERY!!!\n-- " + error);
    }
}

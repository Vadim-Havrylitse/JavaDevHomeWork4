package states;

import application.Application;

public class StartState extends State{
    private final String[] MESSAGE_START = {"***MAIN MENU***\nchoose the right point:",
            "press 1 -> CRUD",
            "press 2 -> Home Task Query",
            "press 0 -> EXIT APP"};
    public StartState(Application app) {
        super(app);
    }

    @Override
    public void doWhenPress() {
        int inputNumber = checkCorrectInputAndReturnNumber(MESSAGE_START);
        switch (inputNumber){
            case 1:
                app.changeState(new CRUDState(app));
                break;
            case 2:
                app.changeState(new UtilQueryState(app));
                break;
            case 0:
                exitApp();
        }
    }
}

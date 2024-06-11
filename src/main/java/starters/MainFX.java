package starters;

import engineering.view.SessionManager;
import engineering.view.fx.FilesFXML;
import engineering.view.fx.PageManagerSingleton;
import exception.NotFoundException;
import exception.OperationFailedException;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainFX extends Application{

    static void run(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws OperationFailedException, NotFoundException {
        Integer session = SessionManager.getSessionManager().createSession();
        PageManagerSingleton.getInstance(stage);
        PageManagerSingleton.getInstance().setHome(FilesFXML.HOME.getPath(), session);
    }


}

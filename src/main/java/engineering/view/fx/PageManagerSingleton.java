package engineering.view.fx;

import controller.gui.fx.AbstractGUIControllerFX;
import exception.NotFoundException;
import exception.OperationFailedException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PageManagerSingleton {

    private final Stage primaryStage;
    private static final Deque<String> viewStack = new ArrayDeque<>();
    private static PageManagerSingleton instance = null;

    protected PageManagerSingleton(Stage stage) {
        primaryStage = stage;
    }

    public static synchronized PageManagerSingleton getInstance(Stage ... stage){
        if(instance == null){
            instance = new PageManagerSingleton(stage[0]);
        }
        return instance;
    }

    public void goNext(String fxmlPath, Integer session)  throws OperationFailedException, NotFoundException{
        viewStack.push(fxmlPath);
        changeView(fxmlPath, session);
    }

    public void goBack(Integer session)  throws OperationFailedException, NotFoundException{
        if (!viewStack.isEmpty()) {
            viewStack.pop();
            String previousView = viewStack.peek();
            changeView(previousView, session);
        }
    }

    public void goHome(Integer session)  throws OperationFailedException, NotFoundException{
        if(!viewStack.isEmpty()){
            String home = viewStack.reversed().pop();
            setHome(home, session);
        }
    }

    public void setHome(String fxmlPath, Integer session)  throws OperationFailedException, NotFoundException{
        viewStack.clear();
        viewStack.push(fxmlPath);
        changeView(fxmlPath,session);
    }

    public void changeView(String fxmlPath, Integer session) throws OperationFailedException, NotFoundException {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            AbstractGUIControllerFX controller = loader.getController();
            controller.initialize(session);
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("PrivateEvents");
            primaryStage.getIcons().add(new Image("/icon.png"));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e){
            viewStack.pop();
            String errorMsg = "Impossible to load the view: " + fxmlPath;
            Logger.getAnonymousLogger().log(Level.SEVERE, errorMsg);
            throw new OperationFailedException();
        } catch (OperationFailedException | NotFoundException e){
            viewStack.pop();
            throw e;
        }
    }

}



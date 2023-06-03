package javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Класс для запуска программы.
 * @author Трунилина Виктория витальевна
 * Группа бПИэ-201
 */

public class HelloApplication extends Application
{
        @Override
        public void start(Stage stage) throws IOException
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 460);
            stage.setTitle("База данных");
            stage.setScene(scene);
            stage.show();
        }
    public static void main(String[] args)
    {
        launch();
    }
}
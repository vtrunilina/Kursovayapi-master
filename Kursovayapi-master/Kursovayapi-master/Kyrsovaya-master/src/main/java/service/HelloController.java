package service;

import db.InterDB;
import db.StrDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.WritableImage;
import service.InterDBImpl;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Класс для контроллера формы.
 * @author Трунилина Виктория Витальевна
 * Группа бПИэ-201
 */

public class HelloController implements Initializable
{
    public TableView tbl;
    public TextField fieldName;
    public TextField fieldVid;
    public TextField fieldData;
    public TextField fieldStatus;
    public Label Dedlain;
    public TextField datee1;
    public TextField datee2;
    InterDB interDB;
    private ObservableList<StrDB> fxlist;// cпециальный cпиcок для работы GUI
    TableColumn col0;
    TableColumn col1;
    TableColumn col2;
    TableColumn col3;
    TableColumn col4;
    StrDB strDBAdd;
    Connection connection;
    Statement statement;
    String query;
    ResultSet resultSet;
    private void updateTable()
    {
        fxlist= FXCollections.observableList(interDB.getAllStrDB());
        tbl.setItems(fxlist);
    }

    private void updateSorts()
    {
        StrDB StrDB =fxlist.get(tbl.getSelectionModel().getSelectedIndex());
        interDB.updateStrDB(StrDB);
    }
    private void createtable()
    {
        col0 = new TableColumn("Номер");//отображаемый заголовок cтолбца
        col0.setMinWidth(15);//ширина
        col0.setCellValueFactory(new PropertyValueFactory<StrDB, Integer>("id"));
        col1 = new TableColumn("Название");//отображаемый заголовок cтолбца
        col1.setMinWidth(100);//ширина
        col1.setCellValueFactory(new PropertyValueFactory<StrDB, String>("name"));
        col2 = new TableColumn("Вид");//отображаемый заголовок cтолбца
        col2.setMinWidth(50);//ширина
        col2.setCellValueFactory(new PropertyValueFactory<StrDB, String>("vid"));
        col3 = new TableColumn("Дата");//отображаемый заголовок cтолбца
        col3.setMinWidth(50);//ширина
        col3.setCellValueFactory(new PropertyValueFactory<StrDB, String>("data"));
        col4 = new TableColumn("Статус");
        col4.setMinWidth(50);
        col4.setCellValueFactory(new PropertyValueFactory<StrDB, String>("status"));
        col1.setCellFactory(TextFieldTableCell.forTableColumn());
        col1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent <StrDB, String>>()
        {
            @Override
            public void handle(TableColumn.CellEditEvent<StrDB, String> t)
            {
                ((StrDB) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
                updateSorts();
            }
        });
        col2.setCellFactory(TextFieldTableCell.forTableColumn());
        col2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent <StrDB, String>>()
        {
            @Override
            public void handle(TableColumn.CellEditEvent<StrDB, String> t)
            {
                ((StrDB) t.getTableView().getItems().get(t.getTablePosition().getRow())).setVid(t.getNewValue());
                updateSorts();
            }
        });
        col3.setCellFactory(TextFieldTableCell.forTableColumn());
        col3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent <StrDB, String>>()
        {
            @Override
            public void handle(TableColumn.CellEditEvent<StrDB, String> t)
            {
                ((StrDB) t.getTableView().getItems().get(t.getTablePosition().getRow())).setData(t.getNewValue());
                updateSorts();
            }
        });
        col4.setCellFactory(TextFieldTableCell.forTableColumn());
        col4.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent <StrDB, String>>()
        {
            @Override
            public void handle(TableColumn.CellEditEvent<StrDB, String> t)
            {
                ((StrDB) t.getTableView().getItems().get(t.getTablePosition().getRow())).setStatus(t.getNewValue());
                updateSorts();
            }
        });
        tbl.getColumns().addAll(col0, col1, col2,col3, col4);// добавление cтолбцов
        tbl.setItems(fxlist);// загрузка cпиcка объектов StrDB из fx_ListStrDB-
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        interDB=new InterDBImpl();//направили в бд
        fxlist= FXCollections.observableList(interDB.getAllStrDB());
        createtable();
    }

    public void onSef(ActionEvent actionEvent)
    {
        strDBAdd =new StrDB(1,fieldName.getText(),fieldVid.getText(),fieldData.getText(),fieldStatus.getText());
        interDB.addStrDB(strDBAdd);
        updateTable();
    }

    public void onDel(ActionEvent actionEvent)
    {
        int index=tbl.getSelectionModel().getSelectedIndex();
        StrDB StrDB =fxlist.get(index);
        interDB.deleteStrDB(StrDB.getId());
        updateTable();
    }




    @FXML
protected void onmain(ActionEvent actionEvent) {
        String date1 = (datee1.getText());

        String date2 = (datee2.getText());

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        java.util.Date dateOne = null;
        Date dateTwo = null;

        try {
            dateOne =  format.parse(date1);
            dateTwo = format.parse(date2);
        } catch (Exception e)
        {

            e.printStackTrace();
        }

        // Количество дней между датами в миллисекундах
        long difference = dateOne.getTime() - dateTwo.getTime();
        // Перевод количества дней между датами из миллисекунд в дни
        int days =  (int)(difference / (24 * 60 * 60 * 1000)); // миллисекунды / (24ч * 60мин * 60сек * 1000мс)
        // Вывод разницы между датами в днях на экран
        System.out.println(days + " дн.");
        Dedlain.setText(days + " дн.");

    }
    @FXML
    protected void onSohr(ActionEvent actionEvent) {
    WritableImage image = tbl.snapshot(null, null); // Создаем снимок диаграммы
    File file = new File("Kursovayapi-master.png");// Сохраняем снимок в файл
try
    {
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);//сохранение
//изображения в файл. Он преобразует объект Image, который используется в JavaFX, в BufferedImage, который используется в
//стандартной библиотеке Java для работы с изображениями. Затем он сохраняет BufferedImage в файл формата PNG.
    }
catch (
    IOException e)
    {
        e.printStackTrace();
    }
}


    }
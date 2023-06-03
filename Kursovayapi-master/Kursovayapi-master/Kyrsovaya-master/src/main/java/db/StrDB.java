 package db;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Класс для создания одной строки в БД.
 * @author Трунилина Виктория витальевна
 * Группа бПИэ-201
 */

public class StrDB
{
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty vid;
    private SimpleStringProperty data;
    private SimpleStringProperty status;

    public StrDB(int id, String name, String vid, String data, String status)
    {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.vid = new SimpleStringProperty(vid);
        this.data = new SimpleStringProperty(data);
        this.status = new SimpleStringProperty(status);
    }

    public int getId() {
        return id.get();
    }
    public String getName() {return name.get();}
    public void setName(String name) {this.name.set(name);}
    public String getVid() {return vid.get();}
    public void setVid(String vid) {this.vid.set(vid);}
    public String getData() {return data.get();}
    public void setData(String data) {this.data.set(data);}
    public String getStatus() {return status.get();}
    public void setStatus(String status) {this.status.set(status);}
}

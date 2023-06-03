package service;

import db.InterDB;
import db.StrDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для реализации интерфейса InterfaceDB для работы с БД.
 * @author Трунилина Виктория витальевна
 * Группа бПИэ-201
 */

public class InterDBImpl implements InterDB
{
    private Connection conn;

    public InterDBImpl()
    {
        try
        {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/tes", "11", "11");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS strDB (" +
                    "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(255)," +
                    "vid VARCHAR(255)," +
                    "data VARCHAR(255),"+
                    "status VARCHAR(255))");
        }
        catch (SQLException e) { e.printStackTrace();  }
        catch (ClassNotFoundException e) { throw new RuntimeException(e);  }
    }

    @Override
    public List<StrDB> getAllStrDB()
    {
        List<StrDB> strDBS = new ArrayList<>();
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM strDB");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StrDB strDB = new StrDB(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("vid"),
                        rs.getString("data"),
                        rs.getString("status"));
                strDBS.add(strDB);
            }
        }
        catch (SQLException e) { e.printStackTrace(); }
        return strDBS;
    }

    @Override
    public StrDB getStrDBById(int id)
    {
        StrDB strDB = null;
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM strDB WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                strDB = new StrDB(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("vid"),
                        rs.getString("data"),
                        rs.getString("status"));
            }
        }
        catch (SQLException e) { e.printStackTrace();  }
        return strDB;
    }

    @Override
    public void addStrDB(StrDB strDB)
    {
        try
        {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO strDB (name, vid, data, status) VALUES (?, ?, ?, ?)");
            ps.setString(1, strDB.getName());
            ps.setString(2, strDB.getVid());
            ps.setString(3, strDB.getData());
            ps.setString(4, strDB.getStatus());
            ps.executeUpdate();
        }
        catch (SQLException e)  { e.printStackTrace(); }
    }

    @Override
    public void updateStrDB(StrDB strDB)
    {
        try
        {
            PreparedStatement ps = conn.prepareStatement("UPDATE strDB SET name=?, vid=?, data=?, status=? WHERE id = ?");
            ps.setString(1, strDB.getName());
            ps.setString(2, strDB.getVid());
            ps.setString(3, strDB.getData());
            ps.setString(4, strDB.getStatus());
            ps.setInt(5, strDB.getId());
            ps.executeUpdate();
        }
        catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public void deleteStrDB(int id)
    {
        try
        {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM strDB WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch (SQLException e) { e.printStackTrace();   }
    }
}

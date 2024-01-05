package dao;

import db.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import Dropbox.Dropbox;


public class DropboxDao {
    public int insert(Dropbox dropbox) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection
                    .prepareStatement("Insert into dropbox (id,location,point) values (?,?,?)");
            statement.setString(1, dropbox.getId());
            statement.setString(2, dropbox.getLocation());
            statement.setString(3, dropbox.getPoint());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Dropbox dropbox) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection
                    .prepareStatement("Update into dropbox (location=?,point=?) where id=?");
            statement.setString(1, dropbox.getLocation());
            statement.setString(2, dropbox.getId());
            statement.setString(3, dropbox.getPoint());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(Dropbox dropbox) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("delete from dropbox where id=?");
            statement.setString(1, dropbox.getId());
            statement.setString(2, dropbox.getLocation());
            statement.setString(3, dropbox.getPoint());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Dropbox> findAll() {
        List<Dropbox> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
                Statement statement = connection.createStatement();) {
            try (ResultSet resultSet = statement.executeQuery("select * from dropbox");) {
                while (resultSet.next()) {
                    Dropbox dropbox = new Dropbox();
                    dropbox.setId(resultSet.getString("id"));
                    dropbox.setLocation(resultSet.getString("nama"));
                    dropbox.setPoint(resultSet.getString("point"));
                    list.add(dropbox);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

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
            statement.setString(3, "0");

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Dropbox> findAll() {
        List<Dropbox> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();) {
            try (ResultSet resultSet = statement.executeQuery("select * from dropbox");) {
                while (resultSet.next()) {
                    Dropbox dropbox = new Dropbox();
                    dropbox.setId(resultSet.getString("id"));
                    dropbox.setLocation(resultSet.getString("location"));
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

    public int update(Dropbox dropbox) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection
                    .prepareStatement("Update dropbox set location = ?, point = ? where id = ?");
            statement.setString(1, dropbox.getLocation());
            statement.setString(2, dropbox.getPoint());
            statement.setString(3, dropbox.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(Dropbox dropbox) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("delete from dropbox where id = ?");
            statement.setString(1, dropbox.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Dropbox findByLocation(String location) {
        Dropbox dropbox = null;
        try {
            ResultSet resultSet = getResultSetNama(location);
            while (resultSet.next()) {
                dropbox = new Dropbox();
                dropbox.setId(resultSet.getString("id"));
                dropbox.setLocation(resultSet.getString("location"));
                dropbox.setPoint(resultSet.getString("location"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dropbox;
    }
    private static ResultSet getResultSetNama(String nama) throws SQLException {
        Connection connection = MySqlConnection.getInstance().getConnection();
        String query = "select * from dropbox where location = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nama);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
}

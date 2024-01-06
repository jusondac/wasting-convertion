package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Waste.Waste;
import db.MySqlConnection;
import dropbox.Dropbox;

public class WasteDao {
    public int insert(Waste waste) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection
                    .prepareStatement("Insert into waste (id,location,point) values (?,?,?)");
            statement.setString(1, waste.getId());
            statement.setString(3, waste.getPoint());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Waste waste) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection
                    .prepareStatement("Update into waste (location=?,point=?) where id=?");
            statement.setString(2, waste.getId());
            statement.setString(3, waste.getPoint());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(Waste waste) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("delete from waste where id=?");
            statement.setString(1, waste.getId());
            statement.setString(3, waste.getPoint());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Waste> findAll() {
        List<Waste> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
                Statement statement = connection.createStatement();) {
            try (ResultSet resultSet = statement.executeQuery("select * from waste");) {
                while (resultSet.next()) {
                    Waste waste = new Waste();
                    waste.setId(resultSet.getString("id"));
                    waste.setPoint(resultSet.getString("point"));
                    list.add(waste);
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

package dao;

import Category.Categories;
import Dropbox.Dropbox;
import db.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Waste.Waste;

public class WasteDao {
    public int insert(Waste member) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("Insert into wastes (id, category_id, dropbox_id) values (?, ?, ?)");
            statement.setString(1, member.getId());
            statement.setString(2, member.getCategory().getId());
            statement.setString(3, member.getDropbox().getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

//    public int update(Waste member) {
//        int result = -1;
//        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
//            PreparedStatement statement = connection.prepareStatement("update member set nama = ?, jenis_member_id = ? where id = ?");
//            statement.setString(1, member.getNama());
//            statement.setString(2, member.getJenisWaste().getId());
//            statement.setString(3, member.getId());
//            result = statement.executeUpdate();
//            System.out.println("Update should be successfully");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return result;
//    }

    public int delete(String id) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("delete from wastes where id = ?");
            statement.setString(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Waste findById(String id) {
        Waste waste = null;
        try {
            ResultSet resultSet = getResultSetId(id);
            while (resultSet.next()) {
                waste = new Waste();
                waste.setId(resultSet.getString("id"));

                Dropbox dropbox = new Dropbox();
                dropbox.setId(resultSet.getString("dropbox_id"));
                dropbox.setLocation(resultSet.getString("dropbox_location"));
                dropbox.setPoint("0");

                Categories categories = new Categories();
                categories.setId(resultSet.getString("categories_id"));
                categories.setNama(resultSet.getString("categories_name"));

                waste.setCategory(categories);
                waste.setDropbox(dropbox);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return waste;
    }

    private static ResultSet getResultSetId(String id) throws SQLException {
        Connection connection = MySqlConnection.getInstance().getConnection();
        String query = "select wastes.id, dropbox.id dropbox_id, dropbox.location dropbox_location, categories.id categories_id, categories.name categories_name from wastes join dropbox on wastes.dropbox_id = dropbox.id join categories on wastes.category_id = categories.id where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public List<Waste> findAll() {
        List<Waste> list = new ArrayList<>();
        try(Connection connection = MySqlConnection.getInstance().getConnection(); Statement statement = connection.createStatement();) {
            try(ResultSet resultSet = statement.executeQuery(
                            "select wastes.id, dropbox.id dropbox_id, dropbox.location dropbox_location, categories.id categories_id, categories.name categories_name from wastes join dropbox on wastes.dropbox_id = dropbox.id join categories on wastes.category_id = categories.id "
                    );)
            {
                while (resultSet.next()) {
                    Waste waste = new Waste();
                    waste.setId(resultSet.getString("id"));

                    Dropbox dropbox = new Dropbox();
                    dropbox.setId(resultSet.getString("dropbox_id"));
                    dropbox.setLocation(resultSet.getString("dropbox_location"));
                    dropbox.setPoint("0");

                    Categories categories = new Categories();
                    categories.setId(resultSet.getString("categories_id"));
                    categories.setNama(resultSet.getString("categories_name"));

                    waste.setCategory(categories);
                    waste.setDropbox(dropbox);
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

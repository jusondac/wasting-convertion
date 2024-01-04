package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;
import Category.Categories;
import db.MySqlConnection;

/**
 * CategoriesDao
 */
public class CategoriesDao {
    public int insert(Categories categories) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection
                    .prepareStatement("Insert into categories (id,name,point) values (?,?,?)");
            statement.setString(1, categories.getId());
            statement.setString(2, categories.getNama());
            statement.setString(3, categories.getPoint());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Categories categories) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection
                    .prepareStatement("Update into categories (name=?,point=?) where id=?");
            statement.setString(1, categories.getNama());
            statement.setString(2, categories.getId());
            statement.setString(3, categories.getPoint());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(Categories categories) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("delete from categories where id=?");
            statement.setString(1, categories.getId());
            statement.setString(2, categories.getNama());
            statement.setString(3, categories.getPoint());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Categories> findAll() {
        List<Categories> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
                Statement statement = connection.createStatement();) {
            try (ResultSet resultSet = statement.executeQuery("select * from categories");) {
                while (resultSet.next()) {
                    Categories categories = new Categories();
                    categories.setId(resultSet.getString("id"));
                    categories.setNama(resultSet.getString("name"));
                    categories.setPoint(resultSet.getString("point"));
                    list.add(categories);
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
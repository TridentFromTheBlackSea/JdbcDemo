package be.infernalwhale.data;

import be.infernalwhale.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDAO {
    public Optional<Category> getCategoryByID(int id) throws SQLException, NonUniqueResultException {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement statement = conn.prepareStatement("SELECT * FROM Categories WHERE id = ?");
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        List<Category> categories = new ArrayList<>();
        while (rs.next()) {
            Category category = new Category();
            category.setId(rs.getInt("Id"));
            category.setCategoryName(rs.getString("Category"));
            categories.add(category);
        }

        if (categories.size() == 0) return Optional.empty();
        if (categories.size() == 1) return Optional.of(categories.get(0));

        throw new NonUniqueResultException("Found multiple categories for id: " + id);
    }
}

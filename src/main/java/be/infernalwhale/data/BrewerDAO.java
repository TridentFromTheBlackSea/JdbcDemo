package be.infernalwhale.data;

import be.infernalwhale.model.Brewer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BrewerDAO {
    public Optional<Brewer> getBrewerById(int id) throws SQLException, NonUniqueResultException {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement statement = conn.prepareStatement("SELECT * FROM Brewers WHERE Id = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        List<Brewer> brewers = new ArrayList<>();
        while (rs.next()) {
            Brewer brewer = new Brewer();

            brewer.setId(rs.getInt("Id"));
            brewer.setName(rs.getString("Name"));
            brewer.setAddress(rs.getString("Address"));
            brewer.setZipcode(rs.getString("ZipCode"));
            brewer.setCity(rs.getString("City"));
            brewer.setTurnover(rs.getInt("Turnover"));

            brewers.add(brewer);
        }

        if (brewers.size() == 0) return Optional.empty();
        if (brewers.size() == 1) return Optional.of(brewers.get(0));

        throw new NonUniqueResultException("Found multiple brewers for id: " + id);
    }
}

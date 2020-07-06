package be.infernalwhale.data;

import be.infernalwhale.model.Beer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BeerDAO {
    public List<Beer> getAllBeers() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM Beers");

        return parseBeers(rs);
    }

    public List<Beer> getBeersByAlcohol(int lower, int upper) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement statement = conn.prepareStatement("SELECT * FROM Beers WHERE Alcohol BETWEEN ? AND ?");
        statement.setInt(1, lower);
        statement.setInt(2, upper);
        ResultSet rs = statement.executeQuery();

        return parseBeers(rs);
    }

    public Optional<Beer> getBeerById(int id) throws SQLException, NonUniqueResultException {
        Connection conn = ConnectionFactory.getConnection();

        PreparedStatement statement = conn.prepareStatement("SELECT * FROM Beers WHERE Id = ?");
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();
        List<Beer> beers = parseBeers(rs);

        if (beers.size() == 0) return Optional.empty();
        if (beers.size() == 1) return Optional.of(beers.get(0));

        throw new NonUniqueResultException("Found multiple results for id: " + id);
    }


    private List<Beer> parseBeers(ResultSet rs) throws SQLException {
        List<Beer> result = new ArrayList<>();
        while (rs.next()) {
            Beer beer = new Beer();
            beer.setId(rs.getInt("Id"));
            beer.setName(rs.getString("Name"));
            beer.setBrewerId(rs.getInt("BrewerId"));
            beer.setCategoryId(rs.getInt("CategoryId"));
            beer.setPrice(rs.getDouble("Price"));
            beer.setStock(rs.getInt("Stock"));
            beer.setAlcohol(rs.getDouble("Alcohol"));
            beer.setVersion(rs.getInt("Version"));
            result.add(beer);
        }

        return result;
    }


}

package be.infernalwhale.services;

import be.infernalwhale.data.BeerDAO;
import be.infernalwhale.data.BrewerDAO;
import be.infernalwhale.data.CategoryDAO;
import be.infernalwhale.data.NonUniqueResultException;
import be.infernalwhale.model.Beer;
import be.infernalwhale.model.Brewer;
import be.infernalwhale.model.Category;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BeerService {
    private BeerDAO beerDAO = new BeerDAO();
    private BrewerDAO brewerDAO = new BrewerDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();

    public List<Beer> getAllBeers() throws SQLException {
        return beerDAO.getAllBeers();
    }

    public List<Beer> getBeersByAlcohol(int lower, int upper) throws SQLException {
        return beerDAO.getBeersByAlcohol(lower, upper);
    }

    public Optional<Beer> getBeerById(int id) throws SQLException, NonUniqueResultException {
        Optional<Beer> optionalBeer = beerDAO.getBeerById(id);

        if (!optionalBeer.isPresent()) return optionalBeer;
        else {
            Beer beer = optionalBeer.get();

            Optional<Brewer> optionalBrewer = brewerDAO.getBrewerById(beer.getBrewerId());
            if (optionalBrewer.isPresent()) beer.setBrewer(optionalBrewer.get());
            else beer.setBrewer(new Brewer().setName("Brewer information not found in db"));

            Optional<Category> optionalCategory = categoryDAO.getCategoryByID(beer.getCategoryId());
            if (optionalCategory.isPresent()) beer.setCategory(optionalCategory.get());
            else beer.setCategory(new Category().setCategoryName("Category not found in db"));

            return optionalBeer;
        }
    }

    public void saveBeer(Beer beer) {

    }
}

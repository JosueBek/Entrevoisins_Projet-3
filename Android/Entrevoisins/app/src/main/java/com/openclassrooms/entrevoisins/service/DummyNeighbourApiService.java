package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getFavorites() {
        List<Neighbour> mFavorites = new ArrayList<>();
        for(Neighbour n: neighbours) {
            if (n.isFavorites()) {
                mFavorites.add(n);
            }
        }
        return mFavorites;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFavorites(Neighbour neighbour) {
        int position = neighbours.indexOf(neighbour);
        neighbours.get(position).setFavorite(true);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteFavorites(Neighbour neighbour) {
       int position = neighbours.indexOf(neighbour);
       neighbours.get(position).setFavorite(false);

        }
    }

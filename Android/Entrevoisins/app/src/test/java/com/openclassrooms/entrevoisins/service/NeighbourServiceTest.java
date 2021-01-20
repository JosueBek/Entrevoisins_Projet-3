package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }
    @Test
    public void getFavoritesWithSuccess() {
        List<Neighbour> neighbours = service.getFavorites();
        Neighbour neighbour = service.getNeighbours().get(0);
        service.addFavorites(neighbour);
        assertEquals(1, service.getFavorites().size());
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(neighbours.toArray()));
    }

    @Test
    public void addFavoritesNeighbourWithSuccess() {
        Neighbour neighbourToAdd = service.getNeighbours().get(0);
        service.addFavorites(neighbourToAdd);
        assertEquals(1, service.getFavorites().size());
        assertTrue(service.getFavorites().contains(neighbourToAdd));

    }

    @Test
    public void deleteFavoritesNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.addFavorites(neighbourToDelete);
        service.deleteFavorites(neighbourToDelete);
        assertFalse(service.getFavorites().contains(neighbourToDelete));
    }
}

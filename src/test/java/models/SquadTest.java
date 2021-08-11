package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SquadTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        Squad.clearSquad();
    }

    @Test
    void checkSquadIfItsObjectsAre_true() {
        ArrayList<Hero> hero = new ArrayList<>();
        Squad team = new Squad("Avengers", "Change The World", 6, hero);
        assertTrue(team instanceof Squad);
    }

    @Test
    void checkIfStringSquadIsCorrectWithName() {
        ArrayList<Hero> hero = new ArrayList<>();
        Squad team = new Squad("Avengers", "Change The World", 6, hero);
        assertEquals("Avengers", team.getName());
    }

    @Test
    void checkIfStringIsCorrectWithPurpose() {
        ArrayList<Hero> hero = new ArrayList<>();
        Squad team = new Squad("Avengers", "Change The World", 6, hero);
        assertEquals("Change The World", team.getPurpose());
    }

    @Test
    void checkIfIntegerIsCorrectWithTotalSize() {
        ArrayList<Hero> hero = new ArrayList<>();
        Squad team = new Squad("Avengers", "Change The World", 6, hero);
        assertEquals(6, team.getTotalSize());
    }


    @Test
    void checkIfAllSquadAreReturnedCorrectly() {
        ArrayList<Hero> hero = new ArrayList<>();
        Squad team = new Squad("Avengers", "Change The World", 6, hero);
        Squad newSquad = new Squad("The Shield", "Oversee Avengers Operations", 7, hero);
        Squad anotherSquad = new Squad("DC comics", "Make Gotham City A better place", 9, hero);
        assertEquals(3, Squad.getSquadInstances().size());
    }

    @Test
    void checkIfTheWholeSquadContainsAllObjects() {
        ArrayList<Hero> hero = new ArrayList<>();
        Squad team = new Squad("Avengers", "Change The World", 6, hero);
        Squad newSquad = new Squad("The Shield", "Oversee Avengers Operations", 7, hero);
        Squad anotherSquad = new Squad("DC comics", "Make Gotham City A better place", 9, hero);
        assertTrue(Squad.getSquadInstances().contains(team));
        assertTrue(Squad.getSquadInstances().contains(newSquad));
        assertTrue(Squad.getSquadInstances().contains(anotherSquad));
    }


    @Test
    void checkIfYouCanFindSquadBy_Id() {
        ArrayList<Hero> hero = new ArrayList<>();
        Squad team = new Squad("Avengers", "Change The World", 6, hero);
        Squad newSquad = new Squad("The Shield", "Oversee Avengers Operations", 7, hero);
        Squad anotherSquad = new Squad("DC comics", "Make Gotham City A better place", 9, hero);
        Squad searchSquad = Squad.findById(1);
        Squad searchNewSquad = Squad.findById(2);
        Squad searchAnotherSquad = Squad.findById(3);
        assertEquals(team, searchSquad);
        assertEquals(newSquad, searchNewSquad);
        assertEquals(anotherSquad, searchAnotherSquad);
    }

    @Test
    void DeleteSquads() {
        ArrayList<Hero> hero = new ArrayList<>();
        Squad team = new Squad("Avengers", "Change The World", 6, hero);
        Squad newSquad = new Squad("The Shield", "Oversee Avengers Operations", 7, hero);
        Squad.clearSquad();
        assertEquals(0, Squad.getSquadInstances().size());
    }
}
package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        Hero.clearAllHero();
    }

    @Test
    void takesAllHeroObjectsAndTurn_true() {
        Hero superHero = new Hero("Brian", 20, "Speed", "Poor vision");
        assertTrue(superHero instanceof Hero);
    }

    @Test
    void getsStringNameCorrectly() {
        Hero superHero = new Hero("Brian", 20, "Speed", "PoorVision");
        assertEquals("Brian", superHero.getName());
    }

    @Test
    void getsIntegerAgeCorrectly() {
        Hero superHero = new Hero("Brian", 20, "Speed", "PoorVision");
        assertEquals(20, superHero.getAge());
    }

    @Test
    void getsStringSuperPowerCorrectly() {
        Hero superHero = new Hero("Brian", 20, "Speed", "PoorVision");
        assertEquals("Speed", superHero.getPower());
    }

    @Test
    void  getsStringOfHeroesWeakness() {
        Hero superHero = new Hero("Brian", 20, "Speed", "PoorVision");
        assertEquals("PoorVision", superHero.getWeakness());
    }

    @Test
    void  checkIfAllHeroStringsAreCorrectlyReturned_true() {
        Hero superHero = new Hero("Brian", 20, "Speed", "PoorVision");
        Hero otherHero = new Hero("Peter", 24, "Flying", "TerribleLanding");
        assertTrue(Hero.getAllInstances().contains(superHero));
        assertTrue(Hero.getAllInstances().contains(otherHero));
    }

    @Test
    void checkAllHeroesAreCorrectlyReturned() {
        Hero superHero = new Hero("Brian", 20, "Speed", "PoorVision");
        Hero otherHero = new Hero("Peter", 24, "Flying", "TerribleLanding");
        assertEquals(2, Hero.getAllInstances().size());
    }

    @Test
    void willDeleteAllHeroes() {
        Hero superHero = new Hero("Brian", 20, "Speed", "PoorVision");
        Hero otherHero = new Hero("Peter", 24, "Flying", "TerribleLanding");
        Hero.clearAllHero();
        assertEquals(0, Hero.getAllInstances().size());
    }

    @Test
    void willDeleteASpecificHero() {
        Hero superHero = new Hero("Brian", 20, "Speed", "PoorVision");
        Hero otherHero = new Hero("Peter", 24, "Flying", "TerribleLanding");
        otherHero.deleteHero();
        assertEquals(1, Hero.getAllInstances().size());
        assertEquals(Hero.getAllInstances().get(0).getId(), 2);
    }

    @Test
    void checkIfAHeroFitsToBeInASquad() {
        Hero superHero = new Hero("Brian", 20, "Speed", "PoorVision");
        assertFalse(superHero.isSquadMember());
    }

    @Test
    void findAHeroById() {
        Hero superHero = new Hero("Brian", 20, "Speed", "PoorVision");
        Hero newHero = new Hero("Peter", 24, "Flying", "TerribleLanding");
        Hero searchHero = Hero.findById(1);
        Hero searchNewHero = Hero.findById(2);
        assertEquals(superHero, searchHero);
        assertEquals(newHero, searchNewHero);
    }
}
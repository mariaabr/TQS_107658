/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    // @Disabled("TODO revise test logic")
    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

    // o que se passa aqui? é pelo tamanho do setB?
    //     setB.add(11);
    //     assertTrue(setB.contains(11), "add: added element not found in set.");
    //     assertEquals(7, setB.size(), "add: elements count not as expected.");
    }

    // @Disabled("TODO revise to test the construction from invalid arrays")
    @Test
    public void testAddFromBadArray() {
        int[] elems = new int[]{10, -20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }

    @DisplayName("verify if a set is created empty")
    @Test
    public void testEmptySet() {
        
        assertEquals(0, setA.size());
    }

    @DisplayName("verify if the bounded set is full")
    @Test
    public void testBoundedSetSize() {
        int element = 11;

        assertAll(
            () -> assertEquals(6, setB.size()),
            () -> assertThrows(IllegalArgumentException.class, () -> setB.add(element))
        );
    }

    @DisplayName("verify if an element is duplicated")
    @Test
    public void testDuplicatedValue() {
        int element = 20;

        assertAll(
            () -> assertTrue(setB.contains(element)),
            () -> assertFalse(setA.contains(element)),
            () -> assertFalse(setC.contains(element)),
            () -> assertThrows(IllegalArgumentException.class, () -> setB.add(element))
        );
    }
}
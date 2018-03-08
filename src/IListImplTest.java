import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class IListImplTest {
    private IList<String> liste;

    @BeforeEach
    void emptyListInit() {
        liste = new IListImpl<>(); // Lager en ny, tom liste før hver test
    }

    /**
     * Asserter at NoSuchElementException blir kastet hvis man kaller first() på en tom liste.
     */
    @Test
    void oppgave1_1_first() {
        assertThrows(NoSuchElementException.class, liste::first);
    }

    /**
     * Asserter at NoSuchElementException blir kastet hvis man kaller rest() på en tom liste.
     */
    @Test
    void oppgave1_1_rest() {
        assertThrows(NoSuchElementException.class, liste::rest);
    }

    /**
     * Asserter først at listen er tom, deretter at add() returnerer true hvis elementet blir lagt til,
     * og deretter at dataen i elementet som ble lagt til blir returnert med first().
     */
    @Test
    void oppgave1_1_add() {
        assertThrows(NoSuchElementException.class, liste::first);
        assertEquals(true, liste.add("addTest"));
        assertEquals("addTest", liste.first());
    }

    /**
     * Asserter først at listen er tom, deretter at put() returnerer true hvis elementet blir lagt til,
     * og deretter at dataen i elementet som ble lagt til blir returnert med first().
     */
    @Test
    void oppgave1_1_put() {
        assertThrows(NoSuchElementException.class, liste::first);
        assertEquals(true, liste.put("addTest"));
        assertEquals("addTest", liste.first());
    }

    /**
     * Asserter at NoSuchElementException blir kastet hvis man kaller remove() på en tom liste.
     */
    @Test
    void oppgave1_1_remove() {
        assertThrows(NoSuchElementException.class, liste::remove);
    }

    /**
     * Asserter at first() returnerer det første elementet i en liste med ett element.
     */
    @Test
    void oppgave1_2_first() {
        liste.add("test");
        assertEquals("test", liste.first());
    }

    /**
     * Asserter at rest() kaster NoSuchElementException hvis man kun har ett element i listen.
     */
    @Test
    void oppgave1_2_rest() {
        liste.add("test");
        assertThrows(NoSuchElementException.class, liste::rest);
    }

    /**
     * Asserter at add() fungerer når det er ett element i listen.
     */
    @Test
    void oppgave1_2_add() {
        liste.add("test1");
        assertEquals(true, liste.add("test2"));
        assertEquals("test2", liste.rest().first());
    }

    /**
     * Asserter at put() fungerer når det er ett element i lsiten.
     */
    @Test
    void oppgave1_2_put() {
        liste.add("test1");
        assertEquals(true, liste.put("test2"));
        assertEquals("test2", liste.first());
    }

    /**
     * Asserter at remove() fjerner elementet i listen.
     */
    @Test
    void oppgave1_2_remove() {
        liste.add("test1");
        assertEquals("test1", liste.remove());
        assertThrows(NoSuchElementException.class, liste::first);
    }

    /**
     * Asserter at first() returnerer det første elementet i en liste med flere enn ett element.
     */
    @Test
    void oppgave1_3_first() {
        liste.add("test1");
        liste.add("test2");
        liste.add("test3");
        assertEquals("test1", liste.first());
    }

    /**
     * Asserter at rest() lager en ny liste med alle elementene utenom det første.
     */
    @Test
    void oppgave1_3_rest() {
        liste.add("test1");
        liste.add("test2");
        liste.add("test3");
        IList<String> nyListe = liste.rest();
        assertEquals("test2", nyListe.first());
    }

    /**
     * Asserter at add() legger til elementer i en liste som allerede har flere enn ett element i listen.
     */
    @Test
    void oppgave1_3_add() {
        liste.add("test1");
        liste.add("test2");
        assertEquals(true, liste.add("test3"));
        Iterator<String> iterator = liste.iterator();
        iterator.next();
        iterator.next();
        assertEquals("test3", iterator.next());
        assertThrows(NoSuchElementException.class, iterator::next); // TODO: Fjerne denne testen når iterator() har egne tester
    }

    /**
     * Asserter at put() legger til elementer i en liste som allerede har flere enn ett element i listen.
     */
    @Test
    void oppgave1_3_put() {
        liste.put("test3");
        liste.put("test2");
        assertEquals(true, liste.put("test1"));
    }

    /**
     * Asserter at remove() fjerner det først elementet i en liste med flere enn ett element.
     */
    @Test
    void oppgave1_3_remove() {
        liste.add("test1");
        liste.add("test2");
        liste.add("test3");
        assertEquals("test1", liste.remove());
        assertEquals("test2", liste.remove());
        assertEquals("test3", liste.remove());
        assertThrows(NoSuchElementException.class, liste::remove);
    }

    /**
     * Sjekker at remove(Object o) fjerner rett element.
     */
    @Test
    void oppgave2_1() {
        liste.add("test1");
        liste.add("test2");
        liste.add("test3");
        assertEquals(true, liste.remove("test2"));
        assertEquals(true, liste.contains("test1"));
        assertEquals(true, liste.remove("test1"));
        assertEquals("test3", liste.first());
        assertEquals("test3", liste.remove());
    }

    /**
     * Tester at contains(Object) finner objekter som blir holdt i listen, at isEmpty
     * returnerer true kun hvis listen er tom, og at size returner rett størrelse.
     */
    @Test
    void oppgave3_1() {
        assertEquals(true, liste.isEmpty());
        assertEquals(0, liste.size());
        assertEquals(false, liste.contains("test"));
        liste.add("test");
        assertEquals(false, liste.isEmpty());
        assertEquals(1, liste.size());
        assertEquals(true, liste.contains("test"));
        liste.add("test2");
        assertEquals(false, liste.isEmpty());
        assertEquals(2, liste.size());
        assertEquals(true, liste.contains("test2"));
    }
}
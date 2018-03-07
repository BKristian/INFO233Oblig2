import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class IListImplTest {
    private IList<String> emptyList;

    @BeforeEach
    void makeList() {
        emptyList = new IListImpl<>(); // Lager en ny, tom liste før hver test
    }

    /**
     * Asserter at NoSuchElementException blir kastet hvis man kaller first() på en tom liste.
     */
    @Test
    void oppgave1_1_first() {
        assertThrows(NoSuchElementException.class, emptyList::first);
    }

    /**
     * Asserter at NoSuchElementException blir kastet hvis man kaller first() på resultatet av
     * rest() på en tom liste.
     */
    @Test
    void oppgave1_1_rest() {
        IList<String> restListe = emptyList.rest();
        assertThrows(NoSuchElementException.class, restListe::first);
    }

    /**
     * Asserter først at listen er tom, deretter at add() returnerer true hvis elementet blir lagt til,
     * og deretter at dataen i elementet som ble lagt til blir returnert med first().
     */
    @Test
    void oppgave1_1_add() {
        assertThrows(NoSuchElementException.class, emptyList::first);
        assertEquals(true, emptyList.add("addTest"));
        assertEquals("addTest", emptyList.first());
    }

    /**
     * Asserter først at listen er tom, deretter at put() returnerer true hvis elementet blir lagt til,
     * og deretter at dataen i elementet som ble lagt til blir returnert med first().
     */
    @Test
    void oppgave1_1_put() {
        assertThrows(NoSuchElementException.class, emptyList::first);
        assertEquals(true, emptyList.put("addTest"));
        assertEquals("addTest", emptyList.first());
    }

    /**
     * Asserter at NoSuchElementException blir kastet hvis man kaller remove() på en tom liste.
     */
    @Test
    void oppgave1_1_remove() {
        assertThrows(NoSuchElementException.class, emptyList::remove);
    }
}
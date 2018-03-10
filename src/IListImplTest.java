import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

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

    /**
     * Tester at append legger til elementer på slutten av listen, og at prepend legger
     * til elementer på begynnelsen av listen
     */
    @Test
    void oppgave4_1() {
        IList<String> append = new IListImpl<>();
        IList<String> prepend = new IListImpl<>();
        liste.add("test1");
        liste.add("test2");
        append.add("append1");
        append.add("append2");
        prepend.add("prepend1");
        prepend.add("prepend2");
        liste.append(append);
        liste.prepend(prepend);
        Iterator<String> iterator = liste.iterator();
        assertEquals("prepend1", iterator.next());
        assertEquals("prepend2", iterator.next());
        iterator.next();
        iterator.next();
        assertEquals("append1", iterator.next());
        assertEquals("append2", iterator.next());
    }

    /**
     * Tester at concat legger sammen forskjellige lister.
     */
    @Test
    void oppgave5_1() {
        IList<String> concat1 = new IListImpl<>();
        IList<String> concat2 = new IListImpl<>();
        liste.add("test1");
        liste.add("test2");
        concat1.add("test3");
        concat1.add("test4");
        concat2.add("test5");
        concat2.add("test6");
        IList<String> concatListe = liste.concat(concat1, concat2);
        Iterator<String> iterator = concatListe.iterator();
        int i = 1;
        while(iterator.hasNext()) {
            assertEquals("test" + i, iterator.next());
            ++i;
        }
    }

    /**
     * Tester at iterator går korrekt gjennom listen, og at den kaster NoSuchElementException hvis
     * man prøver å gå videre i en liste når man er på slutten.
     */
    @Test
    void oppgave7_1() {
        liste.add("test1");
        liste.add("test2");
        Iterator<String> iterator = liste.iterator();
        int i = 1;
        while(iterator.hasNext()) {
            assertEquals("test" + i, iterator.next());
            ++i;
        }
        assertThrows(NoSuchElementException.class, iterator::next);
    }


    /**
     * Basically samme test som i de vedlagte testene, men med String i stedet.
     */
    @Test
    void oppgave8_1() {
        IList<String> list2 = new IListImpl<>();
        List<String> values2 = Arrays.asList("String", "Blueberry", "Red", "Ananas", "X-men");

        for (String value : values2) {
            list2.add(value);
        }
        list2.sort(Comparator.naturalOrder());

        Iterator<String> iterator = list2.iterator();
        assertEquals("Ananas", iterator.next());
        assertEquals("Blueberry", iterator.next());
        assertEquals("Red", iterator.next());
        assertEquals("String", iterator.next());
        assertEquals("X-men", iterator.next());
    }

    /**
     * Basically samme test som i de vedlagte testene, men med String i stedet.
     */
    @Test
    void oppgave9_1() {
        List<String> values = Arrays.asList("String", "Blueberry", "Red", "Ananas", "X-men");

        IList<String> list = new IListImpl<>();
        for (String value : values) {
            list.add(value);
        }

        list.filter(n -> n.equals("Red"));


        while(list.size() > 0) {
            String n = list.remove();
            if (n.equals("Red")) {
                fail("List contains filtered out elements.");
            }
        }
    }

    /**
     *
     */
    @Test
    void oppgave10_1() {

    }

    /**
     *
     */
    @Test
    void oppgave11_1() {

    }
}
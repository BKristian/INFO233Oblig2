import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public interface IList<E> extends Iterable<E> {
    /**
     * ,* Gir det første elementet i listen.
     * ,*
     * ,* @return Det første elementet i listen.
     * ,* @throws NoSuchElementException Hvis listen er tom.
     * ,
     */
    E first() throws NoSuchElementException;

    /**
     * ,* Returnerer alle elementene i listen bortsett fra det
     * ,* første.
     * ,*
     * ,* @return Resten av listen.
     * ,
     */
    IList<E> rest();

    /**
     * ,* Legger til et element på slutten av listen.
     * ,
     */
    boolean add(E elem);

    /**
     * ,* Legger til et element på begynnelsen av listen.
     * ,
     */
    boolean put(E elem);

    /**
     * ,* Fjerner det første elementet i listen.
     * ,*
     * ,* @return Det første elementet i listen.
     * ,* @throws NoSuchElementException Hvis listen er tom.
     * ,
     */
    E remove() throws NoSuchElementException;

    /**
     * ,* Fjerner det angitte objektet fra listen.
     * ,*
     * ,* @param o Objektet som skal fjernes.
     * ,* @return true hvis et element ble fjernet, false
     * ,* ellers.
     * ,
     */
    boolean remove(Object o);

    /**
     * ,* Sjekker om et element er i listen.
     * ,*
     * ,* @param o objektet vi sjekker om er i listen.
     * ,* @return true hvis objektet er i listen, false ellers.
     * ,
     */
    boolean contains(Object o);

    /**
     * ,* Sjekker om listen er tom.
     * ,*
     * ,* @return true hvis listen er tom, false ellers.
     * ,
     */
    boolean isEmpty();

    /**
     * ,* Legger til alle elementene i den angitte listen på
     * ,* slutten av listen.
     * ,*
     * ,* @param listen som blir lagt til.
     * ,
     */
    void append(IList<? extends E> list);

    /**
     * ,* Legger til alle elementene i den angitte listen på
     * ,* begynnelsen av listen.
     * ,*
     * ,* @param list listen som blir lagt til
     * ,
     */
    void prepend(IList<? extends E> list);

    /**
     * ,* Slår sammen flere lister
     * ,*
     * ,* @param lists listene som skal slås sammen
     * ,* @return Ny liste med alle elementene fra listene som
     * ,* skal slås sammen.
     * ,
     */
    IList<E> concat(IList<? extends E>... lists);

    /**
     * ,* Sorterer listen ved hjelp av en
     * ,* sammenligningsfunksjon
     * ,* @param c sammenligningsfunksjon som angir rekkefølgen
     * ,* til elementene i listen
     * ,
     */
    void sort(Comparator<? super E> c);

    /**
     * ,* Fjerner elementer fra listen som svarer til et
     * ,* predikat.
     * ,* @param filter predikat som beskriver hvilken
     * ,* elementer som skal fjernes.
     * ,
     */
    void filter(Predicate<? super E> filter);


    /**
     * ,* Kjører en funksjon over hvert element i listen
     * ,*
     * ,* @param f en funksjon fra typen til elementene i
     * ,* listen til en annen type
     * ,* @return En liste over elementene som funksjonen
     * ,* returnerer
     * ,
     */
    <U> IList<U> map(Function<? super E, ? extends U> f);

    /**
     * ,* Slår sammen alle elementene i listen ved hjelp av en
     * ,* kombinasjonsfunksjon.
     * ,*
     * ,* @param t Det første elementet i sammenslåingen
     * ,* @param f funksjonen som slår sammen elementene
     * ,* @return Den akkumulerte verdien av sammenslåingene
     * ,
     */
    <T> T reduce(T t, BiFunction<T, ? super E, T> f);

    /**
     * ,* Gir størrelsen på listen
     * ,*
     * ,* @return Størrelsen på listen
     * ,
     */
    int size();

    /**
     * ,* Fjerner alle elementene i listen.
     * ,
     */
    void clear();
}

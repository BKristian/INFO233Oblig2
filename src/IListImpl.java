import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class IListImpl<E> implements IList<E> {
    private Node first; // Starten på listen
    private Node last;  // Slutten på listen
    private int size;   // Antall noder i listen

    public IListImpl() {
        first = new Node();
        last = new Node();
        first.next = last;
        last.previous = first;
        size = 0;
    }

    private class Node {
        private E data;        // Dataen noden holder
        private Node next;     // Neste node i listen
        private Node previous; // Forrige node i listen
    }

    @Override
    public E first() throws NoSuchElementException {
        if(size == 0) {
            throw new NoSuchElementException();
        }
        return first.next.data;
    }

    @Override
    public IList<E> rest() {
        IList<E> liste = new IListImpl<>();
        return null;
    }

    @Override
    public boolean add(E elem) {
        return false;
    }

    @Override
    public boolean put(E elem) {
        return false;
    }

    @Override
    public E remove() throws NoSuchElementException {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void append(IList<? extends E> list) {

    }

    @Override
    public void prepend(IList<? extends E> list) {

    }

    @Override
    public IList<E> concat(IList<? extends E>... lists) {
        return null;
    }

    @Override
    public void sort(Comparator<? super E> c) {

    }

    @Override
    public void filter(Predicate<? super E> filter) {

    }

    @Override
    public <U> IList<U> map(Function<? super E, ? extends U> f) {
        return null;
    }

    @Override
    public <T> T reduce(T t, BiFunction<T, ? super E, T> f) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}

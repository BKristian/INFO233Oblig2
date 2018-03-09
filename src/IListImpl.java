import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class IListImpl<E> implements IList<E> {
    IList<E> liste;
    private Node first; // Starten på listen, er tom
    private Node last;  // Slutten på listen, er tom
    private int size;   // Antall noder i listen

    public IListImpl() {
        first = new Node();
        last = new Node();
        first.next = last;
        last.previous = first;
        size = 0;
    }

    public IListImpl(E elem) {
        liste = new IListImpl<>();
        liste.add(elem);
    }

    public IListImpl(E elem, IList<E> list) {
        liste = new IListImpl<>();
        liste.add(elem);
    }

    private class Node {
        private E data;        // Dataen noden holder
        private Node next;     // Neste node i listen
        private Node previous; // Forrige node i listen
    }

    @Override
    public E first() throws NoSuchElementException {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.next.data;
    }

    @Override
    public IList<E> rest() throws NoSuchElementException {
        if(size == 1) {
            throw new NoSuchElementException(); // Passer at rest() kun kan brukes hvis listen har mer enn ett element
        }
        IList<E> liste = new IListImpl<>();
        Iterator<E> iterator = this.iterator();
        while(iterator.hasNext()) {
            liste.add(iterator.next());
        }
        liste.remove();
        return liste;
    }

    @Override
    public boolean add(E elem) {
        Node newNode = new Node();
        newNode.data = elem;
        newNode.next = last;
        newNode.previous = last.previous;
        newNode.previous.next = newNode;
        last.previous = newNode;
        ++size;
        return true;
    }

    @Override
    public boolean put(E elem) {
        Node newNode = new Node();
        newNode.data = elem;
        newNode.previous = first;
        newNode.next = first.next;
        newNode.next.previous = newNode;
        first.next = newNode;
        ++size;
        return true;
    }

    @Override
    public E remove() throws NoSuchElementException {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        Node toRemove = first.next;
        first.next = toRemove.next;
        toRemove.next.previous = first;
        toRemove.next = null;
        toRemove.previous = null;
        --size;
        return toRemove.data;
    }

    @Override
    public boolean remove(Object o) {
        Node node = first;
        while(node.next != last) {
            node = node.next;
            if(node.data == o) {
                node.previous.next = node.next;
                node.next.previous = node.previous;
                node.next = null;
                node.previous = null;
                --size;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        Iterator<E> iterator = this.iterator();
        while(iterator.hasNext()) {
            if(iterator.next() == o) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void append(IList<? extends E> list) {
        Iterator<? extends E> iterator = list.iterator();
        while(iterator.hasNext()) {
            this.add(iterator.next());
        }
    }

    @Override
    public void prepend(IList<? extends E> list) {
        Iterator<? extends E> iterator = list.iterator();
        IList<E> tempList = new IListImpl<>();
        while(iterator.hasNext()) {
            tempList.put(iterator.next()); // put() legger alltid elementet inn først, så listen blir reversert
        }
        iterator = tempList.iterator();
        while(iterator.hasNext()) {
            this.put(iterator.next()); // Den reverserte listen blir lagt til med put(), som legger elementene inn i rett rekkefølge
        }
    }

    @Override
    public IList<E> concat(IList<? extends E>... lists) {
        IList<E> output = new IListImpl<>();
        Iterator<? extends E> iterator = this.iterator();
        while(iterator.hasNext()) {
            output.add(iterator.next());
        }
        for(IList<? extends E> list : lists) {
            iterator = list.iterator();
            while(iterator.hasNext()) {
                output.add(iterator.next());
            }
        }
        return output;
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
        return size;
    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node current = first.next;

            @Override
            public boolean hasNext() {
                return current != last;
            }

            @Override
            public E next() {
                if(!hasNext()) {
                    throw new NoSuchElementException();
                }
                E toReturn = current.data;
                current = current.next;
                return toReturn;
            }
        };
    }
}
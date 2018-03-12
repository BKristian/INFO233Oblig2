import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class IListImpl<E> implements IList<E> {
    private IList<E> myList;
    private Node head; // Starten på listen, er tom
    private Node tail;  // Slutten på listen, er tom
    private int size;   // Antall noder i listen

    public IListImpl() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.previous = head;
        size = 0;
    }

    public IListImpl(E elem) {
        myList = new IListImpl<>();
        myList.add(elem);
    }

    public IListImpl(E elem, IList<E> list) {
        myList = new IListImpl<>();
        myList.add(elem);
        myList.append(list);
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
        return head.next.data;
    }

    @Override
    public IList<E> rest() throws NoSuchElementException {
        if(size == 1) {
            throw new NoSuchElementException(); // Passer at rest() kun kan brukes hvis listen har mer enn ett element
        }
        IList<E> liste = new IListImpl<>();
        for (E elem : this) {
            liste.add(elem);
        }
        liste.remove();
        return liste;
    }

    @Override
    public boolean add(E elem) {
        Node newNode = new Node();
        newNode.data = elem;
        newNode.next = tail;
        newNode.previous = tail.previous;
        newNode.previous.next = newNode;
        tail.previous = newNode;
        ++size;
        return true;
    }

    @Override
    public boolean put(E elem) {
        Node newNode = new Node();
        newNode.data = elem;
        newNode.previous = head;
        newNode.next = head.next;
        newNode.next.previous = newNode;
        head.next = newNode;
        ++size;
        return true;
    }

    @Override
    public E remove() throws NoSuchElementException {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        Node toRemove = head.next;
        head.next = toRemove.next;
        toRemove.next.previous = head;
        --size;
        return toRemove.data;
    }

    @Override
    public boolean remove(Object o) {
        Node node = head;
        while(node.next != tail) {
            node = node.next;
            if(node.data == o) {
                node.previous.next = node.next;
                node.next.previous = node.previous;
                --size;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        for (E elem : this) {
            if(elem == o) {
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
        for (E elem : list) {
            this.add(elem);
        }
    }

    @Override
    public void prepend(IList<? extends E> list) {
        IList<E> tempList = new IListImpl<>();
        for (E e : list) {
            tempList.put(e); // put() legger alltid elementet inn først, så listen blir reversert
        }
        for(E e: tempList) {
            this.put(e); // Den reverserte listen blir lagt til med put(), som legger elementene inn i rett rekkefølge
        }
    }

    @Override
    public IList<E> concat(IList<? extends E>... lists) {
        IList<E> output = new IListImpl<>();
        for (E elem : this) {
            output.add(elem);
        }
        for(IList<? extends E> list : lists) {
            for (E elem : list) {
                output.add(elem);
            }
        }
        return output;
    }

    @Override
    public void sort(Comparator<? super E> c) {
        if(size < 2) {
            throw new NoSuchElementException();
        }
        IListImpl<E> sorted = this.mergeSort(this, c);
        this.clear();
        this.append(sorted);
        /*
        // Bubblesort
        Node current = head.next;
        Node next;
        while(current.next != tail) {
            next = current.next;
            while(next != tail) {
                if (c.compare(current.data, next.data) > 0) {
                    E temp = next.data;
                    next.data = current.data;
                    current.data = temp;
                }
                next = next.next;
            }
            current = current.next;
        }
        */
    }

    private IListImpl<E> mergeSort(IListImpl<E> list, Comparator<? super E> c) {
        if (list.size() < 2) {
            return list;
        }
        IListImpl<E> left   = new IListImpl<>();
        IListImpl<E> right  = new IListImpl<>();
        IListImpl<E> result;

        int middle = list.size() / 2;

        int added = 0;
        for(E item: list) {
            if(added < middle) {
                left.add(item);
                ++added;
            } else {
                right.add(item);
            }
        }

        left = mergeSort(left, c);
        right = mergeSort(right, c);

        result = merge(left, right,c );
        return result;
    }

    private IListImpl<E> merge(IListImpl<E> left, IListImpl<E> right, Comparator<? super E> c) {
        IListImpl<E> result = new IListImpl<>();
        while((left.size() > 0) && (right.size() > 0)) {
            if (c.compare(left.first(), right.first()) < 0) {
                result.add(left.remove());
            } else {
                result.add(right.remove());
            }
        }

        if(left.size() > 0) {
            result.append(left);
        } else {
            result.append(right);
        }

        return result;
    }

    @Override
    public void filter(Predicate<? super E> filter) {
        for (E e : this) {
            if(filter.test(e)) {
                this.remove(e);
            }
        }
    }

    @Override
    public <U> IList<U> map(Function<? super E, ? extends U> f) {
        IList<U> list = new IListImpl<>();
        for (E e : this) {
            list.add(f.apply(e));
        }
        return list;
    }

    @Override
    public <T> T reduce(T t, BiFunction<T, ? super E, T> f) {
        T output = t;
        for (E e : this) {
            output = f.apply(output, e);
        }
        return output;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head.next = tail;
        tail.previous = head;
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node current = head.next;

            @Override
            public boolean hasNext() {
                return current != tail;
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

            // Bruker ikke remove(Object) for å være sikker på at current blir fjernet, og ikke et annet element som har samme data som current.
            @Override
            public void remove() {
                current.previous.next = current.next;
                current.next.previous = current.previous;
                --size;
            }
        };
    }
}
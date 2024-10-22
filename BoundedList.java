import java.util.Iterator;

public interface BoundedList<E> extends Iterable<E> {
    int capacity();
    int size();
    void add(E element);
    E get(int index);
    E set(int index, E element);
    int indexOf(Object o);
    int lastIndexOf(Object o);
    void clear();

    // Additional methods
    void add(int index, E element);  // Insert at index
    E remove(int index);  // Remove element by index
    boolean remove(Object o);  // Remove first occurrence of an object

    // Default methods
    default boolean isEmpty() {
        return size() == 0;
    }

    default boolean isFull() {
        return size() == capacity();
    }

    default E getFirst() {
        return get(0);
    }

    default E getLast() {
        return get(size() - 1);
    }

    default E setFirst(E element) {
        return set(0, element);
    }

    default E setLast(E element) {
        return set(size() - 1, element);
    }

    default boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    default void addAll(BoundedList<? extends E> other) {
        if (this.size() + other.size() > this.capacity()) {
            throw new IllegalStateException("Not enough capacity to add all elements");
        }
        for (int i = 0; i < other.size(); i++) {
            this.add(other.get(i));
        }
    }

    default void copyTo(BoundedList<? super E> other) {
        if (other.size() + this.size() > other.capacity()) {
            throw new IllegalStateException("Not enough capacity to copy all elements");
        }
        for (int i = 0; i < this.size(); i++) {
            other.add(this.get(i));
        }
    }
}
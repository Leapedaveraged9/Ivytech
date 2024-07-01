public class TwoWayLinkedList<E> extends MyLinkedList<E> {
    private Node<E> head, tail;
    private int size = 0; // Number of elements in the list

    public TwoWayLinkedList() {
    }

    public TwoWayLinkedList(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            add(objects[i]);
        }
    }

    @Override
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    @Override
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    @Override
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e); // Create a new node
        newNode.next = head; // Link the new node with the head
        if (head != null) head.previous = newNode;
        head = newNode; // The new node becomes the head
        size++; // Increase list size

        if (tail == null) // The new node is the only node in the list
            tail = head;
    }

    @Override
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e); // Create a new node for e
        if (tail == null) {
            head = tail = newNode; // The only node in list
        } else {
            tail.next = newNode; // Link the new node with the last node
            newNode.previous = tail;
            tail = newNode; // tail now points to the last node
        }
        size++; // Increase size
    }

    @Override
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<>(e);
            (current.next).next = temp;
            temp.previous = current.next;
            (current.next).previous = current;
            size++;
        }
    }

    @Override
    public E removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node<E> temp = head;
            head = head.next;
            if (head != null) head.previous = null;
            size--;
            if (head == null) tail = null;
            return temp.element;
        }
    }

    @Override
    public E removeLast() {
        if (size == 0) {
            return null;
        } else {
            Node<E> temp = tail;
            tail = tail.previous;
            if (tail != null) tail.next = null;
            size--;
            if (tail == null) head = null;
            return temp.element;
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = temp.next;
            temp.next.previous = current;
            size--;
            return temp.element;
        }
    }

    @Override
    public java.util.ListIterator<E> listIterator() {
        return new LinkedListIterator();
    }

    @Override
    public java.util.ListIterator<E> listIterator(int index) {
        LinkedListIterator iterator = new LinkedListIterator();
        for (int i = 0; i < index; i++)
            iterator.next();
        return iterator;
    }

    private class LinkedListIterator implements java.util.ListIterator<E> {
        private Node<E> current = head; // Current index

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public E next() {
            E e = current.element;
            current = current.next;
            return e;
        }

        @Override
        public boolean hasPrevious() {
            return (current != null && current.previous != null);
        }

        @Override
        public E previous() {
            current = (current == null) ? tail : current.previous;
            return (current != null) ? current.element : null;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException("Not supported.");
        }
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E e) {
            element = e;
        }
    }

    @Override
    public int size() {
        return size;
    }
}

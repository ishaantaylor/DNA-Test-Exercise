/**
 * A doubly linked linked list.
 */

import java.lang.Iterable;

public class DoubleLinkedList<T> implements Iterable<T> {
  
  /** a reference to the first node of the double linked list */
  private DLNode<T> head;
  
  /** a reference to the last node of a double linked list */
  private DLNode<T> tail;
  
  /** Create an empty double linked list. */
  public DoubleLinkedList() {
    head = tail = null;
  }
  
  /** 
   * Returns true if the list is empty.
   * @return  true if the list has no nodes
   */
  public boolean isEmpty() {
    return (head == null);
  }
  
  /**
   * Returns the reference to the first node of the linked list.
   * @return the first node of the linked list
   */
  protected DLNode<T> getHead() {
    return head;
  }
  
  /**
   * Sets the first node of the linked list.
   * @param node  the node that will be the head of the linked list.
   */
  protected void setHead(DLNode<T> node) {
    head = node;
  }
  
  /**
   * Returns the reference to the last node of the linked list.
   * @return the last node of the linked list
   */
  protected DLNode<T> getTail() {
    return tail;
  }
  
  /**
   * Sets the last node of the linked list.
   * @param node the node that will be the last node of the linked list
   */
  protected void setTail(DLNode<T> node) {
    tail = node;
  }
  
  /*----------------------------------------*/
  /* METHODS TO BE ADDED DURING LAB SESSION */
  /*----------------------------------------*/
  
  /**
   * Add an element to the head of the linked list.
   * @param element  the element to add to the front of the linked list
   */
  public void addToFront(T element) {
    if (this.getHead() != null) {
      DLNode<T> newNode = new DLNode<T>(element, null, head);      
      setHead(newNode);
    } else if (this.getHead() == null) {
      DLNode<T> newNode = new DLNode<T>(element, null, null);
      setHead(newNode);
      setTail(getHead());
    }
  }
  
  /**
   * Add an element to the tail of the linked list.
   * @param element  the element to add to the tail of the linked list
   */
  public void addToBack(T element) {
    if (getTail() != null) {
      setTail(new DLNode<T>(element, getTail(), null));
    } else if (getHead() == null && getTail() == null) {
      setTail(new DLNode<T>(element, null, null));
      setHead(getTail());
    } 
  } 
   
  
  /**
   * Required by the Iterable interface. It's required for the "for-each" loop.
   * @return an iterator for the linked list
   */
  public java.util.Iterator<T> iterator() {
    return new DLLIterator();
  }
  
  /**
   * This method overrides the equals method of Object. It determines if two double linked
   * lists are equal.
   *
   * @param DoubleLinkedList<T> dll
   * @return true if the linked lists are equal.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof DoubleLinkedList<?> && // if the lists have the same types
        this instanceof DoubleLinkedList<?>) { 
      DoubleLinkedList<?> dll = (DoubleLinkedList<?>) obj;
                                              // if both lists are empty
      if ((getHead() == null) && (dll.getHead() == null))
        return true;
                                              // if one list is empty
      else if ((getHead() == null) || (dll.getHead() == null))
        return false;
      else {
        int countDLL = 0;
        int countTHIS = 0;
        /** This loops counts the nodes in the doubly linked list. */
        for (DLNode<?> currentNode = dll.getHead(); currentNode != null; currentNode = currentNode.getNext()) {
          countDLL += 1;
        }
        /** This loop counts the node in the "this" linked list. */
        for (DLNode<T> currentNode = this.getHead(); currentNode != null; currentNode = currentNode.getNext()) {
          countTHIS += 1;
        }
        if (countDLL == countTHIS) {
          DLNode<T> listptr = this.getHead();
          DLNode<?> listptr2 = dll.getHead();
          /** This loop tests if each element is equal. */
          while (listptr != null || listptr2 != null) {
            if (!listptr.getElement().equals(listptr2.getElement()))
              return false;
            listptr = listptr.getNext();       // iterate
            listptr2 = listptr2.getNext();
          } return true;
        } return false;
      }
    } return false;
  }


  /**
   * This method appends the node of the linked list to the ends of the nodes
   * of this list.
   * 
   * @param DoubleLinkedList<T> dll
   */
  public void append(DoubleLinkedList<T> dll) {
    if (dll.toString() != "")
      this.getTail().setNext(dll.getHead());
  }

  /**  This inner class creates the iterator.  */
  public class DLLIterator implements java.util.Iterator<T> {
    /** keep track of where we are in the linked list */
    public DLNode<T> listptr = getHead();
    /**
     * This method determines if the current node in the list is
     * pointing to a next one.
     * 
     * @param DLNode<T> current node
     * @return true if the current is pointing to a "next" node.
     */
    public boolean hasNext() {
      return (listptr.getNext() != null);
    }
    
    /**
     * Returns the next element of iteration.
     * 
     * @return T next element
     * @throws NoSuchElementException if there is no next element.
     */
    public T next() {
      if (!hasNext())
        throw new java.util.NoSuchElementException();
      T element = listptr.getElement();
      listptr = listptr.getNext();
      return element;
    }
    
    /** The remove method of Iterator<T> 
      * 
      * @throws UnsupportedOperationException
      */
    public void remove() throws UnsupportedOperationException {
      throw new UnsupportedOperationException();
    };  
  }
}
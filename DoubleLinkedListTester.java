import junit.framework.TestCase;

/** A class that tests the methods of the DoubleLinkedList class. */
public class DoubleLinkedListTester extends TestCase {
  
  /** Tests the addToFront method of DoubleLinkedList. */
  public void testAddToFront() {
    DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
    list.addToFront(3);
    list.addToFront(2);
    list.addToFront(1);
    DLNode<Integer> head = list.getHead();
    DLNode<Integer> tail = list.getTail();
    
    assertEquals("Testing first node of list", new Integer(1), head.getElement());
    assertEquals("Testing second node of list", new Integer(2), head.getNext().getElement());
    assertEquals("Testing third node of list", new Integer(3), head.getNext().getNext().getElement());
    assertEquals("Testing end of list", null, head.getNext().getNext().getNext());
    
    assertEquals("Testing node at back of list", new Integer(3), tail.getElement());
    assertEquals("Testing next to last node", new Integer(2), tail.getPrevious().getElement());
    assertEquals("Testing third to last node", new Integer(1), tail.getPrevious().getPrevious().getElement());
    assertEquals("Testing front of list", null, tail.getPrevious().getPrevious().getPrevious());
  }

  /** Tests the addToBack method of DoubleLinkedList. */
  public void testAddToBack() {
    DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
    list.addToBack(1);
    list.addToBack(2);
    list.addToBack(3);
    DLNode<Integer> head = list.getHead();
    DLNode<Integer> tail = list.getTail();
      
    assertEquals("Testing last node of list", new Integer(3), tail.getElement());
    assertEquals("Testing next to last node", new Integer(2), tail.getPrevious().getElement());
    assertEquals("Testing third to last node", new Integer(1), tail.getPrevious().getPrevious().getElement());
    assertEquals("Testing front of list", null, tail.getPrevious().getPrevious().getPrevious());
    
    assertEquals("Testing node at front of list", new Integer(1), head.getElement());
    assertEquals("Testing second node of list", new Integer(2), head.getNext().getElement());
    assertEquals("Testing third node of list", new Integer(3), head.getNext().getNext().getElement());
    assertEquals("Testing end of list", null, head.getNext().getNext().getNext());
  }
  
  /** Tests the .equals method of DoubleLinkedList */
  public void testEquals() {
    DoubleLinkedList<DNA.Base> list = new DoubleLinkedList<DNA.Base>();
    DoubleLinkedList<DNA.Base> list2 = new DoubleLinkedList<DNA.Base>();
    assertEquals("Using .equals with both empty lists.", true, list.equals(list2));
    list.addToFront(DNA.Base.A);
    list.addToFront(DNA.Base.T);
    list.addToFront(DNA.Base.C);
    list.addToFront(DNA.Base.G);
    assertEquals("Using .equals with one empty list.", false, list.equals(list2));
    list2.addToFront(DNA.Base.A);
    list2.addToFront(DNA.Base.T);
    list2.addToFront(DNA.Base.C);
    assertEquals("Using .equals with uneven lists.", false, list.equals(list2));
    list2.addToFront(DNA.Base.C);
    assertEquals("Using .equals with different elements.", false, list.equals(list2));
    DoubleLinkedList<DNA.Base> list3 = new DoubleLinkedList<DNA.Base>();
    list3.addToFront(DNA.Base.A);
    list3.addToFront(DNA.Base.T);
    list3.addToFront(DNA.Base.C);
    list3.addToFront(DNA.Base.G);
    assertEquals("Using .equals with equal lists.", true, list.equals(list3));
    Object o = new Object();
    assertEquals("Using .equals with different obejcts.", false, list.equals(o));
  }

  /** Tests the append method of DoubleLinkedList */
  public void testAppend() {
    DoubleLinkedList<Integer> list1 = new DoubleLinkedList<Integer>();
    DoubleLinkedList<Integer> list2 = new DoubleLinkedList<Integer>();
    try {
      list1.append(list2);
      fail("Comparing empty lists should return a NullPointerException.");
    }
    catch (NullPointerException e) { /** Pass */ }
    list1.addToBack(1);
    list1.addToBack(2);
    list1.append(list2);
    assertEquals("Using append on one empty list", new Integer(1) , list1.getHead().getElement());
    assertEquals("Using append on one empty list", new Integer(2) , list1.getHead().getNext().getElement());
    list2.addToBack(3);
    list1.append(list2);
    assertEquals("Using append on uneven list", new Integer(2), list1.getTail().getElement());
    assertEquals("Using append on uneven list", new Integer(1), list1.getHead().getElement());
    list2.addToBack(4);
    list1.append(list2);
    assertEquals("Using append on even lists", new Integer(2), list1.getTail().getElement());
    assertEquals("Using append on even lists", new Integer(1), list1.getTail().getPrevious().getElement());
    assertEquals("Using append on even lists", new Integer(1), list1.getHead().getElement());
    assertEquals("Using append on even lists", new Integer(2), list1.getHead().getNext().getElement());
  }
}
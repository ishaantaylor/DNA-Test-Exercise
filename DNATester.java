import junit.framework.TestCase;

/**
 * A class that tests the methods of the DoubleLinkedList class.
 */
public class DNATester extends TestCase {
  
  /** Tests the toString method of DNA.  */
  public void testToString() {
    DNA d = new DNA();
    assertEquals("Test toString with empty list", "", d.toString());
    d.addToBack(DNA.Base.A);
    assertEquals("Test toString with one node", "A", d.toString());
    d.addToBack(DNA.Base.C);
    assertEquals("Test toString with two nodes", "AC", d.toString());
    d.addToBack(DNA.Base.T);
    assertEquals("Test toString with three nodes", "ACT", d.toString());
  }
  
  /** Tests the splice method of DNA */
  public void testSplice() {
    try { 
      DNA d1 = DNA.string2DNA("");
      DNA d2 = null;
      d1.splice(d2, 1);
      fail("Splicing with more bases than nodes didn't throw a null pointer exception.");
    }
    catch (NullPointerException e) { /** Pass */ }
    catch (Exception e) {
      fail("Splicing with more bases than nodes threw the wrong exception");
    }
    DNA d1 = null;
    DNA d2 = null;
    d1 = DNA.string2DNA("TACTGGTA");
    try {
      d1.splice(d2, 1);
      fail("Cannot splice with an empty string");
    }
    catch (NullPointerException e) { /** Pass */ }
      ;
    d2 = DNA.string2DNA("GGTACCCC");
    d1.splice(d2, 4);
    assertEquals("Test splice with 4 overlapping bases (same length)", DNA.string2DNA("TACTGGTACCCC"), d1);
    d2 = DNA.string2DNA("GGTACCCCTAG");
    d1.splice(d2, 8);
    assertEquals("Test splice with 4 overlapping bases (different length)", DNA.string2DNA("TACTGGTACCCCTAG"), d1);
    d1.splice(d2, 0);
    assertEquals("Test splice with 0 overlapping bases (different length)", DNA.string2DNA("TACTGGTACCCCTAG"), d1);
  } 
  
  /** This method tests the string2DNA static method in DNA */
  public void testString2DNA() {
    try {
      DNA d3 = DNA.string2DNA("abc");
      assertEquals("Test string2DNA with an 'wrong' string.", null, d3.getHead());
      fail("Any characters that do not match the enum Base should throw an exception");
    }
    catch (IllegalArgumentException e) { /** Pass */ }
    DNA d3 = DNA.string2DNA("");
    assertEquals("Test string2DNA with an empty string.", null, d3.getHead());
    DNA d2 = DNA.string2DNA("G");
    assertEquals("Test string2DNA with one character in a string.", DNA.Base.G, d2.getHead().getElement());
    DNA d = DNA.string2DNA("TATAGCA");
    assertEquals("Test string2DNA by seeing the elements of the 'many' linked list.", DNA.Base.T, d.getHead().getElement());
    assertEquals("Test string2DNA by seeing the elements of the 'many' linked list.", DNA.Base.A, d.getHead().getNext().getElement());
    assertEquals("Test string2DNA by seeing the elements of the 'many' linked list.", DNA.Base.T, d.getHead().getNext().getNext().getElement());
    assertEquals("Test string2DNA by seeing the elements of the 'many' linked list.", DNA.Base.A, d.getTail().getElement());
    assertEquals("Test string2DNA by seeing the elements of the 'many' linked list.", DNA.Base.C, d.getTail().getPrevious().getElement());
    assertEquals("Test string2DNA by seeing the elements of the 'many' linked list.", DNA.Base.G, d.getTail().getPrevious().getPrevious().getElement());
  }
  
  /** This method tests the overlaps method from DNA */
  public void testOverlaps() {
    DNA d3 = DNA.string2DNA("");
    DNA d4 = DNA.string2DNA("");
    assertEquals("Test overlap with empty lists.", false, DNA.overlaps(d3, d4, 1));
    d3 = DNA.string2DNA("T");
    assertEquals("Test overlap with one empty list", false, DNA.overlaps(d3, d4, 1));
    d4 = DNA.string2DNA("T");
    assertEquals("Test overlaps with length as 1 and overlapping", false, DNA.overlaps(d3, d4, 1));
    d3 = DNA.string2DNA("C");
    assertEquals("Test overlaps with length as 1 and not overlapping", false, DNA.overlaps(d3, d4, 1));
    DNA d1 = DNA.string2DNA("TATGCG");
    DNA d2 = DNA.string2DNA("GTGGTG");
    assertEquals("Test overlaps with even length, non-overlapping linked lists.", false, DNA.overlaps(d1, d2, 3));
    d2 = DNA.string2DNA("GCGGTG");
    assertEquals("Test overlaps with even length, overlapping linked lists.", true, DNA.overlaps(d1,d2,3));
  } 
}
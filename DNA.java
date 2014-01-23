/**
 * This class is a Doubly linked list model of DNA.
 * 
 * @author Ishaan Taylor
 */

public class DNA extends DoubleLinkedList<DNA.Base> {
  public enum Base {
    A, C, G, T
  }
    
  /**
   * Returns a String representation of DNA.
   * 
   * @return String representation of DNA
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    /**
     * This loop iterates through each node and takes the element from
     * each node and returns a String representation of each.
     */
    if (this.getHead() != null) {
      for (DNA.Base base : this) {
        if (base == DNA.Base.A)
          sb.append("A");
        if (base == DNA.Base.T)
          sb.append("T");
        if (base == DNA.Base.G)
          sb.append("G");
        if (base == DNA.Base.C)
          sb.append("C");
      }
      if (getTail().getElement() == DNA.Base.A)
        sb.append("A");
      if (getTail().getElement() == DNA.Base.T)
        sb.append("T");
      if (getTail().getElement() == DNA.Base.G)
        sb.append("G");
      if (getTail().getElement() == DNA.Base.C)
        sb.append("C");
      return sb.toString();
    }
    return "";
  }

  /**
   * Takes a seperate sequence of dna, splices it from the beginning
   * with num bases, and adds it to this DNA if it is shorter in length.
   * 
   * @param DNA dna to be spliced
   * @param numbases number of bases to be spliced from the beginning
   */
  public void splice(DNA dna, int numbases) throws NullPointerException {
    if (numbases < this.toString().length() && numbases < dna.toString().length()) {
      StringBuilder sb2 = new StringBuilder();
      /**
       * For the length-numbases-1 (deletes numbases from beginning),
       * append each character to the stringbuilder for dna.
       */
      for (int i = dna.toString().length() - numbases - 1; i < dna.toString().length(); i++) 
        sb2.append(dna.toString().charAt(i));
      DNA splicedDNA = string2DNA(sb2.toString());
      /**
       * This loops counts the nodes in the doubly linked list.
       */
      this.append(splicedDNA);
    } throw new NullPointerException();
  }

  /**
   * Returns the appropriate instance of DNA from an appropriate input string.
   * 
   * @param String input string ALL CAPS, either A C G T
   * @return DNA
   * @throws IllegalArgumentException
   */
  public static DNA string2DNA(String s) throws IllegalArgumentException {
    DNA dna = new DNA();
    /**
     * This loop makes sure each character in the string fits with the 
     * respective enum value, then adds each respective base to the list.
     */
    for (int i = 0; i < s.length(); i++) {
      s.charAt(i);
      if (s.charAt(i) != 'A' && s.charAt(i) != 'C' &&
          s.charAt(i) != 'G' && s.charAt(i) != 'T') {
        throw new IllegalArgumentException();
      }
      if(s.charAt(i) == 'A')
        dna.addToBack(DNA.Base.A);
      if(s.charAt(i) == 'G')
        dna.addToBack(DNA.Base.G);
      if(s.charAt(i) == 'C')
        dna.addToBack(DNA.Base.C);
      if(s.charAt(i) == 'T')
        dna.addToBack(DNA.Base.T);
    }
    return dna;
  }
  
  /**
   * This method determines if the last n bases of dna1 exactly matches
   * the first n bases of dna2.
   * 
   * @param dna1 the first DNA list
   * @param dna2 the second DNA list
   * @param n the last n bases, and first n bases of dna1 and dna2 respectively
   * @return false if there is no such match
   */
  public static boolean overlaps(DNA dna1, DNA dna2, int n) {
    String dna1s = dna1.toString();                          // dna1 to string
    String dna2s = dna2.toString();
    if (n < dna1s.length() && n < dna2s.length()) {
      StringBuffer d1 = new StringBuffer();                    // sb for new string
      StringBuffer d2 = new StringBuffer();
      /**
       * This loop appends every character from length - n, to length
       * to a string buffer.
       */
      if (d1.length() > 1) {
        for (int i = d1.length()-n ; i < d1.length(); i++)     // for length-n TO length,
          d1.append(dna1s.charAt(i));
      } else if (d1.length() == 1) {
        d1.append(dna1s.charAt(0));
      } else if (d2.length() == 1) {
        d2.append(dna2s.charAt(0));
      }
      /**
       * This loop appends every character from 0, to n
       * to a string buffer.
       */
      for (int i = 0; i < n; i++)
        d2.append(dna2s.charAt(i));
      return (d1.toString().equals(d2.toString()));
    } return false;
  }
  
  /**
   * This method is the main method of DNA<Base>. It takes two strings that 
   * represent DNA sequences, determines the greater overlap between the two,
   * and then performs the appropriate splicing.
   */
  public static void main(String[] s) {
    /*
     * 1. Determine if there is an overlap.
     * 2. If true, then figure out which overlap is greater.
     *    If false, then just concatenate the strings.         end
     * 3. Figure out the greater overlap.
     * 4. Use the greater overlap 1st and 2nd dna respectively
     * 5. Splice
     * 6. Print
     */
    String s1 = s[0];
    String s2 = s[1];
    String lstring;
    if (s1.length() >= s2.length())       // determine the largest string for the following loops
      lstring = s1;
    else
      lstring = s2;
    int large1 = 0;
    int largest1 = 0;    
    /** This loop determines which is the greatest overlap with s1 being first. */
    for (int i = 0; i < lstring.length(); i++) {
      if (overlaps(string2DNA(s1), string2DNA(s2), i)) {
        large1 = i;
        if (largest1 > large1)
          largest1 = large1;
      }
    }
    int large2 = 0;
    int largest2 = 0;
    /** This loop determines which is the greatest overlap with s2 being first. */
    for (int i = 0; i < lstring.length(); i++) {
      if (overlaps(string2DNA(s2), string2DNA(s1), i)) {
        large2 = i;
        if (largest2 > large2)
          largest2 = large2;
      }
    }
    DNA sDNA1 = string2DNA(s1);
    DNA sDNA2 = string2DNA(s2);
    if (largest1 >= largest2) {
      sDNA1.splice(string2DNA(s2), largest1);
      System.out.println(sDNA1);
    }
    else if (largest1 < largest2) {
      sDNA2.splice(string2DNA(s1), largest2);
      System.out.println(sDNA2);
    }
    System.out.println(string2DNA(s1 + s2));
  }
}
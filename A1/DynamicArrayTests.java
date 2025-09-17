import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.beans.Transient;

public class DynamicArrayTests {

    private DynamicArray<Character> a1;
    private DynamicArray<Character> a2;
    private DynamicArray<Character> empty;
    private DynamicArray<Character> s;

    /**
     * Initializes DynamicArray<Character> instances to be used for testing.
     * Re-initializes before each test.
     * This ensures that tests do not interfere with one another.
     */
    @Before
    public void setUp() {
        a1 = stringToArray("abcdef");
        a2 = stringToArray("wxyz");
        empty = stringToArray("");
        s = stringToArray("s");
    }

    /**
     * Puts the characters of a string into an array structure
     */
    public DynamicArray<Character> stringToArray(String s) {
        DynamicArray<Character> result = new DynamicArray<Character>(s.length());
        for (int i = 0; i < s.length(); i++) {
            result.add(i, s.charAt(i));
        }
        return result;
    }

    /**
     * Compares the sizes of a DynamicArray<Character> and a string
     */
    public void compareSize(DynamicArray<Character> arr, String s){
        assertEquals("["+s+"] Array lengths are equal", arr.size(), s.length());
    }

    /**
     * Compares each element in a DynamicArray<Character>
     * against those in a string.
     */
    public void compareToString(DynamicArray<Character> arr, String s) {
        for (int i = 0; i < arr.size(); i++) {
            assertEquals("["+s+"] Elements are equal at index " + i, arr.get(i).charValue(), s.charAt(i));
        }
    }

    // ~*~*~*~*~ Append Tests Below ~*~*~*~*~

    /**
     * Tests that appending two non-empty arrays results in
     * a new array containing the elements of both, in order.
     */
    @Test
    public void testAppendStandard() {
        compareToString(a1.append(a2), "abcdefwxyz");
        compareToString(a2.append(a1), "wxyzabcdef");
    }

    /**
     * Tests that appending a non-empty array to itself results in
     * a new array containing the elements repeated twice.
     */
    @Test
    public void testAppendSelf() {
        compareToString(a1.append(a1), "abcdefabcdef");
        compareToString(a2.append(a2), "wxyzwxyz");
    }

    /**
     * Tests that appending a non-empty array and an array of
     * length one results in a new array containing the elements
     * of both, in order.
     */
    @Test
    public void testAppendSingle() {
    compareToString(a1.append(s),"abcdefs");
    compareToString(s.append(a1),"sabcdef");
    compareToString(s.append(s),"ss");
    }

    /**
     * Tests that appending an empty array
     * results in a new array that matches the other array
     */
    @Test
    public void testAppendEmpty() {
        compareToString(a1.append(empty), "abcdef");
        compareToString(empty.append(a1), "abcdef");
        compareToString(empty.append(empty), "");
    }

    // ~*~*~*~*~ Add Extract Tests Below ~*~*~*~*~

//    /**
//     * Tests that ...
//     */
//    @Test
//    public void testExtractStandard() {
//        // fill in standard cases
//    }
//
//    /**
//     * Tests that ..
//     */
//    @Test
//    public void testExtractEntire() {
//        // fill in extracting the entire array
//    }
//
//    /**
//     * Tests that ..
//     */
//    @Test
//    public void testExtractZero() {
//        // fill in extracting zero elements
//    }
//
//    /**
//     * Tests that ..
//     */
//    @Test
//    public void testExtractEmpty() {
//        // fill in extracting from an empty array
//    }

    /**
     * Tests that extract throws the proper exception
     * when called on invalid indices
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtractBounds() {
        DynamicArray<Character> extract = a1.extract(-1, 5);
        // More bounds that you can check:
        // low index is negative => throws ArrayIndexOutOfBoundsException
        // high index is greater than array length => throws ArrayIndexOutOfBoundsException
        // low index is greater than array length => throws ArrayIndexOutOfBoundsException
        // high index is negative => throws ArrayIndexOutOfBoundsException
        // high index is less than low
    }

    // ~*~*~*~*~ Write More Tests Below ~*~*~*~*~

    // write tests for the other methods here

    //Group 1 tests 

    @Test 
    public void testGetAndSet(){
        DynamicArray<String> arr = new DynamicArray<String>(3);
        arr.add("oval");
        arr.add("circle");

        assertEquals("oval", arr.get(0));
        assertEquals("circle", arr.set(1, "square"));
        assertEquals("square", arr.get(1));
    }

    @Test 
    public void testConstructorSize(){
        DynamicArray<Integer> arr = new DynamicArray<Integer>(10);
        assertEquals(0, arr.size());
    }

    //Group 2 tests 

    @Test 
    public void testAddAndRemnove(){
        DynamicArray<Integer> arr = new DynamicArray<Integer>(3);
        arr.add(0, 1);
        arr.add(1, 2);
        arr.add(2, 3);
        arr.add(1, 4); // array is now [1,4,2,3]

        assertEquals(4, arr.size());
        assertEquals((Integer)4, arr.get(1));
        assertEquals((Integer)2, arr.remove(2)); // array is now [1,4,3]
        assertEquals(3, arr.size());
        assertEquals((Integer)3, arr.get(2));
    }

    //Group 3 tests

    @Test 
    public void testAppend(){
        DynamicArray<String> arr1 = new DynamicArray<String>(2);
        arr1.add("hi");
        arr1.add("bye");

        DynamicArray<String> arr2 = new DynamicArray<String>(2);
        arr2.add("soul");
        arr2.add("mate");

        DynamicArray<String> combined = arr1.append(arr2);
        assertEquals(4, combined.size());
        assertEquals("hi", combined.get(0));
        assertEquals("bye", combined.get(1));
        assertEquals("soul", combined.get(2));
        assertEquals("mate", combined.get(3));
 
    }

    @Test 
    public void testInsert(){
        DynamicArray<String> arr1 = new DynamicArray<String>(2);
        arr1.add("hi");
        arr1.add("bye");

        DynamicArray<String> arr2 = new DynamicArray<String>(2);
        arr2.add("soul");
        arr2.add("mate");

        arr1.insert(1, arr2); // array is now [hi, soul, mate, bye]
        assertEquals(4, arr1.size());
        assertEquals("soul", arr1.get(1));
        assertEquals("mate", arr1.get(2));
    }

    @Test 
    public void testSublist(){
        DynamicArray<String> arr = new DynamicArray<>(5);
        arr.add("csc");
        arr.add("dept");
        arr.add("is");
        arr.add("cool");

        DynamicArray<String> sub = arr.sublist(1,3);
        assertEquals(2,sub.size());
        assertEquals("dept", sub.get(0));
        assertEquals("is", sub.get(1));
    }

    @Test 
    public void testDelete(){
        DynamicArray<String> arr = new DynamicArray<>(5);
        arr.add("csc");
        arr.add("dept");
        arr.add("is");
        arr.add("cool");

        arr.delete(1,3); // array is now [csc, cool]/ shoudl be
        assertEquals(2,arr.size());
        assertEquals("csc", arr.get(0));
        assertEquals("cool", arr.get(1));
    }

    @Test 
    public void testExtract(){
        DynamicArray<String> arr = new DynamicArray<>(5);
        arr.add("csc");
        arr.add("dept");
        arr.add("is");
        arr.add("cool");

        DynamicArray<String> ext = arr.extract(1,3); // array is now [dept, is]
        assertEquals(2,ext.size());
        assertEquals("dept", ext.get(0));
        assertEquals("is", ext.get(1));
        assertEquals(4, arr.size()); // original array should stay the same 
    }

    @Test
    public void testSplitSuffix() {
        DynamicArray<String> arr = new DynamicArray<>(6);
        arr.add("a");
        arr.add("b");
        arr.add("c");
        arr.add("d");
        arr.add("e");
        arr.add("f");

        DynamicArray<String> suffix = arr.splitSuffix(3);
        assertEquals(3, suffix.size());
        assertEquals("d", suffix.get(0));
        assertEquals("e", suffix.get(1));
        assertEquals("f", suffix.get(2));

        assertEquals(3, arr.size());
        assertEquals("a", arr.get(0));
        assertEquals("b", arr.get(1));
        assertEquals("c", arr.get(2));
    }

    @Test
    public void testSplitPrefix() {
        DynamicArray<String> arr = new DynamicArray<>(6);
        arr.add("a");
        arr.add("b");
        arr.add("c");
        arr.add("d");
        arr.add("e");
        arr.add("f");

        DynamicArray<String> prefix = arr.splitPrefix(3);
        assertEquals(3, prefix.size());
        assertEquals("a", prefix.get(0));
        assertEquals("b", prefix.get(1));
        assertEquals("c", prefix.get(2));

        assertEquals(3, arr.size());
        assertEquals("d", arr.get(0));
        assertEquals("e", arr.get(1));
        assertEquals("f", arr.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSplitSuffixNegativeIndex() {
        DynamicArray<String> arr = new DynamicArray<>(3);
        arr.add("x");
        arr.splitSuffix(-1); // should throw
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSplitSuffixTooLargeIndex() {
        DynamicArray<String> arr = new DynamicArray<>(3);
        arr.add("x");
        arr.splitSuffix(arr.size() + 1); // should throw
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSplitPrefixNegativeIndex() {
        DynamicArray<String> arr = new DynamicArray<>(3);
        arr.add("x");
        arr.splitPrefix(-1); // should throw
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSplitPrefixTooLargeIndex() {
        DynamicArray<String> arr = new DynamicArray<>(3);
        arr.add("x");
        arr.splitPrefix(arr.size() + 1); // should throw
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertNegativeIndex() {
        DynamicArray<String> arr1 = new DynamicArray<>(2);
        arr1.add("a");
        DynamicArray<String> arr2 = new DynamicArray<>(2);
        arr2.add("b");
        arr1.insert(-1, arr2); // should throw
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertTooLargeIndex() {
        DynamicArray<String> arr1 = new DynamicArray<>(2);
        arr1.add("a");
        DynamicArray<String> arr2 = new DynamicArray<>(2);
        arr2.add("b");
        arr1.insert(arr1.size() + 1, arr2); // should throw
    }



    //Group 4 tests

    //attempting one test for all 3 methods since they are similar 
    @Test
    public void testIndexInfo(){
        DynamicArray<String> arr = new DynamicArray<>(3);
        arr.add("1");
        arr.add("2");

        assertEquals(0, arr.lowIndex());
        assertEquals(1, arr.highIndex());
        assertTrue(arr.indexInRange(1));
        assertFalse(arr.indexInRange(2));
    }
}



































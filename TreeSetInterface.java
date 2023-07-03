import java.util.LinkedList;
import java.util.NoSuchElementException;

public interface TreeSetInterface <T extends Comparable<T>> {

    /*
     * This method will add the specified element 
     * according to the same sorting order mentioned during the creation of the TreeSet. 
     * Duplicate entries will not get added.
     */
    public void add(T data);

    /*
     * This method will remove all the elements.
     */
    public void clear();

    /*
     * This method will return true if a given element is present in TreeSet else it will return false.
     */
    public boolean contains(T data);

    /*
     * This method will return the first element in TreeSet if TreeSet is not null else it will throw NoSuchElementException.
     */
    public T first() throws NoSuchElementException;

    /*
     * 	This method is used to return true if this set contains no elements or is empty and false for the opposite case.
     */
    public boolean isEmpty();

    /*
     * This method will return the last element in TreeSet if TreeSet is not null else it will throw NoSuchElementException.
     */

    public T last() throws NoSuchElementException;

    /*
     * This method is used to remove a specific element from the set.
     */
    public void remove(T data);

    /*
     * 	This method is used to return the size of the set or the number of elements present in the set.
     */
    public int size();

    /*
     * This method retrieves and removes the first (lowest) element, or returns null if this set is empty.
     */
    public T pollFirst();

    /*
     * This method retrieves and removes the last (highest) element, or returns null if this set is empty.
     */
    public T pollLast();

    public boolean equals(Object obj);

    public String toString();

    /*
     * This method returns the least element in this set greater than or equal to the given element, 
     * or null if there is no such element.
     */
    public T ceiling(T data);

    /*
     * This method returns the greatest element in this set less than or equal to the given element, 
     * or null if there is no such element.
     */
    public T floor(T data);

    /*
     * This method will add all elements of the specified Collection to the set
     */
    public void addAll(T[] data_array);

    /*
     * This method will return elements of TreeSet which are greater than or equal to the specified element.
     */
    public LinkedList<T> tailSet(T data);
    
    /*
     * This method will return elements ranging from fromElement to toElement. 
     * fromElement is inclusive and toElement is exclusive.
     */
    public LinkedList<T> subSet(T fromData, T toData);

    /*
     * This method returns the least element in this set strictly greater than the given element,
     * or null if there is no such element.
     */
    public T higher(T data);

    /*
     * This method returns the greatest element in this set strictly less than the given element,
     * or null if there is no such element.
     */
    public T lower(T data);

    /*
     * This method returns a view of the elements contained in this set.
     */
    public Object[] increasingSet();

    /*
     * This method returns a reverse order view of the elements contained in this set.
     */
    public Object[] descendingSet();



}

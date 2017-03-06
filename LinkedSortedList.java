/**
   A class that implements the ADT sorted list by using a chain of linked nodes.
   Duplicate entries are allowed.
  
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 4.0
*/
public class LinkedSortedList<T extends Comparable<? super T>>
             implements SortedListInterface<T>
{
   private Node firstNode;       // Reference to first node of chain
   private int  numberOfEntries;

   public LinkedSortedList()
   {
      firstNode = null;
      numberOfEntries = 0;
   } // end default constructor

/* < Implementations of the sorted list operations go here.>
   . . . */
   
   public void add(T newEntry)
   {
      Node newNode = new Node(newEntry);
      Node nodeBefore = getNodeBefore(newEntry);

      if (isEmpty() || (nodeBefore == null))
      {
         // Add at beginning
         newNode.setNextNode(firstNode);
         firstNode = newNode;
      }
      else
      {
         // Add after nodeBefore
         Node nodeAfter = nodeBefore.getNextNode();
         newNode.setNextNode(nodeAfter);
         nodeBefore.setNextNode(newNode);
      } // end if

      numberOfEntries++;
   } 
   
   private Node getNodeBefore(T anEntry)
   {
      Node currentNode = firstNode;
      Node nodeBefore = null;

      while ( (currentNode != null) && 
              (anEntry.compareTo(currentNode.getData()) > 0) )
      {
         nodeBefore = currentNode;
         currentNode = currentNode.getNextNode();
      } // end while

      return nodeBefore;
   }
   
   
   private class Node
   {
      private T     data;  // Entry in list
      private Node	next; // Link to next node

      private Node(T dataPortion) 
      {
         data = dataPortion;
         next = null;	
      } // end constructor

      private Node(T dataPortion, Node nextNode) 
      {
         data = dataPortion;
         next = nextNode;	
      } // end constructor

      private T getData()
      {
         return data;
      } // end getData

      private void setData(T newData)
      {
         data = newData;
      } // end setData

      private Node getNextNode()
      {
         return next;
      } // end getNextNode

      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
   } // end Node
} // end LinkedSortedList

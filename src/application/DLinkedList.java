package application;

public class DLinkedList<T extends Comparable<T>> {
    private DNode<T> head;

   
    
    public DNode<T> getHead(){
    	return head;
    }

    public void insertSorted(T data) {
        DNode<T> newDNode = new DNode<>(data);
        if (head == null || data.compareTo(head.getData()) < 0) {
            newDNode.setNext(head);
            if (head != null) {
                head.setPrev(newDNode) ;
            }
            head = newDNode;
        } else {
            DNode<T> current = head;
            while (current.getNext() != null && data.compareTo(current.getNext().getData()) > 0) {
                current = current.getNext();
            }
            newDNode.setNext(current.getNext());
            if (current.getNext() != null) {
                current.getNext().setPrev(newDNode) ;
            }
            current.setNext(newDNode) ;
            newDNode.setPrev(current);
        }
    }

    public void deleteSorted(T data) {
        DNode<T> current = head;
        while (current != null && !current.getData().equals(data)) {
            current = current.getNext();
        }

        if (current == null) {
            System.out.println(data + " not found in the list.");
            return;
        }

        if (current.getPrev() != null) {
            current.getPrev().setNext(current.getNext()) ;
        } else {
            head = current.getNext();
        }

        if (current.getNext() != null) {
            current.getNext().setPrev(current.getPrev()) ;
        }
    }

    public void update(T oldData, T newData) {
        DNode<T> current = head;
        while (current != null && !current.getData().equals(oldData)) {
            current = current.getNext();
        }

        if (current == null) {
            System.out.println(oldData + " not found in the list.");
            return;
        }

        current.setData(newData) ;
    }
    
    public T searchSorted(T data) {
		DNode<T> current = head;
		while (current != null) {

			if (current.getData().equals(data)) {
				return current.getData();

			} else if (current.getData().compareTo(data) > 0) {
				return null;
			}
			current = current.getNext();
		}
		return null;
	}
    
    public void display() {
        DNode<T> current = head;
        System.out.print("Head -> ");
        while (current != null) {
            System.out.print(current.getData() + " -> ");
            current = current.getNext();
        }
        System.out.println("Null");
    }

    
    
}
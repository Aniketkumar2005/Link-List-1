public class Create6 {
    public static class node {
        int data;
        node next; 

        // constructor for the node class
        public node(int data) {
            this.data = data;
            this.next = null; // point to the next null node 
        }
    }

    // Static fields for head and tail of the linked list
    public static node head;
    public static node tail;
    public static int size;

    // Method to add a node at the beginning of the list
    public void addFirst(int data) {
        size++;
        // Create a new node
        node newNode = new node(data);
        
        // In case if the list is empty
        if (head == null) {
            head = tail = newNode;
            return;
        }

        // Step 2 - newNode next = head
        newNode.next = head;

        // Step 3 - head = newNode
        head = newNode;
    }

    // Method to add a node at the end of the list
    public void addLast(int data) {
        size++;
        // Create a new node
        node newNode = new node(data);

        // In case if the list is empty
        if (head == null) {
            head = tail = newNode;
            return;
        }

        // Point the current tail's next to the new node
        tail.next = newNode;

        // Update the tail to be the new node
        tail = newNode;
    }

    // Method to print the linked list
    public void print() {
        if (head == null) {
            System.out.println("YOUR LINKED LIST IS EMPTY");
            return;
        }

        node temp = head; //initialise with head
        while (temp != null) { 
            System.out.print(temp.data + " "); //print the temp head data
            temp = temp.next; //itrate temp to the next node
        }
        System.out.println("null");
    }

    public void Middle(int idx, int data) {
        if(idx == 0){
            addFirst(data);
            return;
        }
        size++;
        node newNode = new node(data);
        node temp = head;
        int i = 0;
         
        while(i<idx-1){
            temp = temp.next;
            i++;
        }
        // i<- idx-1(previous idx)
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public int removeFirst(){
        if(size == 0){ // if there is no node or node is empty
            System.out.println("YOUR NODE IS EMPTY");
            return Integer.MAX_VALUE;
        }
        else if (size == 1){//  there is a single node and want to remove that
            //remove sigle head tail to null
            int val = head.data;
            head = tail = null;
            return val;
        }
        size--;
        int val = head.data;
        head = head.next;
        return val;
    }

    public int removeLast(){
        
        node prev = head;
        if(size == 0){ // if there is no node or node is empty
            System.out.println("YOUR NODE IS EMPTY");
            return Integer.MAX_VALUE;
        }
        else if (size == 1){//  there is a single node and want to remove that
            //remove sigle head tail to null
            int val = head.data;
            head = tail = null;
            size=0;
            return val;
        }

        for(int i=0; i<size-2; i++){
            prev = prev.next;
        }

        int val = prev.next.data;
        prev.next = null;
        prev = tail;
        size--;
        return val;
    }

    // Question 1: search though linear key
    public int itrSearch(int key){ //o(n)
        node temp = head;
        int i = 0;
        while(temp!=null){
            if(temp.data == key){ //key found
                return i;
            }
            temp = temp.next;
            i++;
        }
        // key not found
        return -1;
    }
    // Question 2: search key through recursive call...
    public int helper(node head, int key){
        if(head == null){
            return -1;
        }
        if(head.data == key){
            return 0;
        }
        int idx = helper(head.next, key);
        if(idx == -1){
            return -1;
        }
        return idx+1;
    }
    public int recSearch (int key){
        return helper(head, key);
    }

    // question no 3: reverse the ll

    public void reverse(){
        node prev = null;
        node curr = tail = head;
        node next;

        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }


    // Question no 4: find & remove Nth node from end ...
    public void deleteNthfromEnd(int n){
        // Find size of n
        int size = 0;
        node temp = head;
        while(temp!=null){
            temp = temp.next;
            size++;
        }
        // if we have to delete head 
        if(n==size){
            head = head.next; //remove first opration
            return;
        }

        // find prev of n
        int i = 0;
        int tofindprev = size - n;
        node prev = head;
        while(i<tofindprev){
            prev = prev.next;
            i++;
        }

        prev.next = prev.next.next;
        return;

    }

    public node findMid(node head){
        // step 1: find mid 
        node fast = head;
        node slow = head;
        while(fast!=null || fast.next!=null){
            slow = slow.next; // +1 jump
            slow = slow.next.next; //+2 jump
        }
        return slow; //slow is our mid of node
    }

    public boolean cheakPalindrom(){
        //base case
        if(head == null || head.next!=null){
            return true;
        }
        // step1 - find mid 
        node midNode = findMid(head);
        // step2  - reverse 2nd half
        node prev = null;
        node curr = midNode;
        node next;
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        node right = prev ;// right half head
        node left = head; // left half head

        // step3 - cheak left half & right half 
        while(right != null){
            if(left.data!=right.data){
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    public static void main(String[] args) {
        Create6 ll = new Create6();
        ll.print();
        ll.addFirst(1);
        ll.print();
        ll.addFirst(2);
        ll.print();
        ll.addFirst(4);
        ll.print();
        
        ll.addFirst(5);
        ll.Middle(2,3);
        ll.print();
        System.out.println(size);
        ll.removeFirst();
        ll.print();
        System.out.println(size);
        
        ll.removeLast();
        ll.print();
        System.out.println(size);

        
        System.out.println(ll.itrSearch(3));
        System.out.println(ll.itrSearch(30));
        System.out.println(ll.recSearch(3));
        System.out.println(ll.recSearch(40));
        ll.reverse();
        ll.print();

        ll.deleteNthfromEnd(4);
        ll.print();
    }
}


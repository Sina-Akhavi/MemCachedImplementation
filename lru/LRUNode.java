package lru;

class LRU {
    private LRUNode head;
    
    private LRUNode tail;

    private int size;

    private int counter;

    public LRU() {
        this.size = 0;
        this.counter = 0;
        this.head = null;
        this.tail = null;
    }

    public void doAnything(int i, int j) {
        if (this.head == null) {
            this.head = new LRUNode(null, null, i, j);
            this.counter++;
            return;
        }

        LRUNode cell = this.head;

        for (int index = 0; index < this.counter; index++) {
            if (cell.i == i && cell.j == j) {
                cell.before.next = cell.next;

                 LRUNode newHead = new LRUNode(null, null, -1, -1);
                 newHead.next = this.head;
                 newHead.i = cell.i;
                 newHead.j = cell.j;
                 
                 this.head = newHead;
                 
                 return;
            }
            
            cell = cell.next; 
        }

        // System.out.println("t");
        // create new head
    }

    
    public void show() {

        LRUNode cell = this.head;

        for (int i = 0; i < this.counter; i++) {
            System.out.print("i=" + cell.i + ", j=" + cell.j + " --> ");
        }
        System.out.println("\n***********************");

    }

    
}

public class LRUNode {
    public int i;
    
    public int j;

    public LRUNode next;

    public LRUNode before;
    

    public LRUNode(LRUNode next, LRUNode before, int i, int j) {
        this.i = i;
        this.j = j;
        this.next = next;
        this.before = before;
    }


    public static void main(String[] args) {
        LRU lru = new LRU();
        
        lru.doAnything(1, 2);
        lru.show();

        lru.doAnything(1, 3);
        lru.show();
        lru.doAnything(1, 4);
        lru.doAnything(1, 3);

        lru.doAnything(1, 6);
        lru.doAnything(1, 7);
        lru.doAnything(1, 8);
        lru.doAnything(1, 9);
        lru.show();
    }

}
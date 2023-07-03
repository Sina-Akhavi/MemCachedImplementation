import java.util.concurrent.Semaphore;

class LRU {
    private LRUNode head;
    
    private LRUNode tail;

    private int size;

    private int counter;

    private Semaphore semaphore;

    public LRU() {
        this.size = 0;
        this.counter = 0;
        this.head = null;
        this.tail = null;
        this.semaphore = new Semaphore(1);
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
                // System.out.println("kkkk");
                if (cell.before == null) {
                    return;
                }

                try {
                    cell.before.next = cell.next; // problem for the last node
                } catch (NullPointerException e) {
                }

                try {
                    cell.next.before = cell.before; 

                } catch (NullPointerException e) {
                } 

                LRUNode newHead = new LRUNode(null, null, -1, -1);
                newHead.next = this.head;
                this.head.before = newHead;
                newHead.i = cell.i;
                newHead.j = cell.j;
                 
                this.head = newHead;
                return;
            }
            
            cell = cell.next; 
        }

        LRUNode newNode = new LRUNode(null, null, i, j);
        this.counter++;
        newNode.next = this.head;
        this.head.before = newNode;
        this.head = newNode;

    }

    
    public void show(int chunkSizeId) {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("------------------ LRU " + chunkSizeId + " ---------------------");

        LRUNode cell = this.head;
        
        for (int i = 0; i < this.counter; i++) {
            System.out.print("i=" + cell.i + ", j=" + cell.j + " --> ");
            cell = cell.next;
        }
        
        System.out.println("\n----------------------------------------");

        semaphore.release();
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



        
        // lru.doAnything(1, 4);
        // lru.show();

        // lru.doAnything(1, 3);
        // lru.show();

        // lru.doAnything(1, 3);
        // lru.show();

        // lru.doAnything(1, 2);
        // lru.show();

        // lru.doAnything(1, 3);
        // lru.show();
        // lru.doAnything(1, 6);
        // lru.show();
        // lru.doAnything(1, 7);
        // lru.show();

        // lru.doAnything(1, 4);
        // lru.show();

        // lru.doAnything(1, 3);
        // lru.show();


        // lru.doAnything(1, 8);
        // lru.show();

        // lru.doAnything(1, 2);
        // lru.show();


        // lru.doAnything(1, 2);
        // lru.show();

}
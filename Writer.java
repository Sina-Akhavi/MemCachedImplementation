public class Writer implements Runnable {

    private String key;

    private int data;

    public Writer(String key, int data) {
        this.key = key;
        this.data = data;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        PageManager.addData(data, key);
        
    }


    public static void main(String[] args) throws InterruptedException {
        int[] chunkSizes = new int[2];
        // System.out.println("kk");
        chunkSizes[0] = 625;
        chunkSizes[1] = 2500;

        PageManager.createPages(chunkSizes);
        Page[] pages = PageManager.getPages();

        int numWriters = 10;
        String[] keys = RandomUtil.getKeys();
        int[] datas = RandomUtil.getData(numWriters, RandomUtil.getaMaxChunkSize(pages)); // create pages


        Thread[] wrThreads = new Thread[10];
        for (int i = 0; i < numWriters; i++) {
            Writer writer = new Writer(keys[i], datas[i]);
            wrThreads[i] = new Thread(writer);
        }

        for (int i = 0; i < wrThreads.length; i++) {
            wrThreads[i].start();
        }

        // wait till all threads terminate

        for (int i = 0; i < wrThreads.length; i++) {
            wrThreads[i].join();
        }

        System.out.println("========================= End Of Other Threads ==========================");
        for (int i = 0; i < pages.length; i++) {
            LRU lru = pages[i].getLRU();
            lru.show(pages[i].chunkSize);
        }

        for (int i = 0; i < pages.length; i++) {
            HashTable lru = pages[i].getHashTable();
            lru.show(pages[i].chunkSize);
        }
        

        PageManager.showPages();
        StoreAddresses.show();

    }
}

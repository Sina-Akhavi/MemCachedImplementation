import java.util.concurrent.Semaphore;

public class PageManager {
    private static Page[] pages;

    private static Semaphore semaphore = new Semaphore(1);

    // public PageManager(int[] chunkSizes) {
    //     createPages(chunkSizes);
    // }

    public static void createPages(int[] chunkSizes) {
        int numPages = chunkSizes.length;
        pages = new Page[numPages];
        
        for (int i = 0; i < numPages; i++) {
            pages[i] = new Page(chunkSizes[i]);
        }

    }

    public static void addData(int data, String key) {


        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Page bestPage = chooseBestPage(data);

        // System.out.println("chunkSize " + bestPage.chunkSize + " is suitable for " + " data " + data);
        int[] ij = bestPage.addData(data, key);
        StoreAddresses.addAddress(bestPage.chunkSize, ij[0], ij[1]);
        semaphore.release();
    }

    public static Page chooseBestPage(int data) {
        Page bestPage = null;

        int min = 1000000;
        for (int i = 0; i < pages.length; i++) {
            if (pages[i].chunkSize < data) {
                continue;
            }

            if (pages[i].chunkSize - data < min) {
                min = pages[i].chunkSize;
                bestPage = pages[i];
            }
        }

        return bestPage;
    }

    public static Page[] getPages() {
        return pages;
    }

    public static void showPages() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("&&&& pages &&&&");
        for (int i = 0; i < pages.length; i++) {
            pages[i].show();
        }

        semaphore.release();
    }


    
}

import java.util.ArrayList;

public class StoreAddresses {
    private static ArrayList<String> addresses = new ArrayList<>();
    
    public static void addAddress(int chunkSize, int i, int j) {
        String address = chunkSize + "-" + i + "-" + j;
        addresses.add(address);
    }


    public static void show() {
        System.out.println("##########################");
        System.out.println("-- StoreAddresses: ");
        for (int i = 0; i < addresses.size(); i++) {
            System.out.println(addresses.get(i));
        }
        System.out.println("##########################");
    }
}

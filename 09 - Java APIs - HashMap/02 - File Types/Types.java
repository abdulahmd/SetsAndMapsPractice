import java.io.*;
import java.util.*;

public class Types {
    public static void main(String[] args) {
        Map<String, String> ret = types("types.dat");
        System.out.println(ret);
    }

    public static Map<String, String> types(String fileName) {
        Map<String, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(new File(fileName)));
            
            int iter1 = bf.read();
            int iter2 = bf.read();
            for(int i = 0; i < iter1; i++) {
                String str = bf.readLine();
                if(!map.containsKey(str)) {
                    map.put(str, "");
                    set.add(str.substring(0,4));
                }
            }

            for(int j = 0; j < iter2; j++) {
                String str = bf.readLine();
                if(set.contains(str.substring(str.length() - 4))) {
                    String file = map.get(str.substring(str.length() - 4));
                    map.put(file, str);
                }
            }
            bf.close();
        } catch(IOException e) {
            System.out.println("Exception");
        }

        return map;
    }
}

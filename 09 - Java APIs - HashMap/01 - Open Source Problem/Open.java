import java.io.*;
import java.util.*;

public class Open {
    public static void main(String[] args) {
        Map<String, Integer> map = OpenSource("open.dat");
        System.out.println(map);
    }

    public static Map<String, Integer> OpenSource(String fileName) {
        Map<String, Integer> map = new HashMap<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(new File(fileName)));
            Set<String> set = new HashSet<>();

            while (bf.ready()) {
                set.add(bf.readLine());
            }

            String str = bf.readLine();
            if (str.toUpperCase().equals(str)) {
                if (!map.containsKey(str)) {
                    map.put(str, 1);
                } else {
                    Integer i = map.get(str);
                    map.put(str, i++);
                }
            }
            bf.close();
        } catch (IOException e) {
            System.out.println("Exception");
        }
        return map;
    }
}
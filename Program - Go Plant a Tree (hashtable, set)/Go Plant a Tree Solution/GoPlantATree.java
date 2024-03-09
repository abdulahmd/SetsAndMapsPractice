import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Function;

public class GoPlantATree {

  public static void main(String[] args) throws FileNotFoundException {

    Scanner scan = new Scanner(new File("plant.dat"));

    int n = Integer.parseInt(scan.nextLine());
    scan.nextLine();

    for (int i = 0; i < n; i++) {
      String line = scan.nextLine();
      TreeMap<String, Integer> freq = new TreeMap<>();
      int count = 0;
      while (!line.equals("")) {
        count++;
        if (freq.containsKey(line)) {
          freq.put(line, freq.get(line) + 1);
        } else {
          freq.put(line, 1);
        }
        if (scan.hasNextLine()) {
          line = scan.nextLine();
        } else {
          break;
        }
      }

      for (Map.Entry<String, Integer> tree : freq.entrySet()) {
        double percent = tree.getValue() / (double) count * 100;
        System.out.printf("%s %.4f%n", tree.getKey(), percent);
      }
      if (i < n - 1) {
        System.out.println();
      }

    }

  }


  private static void makeTestCase() {
//    String[] treeTypes = {"Douglas-fir", "Lodgepole Pine",
//        "Coast Redwood", "Ponderosa Pine", "Giant Sequoia"};
//    double[] prob = {.25, .20, .15, .28, .12};

    String[] treeTypes = {"Hickory", "Red Elm",
        "Soft Maple", "Willow", "Cypress", "Willow",
        "Beech", "Ash"};
    double[] prob = {.15, .10, .13, .24, .09, .16, .04, .09};

    for (int i = 0; i < 25; i++) {
      double r = Math.random();
      double pr = 0;
      if (r < (pr += prob[0])) {
        System.out.println(treeTypes[0]);
      } else if (r < (pr += prob[1])) {
        System.out.println(treeTypes[1]);
      } else if (r < (pr += prob[2])) {
        System.out.println(treeTypes[2]);
      } else if (r < (pr += prob[3])) {
        System.out.println(treeTypes[3]);
      } else if (r < (pr += prob[4])) {
        System.out.println(treeTypes[4]);
      } else if (r < (pr += prob[5])) {
        System.out.println(treeTypes[5]);
      } else if (r < (pr += prob[6])) {
        System.out.println(treeTypes[6]);
      } else if (r < (pr += prob[7])) {
        System.out.println(treeTypes[7]);
      }
    }
  }
}

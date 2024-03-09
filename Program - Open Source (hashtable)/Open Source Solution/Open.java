
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


/**
 * @author petersch
 */
public class Open {
  static Comparator<Project> sort = (p,q) -> p.name.compareTo(q.name);

  public static void main(String[] args) throws FileNotFoundException {
    Scanner scan = new Scanner(new File("open.dat"));

    String word = scan.nextLine();

    while (!word.equals("0")) {
      Set<Project> projects = new HashSet<>();
      Set<String> students = new HashSet<>();
      Set<String> rejected = new HashSet<>();
      Project currProj = null;
      while (!word.equals("1")) {
        if(Character.isUpperCase(word.charAt(0))) //project
        {
          currProj = new Project(word);
          projects.add(currProj);
        }
        if( Character.isLowerCase(word.charAt(0)) ||
            Character.isDigit(word.charAt(0)))  //student
        {
          currProj.addStudent(word);
        }
        word = scan.nextLine();
      }

      for (Project p: projects ) {
        for (String st : p.studSet ) {
          if(!students.add(st))
            rejected.add(st);
        }
      }

//      rejected.forEach(System.out::println);

      for (Project p: projects ) {
        for (String st : rejected ) {
          if(p.studSet.contains(st))
            p.studSet.remove(st);
        }
      }

      Set<Project> sortedProjects = new TreeSet<>(projects);
      for (Project p: sortedProjects  ) {
        System.out.println(p.name + " " + p.studSet.size());
      }

      System.out.println();
      word = scan.nextLine();
    }


  }

  static class Project implements Comparable<Project>{
    private String name;
    private Set<String> studSet = new TreeSet<>();

    public Project(String name) {
      this.name = name;
    }

    public void addStudent(String student){
      studSet.add(student);
    }

    public void print(){
      System.out.println(name + " == "+ studSet.size());
      studSet.forEach(System.out::println);
    }

    @Override
    public int compareTo(Project other) {

      int v = other.studSet.size() - this.studSet.size();

      if(v == 0)
        v = this.name.compareTo(other.name);

      return v;
    }

    @Override
    public String toString() {
      return "Project{" +
          "name='" + name + '\'' +
          ", students=" + studSet +
          '}';
    }


  }



}

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


public class Open {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("open.dat"));
		Formatter out = new Formatter(System.out);
		while (true) {
			HashMap<String, String> signup = new HashMap<>();
			ArrayList<String> vals = new ArrayList<>();
			String project = in.nextLine().trim();
			int a = 0;
			if (project.equals("0")) {
				break;
			}
			while (!project.equals("1")) {
				vals.add(project);
				String user = in.nextLine().trim();
				while (Character.isLowerCase(user.charAt(0))) {
					if (signup.containsKey(user)
							&& !signup.get(user).equals(project)) {
						signup.put(user, nope);
					} else {
						signup.put(user, project);
					}
					user = in.nextLine().trim();
				}
				project = user;
			}
			Iterator<String> i = signup.keySet().iterator();
			ArrayList<Project> proj = new ArrayList<>();
			while (!vals.isEmpty()) {
				proj.add(new Project(vals.remove(0)));
			}
			while (i.hasNext()) {
				String snur = i.next();
				for (Project prj : proj) {
					if (prj.name.equals(signup.get(snur))
							&& !signup.get(snur).equals(nope)) {
						++prj.students;
						break;
					}
				}
			}
			Collections.sort(proj);
			for (Project prj : proj) {
				out.format("%s%n", prj.toString());
			}
			out.format("%n");
		}
		in.close();
		out.close();
	}
	public final static String nope = "AbAbADifhFb4";
}

class Project implements Comparable<Object> {
	public int students;
	public String name;

	public Project() {
		students = 0;
	}

	public Project(String n) {
		name = n;
		students = 0;
	}

	@Override
	public int compareTo(Object o) {
		Project p = (Project) o;
		return Integer.compare(p.students, students) != 0 ? Integer.compare(
				p.students, students) : name.compareTo(p.name);
	}

	@Override
	public String toString() {
		return name + " " + students;
	}

}

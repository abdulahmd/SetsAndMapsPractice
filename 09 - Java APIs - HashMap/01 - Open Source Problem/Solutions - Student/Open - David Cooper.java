import java.util.*;
import java.io.File;

public class OpenSource {

	private Scanner file;
	private ArrayList<Project> projects;

    public OpenSource() throws Exception {
    	file = new Scanner(new File("open.dat"));
		projects = new ArrayList<Project>();

		String currentProjectName = "";
		ArrayList<String> studentList = new ArrayList<String>();

		while (file.hasNextLine()) {
			String inputLine = file.nextLine();
			if (inputLine.charAt(0) == '0') {
				break;
			} else if (inputLine.charAt(0) == '1') {
				if (!currentProjectName.isEmpty()) {
					projects.add(new Project(currentProjectName, studentList));
					currentProjectName = inputLine;
				}
				printData();
				projects = new ArrayList<Project>();
				currentProjectName = "";
				studentList = new ArrayList<String>();
			} else if (Character.isUpperCase(inputLine.charAt(0))) {
				if (currentProjectName.isEmpty()) {
					currentProjectName = inputLine;
				} else {
					projects.add(new Project(currentProjectName, studentList));
					currentProjectName = inputLine;
					studentList = new ArrayList<String>();
				}
			} else if (Character.isLowerCase(inputLine.charAt(0))) {
				studentList.add(inputLine);
			}
		}
    }

    public void printData() {
		for (int i = 0; i < projects.size(); i++) {
			for (int j = i + 1; j < projects.size(); j++) {
				projects.get(i).removeCollisions(projects.get(j));
			}
		}

		ArrayList<Project> printList = new ArrayList<Project>();

		for (int i = 0; i < projects.size(); i++) {
			int index = 0;
			for (int j = 0; j < printList.size(); j++) {
				if (projects.get(i).getSize() > printList.get(j).getSize()) {
					index = j;
					break;
				} else if (printList.get(j).getSize() == projects.get(i).getSize()) {
					if (printList.get(j).getName().compareTo(projects.get(i).getName()) < 0) {
						index = j;
						break;
					}
				} else {
					index = projects.size();
					break;
				}
			}
			if (index != projects.size())
				printList.add(index, projects.get(i));
			else
				printList.add(projects.get(i));
		}

		for (int i = 0; i < printList.size(); i++) {
			System.out.println(printList.get(i));
		}
    }

    public static void main(String[] args) throws Exception {
    	new OpenSource();
    }

}

class Project {

	private String name;
	private ArrayList<String> students;

	public Project(String name, ArrayList<String> students) {
		this.name = name;
		this.students = students;
		removeDuplicates();
	}

	// Postcondition: Students contains unique elements.
	private void removeDuplicates() {
		for (int i = 0; i < students.size(); i++) {
			if (students.indexOf(students.get(i)) != students.lastIndexOf(students.get(i))) {
				students.remove(students.lastIndexOf(students.get(i)));
				i--;
			}
		}
	}

	// Precondition: Each element in the ArrayList students of each project is unique.
	public void removeCollisions(Project p) {
		for (int i = 0; i < students.size(); i++) {
			String student = students.get(i);
			if (p.getStudentArray().contains(student)) {
				p.getStudentArray().remove(student);
				this.getStudentArray().remove(student);
				i--;
			}
		}
	}

	protected ArrayList<String> getStudentArray() {
		return this.students;
	}

	public String toString() {
		return  name + " " + students.size();
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return students.size();
	}

}

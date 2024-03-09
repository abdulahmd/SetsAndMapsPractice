import java.util.*;
import java.io.*;
public class Open {
    public static void main(String[] args) throws IOException{
    	//Scanner fi = new Scanner(new File("F:\\CS3\\MP2\\HashMap Problems\\OpenSource\\src\\Open.dat"));
    	Scanner fi = new Scanner(new File("Open.dat"));
    	HashMap<String,String> nameToProj = new HashMap<String,String>();
    	String currentProj = "";
    	ArrayList<String> temp = new ArrayList<String>();
    	ArrayList<String> temp2 = new ArrayList<String>();
    	ArrayList<Project> projs = new ArrayList<Project>();
    	while(fi.hasNext()){
    		String line = fi.nextLine().trim();
    		if(line.charAt(0) != '0'){
    			if(line.charAt(0)<='Z' && line.charAt(0) != '1'){
    				currentProj = line;
    				projs.add(new Project(line));
    				temp = new ArrayList<String>();
	    		}else if(line.charAt(0) == '1'){
	    			Iterator iter = (Iterator) nameToProj.entrySet().iterator();
	    			while (iter.hasNext()) {
				        Map.Entry pairs = (Map.Entry)iter.next();
				        find((String)pairs.getValue(),projs).add();
				    }
					Comparator<Project> comp = new Project("");
					Collections.sort(projs,comp);
	    			process(projs);
	    			nameToProj = new HashMap<String,String>();
	    			temp = new ArrayList<String>();
	    			projs = new ArrayList<Project>();
	    		}else{
	    			if(nameToProj.get(line) == null && line.charAt(0) != '1'
	    			   && !temp2.contains(line)){
	    					nameToProj.put(line,currentProj);
	    					temp.add(line);
	    					temp2.add(line);
	    			}else{
	    				if(!temp.contains(line)){
	    					nameToProj.remove(line);
	    				}
	    			}
	    		}
    		}else{
    			Iterator iter = (Iterator) nameToProj.entrySet().iterator();
	    		while (iter.hasNext()){
					Map.Entry pairs = (Map.Entry)iter.next();
				    find((String)pairs.getValue(),projs).add();
				}
				Comparator<Project> comp = new Project("");
				Collections.sort(projs,comp);
	    		process(projs);
    		}
    	}
    }
    public static Project find(String s,ArrayList<Project>p){
    	for(int i = 0; i < p.size(); i++){
    		if(p.get(i).getName().equals(s)){
    			return p.get(i);
    		}
    	}
    	return null;
    }
    public static void process(ArrayList<Project> map){
		for(int i = map.size()-1; i >= 0 ; i--){
			System.out.println(map.get(i).getName() + " " + map.get(i).getNum());
		}
    }
    public static class Project implements Comparator<Project>{
		public String name;
		public int num;
		public Project(String name){
			this.name = name;
		}
		public void add(){
			num++;
		}
		public int getNum(){
			return num;
		}
		public String getName(){
			return name;
		}
		public String toString(){
			return name;
		}
		public int compare(Project p, Project p2){
	    	if(p.getNum() != p2.getNum()){
	    		return p.getNum() - p2.getNum();
	    	}else{
	    		return p2.getName().compareTo(p.getName());
	    	}
    	}
    }
}

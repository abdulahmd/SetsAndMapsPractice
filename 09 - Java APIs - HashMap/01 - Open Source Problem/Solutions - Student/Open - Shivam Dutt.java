import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Open {
	public static void main(final String[] args) throws Exception {
		final Scanner in = new Scanner(new File("open.dat"));
		final Formatter out = new Formatter(System.out);
		outer: while (true) {
			final Map<String, String> assign = new HashMap<>();
			final Map<String, Integer> counts = new HashMap<>();
			String curProj = null;
			while (true) {
				final String line = in.nextLine().trim();
				if (line == null || line.isEmpty()) {
					continue;
				} else if ("0".equals(line)) {
					break outer;
				} else if ("1".equals(line)) {
					break;
				} else if (Character.isUpperCase(line.charAt(0))) {
					curProj = line;
					counts.put(curProj, 0);
				} else {
					assign.put(line, assign.containsKey(line) && !curProj.equals(assign.get(line)) ?
						null : curProj);
				}
			}
			for (final Entry<String, String> entry: assign.entrySet()) {
				final String project = entry.getValue();
				if (project == null) {
					continue;
				}
				counts.put(project, counts.get(project) + 1);
			}
			final List<Entry<String, Integer>> entries = new ArrayList<>(counts.entrySet());
			Collections.sort(entries, (a, b) -> {
				int ret = Integer.compare(b.getValue(), a.getValue());
				if (ret != 0)
					return ret;
				final String ak = a.getKey();
				final String bk = b.getKey();
				if (ak == bk)
					return 0;
				if (ak == null)
					return -1;
				if (bk == null)
					return 1;
				return ak.compareTo(bk);
			});
			for (final Entry<String, Integer> entry: entries) {
				final String project = entry.getKey();
				if (project == null) {
					continue;
				}
				out.format("%s %d%n", project, entry.getValue());
			}
		}
		in.close();
	}
}

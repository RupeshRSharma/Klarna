/**
 * 
 */
package main.java.com.klarna;

import java.util.HashSet;
/**
 * @author Rupesh Sharma
 *
 */
import java.util.Set;
import java.util.TreeSet;

public class DiskSpace {

	public static boolean isWritable(int blockSize, int fileSize, Set<Integer> occupiedSectors) {

		System.out.println(blockSize);
		System.out.println(fileSize);
		occupiedSectors.forEach(System.out::print);

		Set<Integer> set = new TreeSet<>(occupiedSectors);

		if (set.isEmpty()) {
			return true;
		}

		if (set.size() == 1 ) {
			
			if(blockSize < fileSize+set.iterator().next()) {
				return true;
			}
			return false;
		}

		Integer prev = 0;
		int index = 0;
		for (Integer item : set) {
			if (index == 0) {
				prev = item;
				index++;
				continue;
			}
			if (item - prev >= fileSize) {
				return true;
			}
			prev = item;
		}

		if (blockSize - prev >= fileSize) {
			return true;
		}
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>();
		set.add(1);
		set.add(4);
		System.out.println(isWritable(4, 2, set));

	}

}

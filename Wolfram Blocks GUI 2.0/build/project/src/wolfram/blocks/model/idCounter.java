package wolfram.blocks.model;
import java.util.concurrent.atomic.AtomicLong;

public class idCounter {
	private static AtomicLong counter = new AtomicLong(0);
	 
	public static long nextId() {
		return counter.incrementAndGet();
	} 
}

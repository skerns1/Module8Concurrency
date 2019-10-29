import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Concurrency {

	public static void main(String[] args) throws Exception {		
		List<Integer> array = new ArrayList<Integer>();
		fillArray(array);
		singleThreadArraySum(array);
		parallelArraySum(array);
		
	}//end main
	
	public static void fillArray(List<Integer> array) {
		for (int i = 0; i < 100000000; i++) {
			array.add((int) (Math.random() * ((10 - 1) + 1)) + 1);
		}//end for	
	}//end makeArray

	@SuppressWarnings("unchecked")
	public static void parallelArraySum(List<Integer> array) throws Exception {
		long startTime = System.nanoTime();
		int sum = 0;
		int indexStart = 0;
		int indexEnd = 12500000;
		FutureTask<Integer>[] ThreadTask = new FutureTask[8];
		for (int i = 0; i < 8; i++) {
			Callable<Integer> callable = new CallableClass(array, sum, indexStart, indexEnd);
			ThreadTask[i] = new FutureTask<Integer>(callable);
			Thread t = new Thread(ThreadTask[i]);
			t.start();
			indexStart = indexEnd;
			indexEnd += 12500000;
		}
		for (int i = 0; i < 8; i++)
			sum += ThreadTask[i].get();
		long endTime = System.nanoTime() - startTime;
		System.out.println("Parallel Array Thread Sum: " + sum + " Run Time: " + endTime);
	}//end parallelArraySum
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void singleThreadArraySum(List<Integer> array) throws Exception {
		long startTime = System.nanoTime();
		int sum = 0;
		int indexStart = 0;
		int indexEnd = 100000000;
		FutureTask[] singleThreadTask = new FutureTask[1];
		Callable callable = new CallableClass(array, sum, indexStart, indexEnd);
		singleThreadTask[0] = new FutureTask(callable);
		Thread t1 = new Thread(singleThreadTask[0]);
		t1.start();
		t1.join();
		long endTime = System.nanoTime() - startTime;
		System.out.println("Single Thread Sum: " + singleThreadTask[0].get() + " Run Time: " + endTime + "\n");
		
	}//end singleThreadRun
	
}//end Concurrency

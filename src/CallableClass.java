import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CallableClass implements Callable<Integer> {
	private List<Integer> array = new ArrayList<Integer>();
	private int sum;
	private int indexStart;
	private int indexEnd;
	
	public CallableClass(List<Integer> array, int sum, int indexStart, int indexEnd) {
		super();
		this.array = array;
		this.sum = sum;
		this.indexStart = indexStart;
		this.indexEnd = indexEnd;
	}//end ListAndSum
	
	public Integer call() throws Exception {
		for (int i = indexStart; i < indexEnd; i++) {
			sum += addToSum(sum, i);
		}//end for
		return sum;
	}//end run
	
	public synchronized int addToSum(int sum, int i) {
		return array.get(i);
	}//end addToSum
}//end CallableClass

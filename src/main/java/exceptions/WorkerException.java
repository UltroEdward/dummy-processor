package exceptions;

@SuppressWarnings("serial")
public class WorkerException extends Exception {

	public WorkerException(String name) {
		super(name);
	}
}

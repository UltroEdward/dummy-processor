package tasks;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import actions.impl.Printer;
import dto.TaskDto;
import exceptions.WorkerException;

public class Task {

	private TaskDto task;
	protected static final Logger LOG = LoggerFactory.getLogger(Printer.class);

	public Task(TaskType type, String description) {
		task = new TaskDto();
		task.setType(type);
		task.setDescription(description);
	}

	public Task(TaskDto dto) {
		task = dto;
	}

	public void doWork() throws WorkerException {
		preProcessing();
		TaskStatus status = task.getAction().doWork();
		if (status.equals(TaskStatus.FAILED)){
			task.setStatus(TaskStatus.FAILED);
			throw new WorkerException(String.format("Worker failed, task type was: %s", task.getStatus()) );
		}
		postProcessing(status);
	}

	//TODO: think about encapsulation of Task object
	public TaskDto getTask() {
		return task;
	}
	
	private void preProcessing() {
		task.setStatus(TaskStatus.IN_PROGRESS);
		task.setStartTime(Calendar.getInstance().get(Calendar.MILLISECOND));
		LOG.debug(String.format("Task %s started at %s", task.getType().toString() + task.getStartTime()));
	}

	private void postProcessing(TaskStatus status) {
		task.setStatus(status);
		if (!status.equals(TaskStatus.FAILED)) {
			task.setEndTime(Calendar.getInstance().get(Calendar.MILLISECOND));
		}
	}

	
}

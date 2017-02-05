package pipe;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.ExecutionDto;
import exceptions.WorkerException;
import tasks.Task;
import tasks.TaskStatus;
import tasks.TaskType;

public class Pipe {

	public static Logger LOG = LoggerFactory.getLogger(Pipe.class);

	// in case we have several Tasks with the same type, need clarify
	private List<Task> taskList;
	private ExecutionDto execution;

	private Pipe(List<Task> taskList) {
		this.taskList = taskList;
		execution = new ExecutionDto();
	}

	public Pipe getInstance(List<Task> taskList) {
		return new Pipe(taskList);
	}

	public void runPipe() {
		preSetup();
		runFlow(taskList);
		postSetup();
	}

	public ExecutionDto getExecution() {
		return execution;
	}

	private void preSetup() {
		taskList.stream().parallel().forEach(task -> task.getTask().setStatus(TaskStatus.PENDING));
		execution.setStatus(ExecutionStatus.IN_PROGRESS);
		execution.setStartTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
	}

	private void postSetup() {
		execution.setEndTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
		execution.setStatus(getExecutionStatus(taskList));
	}

	private void runFlow(List<Task> tasks) {
		runTasks(findTaskByType(tasks, TaskType.BUILD));
		runTasks(findTaskByType(tasks, TaskType.TEST));

		List<Task> parallelTasksPool = Stream.concat(findTaskByType(tasks, TaskType.DOCS).stream(),
				findTaskByType(tasks, TaskType.INGEGRATION_TEST).stream()).collect(Collectors.toList());
		runTasks(parallelTasksPool);

		runTasks(findTaskByType(tasks, TaskType.PUBLISH));
		runTasks(findTaskByType(tasks, TaskType.SYNC));
	}

	private List<Task> findTaskByType(List<Task> tasks, final TaskType dresiredTask) {
		return tasks.stream().filter(e -> e.getTask().getType().equals(dresiredTask)).collect(Collectors.toList());
	}

	private List<TaskStatus> runTasks(List<Task> tasks) {
		if (!execution.getStatus().equals(ExecutionStatus.FAILED)) {
			tasks.stream().parallel().peek(e -> {
				try {
					e.doWork();
				} catch (WorkerException ex) {
					LOG.error("Worker failed");
					execution.setStatus(ExecutionStatus.FAILED);
				}
			});
			return tasks.stream().map(e -> e.getTask().getStatus()).collect(Collectors.toList());
		} else
			return tasks.stream().peek(e -> e.getTask().setStatus(TaskStatus.SKIPPED)).map(e -> e.getTask().getStatus()).collect(Collectors.toList());
	}

	private ExecutionStatus getExecutionStatus(List<Task> tasks) {
		List<TaskStatus> statuses = tasks.stream().map(e -> e.getTask().getStatus()).collect(Collectors.toList());

		if (statuses.contains(TaskStatus.FAILED)) {
			return ExecutionStatus.FAILED;
		} else if (!statuses.contains(TaskStatus.FAILED) && statuses.contains(TaskStatus.SKIPPED)
				&& !statuses.contains(TaskStatus.IN_PROGRESS) && !statuses.contains(TaskStatus.PENDING)) {
			return ExecutionStatus.SKIPPED;
		} else if ((!statuses.contains(TaskStatus.FAILED))
				&& (statuses.contains(TaskStatus.PENDING) || statuses.contains(TaskStatus.IN_PROGRESS))) {
			return ExecutionStatus.IN_PROGRESS;
		}
		return ExecutionStatus.COMPLETED;
	}

}

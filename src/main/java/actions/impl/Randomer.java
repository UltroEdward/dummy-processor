package actions.impl;

import java.util.Random;

import actions.Action;
import tasks.TaskStatus;

public class Randomer extends Action {

	private TaskStatus[] possibleStatuses = { TaskStatus.COMPLETED, TaskStatus.SKIPPED, TaskStatus.FAILED,
			TaskStatus.IN_PROGRESS };

	public Randomer(String triggerTaskName) {
		super(triggerTaskName);
	}

	public TaskStatus doWork(){
		printName();
		waitSomeSeconds(1);
		return getRandomStatus();
	}

	private TaskStatus getRandomStatus() {
		return possibleStatuses[new Random().nextInt(possibleStatuses.length)];
	}
}

package actions.impl;

import actions.Action;
import tasks.TaskStatus;

public class Delayer extends Action {

	public Delayer(String triggerTaskName) {
		super(triggerTaskName);
	}

	public TaskStatus doWork() {
		printName();
		waitSomeSeconds(10);
		return TaskStatus.COMPLETED;
	}
}

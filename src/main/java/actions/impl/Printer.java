package actions.impl;

import actions.Action;
import tasks.TaskStatus;

public class Printer extends Action {
	
	public Printer(String triggerTaskName) {
		super(triggerTaskName);
	}

	public TaskStatus doWork() {
		printName();
		return TaskStatus.COMPLETED;
	}

}

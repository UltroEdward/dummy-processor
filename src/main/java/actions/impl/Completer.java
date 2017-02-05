package actions.impl;

import actions.Action;
import tasks.TaskStatus;

public class Completer extends Action {

	public Completer(String triggerTaskName) {
		super(triggerTaskName);
	}

	public TaskStatus doWork() {
		printName();
		waitSomeSeconds(1);
		return TaskStatus.COMPLETED;
	}

}

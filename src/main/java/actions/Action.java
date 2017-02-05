package actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tasks.TaskStatus;

public abstract class Action {

	protected static final Logger LOG = LoggerFactory.getLogger(Action.class);
	protected static final String TASK_NAME_TEMPL = "Task %s is being executed";
	protected String triggerTaskName;

	public Action(String triggerTaskName) {
		this.triggerTaskName = triggerTaskName;
	}
	
	public Action() {
		this("Default name");
	}

	public abstract TaskStatus doWork();

	protected void printName(){
		LOG.info(String.format(TASK_NAME_TEMPL), triggerTaskName);
	}
	
	protected void waitSomeSeconds(int seconds){
		try {
			Thread.sleep(seconds + 1000);
		} catch (InterruptedException e) {
			LOG.error(String.format("Thread %d is interrupted, error: %s", Thread.currentThread().getId()),
					e.getMessage());
		}
	}
}

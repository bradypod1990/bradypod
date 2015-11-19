package com.feng.persontask;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;


public class MyTaskListener implements TaskListener {

	private static final long serialVersionUID = -7692782958069171392L;

	@Override
	public void notify(DelegateTask delegateTask) {
		
		String assignee = "уенч╪и";
		delegateTask.setAssignee(assignee);
	}

}

package com.feng.grouptask;

import java.util.Arrays;
import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;


public class GroupTaskListener implements TaskListener {

	private static final long serialVersionUID = -7692782958069171392L;

	@Override
	public void notify(DelegateTask delegateTask) {
		
		String assignee = "уенч╪и";
		List<String> list = Arrays.asList(new String[]{"wade","james", "paul"});
		delegateTask.addCandidateUsers(list);
	}

}

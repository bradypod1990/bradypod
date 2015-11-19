package com.feng;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class ProcessInstanceTest {

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	@Test
	public void deployProcess() {
		String key = "myProcess";
		processEngine.getRuntimeService().startProcessInstanceByKey(key);
		System.out.println("start success");
	}
	
	@Test
	public void findTask() {
		String assignee = "ÍõÎå";
		List<Task> list = processEngine.getTaskService().createTaskQuery().taskAssignee(assignee).list();
		if(list != null && !list.isEmpty()) {
			for(Task task : list) {
				System.out.println("assignee:"+task.getAssignee());
				System.out.println("processDefinitionId:"+task.getProcessDefinitionId());
				System.out.println("processINstanceId:"+task.getProcessInstanceId());
				System.out.println("taskId:" + task.getId());
			}
		}
	}
	
	@Test
	public void completeTask() {
		String taskId = "1202";
		processEngine.getTaskService().complete(taskId);
	}
}

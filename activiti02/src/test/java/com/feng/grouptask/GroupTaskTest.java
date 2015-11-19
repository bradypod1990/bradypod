package com.feng.grouptask;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class GroupTaskTest {
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	@Test
	public void deploymentProcess() {
		InputStream inBpmn = this.getClass().getResourceAsStream("groupProcess.bpmn");
		InputStream inPng = this.getClass().getResourceAsStream("groupProcess.png");
		processEngine.getRepositoryService().createDeployment()
				.name("组任务")
				.addInputStream("groupProcess.bpmn", inBpmn)
				.addInputStream("groupProcess.png", inPng)
				.deploy();
	}

	@Test
	public void startProcess() {
		String key = "groupProcess";
//		Map<String, Object> variables = new HashMap<String, Object>();
//		variables.put("userId", "张三丰");
		processEngine.getRuntimeService()
				.startProcessInstanceByKey(key);
		System.out.println("start success");
	}
	
	@Test
	public void findTask() {
		String candidateUser = "wade";
		List<Task> list = processEngine.getTaskService()
				.createTaskQuery()
//				.taskAssignee(candidateUser)
				.taskCandidateUser(candidateUser)
				.list();
		if(list != null && !list.isEmpty()) {
			for(Task task : list) {
				System.out.println("assignee:"+task.getAssignee());
				System.out.println("processDefinitionId:"+task.getProcessDefinitionId());
				System.out.println("processINstanceId:"+task.getProcessInstanceId());
				System.out.println("taskId:" + task.getId());
			}
		}
	}
	
	/**
	 * 拾起任务，由组任务分配到个人任务,拾起后转成个人任务, 设置的办理人可以为组任务成员，也可以为不是
	 */
	@Test
	public void claim() {
		String taskId = "6304";
		String userId = "james";
		processEngine.getTaskService().claim(taskId, userId);
	}
	
	/**
	 * 由个人任务转成组任务，前提为这个任务原本是组任务
	 */
	@Test
	public void setGroup() {
		String taskId = "5504";
		processEngine.getTaskService().setAssignee(taskId, null);
	}
	
	/**
	 * 添加组成员 
	 */
	@Test
	public void addGroupUser() {
		String taskId = "5504";
		String userId = "波什";
		processEngine.getTaskService().addCandidateUser(taskId, userId);
	}
	
	/**
	 * 删除组成员 
	 */
	@Test
	public void delGroupUser() {
		String taskId = "5504";
		String userId = "波什";
		processEngine.getTaskService().deleteCandidateUser(taskId, userId);
	}
	
	@Test
	public void setExclusiveness() {
		String taskId = "6304";
		Map<String, Object> variables = new HashMap<String, Object>();
		//variables.put("message", "重要");
		processEngine.getTaskService().complete(taskId);
	}
}

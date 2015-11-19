package com.feng;

import java.io.InputStream;
import java.util.Date;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.junit.Test;

public class ProcessVariblesTest {

ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	@Test
	public void deploymentProcess() {
		InputStream inBpmn = this.getClass().getClassLoader().getResourceAsStream("diagrams/processVaribles.bpmn");
		InputStream inPng = this.getClass().getClassLoader().getResourceAsStream("diagrams/processVaribles.png");
		processEngine.getRepositoryService().createDeployment()
				.addInputStream("processVaribles.bpmn", inBpmn)
				.addInputStream("processVaribles.png", inPng)
				.name("process varible")
				.deploy();
	}

	@Test
	public void deployProcess() {
		String key = "processVaribles";
		processEngine.getRuntimeService().startProcessInstanceByKey(key);
		System.out.println("start success");
	}
	
	@Test
	public void completeProcess() {
		String taskId = "1504";
		processEngine.getTaskService().complete(taskId);
	}
	
	/**
	 * 设置流程变量
	 */
	@Test
	public void setVarible() {
		TaskService taskService = processEngine.getTaskService();
		String taskId = "1702";
		taskService.setVariable(taskId, "请假天数", 11);
		taskService.setVariable(taskId, "请假原因", "不想上班啊啊啊啊啊啊");
		taskService.setVariable(taskId, "请假时间", new Date());
		
	}
	
	/**
	 * 获取流程变量
	 */
	@Test
	public void getVariable() {
		TaskService taskService = processEngine.getTaskService();
		String taskId = "1702";
		int date = (Integer) taskService.getVariable(taskId, "请假天数");
		String reason = (String) taskService.getVariable(taskId, "请假原因");
		Date time = (Date) taskService.getVariable(taskId, "请假时间");
		
		System.out.println(date + "-----" + reason + "------" + time);
	}
}

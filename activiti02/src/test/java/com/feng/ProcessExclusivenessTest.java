package com.feng;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

public class ProcessExclusivenessTest {
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	@Test
	public void deploymentProcess() {
		InputStream inBpmn = this.getClass().getClassLoader().getResourceAsStream("diagrams/processExclusiveness.bpmn");
		InputStream inPng = this.getClass().getClassLoader().getResourceAsStream("diagrams/processExclusiveness.png");
		processEngine.getRepositoryService().createDeployment()
				.addInputStream("processExclusiveness.bpmn", inBpmn)
				.addInputStream("processExclusiveness.png", inPng)
				.name("process varible")
				.deploy();
	}

	@Test
	public void startProcess() {
		String key = "processExclusiveness";
		processEngine.getRuntimeService().startProcessInstanceByKey(key);
		System.out.println("start success");
	}
	
	@Test
	public void setExclusiveness() {
		String taskId = "2203";
		Map<String, Object> variables = new HashMap<String, Object>();
		//variables.put("message", "живЊ");
		processEngine.getTaskService().complete(taskId, variables);
	}
}

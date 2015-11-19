package com.feng;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

public class ProcessParellelTest {
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	@Test
	public void deploymentProcess() {
		InputStream inBpmn = this.getClass().getClassLoader().getResourceAsStream("diagrams/processParallel.bpmn");
		InputStream inPng = this.getClass().getClassLoader().getResourceAsStream("diagrams/processParallel.png");
		processEngine.getRepositoryService().createDeployment()
				.name("并行网关")
				.addInputStream("processParallel.bpmn", inBpmn)
				.addInputStream("processParallel.png", inPng)
				.deploy();
	}

	@Test
	public void startProcess() {
		String key = "processParallel";
		processEngine.getRuntimeService().startProcessInstanceByKey(key);
		System.out.println("start success");
	}
	
	@Test
	public void setExclusiveness() {
		String taskId = "4202";
		Map<String, Object> variables = new HashMap<String, Object>();
		//variables.put("message", "重要");
		processEngine.getTaskService().complete(taskId);
	}
}

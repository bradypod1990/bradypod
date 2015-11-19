package com.feng;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class ProcessDefinitionTest {

	
ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/*
	 * 根据资源文件部署流程
	 */
	@Test
	public void deploymentProcess_classpath() {
		Deployment deployment = processEngine.getRepositoryService().createDeployment()
			.name("helloworld流程定义")
			.addClasspathResource("diagrams/helloworld.bpmn")
			.addClasspathResource("diagrams/helloworld.png")
			.deploy();
		System.out.println(deployment.getId());
		System.out.println(deployment.getName());
	}
	
	/**
	 * 根据zip包部署流程
	 */
	@Test
	public void deploymentProcess_zip() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("diagrams/helloworld.zip");
		ZipInputStream zipInputStream = new ZipInputStream(in);
		Deployment deployment = processEngine.getRepositoryService().createDeployment()
					.name("helloworld 定义")
					.addZipInputStream(zipInputStream)
					.deploy();
		System.out.println(deployment.getId());
		System.out.println(deployment.getName());
	}
	
	/**
	 * 查询流程定义信息
	 */
	@Test
	public void queryProcessDefinition() {
		ProcessDefinitionQuery pro =  processEngine.getRepositoryService().createProcessDefinitionQuery();
		List<ProcessDefinition> list = pro
//			.processDefinitionId("")
//			.processDefinitionName("processDefinitionName")
			.orderByProcessDefinitionVersion()
			.asc()
			.list();
		if(list != null && list.size() > 0) {
			for(ProcessDefinition pd : list) {
				System.out.println("流程定义ID：" +pd.getId());
				System.out.println("流程定义名称："+pd.getName()); //对应activiti.cfg.xml中的name
				System.out.println("流程定义key:"+pd.getKey());//对应activiti.cfg.xml中的id
				System.out.println("流程定义资源png名称："+pd.getDiagramResourceName());
				System.out.println("流程定义资源bpmn名称"+pd.getResourceName());
				System.out.println("流程定义版本："+pd.getVersion());
				System.out.println("---------------------------------------------------");
			}
		}
	}
	
	/**
	 * 删除流程定义
	 */
	@Test
	public void deleteProcessDefinition() {
		String deploymentId = "601";
		RepositoryService repositoryService = processEngine.getRepositoryService();
		/**
		 * 不级联删除
		 * 只能删除没有部署的流程，删除部署的流程会报错
		 */
		repositoryService.deleteDeployment(deploymentId);
		
		
		//repositoryService.deleteDeployment("601", true);
		
		
		System.out.println("删除流程定义成功");
	}
	
	@Test
	public void getPic() throws IOException {
		RepositoryService repositoryService = processEngine.getRepositoryService();
		String deploymentId = "601";
		String resourceName = "";
		List<String> list = repositoryService.getDeploymentResourceNames(deploymentId);
		if(list != null && list.size() > 0) {
			for(String str : list) {
				if(str.indexOf(".png") > 0) {
					resourceName = str;
					break;
				}
			}
		}
		
		InputStream in = repositoryService.getResourceAsStream(deploymentId, resourceName);
		FileUtils.copyInputStreamToFile(in, new File("F:/" + resourceName));
	}
	
	/**
	 * 删除流程定义
	 */
	@Test
	public void deleteProcessDefinitionByKey() {
		RepositoryService repositoryService = processEngine.getRepositoryService();
		String key = "myProcess";
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(key)
				.list();
		for(ProcessDefinition pd : list) {
			String id = pd.getDeploymentId();
			repositoryService.deleteDeployment(id, true);
		}
			
	}
}

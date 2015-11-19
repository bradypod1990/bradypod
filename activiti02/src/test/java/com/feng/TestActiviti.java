package com.feng;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class TestActiviti {

	
	@Test
	public void testActiviti() {
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
//		  protected String databaseSchemaUpdate = DB_SCHEMA_UPDATE_FALSE;
//		  protected String jdbcDriver = "org.h2.Driver";
//		  protected String jdbcUrl = "jdbc:h2:tcp://localhost/activiti";
//		  protected String jdbcUsername = "sa";
//		  protected String jdbcPassword = "";
		processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
		processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/activititest");
		processEngineConfiguration.setJdbcUsername("root");
		processEngineConfiguration.setJdbcPassword("root");
		processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
		System.out.println("processEngine:" + processEngine);
	}
	
	@Test
	public void testAciviti2() {
		ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti¡£cfg.xml")
					.buildProcessEngine();
		System.out.println("processEngine:" + processEngine);
	}
}

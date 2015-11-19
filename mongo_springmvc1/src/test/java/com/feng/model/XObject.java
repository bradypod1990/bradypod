package com.feng.model;

public class XObject {

	private int age;
	
	//private int count;
	private String names;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

//	public int getCount() {
//		return count;
//	}
//
//	public void setCount(int count) {
//		this.count = count;
//	}


	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	@Override
	public String toString() {
		return "XObject [age=" + age + ", names=" + names + "]";
	}
}

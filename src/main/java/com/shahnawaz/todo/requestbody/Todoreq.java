package com.shahnawaz.todo.requestbody;

public class Todoreq {
    @Override
	public String toString() {
		return "Todoreq [taskString=" + taskString + ", completedString=" + completedString + "]";
	}
	private String taskString;
    private String completedString;
    public String getTaskString(){
        return this.taskString;
    }
    public String getCompletedString(){
        return this.completedString;
    }
	public void setTaskString(String taskString) {
		this.taskString = taskString;
	}
	public void setCompletedString(String completedString) {
		this.completedString = completedString;
	}
}

package com.zhuravchak.entity;

public class Task extends Entity{
    private long id;
    private String discription;
    private long employee_id;

    public Task() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDiscription() {
        return this.discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public long getEmployee_id() {
        return this.employee_id;
    }

    public void setEmployee_id(long employee_id) {
        this.employee_id = employee_id;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(o != null && this.getClass() == o.getClass()) {
            Task task = (Task)o;
            return this.id != task.id?false:(this.employee_id != task.employee_id?false:this.discription.equals(task.discription));
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = (int)(this.id ^ this.id >>> 32);
        result = 31 * result + this.discription.hashCode();
        result = 31 * result + (int)(this.employee_id ^ this.employee_id >>> 32);
        return result;
    }

    public String toString() {
        return "Task{id=" + this.id + ", discription=\'" + this.discription + '\'' + ", employee_id=" + this.employee_id + '}' + "\n";
    }
}

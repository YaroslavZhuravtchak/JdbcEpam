package com.zhuravchak.entity;

public class Employee extends Entity {
    private long id;
    private String lastname;
    private String firstname;
    private String position;
    private long department_id;

    public Employee() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public long getDepartment_id() {
        return this.department_id;
    }

    public void setDepartment_id(long department_id) {
        this.department_id = department_id;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(o != null && this.getClass() == o.getClass()) {
            Employee employee = (Employee)o;
            return this.id != employee.id?false:(this.department_id != employee.department_id?false:(!this.lastname.equals(employee.lastname)?false:(!this.firstname.equals(employee.firstname)?false:(this.position != null?this.position.equals(employee.position):employee.position == null))));
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = (int)(this.id ^ this.id >>> 32);
        result = 31 * result + this.lastname.hashCode();
        result = 31 * result + this.firstname.hashCode();
        result = 31 * result + (this.position != null?this.position.hashCode():0);
        result = 31 * result + (int)(this.department_id ^ this.department_id >>> 32);
        return result;
    }

    public String toString() {
        return "Employee{id=" + this.id + ", lastname=\'" + this.lastname + '\'' + ", firstname=\'" + this.firstname + '\'' + ", position=\'" + this.position + '\'' + ", department_id=" + this.department_id + '}' + "\n";
    }
}

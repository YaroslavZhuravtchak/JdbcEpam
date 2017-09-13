package com.zhuravchak.entity;


public class Department extends Entity {
    private long id;
    private String name;
    private String phoneNumber;

    public Department() {
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(o != null && this.getClass() == o.getClass()) {
            Department that = (Department)o;
            if(this.id != that.id) {
                return false;
            } else {
                if(this.name != null) {
                    if(this.name.equals(that.name)) {
                        return this.phoneNumber != null?this.phoneNumber.equals(that.phoneNumber):that.phoneNumber == null;
                    }
                } else if(that.name == null) {
                    return this.phoneNumber != null?this.phoneNumber.equals(that.phoneNumber):that.phoneNumber == null;
                }

                return false;
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = (int)(this.id ^ this.id >>> 32);
        result = 31 * result + (this.name != null?this.name.hashCode():0);
        result = 31 * result + (this.phoneNumber != null?this.phoneNumber.hashCode():0);
        return result;
    }

    public String toString() {
        return "Department{id=" + this.id + ", name=\'" + this.name + '\'' + ", phoneNumber=\'" + this.phoneNumber + '\'' + '}' + "\n";
    }
}

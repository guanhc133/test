package org.example.dto;

import java.util.List;

/**
 * 测试对象
 */
public class User {

    private String name;

    public List<String> getLl() {
        return ll;
    }

    public void setLl(List<String> ll) {
        this.ll = ll;
    }

    private List<String> ll;

    private Child child;

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public User() {
    }

    public User(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", ll=" + ll +
                ", child=" + child +
                ", address='" + address + '\'' +
                '}';
    }

    public static void main(String[] args) {
        User user = new User();
        System.out.println(user.getName());
    }
}

package org.example.dto;


public class Child {

    private String birth;

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Child{" +
                "birth='" + birth + '\'' +
                '}';
    }
}

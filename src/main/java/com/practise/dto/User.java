package com.practise.dto;

import java.util.Objects;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/6/20 16:40
 **/
public class User {
    private Integer age;
    private String name;

    public User(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(){
        System.out.println("user create... hashCode:"+ this.hashCode());
    }


    @Override
    public boolean equals(Object obj){
        if( obj ==null) return false;
        if( obj == this){
            return true;
        }
        User other = (User) obj;
        return this.age.equals(other.getAge());
    }

     @Override
    public int hashCode() {
         return Objects.hash(getAge());
     }
}

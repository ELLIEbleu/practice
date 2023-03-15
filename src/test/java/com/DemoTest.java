package com;

import com.google.common.collect.Lists;
import com.practise.dto.Person;
import com.practise.dto.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author dan.he
 * @Date 2023/3/1 22:18
 **/
public class DemoTest {

    public void test2(){
        List list = new LinkedList();
        for (;;){
            byte[] b= new byte[1024 *1024];
            list.add(b);
        }

    }
    public static void main(String[] args) {
        DemoTest demoTest = new DemoTest();
//        demoTest.test2();

        List<User> list = Lists.newArrayList();
        User user1 = new User(1,"test1");
        list.add(user1);
        User user11 = new User(1,"test11");
        list.add(user11);
        User user111 = new User(1,"test111");
        list.add(user111);
        User user2 = new User(2,"test2");
        list.add(user2);
        User user3 = new User(3,"test3");
        list.add(user3);
        User user4 = new User(4,"test4");
        list.add(user4);

        //key->单字段，value ->实体对象
        Map<Integer,User> userMap = list.stream().collect(Collectors.toMap(User::getAge, a -> a, (k1, k2) -> k2));
        System.out.println("age = 1,name="+ userMap.get(1).getName());

        //key ->单字段， value->单字段
        Map<Integer,String> map = list.stream().collect(Collectors.toMap(User::getAge, User::getName,(k1,k2) -> k2));
        System.out.println("age = 1,name="+ map.get(1));

        //key ->单字段，value -> 实体对象数组
        Map<Integer,List<User>> newMap = list.stream().collect(Collectors.groupingBy(User::getAge));
        System.out.println("age=1,user size="+ newMap.get(1).size());

        //key，value 均使用拼接
        Map<String, String> map2 = list.stream().collect(Collectors.toMap(k -> "age="+k.getAge(), v -> "name="+ v.getName()
                ,(k1,k2) -> k1));
        System.out.println(map2.get("age=1"));


        //map -> list
        List<String> values = map2.values().stream().collect(Collectors.toList());
        System.out.println("values:"+ values.size());

        List<Person> personList = map.entrySet().stream().map(r -> new Person(111L,r.getValue(),r.getKey())).collect(Collectors.toList());
        System.out.println("personList:"+ personList.size());

        //去重  实体对象-> distinct  不重写对象的equals hashcode无效，根据自己去重的规则
        List<User> collect = list.stream().distinct().collect(Collectors.toList());
        System.out.println("collect:"+ collect.size());

    }
}

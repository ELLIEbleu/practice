package com;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/11/18 14:26
 **/

public class ArrayListDemo {

    private static List<UserModel> list = new ArrayList<>();

    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        for (int i = 0; i < 3; i++) {
            Thread mThread1 = new Thread(thread1, "线程" + i);
            mThread1.start();
        }
    }

    public static void testUserModel() {
        list.add(new UserModel(1));
        list.add(new UserModel(2));
        list.add(new UserModel(3));
        createImageInfo(list);
    }

    public static class MyThread implements Runnable {
        @Override
        public void run() {
            testUserModel();
        }
    }

    private static List<UserModel> createImageInfo(List<UserModel> imageEntities) {
        List<UserModel> images = new ArrayList<>();
        if (imageEntities != null) {
            Iterator<UserModel> iterator = imageEntities.iterator();
            while (iterator.hasNext()) {
                UserModel image = createImageInfo(iterator.next());
                System.out.println("imageEntities = [" + image.toString() + "]");
                images.add(image);
            }
        }
        System.out.println("imageEntities size = [" + images.size() + "]");
        return images;
    }

    public static UserModel createImageInfo(UserModel userModel) {
        return userModel;
    }

    public static class UserModel {
        private int id;

        public UserModel() {
        }

        public UserModel(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "UserModel{" +
                    "id=" + id +
                    '}';
        }
    }
}

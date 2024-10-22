package com.chenchi.learning.java.nullpoint;

import lombok.Getter;

import java.util.Optional;

public class NullDemo {
    public static void main(String[] args) {
        User cc1 = new User(1, "cc",null);
        User cc2 = new User(2, "cc2", new Father(2, "f2", null));
        User cc3 = new User(3, "cc3", new Father(3, "f3", new Wife(3, "w3")));
        NullDemo nullDemo = new NullDemo();
        System.out.println(nullDemo.getWifeIdByUser(cc1));
        System.out.println(nullDemo.getWifeIdByUser(cc2));
        System.out.println(nullDemo.getWifeIdByUser(cc3));
    }

    private Integer getWifeIdByUser(User user){
        return Optional.of(user)
                .map(User::getFather)
                .map(Father::getWife)
                .map(Wife::getId)
                .orElse(null)
                ;
    }

    private void optionDemo(User user){
        Optional<User> user1 = Optional.of(user);

    }
    private static class Father{
        private Integer id;
        private String name;
        @Getter
        private Wife wife;

        public Father(Integer id, String name, Wife wife) {
            this.id = id;
            this.name = name;
            this.wife = wife;
        }

    }
    private static class User{
        private Integer id;
        private String name;
        @Getter
        private Father father;

        public User(Integer id, String name, Father father) {
            this.id = id;
            this.name = name;
            this.father = father;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

    private static class Wife {
        private Integer id;
        private String name;

        public Wife(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

    }
}

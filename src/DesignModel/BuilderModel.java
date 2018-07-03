package DesignModel;

/**
 * Created by tiang on 2018/6/25.
 */
public class BuilderModel {
    static class Person{
        private String name;
        private int age;
        private int height;
        private int width;
        private String hobby;

        public Person setName(String name){
            this.name = name;
            return this;
        }
        public Person setAge(int age){
            this.age = age;
            return this;
        }
        public Person setHeight(int h){
            height = h;
            return this;
        }
        public Person setWidth(int w){
            width = w;
            return this;
        }
        public Person setHobby(String h){
            hobby = h;
            return this;
        }

        @Override
        public String toString() {
            return "My name is "+name+", and I am "+age+" years old, my height is "+height+", my width is "+width+"," +
                    "and I like "+hobby;
        }
    }

    public static void main(String[] args) {
        Person p = new Person().setName("tiang").setAge(18).setHeight(200).setWidth(120).setHobby("play game");
        System.out.println(p);
    }

}


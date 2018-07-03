package DesignModel;

import java.util.ArrayList;

/**
 * Created by tiang on 2018/7/2.
 * 访问者模式，数据固定，而操作可能会经常变
 */
public class VisitorModel {
    /**
     * 定义节点类
     */
    interface Element{
        /**
         * 定义该节点可以被访问的方法，但是实际上并不必要，在访问者类中已经定义了访问规则
         */
//        void accept(Visitor visitor);
    }

    /**
     * 访问者类
     */
    interface Visitor{
        /**
         * 访问节点的具体实现方法
         * @param element
         */
        void view(Element element);
    }

    /**
     * 节点类1
     */
    static class Score implements Element{

        private int grade;

        private String name;
        public Score(int g, String s){
            grade = g;
            name = s;
        }

        public int getGrade(){
            return grade;
        }

        public String getName(){
            return name;
        }

//        @Override
//        public void accept(Visitor visitor) {
//            visitor.view(this);
//        }
    }

    /**
     * 节点类2
     */
    static class Classroom implements Element{

        private String owner;
        private String className;
        public Classroom(String o, String c){
            owner = o;
            className = c;
        }

        public String getOwner(){
            return owner;
        }

        public String getClassName(){
            return className;
        }

//        @Override
//        public void accept(Visitor visitor) {
//            visitor.view(this);
//        }
    }

    /**
     * 访问者类1
     */
    static class Teacher implements Visitor{

        @Override
        public void view(Element element) {
            if(element instanceof Score){
                Score score = (Score) element;
                System.out.println(String.format("%s 同学的分数是：%d", score.getName(), score.getGrade()));
            }else if (element instanceof Classroom){
                Classroom classroom = (Classroom)element;
                System.out.println(String.format("%s教室的主人是：%s", classroom.getClassName(), classroom.getOwner()));
            }
        }
    }

    /**
     * 访问者类2
     */
    static class Master implements Visitor{

        @Override
        public void view(Element element) {
            if(element instanceof Score){
                Score score = (Score) element;
                if(score.getGrade() > 80)
                    System.out.println(String.format("%s 同学是个好同学", score.getName()));
                else
                    System.out.println(String.format("%s 同学不怎么样啊", score.getName()));
            }
        }
    }

    /**
     * 聚合类，该类保存所有的节点数据，然后接受访问者的访问
     */
    static class ObjectStructure{
        private ArrayList<Element> list = new ArrayList<>();
        public void add(Element e){
            list.add(e);
        }
        public void accept(Visitor visitor){
            for(Element e: list){
//                e.accept(visitor);
                visitor.view(e);
            }
        }
    }

    public static void main(String[] args) {
        ObjectStructure structure = new ObjectStructure();
        structure.add(new Score(87, "tiang"));
        structure.add(new Score(59, "ling"));
        structure.add(new Classroom("tiang", "gold"));
        structure.add(new Classroom("ling", "silver"));
        Visitor visitor = new Teacher();
        structure.accept(visitor);
        System.out.println("=============================");
        visitor = new Master();
        structure.accept(visitor);
    }
}

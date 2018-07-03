package DesignModel;

/**
 * Created by tiang on 2018/7/2.
 * 装饰模式，适用于为目标类动态添加功能，且添加的功能可能出现自由组合的状况
 */
public class DecoratorModel {
    /**
     * 被装饰的目标接口
     */
    interface Component{
        /**
         * 被装饰的目标方法
         */
        void print();
    }

    /**
     * 初始的实现
     */
    static class RealComponent implements Component{

        @Override
        public void print() {
            System.out.println("I am the real one");
        }
    }

    /**
     * 装饰器父类，该父类仅仅提供调用被装饰目标的方法
     */
    static abstract class Decorator implements Component{

        /**
         * 保存一个Component对象
         */
        protected Component component;
        public Decorator(Component c){
            component = c;
        }

        /**
         * 调用目标方法
         */
        @Override
        public void print() {
            component.print();
        }
    }

    /**
     * 具体装饰器
     */
    static class RealDecorator extends Decorator{

        public RealDecorator(Component c) {
            super(c);
        }

        @Override
        public void print() {
            super.print();
            System.out.println("sub decorator print");
        }
    }

    static class RealDecorator2 extends Decorator{

        public RealDecorator2(Component c) {
            super(c);
        }

        @Override
        public void print() {
            super.print();
            System.out.println("sub decorator print 2");
        }
    }

    public static void main(String[] args) {
        Decorator decorator = new RealDecorator(new RealDecorator2(new RealComponent()));
        decorator.print();
    }
}

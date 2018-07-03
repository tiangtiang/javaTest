package DesignModel;


/**
 * Created by tiang on 2018/4/23.
 * 简单工厂模式
 */
public class SimpleFactory {
    /**
     * 直接在方法中写获取对象的逻辑，缺点就是以后扩展的时候需要修改该方法的逻辑，使之生成新的对象
     * @param fruitName
     * @return
     */
    public static Fruit getFruit(String fruitName){
        if(fruitName.equals("Apple"))
            return new Apple();
        else if(fruitName.equals("Orange"))
            return new Orange();
        return null;
    }

    public static void main(String[] args) {
        Fruit fruit = SimpleFactory.getFruit("Apple");
        fruit.eatFruit();
    }
}

interface Fruit{
    void eatFruit();
}

class Apple implements Fruit{

    @Override
    public void eatFruit() {
        System.out.println("eating apple");
    }
}

class Orange implements Fruit{

    @Override
    public void eatFruit() {
        System.out.println("eating orange");
    }
}
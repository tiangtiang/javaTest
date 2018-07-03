package DesignModel;

/**
 * Created by tiang on 2018/4/23.
 * 抽象工厂模式
 * 当一个工厂需要生产多个种类的产品时，就变成了抽象工厂模式，其他感觉没什么区别
 */
public class AbstractFactoryModel {
    public static void main(String[] args) {
        MultiFactory factory = new Lunch();
        Tools t = factory.getTool();
        Food f = factory.getFood();
        t.useTools();
        f.eatingFood();
    }
}

interface Tools{
    void useTools();
}

class Knife implements Tools{

    @Override
    public void useTools() {
        System.out.println("using knife");
    }
}

class Fork implements Tools{

    @Override
    public void useTools() {
        System.out.println("using fork");
    }
}

interface MultiFactory{
    Food getFood();
    Tools getTool();
}

class Breakfast implements MultiFactory{

    @Override
    public Food getFood() {
        return new Bread();
    }

    @Override
    public Tools getTool() {
        return new Knife();
    }
}

class Lunch implements MultiFactory{

    @Override
    public Food getFood() {
        return new Rice();
    }

    @Override
    public Tools getTool() {
        return new Fork();
    }
}
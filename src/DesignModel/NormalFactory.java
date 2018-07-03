package DesignModel;

/**
 * Created by tiang on 2018/4/23.
 * 普通工厂模式
 * 该模式的优点是扩展的时候无需修改功能性代码，只需要添加一个新的功能类和对应的工厂类即可（当然使用的地方也要做相应的修改）
 * 但是该模式有个很明显的缺点是需要创建大量的类，一个功能需要实现一个功能性类和一个工厂类。
 */
public class NormalFactory {
    public static void main(String[] args) {
        AbstractFactory factory = new BreadFactory();
        Food food = factory.createFood();
        food.eatingFood();
    }
}

/**
 * 具体的功能类
 */
interface Food{
    void eatingFood();
}

class Bread implements Food{

    @Override
    public void eatingFood() {
        System.out.println("eating bread");
    }
}

class Rice implements Food{

    @Override
    public void eatingFood() {
        System.out.println("eating rice");
    }
}

/**
 * 具体的工厂类
 */
interface AbstractFactory{
    Food createFood();
}
class BreadFactory implements AbstractFactory{

    @Override
    public Food createFood() {
        return new Bread();
    }
}

class RiceFactory implements AbstractFactory{

    @Override
    public Food createFood() {
        return new Rice();
    }
}
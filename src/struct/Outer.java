package struct;

/**
 * Created by lianglab on 2018/4/13.
 */
public class Outer {
    private int b = 2;
    private static String name = "outer";
    public class inner{
        public inner(){
            System.out.println("inner "+ b+" "+name);
        }
    }

    public static class Inner2{
        static int a = 3;
        public Inner2(){
            System.out.println("inner "+a+" "+name);
        }
    }

    public static void main(String[] args) {
        Outer o = new Outer();
        Outer.inner i = o.new inner();
        Outer.Inner2 i2 = new Outer.Inner2();
        String out = "ooooo";
        class Inner3{
            public Inner3(){
                System.out.println(out);
            }
            public void change(){
                o.b = 8;
                System.out.println(o.b);
            }
        }
        Inner3 i3 = new Inner3();
        i3.change();
        System.out.println(o.b);
        Inner3 i4 = new Inner3(){
            public void change(){
                System.out.println("this is a nameless class");
            }
        };
        i4.change();
    }
}

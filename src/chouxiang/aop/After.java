package chouxiang.aop;

/**
 * Created by lianglab on 2017/11/24.
 *
 * 在切点执行完毕后执行
 */
public @interface After {
    //参数，return, Others
    String value() default "";
}

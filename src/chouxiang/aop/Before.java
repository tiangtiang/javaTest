package chouxiang.aop;

/**
 * Created by lianglab on 2017/11/24.
 *
 * 在切点之前执行
 */
public @interface Before {
    //函数参数，return, others
    String value() default "";
}

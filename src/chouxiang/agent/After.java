package chouxiang.agent;

import java.lang.annotation.*;

/**
 * 在被调用函数之后调用
 *
 * Created by lianglab on 2017/11/24.
 */
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface After {
    /**
     * 被调用的方法
     * @return
     */
    String method() default "";

    /**
     * 被调用方法接收的参数类型列表
     * @return
     */
    String[] parameterTypes() default "";

    /**
     * 被调用的方法接收的参数值列表
     * @return
     */
    String[] parameterValues() default "";
}

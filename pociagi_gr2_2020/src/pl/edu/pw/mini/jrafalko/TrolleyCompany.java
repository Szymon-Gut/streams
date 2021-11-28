package pl.edu.pw.mini.jrafalko;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention ( RetentionPolicy . RUNTIME )
@Target(ElementType.FIELD)
public @interface TrolleyCompany {
    public String nazwaProducenta() default "";
    public String wielkoscDrezyny() default "";
}

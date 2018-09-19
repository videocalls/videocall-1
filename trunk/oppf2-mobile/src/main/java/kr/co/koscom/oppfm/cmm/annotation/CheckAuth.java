package kr.co.koscom.oppfm.cmm.annotation;

import java.lang.annotation.*;

/**
 * CheckAuth
 * <p>
 * Created by chungyeol.kim on 2017-05-16.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckAuth {
}

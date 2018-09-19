package kr.co.koscom.oppfm.cmm.message;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

/**
 * MessageUtil
 * <p>
 * Created by chungyeol.kim on 2017-04-21.
 */
public class MessageUtil extends ReloadableResourceBundleMessageSource implements MessageSource {
    private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

    public void setReloadableResourceBundleMessageSource(ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
        this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
    }

    public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
        return reloadableResourceBundleMessageSource;
    }

    public String getMessage(String code) {
        return getReloadableResourceBundleMessageSource().getMessage(code, null, Locale.getDefault());
    }

    public String getMessage(String code, Object[] args) {
        return getReloadableResourceBundleMessageSource().getMessage(code, args, Locale.getDefault());
    }
}

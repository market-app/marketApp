package br.com.marketapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Configuration
public class LocaleResolverConfig extends AcceptHeaderLocaleResolver {

    List<Locale> locales = Arrays.asList(
            Locale.US,
            Locale.of("pt", "BR")
    );

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String headLang = request.getHeader("Accept-Language");
        return headLang == null || headLang.isEmpty()
                ? Locale.ENGLISH
                : Locale.lookup(Locale.LanguageRange.parse(headLang), locales);
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
        rs.addBasenames(
                "messages-application",
                "messages-common",
                "messages-product"
        );
        rs.setUseCodeAsDefaultMessage(true);
        rs.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return rs;

    }
}

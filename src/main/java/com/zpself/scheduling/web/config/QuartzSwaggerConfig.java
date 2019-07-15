package com.zpself.scheduling.web.config;

import com.fasterxml.classmate.TypeResolver;
import com.zpself.scheduling.handler.CommonError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * swagger2配置类
 * @author LiMingSu
 */

@Configuration
@EnableSwagger2
@Import({BeanValidatorPluginsConfiguration.class})
public class QuartzSwaggerConfig {
    private static final String API_TITLE = "api.title";
    private static final String API_DESCRIPTION = "api.description";
    private static final String API_LICENSE = "api.license";
    private static final String API_LICENSE_URL = "api.license.url";
    private static final String API_VERSION = "api.version";

    @Autowired
    private  TypeResolver typeResolver;

    @Value("${token.header}")
    private String tokenHeader;
    @Value("${token.type}")
    private String tokenType;

    @Bean
    public Docket jobApi() {
        return (new Docket(DocumentationType.SWAGGER_2))
                .groupName("任务调度管理")
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zpself.scheduling.web.controller"))
                .paths(PathSelectors.any())
                .build()
                .consumes(Stream.of("application/json;charset=UTF-8").collect(Collectors.toSet()))
                .produces(Stream.of("application/json;charset=UTF-8").collect(Collectors.toSet()))
                .globalOperationParameters(Stream.of((new ParameterBuilder())
                        .name(this.tokenHeader)
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .required(true)
                        .description("令牌")
                        .defaultValue(this.tokenType + " ").build())
                        .collect(Collectors.toList()))
                .pathMapping("/")
                .additionalModels(this.typeResolver.resolve(CommonError.class))
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, this.responseMessages())
                .globalResponseMessage(RequestMethod.PUT, this.responseMessages())
                .globalResponseMessage(RequestMethod.POST, this.responseMessages())
                .globalResponseMessage(RequestMethod.DELETE, this.responseMessages());

    }

    private ApiInfo apiInfo() {
        Locale currentLocale = LocaleContextHolder.getLocale();

        ReloadableResourceBundleMessageSource messageSource = messageSource();

        return (new ApiInfoBuilder())
                .title(messageSource.getMessage(API_TITLE, null, currentLocale))
                .description(messageSource.getMessage(API_DESCRIPTION, null, currentLocale))
                .license(messageSource.getMessage(API_LICENSE, null, currentLocale))
                .licenseUrl(messageSource.getMessage(API_LICENSE_URL, null, currentLocale))
                .version(messageSource.getMessage(API_VERSION, null, currentLocale)).build();
    }

    private List<ResponseMessage> responseMessages() {
        return Stream.of((new ResponseMessageBuilder()).code(HttpStatus.BAD_REQUEST.value())
                        .message(HttpStatus.BAD_REQUEST.getReasonPhrase()).responseModel(new ModelRef("CommonError")).build(),
                (new ResponseMessageBuilder()).code(HttpStatus.UNAUTHORIZED.value())
                        .message(HttpStatus.UNAUTHORIZED.getReasonPhrase()).responseModel(new ModelRef("CommonError")).build(),
                (new ResponseMessageBuilder()).code(HttpStatus.NOT_FOUND.value()).message(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .responseModel(new ModelRef("CommonError")).build(), (new ResponseMessageBuilder()).code(HttpStatus.FORBIDDEN.value())
                        .message(HttpStatus.FORBIDDEN.getReasonPhrase()).responseModel(new ModelRef("CommonError")).build(),
                (new ResponseMessageBuilder()).code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .responseModel(new ModelRef("CommonError")).build()).collect(Collectors.toList());
    }

    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
        messageBundle.addBasenames(new String[]{"classpath:/messages/swagger-api"});
        messageBundle.setDefaultEncoding("UTF-8");
        return messageBundle;
    }
}

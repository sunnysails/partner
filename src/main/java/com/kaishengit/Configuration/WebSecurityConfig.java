package com.kaishengit.Configuration;

import com.kaishengit.security.MyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//允许进入页面方法前检验
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAuthenticationProvider provider;//自定义验证

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }
    /**
     * 自定义异常页
     *//*
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.FORBIDDEN, "/403");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
            container.addErrorPages(error401Page, error404Page, error500Page);
        });
    }*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/login", "/static/**", "/403").permitAll()//无需访问权限

//                .antMatchers().hasAuthority("管理员")//admin角色访问权限

//                .antMatchers().hasAuthority("")//user角色访问权限
                .anyRequest()//all others request authentication
                .authenticated()
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error").successForwardUrl("/home").permitAll()
                .and()
                .logout().logoutUrl("/logout").permitAll()
                .and()
                .csrf().disable();//关闭禁用POST等提交方式
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //将验证过程交给自定义验证工具
        auth.authenticationProvider(provider);
    }
    /**
     * URL拦截配置

     URL拦截配置可以在上一小节的WebSecurityConfig 中配置，但是此方法适用于大方向上的配置，具体的特殊路径也可以在@Controller的注解中具体配置。详细说明可以参考：Spring Boot Security Application
     */
/*    @ResponseBody
    @PreAuthorize("hasAuthority('"+StaticParams.USERROLE.ROLE_ADMIN+"')")//这里可以指定特定角色的用户访问权限
    @RequestMapping(value = "adminrequire", method = RequestMethod.GET)
    public String adminrequire(){
        return "HELLO from web but you should be admin";
    }*/
}
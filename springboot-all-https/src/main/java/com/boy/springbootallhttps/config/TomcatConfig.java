package com.boy.springbootallhttps.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-19 23:14
 */
@Configuration
public class TomcatConfig {
    @Bean
    TomcatServletWebServerFactory tomcatServletWebServerFactory() {
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection securityCollection = new SecurityCollection();
                securityCollection.addPattern("/*");
                securityConstraint.addCollection(securityCollection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcatServletWebServerFactory.addAdditionalTomcatConnectors(myConnectors());
        return tomcatServletWebServerFactory;
    }



    private Connector myConnectors() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11Nio2Protocol");
        connector.setScheme("http");
        connector.setPort(8081);
        connector.setSecure(false);
        connector.setRedirectPort(8080);
        return connector;

    }
}

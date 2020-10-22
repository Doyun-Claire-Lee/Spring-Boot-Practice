package me.doyun;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

@SpringBootApplication
public class SpringBootTomcatApplication {

    public static void main(String[] args) {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();
        String docBase = null;

        try {
            docBase = Files.createTempDirectory("tomcat-basedir").toString();
            Context context = tomcat.addContext("/test", docBase);


            HttpServlet servlet = new HttpServlet() {
                @Override
                protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    PrintWriter writer = resp.getWriter();
                    writer.println("<html><head><title>");
                    writer.println("Hey, Tomcat");
                    writer.println("</title></head>");
                    writer.println("<body><h1>Hello Tomcat</h1></body>");
                    writer.println("</html>");
                }
            };
            String servletName = "helloServlet";
            tomcat.addServlet("/test", servletName, servlet);
            context.addServletMappingDecoded("/hello.do", servletName);

            tomcat.start();
            tomcat.getServer().await();



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}

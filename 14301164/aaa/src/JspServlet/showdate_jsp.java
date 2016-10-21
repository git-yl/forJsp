package JspServlet;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
public class showdate_jsp implements Servlet {
public void init(ServletConfig config) throws ServletException {}
public void service(ServletRequest request, ServletResponse response)
throws ServletException, IOException {
PrintWriter out = response.getWriter();
out.println("     ");
out.println("<html>     ");
out.println("<head>     ");
out.println("<title>Show time</title>     ");
out.println("</head>     ");
out.println("<body>      ");
out.println("     Hello :      ");
out.println("     ");
out.println("");
     
         SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");     
         String str = format.format(new Date());     
      
out.println("     ");
out.println("      ");
out.println("");
out.println(str );
out.println("     ");
out.println("</body>     ");
out.println("</html> ");
out.println("");}
public void destroy() {}
public String getServletInfo() {
return null;
}
public ServletConfig getServletConfig() {
return null;}}
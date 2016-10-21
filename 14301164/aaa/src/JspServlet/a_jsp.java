package JspServlet;
import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
public class a_jsp implements Servlet {
public void init(ServletConfig config) throws ServletException {}
public void service(ServletRequest request, ServletResponse response)
throws ServletException, IOException {
PrintWriter out = response.getWriter();
out.println("     ");
out.println("<html>     ");
out.println("<head>     ");
out.println("<title>compute</title>     ");
out.println("</head>     ");
out.println("<body>      ");
out.println("     1+2+...+10=      ");
out.println("     ");
out.println("");
     
     	int p = 0;
         for(int i=1;i<=10;i++){
        	 p += i;
         }
      
out.println("     ");
out.println("      ");
out.println("");
out.println(p );
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
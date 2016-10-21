<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
     <html>
       <head>
         <title>欢迎</title>
       </head>
        <body>
               欢迎学习Java JSP现在的时间是:
               <%
               out.println(new java.util.Date());
               %>
          
        </body>
    </html>

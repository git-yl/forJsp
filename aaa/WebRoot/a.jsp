<%@ page language="java" contentType="text/html;charset=gb2312" %>     
<html>     
<head>     
<title>compute</title>     
</head>     
<body>      
     1+2+...+10=      
     <%     
     	int p = 0;
         for(int i=1;i<=10;i++){
        	 p += i;
         }
      %>     
      <%=p %>     
</body>     
</html> 

package container;
import java.io.File;
import java.io.IOException;
import java.net.URL;  
import java.net.URLClassLoader;  
import javax.servlet.Servlet;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;

import readJsp.readJsp;  
  
public class ServletProcessor
{  
    public void process(Request request, Response response)  
    {  
    	//得到路径，并取得相应servlet名称
        String uri = request.getUri();  
        String servletname = uri.substring(uri.lastIndexOf("/") + 1);  
        URLClassLoader loader = null;
        try  
        {  
            //创建路径及相应数据流
            URL[] urls = new URL[1];  
            File classPath = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator);
            //将完整路径转为相应格式
            URL url = classPath.toURI().toURL();
            urls[0]=url;
            //加载该路径
            loader = new URLClassLoader(urls);  
        }  
        catch (IOException e)  
        {  
            System.out.println(e.toString());  
        }  
        Class<?> myClass = null; 
        try  
        {  
            //加载相应路径下的相应类  
            myClass = loader.loadClass(readXml.getPath(servletname));  
        }  
        catch (ClassNotFoundException e)  
        {  
            System.out.println(e.toString());  
        }  
        Servlet servlet = null;  
        try  
        {  
            //加载相应的类并实例化，然后调用servlet的init,service方法。  
            servlet = (Servlet) myClass.newInstance();
            servlet.service((ServletRequest) request, (ServletResponse) response);
        }  
        catch (Exception e)  
        {  
            System.out.println(e.toString());  
        }  
        catch (Throwable e)  
        {  
            System.out.println(e.toString());  
        }  
    }  
    
    public void process2(Request request, Response response)  
    {  
    	//得到路径，并取得相应jsp名称
        String uri = request.getUri();  
        String jspname = uri.substring(uri.lastIndexOf("/") + 1);
        String[] names = jspname.split("\\.");
        File file = new File(System.getProperty("user.dir") + File.separator + "WebRoot" + File.separator + jspname);
        
        //当jsp文件存在时转换为servlet文件
        if(file.exists()){
        	try {
				readJsp.readFile(jspname);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
        }else{
        	//找不到文件时返回404信息
        	String errorMessage = "HTTP/1.1 404 File Not Found\r\n" + "Content-Type: text/html\r\n" + "Content-Length: 23\r\n" + "\r\n" + "<h1>File Not Found</h1>";  
            try {
				response.output.write(errorMessage.getBytes());
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
            return; 
        }

		File jsp1 = new File(System.getProperty("user.dir") + File.separator 
    			+ "bin" + File.separator + "JspServlet" + File.separator + names[0] + "_jsp.class");
		while(!jsp1.exists()){}
		
		File jsp2 = new File(System.getProperty("user.dir") + File.separator 
    			+ "src" + File.separator + "JspServlet" + File.separator + names[0] + "_jsp.java");
		while(!jsp2.exists()){}
		
        URLClassLoader loader = null;
        try  
        {  
            //创建路径及相应数据流
            URL[] urls = new URL[1];  
            File classPath = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator);
            //将完整路径转为相应格式
            URL url = classPath.toURI().toURL();
            urls[0]=url;
            //加载该路径
            loader = new URLClassLoader(urls);  
        }  
        catch (IOException e)  
        {  
            System.out.println(e.toString());  
        }  
        Class<?> myClass = null; 
        try  
        {
            //加载相应路径下的相应类  
            myClass = loader.loadClass("JspServlet." + names[0] + "_jsp");
        }  
        catch (ClassNotFoundException e)  
        {  
        	//类不能正常加载时返回404信息
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" + "Content-Type: text/html\r\n" + "Content-Length: 23\r\n" + "\r\n" + "<h1>File Not Found</h1>";  
            try {
				response.output.write(errorMessage.getBytes());
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
            System.out.println("meilei");
            return; 
        }  
        Servlet servlet = null;  
        try  
        {  
            //加载相应的类并实例化，然后调用servlet的init,service方法。  
            servlet = (Servlet) myClass.newInstance();
            servlet.service((ServletRequest) request, (ServletResponse) response);
        }  
        catch (Exception e)  
        {  
            System.out.println(e.toString());  
        }  
        catch (Throwable e)  
        {  
            System.out.println(e.toString());  
        }  
    }  

}  
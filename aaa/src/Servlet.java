

import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.InetAddress;  
import java.net.ServerSocket;  
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import container.Request;
import container.Response;
import container.ServletProcessor;
import container.StaticResourceProcessor;
import container.readXml;  
  
public class Servlet
{  
    //定义关闭
    private static final String CLOSE_COMMAND = "/SHUTDOWN";
    private boolean close = false;  
  
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException  
    {  
    	//实例化本身并调用,读取xml文件
    	Servlet servlet = new Servlet();
    	readXml.readX();
    	servlet.waiting();  
    }  
  
    public void waiting()  
    {  
    	//添加服务器处理
        ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8080, 1, InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}  
        
        //服务未关闭时，建立连接 
        while (!close)  
        {  
        	//定义变量
            Socket socket = null;  
            InputStream ins = null;  
            OutputStream os = null;  
            try  
            {
            	//得到输入输出
                socket = serverSocket.accept();  
                ins = socket.getInputStream();  
                os = socket.getOutputStream();  
                //实例化请求类
                Request request = new Request(ins);  
                request.parse();  
                //实例化返回类  
                Response response = new Response(os);  
                response.setRequest(request);
                String uri = request.getUri();
                boolean flag = false;
                try{
                	if(uri.substring(uri.length()-4).equals(".jsp")){
                		flag = true;
                	}
                } catch(Exception e){}
                //判断请求是否为servlet 
                if (readXml.ifServlet(uri))  
                {
                	//返回servlet网页
                    ServletProcessor processor = new ServletProcessor();  
                    processor.process(request, response);  
                }  
                else if(flag)
                {
                	//返回一个固定网页
                	ServletProcessor processor = new ServletProcessor();  
                    processor.process2(request, response);  
                }  
                else
                {
                	//返回一个固定网页
                    StaticResourceProcessor processor = new StaticResourceProcessor();  
                    processor.process(request, response);  
                }  
                //关闭连接
                socket.close();  
                //判断是否需要关闭连接  
                close = request.getUri().equals(CLOSE_COMMAND);  
            }  
            catch (Exception e)  
            {  
                e.printStackTrace();  
                System.exit(0);
            }  
        }  
    }  
}  
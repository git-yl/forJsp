package container;
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.UnsupportedEncodingException;  
import java.util.Enumeration;  
import java.util.Locale;  
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;  
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;  
  
public class Request implements ServletRequest  
{
	//定义输入及路径
    private InputStream input;  
    private String uri;  
    private String requestw; 
    
    public Request(InputStream input)  
    {  
        this.input = input;  
    }  

    public void parse()  
    {  
        //读取信息
        StringBuffer request = new StringBuffer(2048);  
        int i;  
        byte[] buffer = new byte[2048];  
        try  
        {  
            i = input.read(buffer);  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
            i = -1;  
        }  
        for (int j = 0; j < i; j++)  
        {  
            request.append((char) buffer[j]);  
        }
        //处理信息得到路径 
        requestw = request.toString();
        uri = parseUri(request.toString());  
    }
    
    public String getUri()  
    {  
    	if(uri==null){
    		return "";
    	}
        return uri;  
    }  
  
    //将路径从请求信息中取出
    private String parseUri(String requestdata)  
    {  
    	try{
	    	String[] data = requestdata.split("\r\n");
	    	String[] data2 = data[0].split(" ");
	    	return data2[1];
    	} catch(Exception e){
    		return null;
    	}  
    }  

	public String getParameter(String arg0) {
		String value = null;
		try{
			//从请求中获得相应变量值
			String[] data = requestw.split("\r\n\r\n");
			String[] paras = data[1].split("&");
			for(int i = 0; i < paras.length+1; i++){
				String[] a = paras[i].split("=");
				if(arg0.equals(a[0])){
					value = a[1];
					break;
				}
			}
		}catch(Exception e){
			return null;
		}
		return value;
	}

	@Override
	public AsyncContext getAsyncContext() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Object getAttribute(String arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String getCharacterEncoding() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public int getContentLength() {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public long getContentLengthLong() {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public String getContentType() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public DispatcherType getDispatcherType() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String getLocalAddr() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String getLocalName() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public int getLocalPort() {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public Locale getLocale() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Enumeration<Locale> getLocales() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String[] getParameterValues(String arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String getProtocol() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String getRealPath(String arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String getRemoteAddr() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String getRemoteHost() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public int getRemotePort() {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String getScheme() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String getServerName() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public int getServerPort() {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public ServletContext getServletContext() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public boolean isAsyncStarted() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean isAsyncSupported() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean isSecure() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public void removeAttribute(String arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void setAttribute(String arg0, Object arg1) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void setCharacterEncoding(String arg0) throws UnsupportedEncodingException {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public AsyncContext startAsync() throws IllegalStateException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public AsyncContext startAsync(ServletRequest arg0, ServletResponse arg1) throws IllegalStateException {
		// TODO 自动生成的方法存根
		return null;
	}  
}
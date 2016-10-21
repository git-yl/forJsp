package container;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.OutputStream;  
import java.io.PrintWriter;  
import java.util.Locale;  
  
import javax.servlet.ServletOutputStream;  
import javax.servlet.ServletResponse;  
  
public class Response implements ServletResponse  
{
	//定义部分变量以便使用
    private static final int BUFFER_SIZE = 1024;  
    public final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "WebRoot";  
    Request request;  
    OutputStream output;  
    PrintWriter writer;  
  
    public Response(OutputStream output)  
    {  
        this.output = output;  
    }  
  
    public void setRequest(Request request)  
    {  
        this.request = request;  
    }  
  
    //用于返回网页  
    public void sendStaticResource() throws IOException  
    {  
        byte[] bytes = new byte[BUFFER_SIZE];  
        FileInputStream fins = null;  
        try  
        {  
            //打开路径对应的网页  
            File file = new File(WEB_ROOT, request.getUri());  
            fins = new FileInputStream(file);  
            //读取文件中的数据  
            int ch = fins.read(bytes, 0, BUFFER_SIZE);
            //将数据循环输出
            while (ch != -1)  
            {  
                output.write(bytes, 0, ch);  
                ch = fins.read(bytes, 0, BUFFER_SIZE);  
            }  
        }  
        catch (Exception e)  
        {  
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" + "Content-Type: text/html\r\n" + "Content-Length: 23\r\n" + "\r\n" + "<h1>File Not Found</h1>";  
            output.write(errorMessage.getBytes());  
        }  
        finally  
        {  
        	//关闭连接
            if (fins != null)  
                fins.close();  
        }  
    }

	@Override
	public void flushBuffer() throws IOException {
		// TODO 自动生成的方法存根
		output.flush();
	}

	@Override
	public int getBufferSize() {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public String getCharacterEncoding() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String getContentType() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Locale getLocale() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		// TODO 自动生成的方法存根
		writer = new PrintWriter(output, true);  
        return writer;  
	}

	@Override
	public boolean isCommitted() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public void reset() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void resetBuffer() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void setBufferSize(int arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void setCharacterEncoding(String arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void setContentLength(int arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void setContentLengthLong(long arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void setContentType(String arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void setLocale(Locale arg0) {
		// TODO 自动生成的方法存根
		
	}  
}
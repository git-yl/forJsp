package container;
import java.io.IOException;  
  
public class StaticResourceProcessor  
{  
    public void process(Request request, Response response)  
    {  
        try  
        {  
        	//返回相应的网页
            response.sendStaticResource();
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
    }  
}  
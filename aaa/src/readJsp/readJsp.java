package readJsp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class readJsp {
	  
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "WebRoot";
	
	//用于读取jsp文件  
    public static void change(String file, String filename) throws IOException  
    {
    	//将改变后的jsp存入StringBuffer
    	StringBuffer content = new StringBuffer();
    	String[] names = filename.split("\\.");
    	content.append("package JspServlet;" + "\r\n");
    	int p = file.indexOf("%>", file.lastIndexOf("<%@"));
    	p += 2;
    	int p4 = 0;
    	int p5 = 0;
    	int p6 = 0;
    	int p7 = 0;
    	//将jsp文件中需要导入的文件写入文件头
    	while(true){
    		p4 = file.indexOf("import", p4);
    		if(p4 == -1 || p4 > p){
    			break;
    		} else{
    			p5 = file.indexOf("\"", p4);
    			p6 = file.indexOf("\"", p5+1);
    			p7 = file.indexOf(";", p4);
    			if(p5<p6){
    				String[] datas = file.substring(p5+1, p7).split(",");
    				for(int i=0; i<datas.length; i++){
    					content.append("import " + datas[i] + ";\r\n");
    				}
    			}
    		}
    		p4 = p6+1;
    	}
    	//添加其他需要的引入
    	content.append("import javax.servlet.*;" + "\r\n"
    			+ "import java.io.IOException;" + "\r\n"
    			+ "import java.io.PrintWriter;" + "\r\n"
    			+ "public class " 
    			+ names[0] + "_jsp" 
    			+ " implements Servlet {" + "\r\n"
    			+ "public void init(ServletConfig config) throws ServletException {}" + "\r\n"
    			+ "public void service(ServletRequest request, ServletResponse response)" + "\r\n"
    			+ "throws ServletException, IOException {" + "\r\n"
    			+ "PrintWriter out = response.getWriter();\r\n");
    	
    	//添加具体的文件内容
    	content.append("out.println(\"");
    	int p2 = 0;
    	int p3 = 0;
    	int j;
    	while(true){
    		//添加html文件的部分
    		p2 = file.indexOf("<%=", p);
    		p3 = file.indexOf("<%", p);
    		if(p3 == -1){
    			String[] datas = file.substring(p).split("\n");
    			for(int i=0; i<datas.length; i++){
    				String[] datas2 = datas[i].split("\"");
    				for(j=0; j<datas2.length-1; j++){
    					content.append(datas2[j] + "\\\"");
    				}
    				content.append(datas2[j] + "\");\r\nout.println(\"");
    			}
    			content.append("\");");
    			break;
    		//添加<% %>和<%= %> 部分
    		} else{
    			String[] datas = file.substring(p,p3).split("\n");
    			for(int i=0; i<datas.length; i++){
    				String[] datas2 = datas[i].split("\"");
    				for(j=0; j<datas2.length-1; j++){
    					content.append(datas2[j] + "\\\"");
    				}
    				content.append(datas2[j] + "\");\r\nout.println(\"");
    			}
    			content.append("\");\r\n");
    			p = file.indexOf("%>", p3);
    			//当下一个标签是<%时添加
        		if(p2 == -1 || p3 < p2){
        			String[] datas12 = file.substring(p3+2, p).split("\n");
        			for(int i=0; i<datas12.length; i++){
        				content.append(datas12[i] + "\r\n");
        			}
        	    	content.append("out.println(\"");
        	    //当标签是<%=时添加
        		}else {
        			String[] datas12 = file.substring(p2+3, p).split("\n");
        			for(int i=0; i<datas12.length; i++){
        				content.append("out.println(" + datas12[i] + ");\r\n");
        			}
        	    	content.append("out.println(\"");
        		}	
        		p += 2;
    		}
    	}
    	//添加其他函数
    	content.append("}" + "\r\n"
    			+ "public void destroy() {}" + "\r\n"
    			+ "public String getServletInfo() {" + "\r\n"
    			+ "return null;" + "\r\n"
    			+ "}" + "\r\n"
    			+ "public ServletConfig getServletConfig() {" + "\r\n"
    			+ "return null;}}");
    	//将数据传输到文件
    	outToFile(filename, content);
    }
    	
    private static void outToFile(String filename, StringBuffer content) {
    	// TODO 自动生成的方法存根
    	String[] jspname = filename.split("\\."); 
    	File jsp = new File(System.getProperty("user.dir") + File.separator 
    			+ "src" + File.separator + "JspServlet" + File.separator + jspname[0] + "_jsp.java");
		try {
			//打开文件将数据传入文件
			FileOutputStream fos = new FileOutputStream(jsp);
            PrintWriter pw = new PrintWriter(fos);
            pw.write(content.toString().toCharArray());
            pw.flush();
            pw.close();
            fos.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	//用于读取jsp文件  
    public static void readFile(String uri) throws IOException  
    {
        FileInputStream fins = null;
        String filecontent = "";
        try  
        {  
            //打开相应的jsp文件进行读取
            File file = new File(WEB_ROOT, uri);  
            StringBuilder sb = new StringBuilder();
            String s ="";
            BufferedReader br = new BufferedReader(new FileReader(file));

            while( (s = br.readLine()) != null) {
            sb.append(s + "\n");
            }

            //关闭文件流
            br.close();
            filecontent = sb.toString();
        }  
        catch (Exception e)  
        {  
        	//找不到文件时返回
            filecontent = "HTTP/1.1 404 File Not Found\r\n" + "Content-Type: text/html\r\n" + "Content-Length: 23\r\n" + "\r\n" + "<h1>File Not Found</h1>";  
        }  
        finally  
        {  
        	//关闭连接
            if (fins != null)  
                fins.close();  
        }  
        //将jsp形式变为servlet
        change(filecontent, uri);
    }

}

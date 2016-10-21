package container;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class readXml {
	
	static ArrayList<String> files;
	static ArrayList<String[]> servlets;
	
	public static boolean ifServlet(String uri){
		boolean ifServlet = false;
		try{
		//判断所需的uri是否为servlet
		for(int i = 0; i < servlets.size(); i++){
			if(servlets.get(i)[0].equals(uri.substring(uri.lastIndexOf("/") + 1))){
				ifServlet = true;
				break;
			}
		}
		}catch(Exception e){
			return false;
		}
		return ifServlet;
	}
	
	public static void readX() throws ParserConfigurationException, SAXException, IOException{
		//将xml文件读取为document类型
        DocumentBuilder db=DocumentBuilderFactory.newInstance().newDocumentBuilder();
		File directory = new File("");
        Document document=db.parse(new File(directory.getCanonicalPath()+"\\src\\web.xml"));
 
        //根据不同变量名得到相应的属性值
        Element root=document.getDocumentElement() ;
        NodeList list=root.getElementsByTagName("welcome-file-list");
        files = readFiles(list);       
        NodeList list2=root.getElementsByTagName("servlet");
        NodeList list3=root.getElementsByTagName("servlet-mapping");
        servlets = readServlets(list2,list3);
	}
	
	//读取servlet及其相关属性
	public static ArrayList<String[]> readServlets(NodeList n1, NodeList n2){
		ArrayList<String[]> servlets = new ArrayList<String[]>();
		for(int i = 0; i < n1.getLength(); i++){
			Node node = n1.item(i);
			Node node2 = n2.item(i);
			//子节点
			NodeList childList = node.getChildNodes();
			NodeList childList2 = node2.getChildNodes();
        	String[] a = new String[4];
			for(int x=0;x<childList.getLength();x++){
				Node childNode=childList.item(x);
				Node childNode2=childList2.item(x);
				//判断取出的值是否属于Element元素,目的是过滤掉
	            if(childNode instanceof Element){
	            	//得到子节点的名字
	            	String childNodeName=childNode.getNodeName();
	            	if(childNodeName.equals("servlet-name")){
	            		a[0] = childNode.getTextContent();
	            	}
	            	if(childNodeName.equals("servlet-class")){
	            		a[1] = childNode.getTextContent();
	            	}
	            }
	            if(childNode2 instanceof Element){
	            	//得到子节点的名字
	            	String childNodeName=childNode2.getNodeName();
	            	if(childNodeName.equals("servlet-name")){
	            		a[2] = childNode2.getTextContent();
	            	}
	            	if(childNodeName.equals("url-pattern")){
	            		a[3] = childNode2.getTextContent();
	            	}
	            }
			}
        	servlets.add(a);
		}
		return servlets;
    }
	
	//读取文件名
	public static ArrayList<String> readFiles(NodeList n2){
		ArrayList<String> files = new ArrayList<String>();
		Node n = n2.item(0);
		//子节点
		NodeList childList = n.getChildNodes();
		for(int x=0;x<childList.getLength();x++){
			Node childNode=childList.item(x);
			//判断取出的值是否属于Element元素,目的是过滤掉
            if(childNode instanceof Element){
            	//得到子节点的名字
            	String childNodeName=childNode.getNodeName();
            	if(childNodeName.equals("welcome-file")){
            		files.add(childNode.getTextContent());
            	}
            }
		}
		return files;
    }

	public static String getPath(String servletname) {
		//根据servlet名称获得相应路径
		String path = null;
		for(int i = 0; i < servlets.size(); i++){
			if(servlets.get(i)[0].equals(servletname)){
				path = servlets.get(i)[1];
				break;
			}
		}
		return path;
	}
}

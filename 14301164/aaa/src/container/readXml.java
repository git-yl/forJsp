package container;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class readXml {
	
	static ArrayList<String> files;
	static ArrayList<String[]> servlets;
	static String test;
	
	public static boolean ifServlet(String uri){
		test = "aa";
		boolean ifServlet = false;
		try{
		//判断是否servlet
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
		//��xml�ļ���ȡΪdocument����
        DocumentBuilder db=DocumentBuilderFactory.newInstance().newDocumentBuilder();
		File directory = new File("");
        Document document=db.parse(new File(directory.getCanonicalPath()+"\\src\\web.xml"));
 
        //���ݲ�ͬ�������õ���Ӧ������ֵ
        Element root=document.getDocumentElement() ;
        NodeList list=root.getElementsByTagName("welcome-file-list");
        files = readFiles(list);       
        NodeList list2=root.getElementsByTagName("servlet");
        NodeList list3=root.getElementsByTagName("servlet-mapping");
        servlets = readServlets(list2,list3);
	}
	
	//��ȡservlet�����������
	public static ArrayList<String[]> readServlets(NodeList n1, NodeList n2){
		ArrayList<String[]> servlets = new ArrayList<String[]>();
		for(int i = 0; i < n1.getLength(); i++){
			Node node = n1.item(i);
			Node node2 = n2.item(i);
			//�ӽڵ�
			NodeList childList = node.getChildNodes();
			NodeList childList2 = node2.getChildNodes();
        	String[] a = new String[4];
			for(int x=0;x<childList.getLength();x++){
				Node childNode=childList.item(x);
				Node childNode2=childList2.item(x);
				//�ж�ȡ����ֵ�Ƿ�����ElementԪ��,Ŀ���ǹ��˵�
	            if(childNode instanceof Element){
	            	//�õ��ӽڵ������
	            	String childNodeName=childNode.getNodeName();
	            	if(childNodeName.equals("servlet-name")){
	            		a[0] = childNode.getTextContent();
	            	}
	            	if(childNodeName.equals("servlet-class")){
	            		a[1] = childNode.getTextContent();
	            	}
	            }
	            if(childNode2 instanceof Element){
	            	//�õ��ӽڵ������
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
	
	//��ȡ�ļ���
	public static ArrayList<String> readFiles(NodeList n2){
		ArrayList<String> files = new ArrayList<String>();
		Node n = n2.item(0);
		//�ӽڵ�
		NodeList childList = n.getChildNodes();
		for(int x=0;x<childList.getLength();x++){
			Node childNode=childList.item(x);
			//�ж�ȡ����ֵ�Ƿ�����ElementԪ��,Ŀ���ǹ��˵�
            if(childNode instanceof Element){
            	//�õ��ӽڵ������
            	String childNodeName=childNode.getNodeName();
            	if(childNodeName.equals("welcome-file")){
            		files.add(childNode.getTextContent());
            	}
            }
		}
		return files;
    }

	public static String getPath(String servletname) {
		//����servlet���ƻ����Ӧ·��
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

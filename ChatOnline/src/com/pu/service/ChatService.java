package com.pu.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;



public class ChatService {
	private static ChatService cs;// ���õ���ģʽ
	// ʹ��Properties���󱣴�ϵͳ�������û���Ϣ
	private Properties userList;
	// ʹ��LinkedList���󱣴�������Ϣ
	private LinkedList<String> chatMsg;

	private ChatService() {
	}
    //ͨ������ģʽΨһ����ChatService����
	public static ChatService instance() {
		if (cs == null) {
			cs = new ChatService();
		}
		return cs;
	}
	//��ȡϵͳ�û���Ϣ
	private Properties loadUser()throws IOException{
		if(userList==null){
			File f=new File("userFile.properties");
			if(!f.exists()){
				f.createNewFile();				
			}
			userList=new Properties();
			
			userList.load(new FileInputStream(f));
						
		}
		
		return userList;
		
		
	}
	//����ϵͳ�����û�
	private boolean saveUserList()throws IOException{
	   if(userList==null){
		   return false;
		   
	   }
	   userList.store(new FileOutputStream("userFile.properties"), "Users Info List");
		return true;
		
	}
	//��֤�û���½��Ϣ
	public boolean validLogin(String user,String pass)throws IOException{
		String loadPass=loadUser().getProperty(user);
		if(loadPass!=null&&loadPass.equals(pass)){
			return true;
		}
		return false;
		}
	//ע�����û�
	public boolean addUser(String name,String pass)throws Exception{
		if(userList==null)
		{
			userList=loadUser();
		}
		if(userList.containsKey(name)){
			throw new Exception("�û����Ѿ����ڣ�������ѡ���û���");
			
		}
		userList.setProperty(name, pass);
		saveUserList();
		return true;
		
		
	}
	//��ȡϵͳ�е�����������Ϣ
	public String getMsg(){
		
		if(chatMsg==null){
			chatMsg=new LinkedList<String>();
			return " ";
		}
		StringBuilder result=new  StringBuilder();
		for(String line:chatMsg){
			result.append(line+"\n");
		}
		
		return result.toString();
		
		
	}
//�û����ԣ����������Ϣ
	public void addMsg(String user,String msg){
		if(chatMsg==null){
			chatMsg=new LinkedList<String>();
		}
		if(chatMsg.size()>40){
			chatMsg.removeFirst();
		}
		chatMsg.add(user+"˵"+msg);
	}
}

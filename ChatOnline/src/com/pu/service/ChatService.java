package com.pu.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;



public class ChatService {
	private static ChatService cs;// 采用单利模式
	// 使用Properties对象保存系统中所有用户信息
	private Properties userList;
	// 使用LinkedList对象保存聊天信息
	private LinkedList<String> chatMsg;

	private ChatService() {
	}
    //通过单利模式唯一返回ChatService对象
	public static ChatService instance() {
		if (cs == null) {
			cs = new ChatService();
		}
		return cs;
	}
	//读取系统用户信息
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
	//保存系统所有用户
	private boolean saveUserList()throws IOException{
	   if(userList==null){
		   return false;
		   
	   }
	   userList.store(new FileOutputStream("userFile.properties"), "Users Info List");
		return true;
		
	}
	//验证用户登陆信息
	public boolean validLogin(String user,String pass)throws IOException{
		String loadPass=loadUser().getProperty(user);
		if(loadPass!=null&&loadPass.equals(pass)){
			return true;
		}
		return false;
		}
	//注册新用户
	public boolean addUser(String name,String pass)throws Exception{
		if(userList==null)
		{
			userList=loadUser();
		}
		if(userList.containsKey(name)){
			throw new Exception("用户名已经存在，请重新选择用户名");
			
		}
		userList.setProperty(name, pass);
		saveUserList();
		return true;
		
		
	}
	//获取系统中的所有聊天信息
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
//用户发言，添加聊天信息
	public void addMsg(String user,String msg){
		if(chatMsg==null){
			chatMsg=new LinkedList<String>();
		}
		if(chatMsg.size()>40){
			chatMsg.removeFirst();
		}
		chatMsg.add(user+"说"+msg);
	}
}

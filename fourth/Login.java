package myPackage2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.net.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.ListSelectionModel;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

public class Login
{
	
	//
	static String ip=null; //txt���Ͽ���, ip�о��.
	static int portnum=0; //port number
    String serverAddress; //���� �ּ�
    Scanner in;
    PrintWriter out;
    String curid;    //���� �α��� id
    
    //login gui variables
    JFrame frame=new JFrame("login form");
    JLabel lbl,la1,la2,la3,emp;
    JTextField id;
    JPasswordField passwd;
    JPanel emptyPanel,idPanel,paPanel,loginPanel;
    JButton b1,b2;
    JTextArea content;
    
    //TODO:
    //�޴�â
    
    JFrame menu_frame;
	JLabel user_name;
	JLabel user_state;
	JPanel menu_p;
	JButton make_room;
	JButton add_friend;
	JList friend_list;
	DefaultListModel friend_model;
	JButton change_info;
	JPopupMenu popupMenu;
	JMenuItem lookinfo;
	JMenuItem sendMsg;
    
    
    //ä�ù� swing ����
    JFrame room_frame;
	JTextField sendTF; //���� �޽��� ���� ��
	JLabel la_msg;	
	JTextArea ta;	//�޽����� �׿��� ���̴� ��
	JScrollPane sp_ta,sp_list;  
    JList<String> user;	// ���� ä�ù濡 �ִ� ���� ǥ��
	JButton invite_btn,exit_btn;  //�ʴ� ������ ��ư 
	JPanel p;	 
	
	//�߰��Ϸ��� ģ��
    String relaid;
	
    public Login(String serverAddress)
    {
    	this.serverAddress=serverAddress;
          // FlowLayout���
          frame.setLayout( new FlowLayout() );
          
          // Border�� ���� ����
          EtchedBorder eborder =  new EtchedBorder();
          // ���̺� ����     
          lbl = new JLabel( "Enter Id and Password" );
          // ���̺� ���� �����
          lbl.setBorder(eborder);
          // ���̺� �߰�
          frame.add(lbl);

          emptyPanel = new JPanel();
          emp = new JLabel("\n");
          emptyPanel.add(emp);
          frame.add(emp);
          
          // id�гΰ� pw �гλ���
          idPanel = new JPanel();
          paPanel = new JPanel();

          la3 = new JLabel("Username");
          la2 = new JLabel("      Password");
          // id�ؽ�Ʈ�ʵ�� pw�ؽ�Ʈ �ʵ� ����
          id = new JTextField(20);
          passwd = new JPasswordField(20);
          idPanel.add(la3);
          idPanel.add(id);
          paPanel.add(la2);
          paPanel.add(passwd);
          
          // �α��ΰ� ȸ�������� ���� �г� ����
          
          loginPanel = new JPanel();
          b1 = new JButton("Login");
          b2 = new JButton("Resister");
          loginPanel.add(emp);
          loginPanel.add(b1);
          loginPanel.add(b2);
          frame.add(emptyPanel);
          frame.add(emptyPanel);
          frame.add(idPanel);
          frame.add(paPanel);
          frame.add(emptyPanel);
          frame.add(loginPanel);
          
          
          
          
          b1.addActionListener(new ActionListener() 
          {
        	  public void actionPerformed(ActionEvent e)
        	  {
        		  String comment=e.getActionCommand();
        		  if(comment.contentEquals("Login")) //�α��� ��ư�� ������ ��,
        		  {
        			  String logid= id.getText().trim(); //id�� ���� ���� �ҷ���
        			  String logpw= passwd.getText(); //�н����忡 ���� ���� �ҷ���.
        			  out.println("logid"+logid+" "+logpw); //�������� ���̵�� �н����� ����
        			  if(logid.length()==0 || logpw.length()==0) 
                	  {
                	  JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� �Է� �ϼž� �˴ϴ�.", "���̵� ����� �Է�!", JOptionPane.DEFAULT_OPTION);
                	  return;
                	  }
        			  frame.dispose();
        		  }
        	  }
        	  });
          b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Resister();				
			}
          });
          // 3�� 20�� ������ �ؽ�Ʈ����� 
          //content = new JTextArea(3,20);
          //JScrollPane s= new JScrollPane(content);
          //add(s);
          frame.setSize( 400, 200 );
          frame.setVisible(true);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public class Resister{

    	JFrame sub=new JFrame("Resister");
    	JLabel lbl,namelbl,birthlbl,emaillbl, idlbl,pwlbl,nicklbl;
        JTextField nameField, birthField,emailField, idField, nickField;
        JPasswordField passwd;
        JPanel namePanel,birthPanel, emailPanel, idPanel,paPanel,nickPanel;
        JButton resister_btn;
        JTextArea content;
        
    	public Resister() {
    	        sub.setLayout(new FlowLayout());
    	        
    	        EtchedBorder eborder =  new EtchedBorder();
  
    	        lbl = new JLabel( "Enter user information" );
    	        // ���̺� ���� �����
    	        lbl.setBorder(eborder);
    	        sub.add( lbl );
    	        namePanel = new JPanel();
    	        birthPanel = new JPanel();
    	        emailPanel = new JPanel();
    	        idPanel = new JPanel();
    	        paPanel = new JPanel();
    	        nickPanel = new JPanel();
    	        
    	        namelbl = new JLabel("User Name");
    	        nameField = new JTextField(10);
    	        namePanel.add(namelbl);
    	        namePanel.add(nameField);
    	        sub.add(namePanel);
    	        
    	        birthlbl = new JLabel("birthday");
    	        birthField = new JTextField(10);
    	        birthPanel.add(birthlbl);
    	        birthPanel.add(birthField);
    	        sub.add(birthPanel);
    	        
    	        emaillbl = new JLabel("email");
    	        emailField = new JTextField(10);
    	        emailPanel.add(emaillbl);
    	        emailPanel.add(emailField);
    	        sub.add(emailPanel);
    	        idlbl = new JLabel("user Id");
    	        idField = new JTextField(10);
    	        idPanel.add(idlbl);
    	        idPanel.add(idField);
    	        sub.add(idPanel);
    	        pwlbl = new JLabel("password");
    	        passwd = new JPasswordField(10);
    	        paPanel.add(pwlbl);
    	        paPanel.add(passwd);
    	        sub.add(paPanel);
    	        nicklbl = new JLabel("nick name");
    	        nickField = new JTextField(10);
    	        nickPanel.add(nicklbl);
    	        nickPanel.add(nickField);
    	        sub.add(nickPanel);

    	        resister_btn = new JButton("Resister");
    	        sub.add(resister_btn);
    	        resister_btn.addActionListener(new ActionListener() 
    	          {
    	        	  public void actionPerformed(ActionEvent e) 
    	        	  {
    	        		  String comment=e.getActionCommand();
    	        		  if(comment.contentEquals("Resister")) //register��ư �Է½�,
    	        		  {
    	        			  String name=nameField.getText(); //�̸�, ������ϵ��� �ҷ���
    	        			  String bday=birthField.getText();
    	        			  String email=emailField.getText();
    	        			  String id=idField.getText();
    	        			  String pw=passwd.getText();
    	        			  String nick = nickField.getText();
    	        			  out.println("Resister"+name+" "+bday+" "+email+" "+id+" "+pw + " " + nick); //�������� ����.
    	        		  }
    	        		  sub.dispose();
    	        	  }
    	        	  });
    	        sub.setSize( 250, 350 );
    	        sub.setVisible(true);
    	        sub.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	}
    }
     
    //TODO:
    public class Chatting_Room {
    	
    		
    	   public Chatting_Room(String Room_name) {

    		  room_frame = new JFrame(Room_name);

    		  sendTF = new JTextField(15);	  

    		  la_msg = new JLabel("Message");

    		  	  

    		  ta = new JTextArea();

    		  ta.setLineWrap(true);//TextArea ���α��̸� ����� text�߻��� �ڵ� �ٹٲ�

    		  user = new JList<String>();

    		  

    		  sp_ta = new JScrollPane(ta);

    		  sp_list = new JScrollPane(user);

    		  	  

    		  invite_btn = new JButton("ģ�� �ʴ�");

    		  exit_btn = new JButton("������");

    		  

    		  p = new JPanel();

    		  

    		  sp_ta.setBounds(10,10,380,390); 

    		  la_msg.setBounds(10,410,60,30); 

    		  sendTF.setBounds(70,410,320,30); 

    		  

    		  sp_list.setBounds(400,10,120,350); 

    		  invite_btn.setBounds(400,370,120,30); 

    		  exit_btn.setBounds(400,410,120,30); 

    		  

    		  p.setLayout(null);

    		  p.setBackground(Color.GRAY);

    		  p.add(sp_ta);

    		  p.add(la_msg);

    		  p.add(sendTF);

    		  p.add(sp_list);

    		  p.add(invite_btn);

    		  p.add(exit_btn);

    		  

    		  room_frame.add(p);

    		  room_frame.setBounds(300,200,550,500);

    		  room_frame.setVisible(true);

    		  sendTF.requestFocus();	 
    	   }
    	
    }
    //TODO:

    public class Menu {
    	
    	public Menu(String name, String comment, String friend) {
    		
        	popupMenu = new JPopupMenu();
        	lookinfo = new JMenuItem("ģ����������");
        	sendMsg = new JMenuItem("ä�ý�û�ϱ�");
        	popupMenu.add(lookinfo);
        	popupMenu.add(sendMsg);

    		
    		
    		menu_frame = new JFrame("Menu");
    		user_name = new JLabel(name);
    		user_state = new JLabel(comment);
    		make_room = new JButton("�� �����");
    		add_friend=new JButton("ģ���˻�");
    		friend_list = new JList(new DefaultListModel());
    		friend_model = (DefaultListModel)friend_list.getModel();
    		
    		for(String add:friend.split(" ")) {
    			friend_model.addElement(add);
    		}
    		friend_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  		
    		
    		friend_list.addMouseListener(new MouseAdapter() {
    			@Override
    			public void mousePressed(MouseEvent e) {
    				// ������ ��ư Ŭ�� �� ...
    				if(e.getModifiers() == MouseEvent.BUTTON3_MASK) { // ������ 1 ����� 2 �������� 3   BUTTON3_MASK - ������ ��ư
    					// System.out.println("����");
    					popupMenu.show(friend_list, e.getX(), e.getY());
        				friend_list = (JList)e.getSource();
    				}
    			}
    		});		
    		
    		friend_model.remove(0); // db�� ����� ģ���̸��� null�� �����ؼ� �տ� null ������
    		change_info = new JButton("���� ����");
    		
    		
    		user_name.setBounds(10, 50, 150, 20);
    		user_state.setBounds(10, 80, 350, 15);
    		make_room.setBounds(200, 400, 120, 30);
    		add_friend.setBounds(10, 10, 120, 30);
    		friend_list.setBounds(10, 110, 350, 130);
    		change_info.setBounds(150, 10, 120,30);
    		
    		menu_p = new JPanel();
    		menu_p.setLayout(null);

    		menu_p.add(add_friend);
    		menu_p.add(change_info);

    		menu_p.add(user_name);
    		menu_p.add(user_state);
    		
    		menu_p.add(friend_list);
    		
    		menu_p.add(make_room);

    		menu_frame.add(menu_p);
    		
    		
    		
    		menu_frame.setSize(400,500);
    		menu_frame.setVisible(true);	

    		
    		//�游��� ��ư ������
    		make_room.addActionListener(new ActionListener() {
     			@Override
     			public void actionPerformed(ActionEvent e) {
     				new Chatting_Room("chatting room");				
     			}
               });
    		add_friend.addActionListener(new ActionListener() {
     			@Override
     			public void actionPerformed(ActionEvent e) 
     			{
     						new Addfr();		
     			}
               });
    		change_info.addActionListener(new ActionListener() { // �޴����� �������� ��ư ������ 

				@Override
				public void actionPerformed(ActionEvent e) {
						new ChangeInfo();
				}
    		});
    		
	    	}
	
    
	    
    	
    }
    public class Addfr //�α����ؼ�, user�� �˻��ϴ�â
    {
 	   public Addfr()
 	   {
 		   JFrame adder=new JFrame();
 		   adder.setTitle("ģ�� �˻�");
 		  adder.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 			adder.setLayout(new FlowLayout());
 			JLabel searid=new JLabel("���̵�");
 			JTextField uid=new JTextField(20);
 			adder.add(searid);
 			adder.add(uid);
 			adder.setSize(300,150);
 			adder.setVisible(true);
 			JButton search=new JButton("�˻�");
 			JButton add=new JButton("�߰�");
 			JButton list=new JButton("ģ������Ʈ");
 			JPanel pnr=new JPanel();
 			pnr.add(search);
 			pnr.add(add);
 			pnr.add(list);
 			adder.add(pnr,BorderLayout.SOUTH);
 			search.addActionListener(new ActionListener() //text�� �ۼ��ϰ�, �˻��� ��. 
 				       	{
 				    	   public void actionPerformed(ActionEvent e)
 				    	   {
 				    		   String comment=e.getActionCommand();
 		    	        		  if(comment.contentEquals("�˻�")) 
 		    	        		  {
 				    			  String conveyid=uid.getText(); //�ؽ�Ʈâ���� ������ �޾ƿ´�
 				    			  out.println("Search"+conveyid);//������ �˻��ϴ� ���̵� ����.
 		    	        		  }
 				    	   }
 				       });	   
 			add.addActionListener(new ActionListener() //text�� ������ Ȯ���ϰ�, ģ���� �߰���.
				       	{
				    	   public void actionPerformed(ActionEvent e)
				    	   {
				    		   String comment=e.getActionCommand();
		    	        		  if(comment.contentEquals("�߰�")) 
		    	        		  {
				    			  relaid=uid.getText(); //�ؽ�Ʈâ���� ������ �޾ƿ´�
				    			  out.println("ADDF"+relaid);//������ �߰��Ϸ��� id�� ������. 
		    	        		  }
				    	   }
				       });	   
 			list.addActionListener(new ActionListener() //text�� ������ Ȯ���ϰ�, ģ���� �߰���.
			       	{
			    	   public void actionPerformed(ActionEvent e)
			    	   {
			    		   String comment=e.getActionCommand();
	    	        		  if(comment.contentEquals("ģ������Ʈ")) 
	    	        		  {
	    	        			  out.println("List"+curid);
	    	        		  }
			    	   }
			       });	   
 		}  
 	   
 	   }
    
    public class ChangeInfo{ //������ �����ϴ� â
    	public ChangeInfo() {
    		JFrame ch_frame=new JFrame();
    		JPanel ch_panel = new JPanel();
    		ch_panel.setLayout(null);

    		
  		   	ch_frame.setTitle("�� ���� ����");
  		   	
  		   	JLabel nicklbl2 = new JLabel( "Nick Name");
  		   	JTextField nickField2 = new JTextField(50); //�ٲ� �г���
  		   	
  		   	JLabel commentlbl = new JLabel("Comment");
  		   	JTextField commentField = new JTextField(50); // �ٲ� ���� �޽���
  		   	
  		   	JButton ch_btn = new JButton("���� ����"); // ���� ���� ��ư ������ �ٲٰ��� �ϴ� ������ �ݿ���
  		   	
  		   	nicklbl2.setBounds(10,10,100,30);
  		   	nickField2.setBounds(130, 10, 300,30);
  		   	ch_panel.add(nickField2);
  		   	ch_panel.add(nicklbl2);
  		   	
  		   	commentlbl.setBounds(10,80,100,30);
  		   	commentField.setBounds(130, 80, 300,30);
  		   	ch_panel.add(commentlbl);
  		   	ch_panel.add(commentField);
  		   	
  		   	ch_btn.setBounds(330,120,100,30);
  		   	ch_panel.add(ch_btn);
  		   	
  		   	ch_frame.add(ch_panel);
  		   	ch_frame.setSize(500,200);
  		   	ch_frame.setVisible(true);
  		   	
  		   	ch_btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String ch_nick = nickField2.getText(); 
					String ch_comment = commentField.getText();
					out.println("ChangeInfo:"+ch_nick+":"+ch_comment);	// �ٲ� ���� ������ �˸�
		    		user_state.setText(ch_comment);	// �ٲ� ������ �޴� ĭ�� �ݿ���
					ch_frame.dispose();
				}
  		   		
  		   	});
  		   	
  		   	
  		   	
  		   
    	}
    	
    }
 private void run() {
              try {
            	  Socket socket = new Socket(serverAddress, portnum); //�Է¹��� ip�ּҿ� portnumber�� ���� ����.
                  in = new Scanner(socket.getInputStream());
                  out = new PrintWriter(socket.getOutputStream(), true);
                  String[] userinfo = null;
                  while(true)
                  {
                	  String line=in.nextLine();
                	  System.out.println(line);
                	  if(line.contains("access"))
                	  {
                		  userinfo = line.split(":");
                		  
                    	  JOptionPane.showMessageDialog(null, "�α��� ����", "�α��� Ȯ��!", JOptionPane.DEFAULT_OPTION);//������ �α��� �����̶�� ���� ��, â ǥ��
                    	  //TODO:
                    	  new Menu(userinfo[1],userinfo[2],userinfo[4]); 
                    	  curid=userinfo[3];
                	  }
                	  // ���̵� Ʋ���� ��
                	  else if(line.contains("id invalid"))
                	  {
                		  JOptionPane.showMessageDialog(null, "���̵� Ʋ��!", "�α��� Ȯ��!", JOptionPane.DEFAULT_OPTION); //�α��� ���� ��, âǥ��
                		  return;
                	  }
                	  
                	  // ��й�ȣ�� Ʋ���� ��
                	  else if(line.contains("pw invalid"))
                	  {
                		  JOptionPane.showMessageDialog(null, "��й�ȣ Ʋ��!", "�α��� Ȯ��!", JOptionPane.DEFAULT_OPTION); //�α��� ���� ��, âǥ��
                		  return;
                	  }
                	  else if(line.contains("Welcome"))
                	  {
                		  JOptionPane.showMessageDialog(null, "ȸ�����Լ���", "ȯ���մϴ�!", JOptionPane.DEFAULT_OPTION); //ȸ������ ���� ��, âǥ��
                		  return;
                	  }
                	  else if(line.contains("Searching")) //�˻��� ������ �޾��� ��,
                	  {
                		  String printinfo=line.substring(9); //���� �̾Ƴ�
                		  String [] showbox=printinfo.split(" ");
                		  String wholeinfo="";
                		 wholeinfo="name"+":"+showbox[0]+"\n"+"birthday"+":"+showbox[1]+"\n"+"email"+":"+showbox[2]+"\n"+"comment"+":"+showbox[3];
                		  JOptionPane.showMessageDialog(null, wholeinfo, "�˻��Ͻ� ������ ���� �����ϴ�.", JOptionPane.DEFAULT_OPTION); 
                		  //���� ������ ���.
                	
                	  }
                	  else if(line.contains("ADD")) //ģ�� �߰��Ϸ��
                	  {
                		  JOptionPane.showMessageDialog(null, "ģ���߰��Ϸ�", "�����մϴ�", JOptionPane.DEFAULT_OPTION); //ģ���߰��� ���, âǥ��
                		  friend_model.addElement(relaid);
                	  }
                	  else if(line.contains("DUPLICATION")) 
                	  {
                		  JOptionPane.showMessageDialog(null, "�̹� �߰��� ģ���Դϴ�.", "ģ�� �߰� ����",JOptionPane.DEFAULT_OPTION);// ģ���� �ߺ��� ���
                	  }
                	  else if(line.contains("Frlist")) //server�� ģ������Ʈ�� �������� ��.
                	  {
                		  String flend=line.substring(6); //ģ������Ʈ�� ���ڿ�ȭ
                		  String [] flist=flend.split(" "); //ģ������Ʈ�� �Ѳ����� �Ա� ������, �ɰ��ش�.
                		  String ourfr="";             
                		  for(int i=0; i<flist.length;i++)
                		  {
                			  ourfr=ourfr+flist[i]+"\n"; //ourfr�� ģ������Ʈ �ɰ��� ����
                		  }
                		  JOptionPane.showMessageDialog(null, ourfr, "������ ģ������� �Ʒ��� �����ϴ�", JOptionPane.DEFAULT_OPTION); //ģ������� �ӽ÷� âǥ��
                		  return; //ģ������Ʈ ���.
                	  }
                	  else if(line.contains("No user")) //server����, �ش� ����ڰ� ���ٰ� ���� ��� ���� ���.
                	  {
                		  JOptionPane.showMessageDialog(null, "�������� �ʴ� ���̵��Դϴ�.", "error	", JOptionPane.DEFAULT_OPTION); //ģ������� �ӽ÷� âǥ��
                		  
                	  }
                  }
                  }
              catch(Exception e)
              {
            	  System.out.println(e);
              }
              finally {
                  frame.dispose();
               
              }
               
              }
    public static void server(String fileName) //input.txt���Ͽ��� ip�ּҿ� port number�� �ҷ����� �Լ�.
          {  
      		Scanner inputStream = null;
      		try 
      		{
      			inputStream = new Scanner(new File(fileName));//input������ �о��.
      		} 
      		catch (FileNotFoundException e) //������ ���� ���, �ڵ�����
      		{
      			ip = "localhost";
      			portnum = 9999; 
      			e.printStackTrace();
      		}
      		
      		while (inputStream.hasNext()) //input���Ͽ���, ip�ּҿ� portnumber�� �о��.
      		{
      			ip = inputStream.next(); 
      			portnum = inputStream.nextInt();
      		}
          }
    public static void main(String args[])
    {
    	 String fname="input.txt";
	        server(fname); // input�������� ���� �ҷ�����
	        Login client=new Login(ip);
	        client.run();
    }
}

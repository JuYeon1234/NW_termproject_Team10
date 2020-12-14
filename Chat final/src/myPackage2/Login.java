package myPackage2;
import java.awt.event.ActionEvent;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.MouseAdapter;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.net.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import myPackage2.socialDate;
public class Login
{
   //
   static String ip=null; //txt���Ͽ���, ip�о��.
   static int portnum=0; //port number
    String serverAddress; //���� �ּ�
    Scanner in; //������ �޾Ƶ��� �Ű�ü
    PrintWriter out; //�������� ���� �Ű�ü
    String curid;    //���� �α��� id
    
    //login gui variables
    JFrame frame=new JFrame("login form"); //ó�� �α���â
    JLabel lbl,la1,la2,la3,emp;
    JTextField id;
    JPasswordField passwd;
    JPanel emptyPanel,idPanel,paPanel,loginPanel;
    JButton b1,b2,b3;
    JTextArea content;
    String host; //ä�ù� �����ڸ� �����Ͽ�, ǥ���ϱ� ���� ���� ����
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
   JTextArea ta;   //�޽����� �׿��� ���̴� ��
   JScrollPane sp_ta,sp_list;  
    JList<String> user;   // ���� ä�ù濡 �ִ� ���� ǥ��
   JButton invite_btn,exit_btn;  //�ʴ� ������ ��ư 
   JPanel p;  
   DefaultListModel user_model;

   
   //�߰��Ϸ��� ģ��
    String relaid;
    //��й�ȣ ��ȣȭ �ڵ�
   public String encryptSHA256(String str) {
        String sha="";
        try { MessageDigest sh= MessageDigest.getInstance("SHA-256");
        sh.update(str.getBytes());
        byte[] byteData = sh.digest();
        StringBuilder sb=new StringBuilder();
        for (byte byteDatum : byteData) {
           sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
           } 
           sha=sb.toString(); }
        catch (NoSuchAlgorithmException e) {
           System.out.println("��ȣȭ ����-NoSuchAlgorithmException");
           sha=null;
        }
        return sha;
   }
   //constructor
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

          la3 = new JLabel("User ID       ");
          la2 = new JLabel("Password  ");
          // id�ؽ�Ʈ�ʵ�� pw�ؽ�Ʈ �ʵ� ����
          id = new JTextField(20);
          passwd = new JPasswordField(20);
          idPanel.add(la3);
          idPanel.add(id);
          idPanel.setBackground(Color.yellow);
          paPanel.add(la2);
          paPanel.add(passwd);
          paPanel.setBackground(Color.yellow);
          
          // �α��ΰ� ȸ�������� ���� �г� ����
          
          loginPanel = new JPanel();
          loginPanel.setBackground(Color.white);
          b1 = new JButton("Login");
          b2 = new JButton("Resister");
          b3 = new JButton("Withdrawal");
          b1.setBackground(Color.yellow);
          b2.setBackground(Color.yellow);
          b3.setBackground(Color.yellow);
          
          loginPanel.add(emp);
          loginPanel.add(b1);
          loginPanel.add(b2);
          loginPanel.add(b3);
          frame.add(idPanel, BorderLayout.WEST);
          frame.add(paPanel, BorderLayout.WEST);
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
                   String encryptLogPW = encryptSHA256(logpw); //��ȣȭ�� ��й�ȣ�� �ص��ؼ� �޾ƿ�
                   out.println("logid"+logid+" "+encryptLogPW); //�������� ���̵�� �н����� ����
                   if(logid.length()==0 || logpw.length()==0) //id�� ��й�ȣ�� �ƹ� �͵� �Է����� �ʾ��� ��, ���� �޽����� ǥ����.
                     {
                     JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� �Է� �ϼž� �˴ϴ�.", "���̵� ����� �Է�!", JOptionPane.DEFAULT_OPTION);
                     return;
                     }
                   frame.dispose();
                }
             }
             });
          b2.addActionListener(new ActionListener() { //ȸ�������� ������ ��, ȸ������ â���� �̵������ش�.
         @Override
         public void actionPerformed(ActionEvent e) {
            new Resister();            
         }
          });
          
          b3.addActionListener(new ActionListener() { //ȸ�������� ������ ��, ȸ������ â���� �̵������ش�.
         @Override
         public void actionPerformed(ActionEvent e) {
            new Withdrawal();            
         }
          });
          // 3�� 20�� ������ �ؽ�Ʈ����� 
          //content = new JTextArea(3,20);
          //JScrollPane s= new JScrollPane(content);
          //add(s);
          frame.setSize( 400, 200 );
          frame.getContentPane().setBackground(Color.white);
          frame.setVisible(false);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    //TODO:
    //ä�ù� ������
    public class Chatting_Room {
          public Chatting_Room(String id,String hst,  String friend) 
          {
        	  
            room_frame = new JFrame(id+"'s chatting room"+" host-"+hst); //room frame ��ܿ� �ش� ����� ä�ù��̴ٴ� ���� ǥ�� �����ְ�, ä�ù��� ȣ��Ʈ�� �������� �˷��ش� 
            sendTF = new JTextField(15);     
            la_msg = new JLabel("Message");
            ta = new JTextArea();
            ta.setLineWrap(true);//TextArea ���α��̸� ����� text�߻��� �ڵ� �ٹٲ�
            user = new JList(new DefaultListModel()); //ä�ù��� ����Ʈ�� ǥ�����ְ� ������Ʈ ���ش�
            user_model = (DefaultListModel)user.getModel();
            //friend = friend.substring(1,friend.length()-1)
            for(String f : friend.split(",")) {  //ä�ù��� ����� ��¿�
               user_model.addElement(f);
            }
           
            sp_ta = new JScrollPane(ta);
            sp_list = new JScrollPane(user);
            invite_btn = new JButton("ģ�� �ʴ�"); //ģ�� �ʴ� ��ư
            exit_btn = new JButton("������"); //�������ư
            
            
            sp_ta.setBounds(10,10,380,390); 
            la_msg.setBounds(10,410,60,30); 
            sendTF.setBounds(70,410,320,30); 
            sp_list.setBounds(400,10,120,350); 
            invite_btn.setBounds(400,370,120,30); 
            exit_btn.setBounds(400,410,120,30);
            JPanel p=new JPanel();
            p.setLayout(null);
            p.setBackground(Color.GRAY);
            p.add(sp_ta);
            p.add(la_msg);
            p.add(sendTF);
            p.add(sp_list);
            p.add(invite_btn);
            p.add(exit_btn);
            
            sendTF.addActionListener(new ActionListener() { //ä��â���� �ؽ�Ʈ�� �Է����� ��
              public void actionPerformed(ActionEvent e) {
                 out.println("chatting TEXT"+host+":"+curid+":"+sendTF.getText()); //�������ݷ� host�� ���� �α��� id �׸��� ���� �޽����� ������ �����ش�
                 sendTF.setText("");
              }
            });
            
            exit_btn.addActionListener(new ActionListener() { //ä�ù��� ���� ���
              public void actionPerformed(ActionEvent e) {
                 if (e.getSource() == exit_btn)
                    out.println("Exit"+host+":"+curid); //������ ä�ù��� host�� ������ id�� ������ �����ش�
                    room_frame.dispose();
              }
            });
            invite_btn.addActionListener(new ActionListener(){ //ģ�� �ʴ� ��ư
           public void actionPerformed(ActionEvent e)
           {
              if(e.getSource()==invite_btn)
              {
                 new Invite(curid); //�ʴ��Ϸ��� user�� �˻��ϴ� â�� �����ش�
              }
           }
            });
            
           room_frame.add(p);
            room_frame.setBounds(300,200,550,500);
            room_frame.setVisible(true);
            room_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            sendTF.requestFocus();    
          }
    }
    public class Withdrawal {
    	JFrame sub=new JFrame("Withdrawal");
    	JLabel idlbl, pwlbl;
    	JTextField idField;
    	JPasswordField passwd;
    	JPanel idPanel, paPanel;
    	JButton with_btn;
    	JTextArea content;
    	
    	public Withdrawal() {
    		sub.setLayout(new FlowLayout());
            EtchedBorder eborder =  new EtchedBorder();
            
            lbl = new JLabel( "Enter user information" );
            // ���̺� ���� �����
            lbl.setBorder(eborder);
            sub.add( lbl );
            
            idPanel = new JPanel();
            paPanel = new JPanel();
            
            idlbl = new JLabel("user Id        ");
            idField = new JTextField(10);
            idPanel.add(idlbl);
            idPanel.add(idField);
            idPanel.setBackground(Color.yellow);
            sub.add(idPanel);
            pwlbl = new JLabel("password  ");
            passwd = new JPasswordField(10);
            paPanel.add(pwlbl);
            paPanel.add(passwd);
            paPanel.setBackground(Color.yellow);
            sub.add(paPanel);
            
            with_btn = new JButton("Withdrawal");
            with_btn.setBackground(Color.yellow);
            sub.add(with_btn);
            
            with_btn.addActionListener(new ActionListener() 
            {
               public void actionPerformed(ActionEvent e) 
               {
                  String comment=e.getActionCommand();
                  if(comment.contentEquals("Withdrawal")) //register��ư �Է½�,
                  {
                     String id=idField.getText();
                     String pw=passwd.getText();
                     String encryptPW = encryptSHA256(pw);
                     out.println("Withdrawal"+id+" "+encryptPW); //�������� ����.
                  }
                  sub.dispose();
               }
               });
          sub.setSize( 250, 200);
          sub.getContentPane().setBackground(Color.white);
          sub.setVisible(true);
          sub.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    		
    	}
    }
    
    public class Resister { //ȸ�������� �� ���� â

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
                namePanel.setBackground(Color.yellow);
                sub.add(namePanel);
                
                birthlbl = new JLabel("birthday     ");
                birthField = new JTextField(10);
                birthPanel.add(birthlbl);
                birthPanel.add(birthField);
                birthPanel.setBackground(Color.yellow);
                sub.add(birthPanel);
                
                emaillbl = new JLabel("email          ");
                emailField = new JTextField(10);
                emailPanel.add(emaillbl);
                emailPanel.add(emailField);
                emailPanel.setBackground(Color.yellow);
                sub.add(emailPanel);
                idlbl = new JLabel("user Id        ");
                idField = new JTextField(10);
                idPanel.add(idlbl);
                idPanel.add(idField);
                idPanel.setBackground(Color.yellow);
                sub.add(idPanel);
                pwlbl = new JLabel("password  ");
                passwd = new JPasswordField(10);
                paPanel.add(pwlbl);
                paPanel.add(passwd);
                paPanel.setBackground(Color.yellow);
                sub.add(paPanel);
                nicklbl = new JLabel("nick name ");
                nickField = new JTextField(10);
                nickPanel.add(nicklbl);
                nickPanel.add(nickField);
                nickPanel.setBackground(Color.yellow);
                sub.add(nickPanel);
                

                resister_btn = new JButton("Resister");
                resister_btn.setBackground(Color.yellow);
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
                           String encryptPW = encryptSHA256(pw);
                           String nick = nickField.getText();
                           out.println("Resister"+name+" "+bday+" "+email+" "+id+" "+encryptPW + " " + nick); //�������� ����.
                        }
                        sub.dispose();
                     }
                     });
                sub.setSize( 250, 350 );
                sub.getContentPane().setBackground(Color.white);
                sub.setVisible(true);
                sub.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
     }
    public class Menu{ //�α��� ���� �Ǿ��� ��� ������ â
    public Menu(String name, String comment, String friend) {
    	  ImageIcon background = new ImageIcon("����޴�.png");
          JPanel contentPane = new JPanel() {
             public void paintComponent(Graphics g) {
                g.drawImage(background.getImage(), 0, 0, 400, 500, null);
                setOpaque(false);// ��� ����ֱ�s
                super.paintComponent(g);
             }
          };
         
        popupMenu = new JPopupMenu(); //ģ������ ǥ�����ִ� �˾� �޴�
        lookinfo = new JMenuItem("ģ����������"); //������ ��ư Ŭ���� ģ����������� ä�ý�û�ϱ� â
        sendMsg = new JMenuItem("ä�ý�û�ϱ�");
        popupMenu.add(lookinfo);
        popupMenu.add(sendMsg);
       menu_frame = new JFrame("Menu");
       user_name = new JLabel(name);
       JLabel userNamelbl = new JLabel("NAME: "); //�α��� �� ����� �̸��� ���¸޽����� ǥ�����ִ� label
       JLabel user_status_lbl = new JLabel("STATUS MESSAGE: ");
       user_state = new JLabel(comment);
       JLabel friends=new JLabel("Friends:"); //ģ������Ʈ�� ���� �г�
       add_friend=new JButton("ģ���˻�");
       friend_list = new JList(new DefaultListModel()); 
       friend_model = (DefaultListModel)friend_list.getModel(); //�ڽ��� ģ������Ʈ�� ���� �г�
       
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
                 friend_list = (JList)e.getSource(); //������ ��ư�� ������ �� ������ ���� ���ش�
             }
          }
       });
       
        lookinfo.addActionListener(new ActionListener() {  //ģ���������⿡ ���� ���
              @Override
              public void actionPerformed(ActionEvent e) 
              {
               String comm=e.getActionCommand();
               if(comm.contentEquals("ģ����������")) //��ư Ŭ�� ��,
                  {
                  String str = (String)friend_list.getSelectedValue();  
                  out.println("Search"+str); //�������� �������⸦ ���ϴ� ����ڿ� ���������� ������.
                  }
              }
               });
        
        sendMsg.addActionListener(new ActionListener() { //ģ������ ä�ÿ�û�� �ϴ� ���
              @Override
              public void actionPerformed(ActionEvent e) {
                 String start=e.getActionCommand();
                 if(start.contentEquals("ä�ý�û�ϱ�"))
                 {
                 new Chatting_Room(curid,curid,curid);   //�ش� ��û�� �� ����ڸ��� ä�ù� ����
                 String receiver=(String)friend_list.getSelectedValue(); //ä���� ���� ����� �����´�
              out.println("Start:"+receiver+":"+curid); //ä�ù޴� ����� ä�ÿ�û�ϴ� ����� ���������� ������ �����ش�
              host=curid; //ä�ÿ�û�� �ϴ� ����� host�� ������ش�
                 }
              }
               });
       friend_model.remove(0); // db�� ����� ģ���̸��� null�� �����ؼ� �տ� null ������
       change_info = new JButton("���� ����"); //�ڽ��� ������ �������ִ� ��ư
      
      
       contentPane.setLayout(null);
       contentPane.add(add_friend);
       contentPane.add(change_info);
       contentPane.add(userNamelbl);
       contentPane.add(user_name);
       contentPane.add(user_status_lbl);
       contentPane.add(user_state);
       contentPane.add(friends);
       contentPane.add(friend_list);
       add_friend.setBounds(10, 10, 120, 30);
       change_info.setBounds(150, 10, 120,30);
       userNamelbl.setBounds(10, 60, 150, 20);
       user_name.setBounds(50, 60, 150, 20);
       user_status_lbl.setBounds(10, 90, 350, 15);
       user_state.setBounds(120, 90, 350, 15);
       friends.setBounds(10,100,200,15);
       friend_list.setBounds(10, 130, 350, 200);
       
       //���׿��� ���������� ǥ�� (�޴�â����)
       socialDate s = new socialDate();
       //socialDate Ŭ�������� json parsing �� ������ ��°� ������ �����´�
      
       JLabel locationlbl = new JLabel("������ ������ ���ؽð� : 00��");
       locationlbl.setBounds(10,370,300,20);
       JLabel t1hlbl = new JLabel("���");
       JLabel t1hlbl2 = new JLabel(s.tem);
       JLabel rehlbl = new JLabel("����");
       JLabel rehlbl2 = new JLabel(s.hum);
       locationlbl.setForeground(Color.RED);
       t1hlbl.setBounds(10, 400, 150, 20);
       t1hlbl.setForeground(Color.RED);
       t1hlbl2.setBounds(60, 400, 150,20);
       t1hlbl2.setForeground(Color.RED);
       contentPane.add(t1hlbl);
       contentPane.add(t1hlbl2);
       rehlbl.setBounds(10, 430,150,20);
       rehlbl.setForeground(Color.RED);
       rehlbl2.setBounds(60, 430,150,20);
       rehlbl2.setForeground(Color.RED);
       contentPane.add(rehlbl);
       contentPane.add(rehlbl2);
       contentPane.add(locationlbl);
       contentPane.setSize(400,80);
       contentPane.setVisible(true);
       menu_frame.getContentPane().add(contentPane); 
     menu_frame.addWindowListener(new WindowListener() {
           @Override
           public void windowClosed(WindowEvent e) {
           }
           @Override
           public void windowOpened(WindowEvent e) {            
           }
           @Override
           public void windowClosing(WindowEvent e) {//�޴�â�� �ݾ��� ��, �α� �ƿ��ߴٰ� �������� �����ϰ� ������Ʈ ��. �ֱ� ���ӽð��� 
               //String date   
               Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmm");
                out.println("LOGOUT"+ sdf.format(date));
           }
           @Override
           public void windowIconified(WindowEvent e) {            
           }

           @Override
           public void windowDeiconified(WindowEvent e) {   
           }

           @Override
           public void windowActivated(WindowEvent e) {         
           }

           @Override
           public void windowDeactivated(WindowEvent e) {      
           }

            });
         add_friend.addActionListener(new ActionListener() { //ģ�� �˻� ��ư�� ������ ��
             @Override
             public void actionPerformed(ActionEvent e) 
             {
                      new Addfr();      //�˻�â�� ���´�
             }
              });
         change_info.addActionListener(new ActionListener() { // �޴����� �������� ��ư ������ 

           @Override
           public void actionPerformed(ActionEvent e) {
                 new ChangeInfo(); //��ȭ�����ִ� â�� ���´�
           }
         });
         
         menu_frame.setSize(400,500);
         menu_frame.setVisible(true);
         }

    }
          public class Invite //�α����ؼ�, user�� �˻��ϰ�, �ʴ븦 �� �� �ִ� â
          {
             public Invite(String id)
             {
                JFrame iadder=new JFrame();
                iadder.setTitle("�ʴ��Ͻ� id�� �Է����ּ���.");
               iadder.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                iadder.setLayout(new FlowLayout());
                JLabel inviid=new JLabel("���̵�");
                JTextField iid=new JTextField(20);
                iadder.add(inviid);
                iadder.add(iid);
                iadder.setSize(300,150);
                iadder.setVisible(true);
                JButton invitefr=new JButton("�ʴ�");
                JPanel pnr=new JPanel();
                pnr.add(invitefr);
                iadder.add(pnr,BorderLayout.SOUTH);
          invitefr.addActionListener(new ActionListener() //text�� ������ Ȯ���ϰ�, ģ���� �߰���.
                      {
                      public void actionPerformed(ActionEvent e)
                      {
                         String comment=e.getActionCommand();
                             if(comment.contentEquals("�ʴ�")) //�ʴ� ��ư�� ������ ��
                            {
                          String inviname=iid.getText(); //�ؽ�Ʈâ���� �ʴ��� ��� ���̵� �޾ƿ´�
                           out.println("Invite"+host+":"+inviname+":"+id); //�������� host�̸��� �ʴ��� ���, �ʴ��Ϸ��� ��û�ڸ� �����ش�
                           iadder.dispose(); //â�� �ݾ��ش�
                            }
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
                JPanel pnr=new JPanel();
                pnr.add(search);
                pnr.add(add);
                adder.add(pnr,BorderLayout.SOUTH);
                search.addActionListener(new ActionListener() //�˻��� userid�� �ۼ��ϰ�, �˻��� ��. 
                             {
                             public void actionPerformed(ActionEvent e)
                             {
                                String comment=e.getActionCommand();
                                    if(comment.contentEquals("�˻�"))  //�˻���ư�� ������,
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
                      out.println("ChangeInfo:"+ch_nick+":"+ch_comment);   // �ٲ� ���� ������ �˸�
                       user_state.setText(ch_comment);   // �ٲ� ������ �޴� ĭ�� �ݿ���
                      ch_frame.dispose();
                   }
                        
                     });
                     
                     
                     
                  
              }
              
           }
    //TODO:
 private void run() {
              try {
                 Socket socket = new Socket(serverAddress, portnum); //�Է¹��� ip�ּҿ� portnumber�� ���� ����.
                  in = new Scanner(socket.getInputStream()); //���Ͽ��� �о���� ����
                  out = new PrintWriter(socket.getOutputStream(), true); //���Ͽ� ���� ����
                  String[] userinfo = null; //����� ���������ϴ� ����
                  while(true)
                  {
                     String line=in.nextLine();
                     if(line.contains("access")) //�α����� ������ ������ �˻��ϰ�, ���ε̴ٴ� �޽����� ���� ���
                     {
                        userinfo = line.split(":");
                        
                         JOptionPane.showMessageDialog(null, "�α��� ����", "�α��� Ȯ��!", JOptionPane.DEFAULT_OPTION);//������ �α��� �����̶�� ���� ��, â ǥ��
                         //TODO:
                        this.new Menu(userinfo[1],userinfo[2],userinfo[4]); //�������� �������� �޴� â ����
                         curid=userinfo[3]; //������̵� ����
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
                     else if(line.contains("Welcome")) //ȸ�����Կ� �������� ��
                     {
                        JOptionPane.showMessageDialog(null, "ȸ�����Լ���", "ȯ���մϴ�!", JOptionPane.DEFAULT_OPTION); //ȸ������ ���� ��, âǥ��
                        return;
                     }
                     else if(line.contains("Searching")) //�˻��� ������ �޾��� ��,
                     {
                        String printinfo=line.substring(9); //���� �̾Ƴ�
                        String [] showbox=printinfo.split(" "); 
                        String wholeinfo="";
                        if(showbox[4].equals("1") == true) //�������� �α����̸� 1 �α׾ƿ��̸� 0���� �ٲ��ش�
                           showbox[4] = "on";
                        else
                           showbox[4] = "off";
                       
                        wholeinfo="name"+":"+showbox[0]+"\n"+"birthday"+":"+showbox[1]+"\n"+"email"+":"+showbox[2]+
                                "\n"+"comment"+":"+showbox[3]+"\n"+"status"+":"+showbox[4]+"\n"+"Off time"+":"+showbox[5]+" "+showbox[6];
                        JOptionPane.showMessageDialog(null, wholeinfo, "�˻��Ͻ� ������ ���� �����ϴ�.", JOptionPane.DEFAULT_OPTION); 
                        //���� ������ ���.
                   
                     }
                     else if(line.contains("ADD")) //ģ�� �߰��Ϸ��
                     {
                        JOptionPane.showMessageDialog(null, "ģ���߰��Ϸ�", "�����մϴ�", JOptionPane.DEFAULT_OPTION); //ģ���߰��� ���, âǥ��
                        friend_model.addElement(relaid);
                     }
                     else if(line.contains("DUPLICATION")) //�̹� �߰��Ǿ� �ִ� ģ���� ���, �޽�������
                     {
                        JOptionPane.showMessageDialog(null, "�̹� �߰��� ģ���Դϴ�.", "ģ�� �߰� ����",JOptionPane.DEFAULT_OPTION);// ģ���� �ߺ��� ���
                     }
                     else if(line.contains("No user")) //server����, �ش� ����ڰ� ���ٰ� ���� ��� ���� ���.
                     {
                        JOptionPane.showMessageDialog(null, "�������� �ʴ� ���̵��Դϴ�.", "error   ", JOptionPane.DEFAULT_OPTION); //ģ������� �ӽ÷� âǥ��
                     }
                     else if(line.contains("Message")) //��ҿ� ä�ù濡�� �޽����� �ְ� ���� ��
                     {
                        String msg = line.substring(7);
                        String [] comparator=msg.split(":");// host�� ������ ��� �޽����� �߶��ش�
                        String hostcomp=comparator[0];//host
                        String chattalk=comparator[1]+":"+comparator[2];//ä�ù濡 ������ �޽���
                        if(hostcomp.equalsIgnoreCase(host)) //Ŭ���̾�Ʈ�� ȣ��Ʈ�� �� �޽����� ȣ��Ʈ�� ��ġ�Ҷ��� �޽��� ���
                        {
                        sendTF.setEditable(true); //ä��â Ȱ��ȭ
                        ta.append(chattalk + "\n"); //�޽��� ǥ�������
                        }
                     }
                     else if(line.contains("Entrance")) //ä�ÿ�û�� �¶��ؼ� �����ϴ� ���
                     {
                        String etr=line.substring(8);
                        String []etra=etr.split(":"); //host�̸��� ����޽��� �и�
                        String hostreal=etra[0]; //host
                        if(hostreal.equalsIgnoreCase(host)) //�ش� host�� ä�ù����� �˻�
                        {
                           if(line.contains("has joined")&&curid!=etra[1].split(" ")[0]) { 
                                 user_model.addElement(etra[1].split(" ")[0]);
                              } //������ ���� �޽����� ǥ�� �׸��� ä�� ����Ʈ�� �����ش�
                        sendTF.setEditable(true); //�����ߴٴ� ��ȣ�� �˷��� ä�ù� Ȱ��ȭ
                         ta.append(etra[1] + "\n");  //ä�ø޽��� ǥ��
                        }
                     }
                    else if(line.contains("Request")) // ä�ÿ�û�� ���� ��� Ŭ���̾�Ʈ����
                    {
                       String ifo=line.substring(8);
                       String [] temp=ifo.split(":");
                            String rcv=temp[0]; //�����ڿ� �۽��� �и�
                            String snd=temp[1]; 
                       if(rcv.equalsIgnoreCase(curid)==true) //�� ��û�� ���� �ش� Ŭ���̾�Ʈ���� �˻�
                       {
                        int result=JOptionPane.showConfirmDialog(null, //ä�� ���� ���� �޽��� ǥ�� 
                                "�����Ͻðڽ��ϱ�?", snd+"���κ��� ä�ÿ�û�� �Խ��ϴ�", 
                                JOptionPane.YES_NO_OPTION);
                            if(result == JOptionPane.NO_OPTION)
                            {
                               out.println("Nop"+temp[1]+":"+snd+" "+"reject the chatting");//ä�ÿ�û�� �ź����� ��, �źθ޽��� ����
                               sendTF.setEditable(false);
                            }
                          else if(result == JOptionPane.YES_OPTION)
                          {
                             this.new Chatting_Room(rcv,snd,temp[1]); //ä�ù��� �������� ���, �������� ä�ù� ����
                             host=temp[1]; //host�� �۽��ڸ� �־��ش�
                             out.println("Join"+host+":"+rcv); //�շ��ߴٴ� ����� �������� ����
                          }
                        }
                    }
                   else if(line.startsWith("Invite")) //�ʴ��Ѵٴ� �޽����� �������
                   {
                      String ite=line.substring(6);
                      String [] tmp=ite.split(":");
                      if(curid.equalsIgnoreCase(tmp[1])==true) //���� Ŭ���̾�Ʈ�� �ʴ��Ϸ��� ����� ��ġ�ϴ� ���
                      {
                       int result2=JOptionPane.showConfirmDialog(null,  //ä�� ��û �޽��� â ǥ��
                               "�����Ͻðڽ��ϱ�?",  tmp[2]+"���κ��� ä���ʴ밡 �Խ��ϴ�", 
                               JOptionPane.YES_NO_OPTION);
                           if(result2 == JOptionPane.NO_OPTION)
                           {
                              out.println("Nop"+tmp[0]+":"+tmp[1]+" "+"reject the chatting");//ä�� �źο� ���� �޽��� ����
                              host=null; //host�� �����Ƿ� null�� �ٲ��ش�
                              sendTF.setEditable(false);
                           }
                         else if(result2 == JOptionPane.YES_OPTION)
                         {
                            //String instanceList = tmp[3].substring(1);
                          // for(string a : tmp[3]) 
                             //.out.println("tmp:" + tmp[3].split(",")[0]);
                            host=tmp[0];  //ä��â���� host����
                            out.println("Join"+host+":"+tmp[1]); //������ �շ��ߴٴ� ����� ����
                            this.new Chatting_Room(tmp[1],tmp[2],tmp[3]); //ä�ù� ����
                         }
                       }
                   }
                       else if(line.contains("Denied")) //�ʴ븦 �������� �ʾ��� ��
                     {
                          String denmsg=line.substring(6);
                          String [] hcm=denmsg.split(":");
                          String hcomp=hcm[0]; //host ���ϴ� ����
                          String text=hcm[1]; //�޽���
                          if(hcomp.equalsIgnoreCase(host)) //�����ϴ� ������ host�� ��ġ�ϴ� ä�ù����� Ȯ��
                          {
                          ta.append(text+"\n"); //ȣ��Ʈ�� ��ġ�ϴ� ä�ù濡�� �ź� �޽����� ǥ��
                          }
                     }
                     else if(line.contains("Exit")) //ä�ù��� ���� ���
                    {
                       String exitinfo=line.substring(4);
                       String [] extract=exitinfo.split(":");
                       if(extract[0].equalsIgnoreCase(host)) //�ش� ä�ù��� host�� ��ġ�ϴ� �� Ȯ��
                       {
                    	   user_model.removeElement(extract[1].split(" ")[0]); //���� ��� ����
                          ta.append(extract[1]+extract[2]+"\n"); //ȣ��Ʈ�� ��ġ�ϴ� ��쿡, �����ٴ� �޽��� ǥ��
                       }
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
           client.frame.setVisible(true);
           client.run(); 
    }
}
package myPackage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.net.*;
public class Login
{
	static String ip=null; //txt���Ͽ���, ip�о��.
	static int portnum=0; //port number
    String serverAddress; //���� �ּ�
    Scanner in;
    PrintWriter out;
    JFrame frame=new JFrame("login form");
    JLabel lbl,la1,la2,la3,emp;
    JTextField id;
    JPasswordField passwd;
    JPanel emptyPanel,idPanel,paPanel,loginPanel;
    JButton b1,b2;
    JTextArea content;
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
    	        			  out.println("Resister"+name+" "+bday+" "+email+" "+id+" "+pw); //�������� ����.
    	        		  }
    	        		  sub.dispose();
    	        	  }
    	        	  });
    	        sub.setSize( 250, 350 );
    	        sub.setVisible(true);
    	        sub.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	}
    }
    private void run() {
              try {
            	  Socket socket = new Socket(serverAddress, portnum); //�Է¹��� ip�ּҿ� portnumber�� ���� ����.
                  in = new Scanner(socket.getInputStream());
                  out = new PrintWriter(socket.getOutputStream(), true);
                  while(in.hasNextLine())
                  {
                	  String line=in.nextLine();
                	  System.out.println(line);
                	  if(line.contains("access"))
                	  {
                    	  JOptionPane.showMessageDialog(null, "�α��� ����", "�α��� Ȯ��!", JOptionPane.DEFAULT_OPTION);//������ �α��� �����̶�� ���� ��, â ǥ��
                    	  return;  
                	  }
                	  else if(line.contains("invalid"))
                	  {
                		  JOptionPane.showMessageDialog(null, "�α��� ����", "�α��� Ȯ��!", JOptionPane.DEFAULT_OPTION); //�α��� ���� ��, âǥ��
                		  return;
                	  }
                	  else if(line.contains("Welcome"))
                	  {
                		  JOptionPane.showMessageDialog(null, "ȸ�����Լ���", "ȯ���մϴ�!", JOptionPane.DEFAULT_OPTION); //ȸ������ ���� ��, âǥ��
                		  return;
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

package myPackage;
import myPackage.Resister;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.*;

public class Login extends JFrame
{
    JLabel lbl,la1,la2,la3,emp;
    JTextField id;
    JPasswordField passwd;
    JPanel emptyPanel,idPanel,paPanel,loginPanel;
    JButton b1,b2;
    JTextArea content;
 
    public Login()
    {
          super( "Login Form" );
          
          // FlowLayout���
          setLayout( new FlowLayout() );
          
          // Border�� ���� ����
          EtchedBorder eborder =  new EtchedBorder();
          // ���̺� ����     
          lbl = new JLabel( "Enter Id and Password" );
          // ���̺� ���� �����
          lbl.setBorder(eborder);
          // ���̺� �߰�
          add(lbl);

          emptyPanel = new JPanel();
          emp = new JLabel("\n");
          emptyPanel.add(emp);
          add(emp);
          
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
          b1 = new JButton("Log in");
          b2 = new JButton("Resister");
          loginPanel.add(emp);
          loginPanel.add(b1);
          loginPanel.add(b2);
          add(emptyPanel);
          add(emptyPanel);
          add(idPanel);
          add(paPanel);
          add(emptyPanel);
          add(loginPanel);
          b1.addActionListener(new ActionListener() 
          {
        	  public void actionPerformed(ActionEvent e)
        	  {
        	  String logid = id.getText().trim();
        	  String logpw = passwd.getText().trim();
        	  if(logid.length()==0 || logpw.length()==0) 
        	  {
        	  JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� �Է� �ϼž� �˴ϴ�.", "���̵� ����� �Է�!", JOptionPane.DEFAULT_OPTION);
        	  return;
        	  }
        	  else if(logid.equals("test") && logpw.equals("test1")) 
        	  {
        	  JOptionPane.showMessageDialog(null, "�α��� ����", "�α��� Ȯ��!", JOptionPane.DEFAULT_OPTION);
        	  return;
        	  }
        	  JOptionPane.showMessageDialog(null, "�α��� ����", "�α��� Ȯ��!", JOptionPane.DEFAULT_OPTION);
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
          setSize( 400, 200 );
          setVisible(true);
          setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    

	public static void main(String args[]) { 
        new Login();
    }   
}
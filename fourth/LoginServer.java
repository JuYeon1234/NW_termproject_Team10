package myPackage2;

import java.io.IOException;
import java.util.Iterator;
import java.sql.*;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginServer 
{
	private static Set<String> userlist = new HashSet<>(); //�����ڵ��� ���� ����.
	public static void main(String[] args) throws Exception
	{
		
		System.out.println("The chat server is running...");
		ExecutorService pool = Executors.newFixedThreadPool(500);
		try (ServerSocket listener = new ServerSocket(59001)) 
		{
			while (true)
			{
				pool.execute(new Handler(listener.accept()));
			}
		}
	}
	private static class Handler implements Runnable 
	{

		private String id;
		private Socket socket;
		private Scanner in;
		private PrintWriter out;
		private String pw;
		private String logid="";
		 String DB_URL = "jdbc:mysql://localhost/userlist?serverTimezone=UTC"; //������ DB ����
			Connection conn=null;
			Statement state=null;
		public Handler(Socket socket)
		{
			this.socket = socket;
		}
		public void run() 
		{

			try {
				in = new Scanner(socket.getInputStream());
				out = new PrintWriter(socket.getOutputStream(), true);
			Class.forName("com.mysql.cj.jdbc.Driver"); //mysql driver
			conn=DriverManager.getConnection(DB_URL,"root","12345"); //db�ּҿ�, �����, ��й�ȣ�� ���ؼ� ����.
			state=conn.createStatement(); //mysql ����
			boolean realuser=false;
			Statement state9=conn.createStatement();
			String list="select userid from info";
			ResultSet ulist=state9.executeQuery(list);//�����ͺ��̽��� userid�� �� ������.
			while(ulist.next())
			{
				userlist.add(ulist.getString("userid"));//�����ͺ��̽����� ������ userid��  server�� hashlist������.
			}
			while(true)
			{
			String input=in.nextLine();
			System.out.println(input);
				if(input.startsWith("logid")) //client���� logid�� ���� ��, �α���
				{
					String[]info=input.split(" "); //id�� password�� ���̿ͼ� ������.
					id=info[0].substring(5); //id
					pw=info[1]; //password
					
					String sqlid="select userid from info where userid like"+"'"+id+"'";
					ResultSet rsi=state.executeQuery(sqlid);
					if ((rsi.next() == false || (id.isEmpty()) == true))
					{
						out.println("id invalid"); //�α��� ����
					}
					rsi.close();
					Statement state2=conn.createStatement();
					String sql="select * from info where userid like"+"'"+id+"'"; //id�� �������� ��й�ȣ�� ã��
					ResultSet rs=state2.executeQuery(sql); //��� �� ����.
					Statement state3=conn.createStatement();
					while(rs.next()) //�����ͺ��̽� ��ĵ
					{
						String dbpw=rs.getString("password"); //���ǹ��� �´� �н����� ����\
						if(pw.equalsIgnoreCase(dbpw)) //�Է��� ��й�ȣ�� ���̵� ���� ��, �α��μ���
						{
							
							
							out.println("access:"+rs.getString("name")+":"+rs.getString("comment")+":"+id+":"+rs.getString("friend"));//Ŭ���̾�Ʈ���� �����ߴٰ� ������.
							String sql_update = "update info set login_count = login_count+1 where userid = "+ "'"+id+"'";
							state3.executeUpdate(sql_update);
							logid=id; //�α��� �Ǿ��ִ� ���̵� ����.
						}
						else
						{
						out.println("pw invalid"); //�α��� ����	
						}	
						}
					rs.close();
					state3.close();
					state2.close();
					
					}
				else if(input.startsWith("Resister")) //ȸ������
				{
					String [] nw=input.split(" "); //ȸ�����ԵǼ� �� ������ ������.
					String addname=nw[0].substring(8);
					int addbday=Integer.parseInt(nw[1]);
					String addemail=nw[2];
					String addid=nw[3];
					String addpw=nw[4];
					String addnick = nw[5];
					Statement state4=conn.createStatement();
					String mysql="insert into info values("+"'"+addname+"'"+","+addbday+","+"'"+addemail+"'"+","+"'"+addid+"'"+","+"'"+addpw+"'"+","+0+","+"'"+"hello world"+"'"+","+"'"+null+"'"+"," + "'"+addnick+"'"+")";
					//String mysql="insert into info values("+"'"+addname+"'"+","+addbday+","+"'"+addemail+"'"+","+"'"+addid+"'"+","+"'"+addpw+"'"+","+0+","+"'"+""+"'"+","+"'"+"hello world"+"'"+")";
						
					//�����ͺ��̽��� ��������ִ� ����
					state4.executeUpdate(mysql); //�����ͺ��̽� ������Ʈ
					userlist.add(nw[3]); //�����ϸ�, hashset�� userid �߰�
					out.println("Welcome"); //ȸ������ �Ǿ��ٴ� ���� ������.
					state4.close();
					
				}
				else if(input.startsWith("Search"))//client���� �˻��϶�� ����� �������
				{
					
					String sid=input.substring(6);
					Iterator<String>finduser=userlist.iterator();
					while(finduser.hasNext())
					{
						String comp=finduser.next();
						if(sid.contains(comp))
						{
							realuser=true; //hashset���� �ش� �����ڰ� �ִ��� ��ĵ
						}
					}
						if(realuser==true) //�����ڰ� ���� ��� , �ش簡������ ���� ����ؼ� client���� ������.
						{
							String searchinfo="select * from info where userid like '"+sid+"'";//�ش���̵��� ������ ã��
							Statement st2=null;
							st2=conn.createStatement();
							ResultSet sch=st2.executeQuery(searchinfo); //ã�� ������ ����.
							String fetch="";
							while(sch.next())
							{
								 fetch="Searching"+sch.getString("name")+" "+sch.getString("birthday")+" "+sch.getString("email")+" "+sch.getString("comment");
							//ã�� ������ ������.
							}
							System.out.println(fetch);
							out.println(fetch);
							st2.close();
							realuser=false;
						}
						else if(realuser==false) //�ش簡���ڰ� ���� ��� client���� ������.
						{
						out.println("No user");	
						}
					}
	
				else if(input.startsWith("ADDF")) //ģ���� �߰��Ҷ�,
				{
					boolean check=false;
					String take="select friend from info where userid like"+"'"+logid+"'"; //���� �α��εǾ� �ִ� ������� ģ������� �о��.
					Statement state6=conn.createStatement();
					ResultSet fri=state6.executeQuery(take);
					String prev="";
					while(fri.next())
					{
						prev=fri.getString("friend"); //�� ���� �ִ� ģ������� ������.

					}
					String ptocol=input.substring(4); //�߰��� ģ��id�� ������
					Iterator <String> afinduser=userlist.iterator(); //ģ���� �߰��� id�� ������ ���������� ��ĵ
					while(afinduser.hasNext())
					{
						String comp2=afinduser.next();
						if(ptocol.contains(comp2))
						{
							check=true; //������ true�� �ٲ���
						}
					}

					if(check==true) //�����ڰ� ���� ���, ģ�� �߰� ����
					{
						
						if(prev!=null&&prev.contains(ptocol)) { //ģ������Ʈ�� ģ���� ��ġ�� ��� 
							out.println("DUPLICATION"); 
						}
						else {

							String flist=prev+" "+ptocol; //���� ģ������Ʈ�� �������
							state6.close();
							String fradd="update info set friend="+"'"+flist+"'"+"where userid like"+"'"+logid+"'"; //�����ͺ��̽��� �ݿ�������
							Statement state7=conn.createStatement();
							state7.executeUpdate(fradd);
							state7.close();
							out.println("ADD"); //client���� �߰��ߴٰ� ������.
							check=false;
						}
						
					}
					else if(check==false) //�����ڰ� ���� ���, ����ڰ� ���ٰ� client�� ������.
					{
						out.println("No user");
					}
				}
				else if(input.startsWith("List")) //client���� ģ�� ����Ʈ�� �ʿ��ϴٰ� ������
				{
					String obj=input.substring(4); //����Ʈ�� �ʿ��� �α��� ����� id�� �ҷ��´�.
					String comma="select friend from info where userid like"+"'"+obj+"'"; //�ش� id�� ģ�� ����Ʈ ����
					Statement state8=conn.createStatement();
					ResultSet frlist=state8.executeQuery(comma); //ģ�� ����Ʈ ��ȸ
					String temp="";
					while(frlist.next())
					{
						temp=temp+frlist.getString("friend"); //temp�� ģ������Ʈ ����
					}
					out.println("Frlist"+temp); //client�� ģ������Ʈ�� ������.
				}
				else if(input.startsWith("ChangeInfo")) {  // ������ ���� �� �� 
					String[] ch_info = input.split(":");
					String ch_sql = "update info set nickname = "+"'"+ch_info[1]+"'," + " comment = " + "'"+ch_info[2]+"'"+" where userid = " +"'"+logid+"'";// �г����̶� ���¸޽��� ������
					Statement state10 = conn.createStatement();
					state10.executeUpdate(ch_sql);

					state10.close();
					
				}
				
			}
			}	
			catch (Exception e) 
			{
				System.out.println(e);
			} 
			finally {
				if (out != null) {
					
				}
				}
				try { socket.close(); }
				catch (IOException e){}
				}
			}}
	
	

	
	




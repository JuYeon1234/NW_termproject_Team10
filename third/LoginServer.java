package myPackage;
import java.io.IOException;

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
			String input=in.nextLine();
			Class.forName("com.mysql.cj.jdbc.Driver"); //mysql driver
			conn=DriverManager.getConnection(DB_URL,"root","574128"); //db�ּҿ�, �����, ��й�ȣ�� ���ؼ� ����.
			state=conn.createStatement(); //mysql ����
			while(true)
			{
				if(input.startsWith("logid")) //client���� logid�� ���� ��, �α���
				{
					String[]info=input.split(" "); //id�� password�� ���̿ͼ� ������.
					id=info[0].substring(5); //id
					pw=info[1]; //password
					String sql="select password from info where userid like"+"'"+id+"'"; //id�� �������� ��й�ȣ�� ã��
					ResultSet rs=state.executeQuery(sql); //��� �� ����.
					System.out.println("rs: "+ rs);
					while(rs.next()) //�����ͺ��̽� ��ĵ
					{
						String dbpw=rs.getString("password"); //���ǹ��� �´� �н����� ����\
						System.out.println("dbpw");
						if(pw.equalsIgnoreCase(dbpw)) //�Է��� ��й�ȣ�� ���̵� ���� ��, �α��μ���
						{
							out.println("access");//Ŭ���̾�Ʈ���� �����ߴٰ� ������.
							String sql_update = "update info set login_count = login_count+1 where userid = "+ "'"+id+"'";
							state.executeUpdate(sql_update);
							
						}
						else
						{
						out.println("invalid"); //�α��� ����	
						}	
						}
					rs.close();
					conn.close();
					}
				else if(input.startsWith("Resister")) //ȸ������
				{
					String [] nw=input.split(" "); //ȸ�����ԵǼ� �� ������ ������.
					String addname=nw[0].substring(8);
					int addbday=Integer.parseInt(nw[1]);
					String addemail=nw[2];
					String addid=nw[3];
					String addpw=nw[4];
					String mysql="insert into info values("+"'"+addname+"'"+","+addbday+","+"'"+addemail+"'"+","+"'"+addid+"'"+","+"'"+addpw+"'"+","+0+")";
					//�����ͺ��̽��� ��������ִ� ����
					state.executeUpdate(mysql); //�����ͺ��̽� ������Ʈ
					out.println("Welcome"); //ȸ������ �Ǿ��ٴ� ���� ������.
				}
			state.close();
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
	
	

	
	



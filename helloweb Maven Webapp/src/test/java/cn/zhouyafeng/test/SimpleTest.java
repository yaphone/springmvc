package cn.zhouyafeng.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleTest {

	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/blog?characterEncoding=utf8&useSSL=true";
		String user = "root";
		String password = "19891226";
		try {
			// ������������
			Class.forName(driver);

			// �������ݿ�
			Connection conn = DriverManager.getConnection(url, user, password);

			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");

			// statement����ִ��SQL���
			Statement statement = conn.createStatement();

			// Ҫִ�е�SQL���
			String sql = "select * from blog_blog";

			// �����
			ResultSet rs = statement.executeQuery(sql);

			System.out.println("-----------------");
			System.out.println("ִ�н��������ʾ:");
			System.out.println("-----------------");
			System.out.println(" ѧ��" + "\t" + " ����");
			System.out.println("-----------------");

			// String name = null;

			while (rs.next()) {

				// ѡ��sname��������
				// name = rs.getString("blog_title");

				// ����ʹ��ISO-8859-1�ַ�����name����Ϊ�ֽ����в�������洢�µ��ֽ������С�
				// Ȼ��ʹ��GB2312�ַ�������ָ�����ֽ�����
				// name = new String(name.getBytes("ISO-8859-1"), "GB2312");

				// ������
				System.out.println(rs.getString("id") + "\t" + rs.getString("blog_title"));
			}

			rs.close();
			conn.close();

		} catch (ClassNotFoundException e) {

			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}

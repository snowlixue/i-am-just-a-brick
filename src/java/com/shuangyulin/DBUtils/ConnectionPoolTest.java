package com.shuangyulin.DBUtils;

////////////////////////////////////////////////////////////////////���Գ���
//ConnectionPoolTest.java /////////////////////////////////

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionPoolTest {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try {
			// �������ݿ����ӿ����
			ConnectionPool connPool =new ConnectionPool(
					"com.microsoft.sqlserver.jdbc.SQLServerDriver",
					"jdbc:sqlserver://127.0.0.1;databaseName=SuperMarketInfo",
					"sa", "123");
			// �½����ݿ����ӿ�
			connPool.createPool();
			// SQL�������
			String sql = "select * from admin";
			// �趨����������ʼʱ��
			long start = System.currentTimeMillis();
			// ѭ������100�����ݿ�����
			for (int i = 0; i < 100; i++) {
				Connection conn = connPool.getConnection(); // �����ӿ��л�ȡһ�����õ�����
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
				}
				rs.close();
				stmt.close();
				connPool.returnConnection(conn); // ����ʹ������ͷ����ӵ����ӳ�
			}
			System.out.println("����100�ε�ѭ�����ã�ʹ�����ӳػ��ѵ�ʱ��:"
					+ (System.currentTimeMillis() - start) + "ms");
			// connPool.refreshConnections();//ˢ�����ݿ����ӳ����������ӣ������������Ƿ��������У������������Ӷ��ͷŲ��Żص����ӳء�ע�⣺�����ʱ�Ƚϴ�
			connPool.closeConnectionPool();// �ر����ݿ����ӳء�ע�⣺�����ʱ�Ƚϴ�
			// �趨����������ʼʱ��
			start = System.currentTimeMillis();
			// ��������
			//Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			for (int i = 0; i < 100; i++) {
				// ��������
				Connection conn = DriverManager
						.getConnection(
								//"jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=MyDataForTest",
								"jdbc:odbc:SuperMarket",
								"sa", "yixinhuang");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
				}
				rs.close();
				stmt.close();
				conn.close();// �ر�����
			}
			System.out.println("����100�ε�ѭ�����ã���ʹ�����ӳػ��ѵ�ʱ��:"
					+ (System.currentTimeMillis() - start) + "ms");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

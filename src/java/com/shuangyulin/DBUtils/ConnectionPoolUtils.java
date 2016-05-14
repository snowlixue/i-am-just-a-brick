package com.shuangyulin.DBUtils;

/*���ӳع����࣬����Ψһ��һ�����ݿ����ӳض���*/
public class ConnectionPoolUtils {
	private static ConnectionPool poolInstance = null;
	public static ConnectionPool GetPoolInstance(){
		if(poolInstance == null) {
			poolInstance = new ConnectionPool(
					"com.microsoft.sqlserver.jdbc.SQLServerDriver",
					"jdbc:sqlserver://127.0.0.1;databaseName=SuperMarketInfo",
					"sa", "123");
			try {
				poolInstance.createPool();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return poolInstance;
	}
}

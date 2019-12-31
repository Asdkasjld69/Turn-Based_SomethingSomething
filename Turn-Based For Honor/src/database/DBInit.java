package database;

import java.io.InputStream;

import database.DataSourceUtils;

public class DBInit {
	

	public static void main(String[] args)
	{
		new DBInit().initDatabase();
	}
	
	public InputStream getDBSQLfileStream()
	{
		//new java.io.FileInputStream("db-forhsqldb.sql");
		return this.getClass().getResourceAsStream("/for_mysql.sql");
	}
	public boolean initDatabase() {
		try {
			javax.sql.DataSource ds=DataSourceUtils.getDataSource();
			java.sql.Connection conn=ds.getConnection();

	        System.out.println("connection autocommited="+conn.getAutoCommit());
	        
	        InputStream fileIs=this.getDBSQLfileStream();
	        
	        java.io.BufferedReader reader=new java.io.BufferedReader(new java.io.InputStreamReader(
	        		fileIs
	        		,"UTF-8"));			
			
			java.sql.Statement stment=conn.createStatement();
			StringBuffer[] sbs= {new StringBuffer()};
			
			
			reader.lines().forEach(line->{
				int rowcount=0;
				try {
					if(line.endsWith(";"))
					{
						String sql=sbs[0].toString()+line;
						sbs[0]=new StringBuffer();
						System.out.println(sql);
					    
						rowcount = stment.executeUpdate(sql);
					  
					    System.out.println("result="+rowcount);
					}else
						sbs[0].append(line+"\n");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}			
			    
				
			});
			stment.close();
			
			//conn.commit();
			conn.close();
			synchronized(this)
			{
			   this.wait(1000);
			}
			System.out.println("数据库初始化 finished....");
		}catch(Throwable t)
		{
			t.printStackTrace();
			return false;
		}
		
		return true;
		
	}

}


package com.hlpp.clientcontact.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

public class JDBCUtil
{
    public static Connection getConnection(String dataSourceName) throws SQLException
    {
        DataSource ds=null;
        try
        {
            ds = (DataSource) JNDIUtil.lookup(dataSourceName);
        }
        catch (NamingException nex)
        {
			throw new SQLException(nex.getMessage());
        }
       return ds.getConnection();
    }

    public static Connection getConnection(String driverName, String url, String userName, String pass) throws SQLException
    {
        Driver driver = null;
        Properties props = new Properties();

        try
        {
            props.put("user", userName);
            props.put("password", pass);
            driver = (Driver) Class.forName(driverName).newInstance();
        }
        catch (Exception ex)
        {
        }

        return driver.connect(url, props);

    }

    public static void close(Connection conn, Statement stmt, ResultSet rs)
    {
        close(rs);
        close(stmt);
        close(conn);
    }

    public static void close(Connection conn, Statement stmt)
    {
        close(stmt);
        close(conn);
    }

    public static void close(Connection conn)
    {
        if (conn != null)
        {
            try
            {
                conn.close();
            }
            catch (Exception ex)
            {
            }
        }
    }

    public static void close(Statement stmt)
    {
        if (stmt != null)
        {
            try
            {
                stmt.close();
            }
            catch (Exception ex)
            {

            }
        }
    }

    public static void close(ResultSet rs)
    {
        if (rs != null)
        {
            try
            {
                rs.close();
            }
            catch (Exception ex)
            {

            }
        }
    }

}

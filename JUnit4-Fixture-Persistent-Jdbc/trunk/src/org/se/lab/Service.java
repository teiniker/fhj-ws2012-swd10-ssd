package org.se.lab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Service
{
    public boolean login(Connection c, String username, String password)
            throws SQLException
    {
        final String SQL = "SELECT id FROM User WHERE username ='" + username
                + "' AND password = '" + password + "'";
        System.out.println("SQL> " + SQL);

        Statement stmt = null;
        ResultSet rs = null;
        boolean result = false;
        try
        {
            stmt = c.createStatement();
            rs = stmt.executeQuery(SQL);
            result = rs.next();
        } 
        catch (SQLException e)
        {
            return false;
        } 
        finally
        {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return result;
    }

    
    public boolean secureLogin(Connection c, String username, String password) 
        throws SQLException
    {
        final String SQL = "SELECT id FROM User WHERE username=? AND password=?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean result;
        try
        {
            pstmt = c.prepareStatement(SQL);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            result = rs.next();
        } 
        finally
        {
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
        }
        return result;
    }
}

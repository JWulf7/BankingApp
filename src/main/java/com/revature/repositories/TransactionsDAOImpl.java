package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

import com.revature.util.ConnectionUtil;

public class TransactionsDAOImpl implements TransactionsDAO{

	private static Logger logger = Logger.getLogger(TransactionsDAOImpl.class);

	@Override
	public boolean addTransaction(String time, int actNo, String descrip) {

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql ="INSERT into project0.transactions (timeoccur, accountnum, descript) " +
						"VALUES (?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, time);
			stmt.setInt(2, actNo);
			stmt.setString(3, descrip);
			return stmt.execute();
			
			
		} catch (SQLException e) {
			logger.warn("Unable to log transaction", e);
			e.printStackTrace();
		}
		return false;
	
	}

	@Override
	public ArrayBlockingQueue<String> getTransactions(int actno) {

		ArrayBlockingQueue<String> recentTrans = new ArrayBlockingQueue<>(3);

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM project0.recentTransactions(?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actno);
			

			ResultSet rs = stmt.executeQuery();

			
			while (rs.next()) {
				int transID = rs.getInt("transactionid");
				String timeAt = rs.getString("timeoccur");
				int actNum = rs.getInt("accountnum");
				String desc = rs.getString("descript");

				String trans = timeAt + "Account: " + actNum + "   " + desc + ".  TransactionID: " + transID;

				recentTrans.add(trans);

				
			}

			rs.close();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve recent transactions", e);
		}

		

		return recentTrans;

		
	}
}

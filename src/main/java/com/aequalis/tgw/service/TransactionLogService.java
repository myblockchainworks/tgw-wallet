/**
 * 
 */
package com.aequalis.tgw.service;

import java.util.List;

import com.aequalis.tgw.model.TransactionLog;


/**
 * @author anand
 *
 */
public interface TransactionLogService {
	
	public void addTransactionLog(TransactionLog transactionLog);
	
	public List<TransactionLog> findMyTransactions(String useraddress);
}

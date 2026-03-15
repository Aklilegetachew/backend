
package com.safaricom.tibcoLogger.service;

import java.util.List;
import java.util.Map;

public interface QueryService {
    List<Map<String, Object>> getPaymentTransactions();
    List<Map<String, Object>> getPaymentTransactions(int page, int limit);
    long countPaymentTransactions();
}
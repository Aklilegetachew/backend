package com.safaricom.tibcoLogger.service.impl;

import com.safaricom.tibcoLogger.exception.DatabaseQueryException;
import com.safaricom.tibcoLogger.service.QueryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class QueryServiceImpl implements QueryService {

    private static final Logger log = Logger.getLogger(QueryServiceImpl.class.getName());

    private final JdbcTemplate jdbcTemplate;
    private final String query;

    public QueryServiceImpl(
            JdbcTemplate jdbcTemplate,
            @Value("${app.query.fixed}") String query
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.query = query;
    }

    @Override
    public List<Map<String, Object>> getPaymentTransactions() {

        try {

            log.info("Executing payment transactions query");

            List<Map<String, Object>> results = jdbcTemplate.queryForList(query);

            log.info("Query executed successfully. Records fetched: " + results.size());

            return results;

        } catch (DataAccessException ex) {

            log.severe("Database error occurred while fetching transactions");

            throw new DatabaseQueryException(
                    "Database error occurred while fetching transactions",
                    ex
            );

        } catch (Exception ex) {

            log.severe("Unexpected error occurred");

            throw new DatabaseQueryException(
                    "Unexpected error occurred while processing request",
                    ex
            );
        }
    }

    @Override
    public List<Map<String, Object>> getPaymentTransactions(int page, int limit) {
        try {
            int offset = (page - 1) * limit;

            String paginatedQuery = query + " LIMIT ? OFFSET ?";

            log.info("Executing paginated payment transactions query. page=" + page + ", limit=" + limit);

            List<Map<String, Object>> results = jdbcTemplate.queryForList(
                    paginatedQuery,
                    limit,
                    offset
            );

            log.info("Paginated query executed successfully. Records fetched: " + results.size());

            return results;
        } catch (DataAccessException ex) {
            log.severe("Database error occurred while fetching paginated transactions");
            throw new DatabaseQueryException("Database error occurred while fetching paginated transactions", ex);
        } catch (Exception ex) {
            log.severe("Unexpected error occurred while fetching paginated transactions");
            throw new DatabaseQueryException("Unexpected error occurred while processing paginated request", ex);
        }
    }

    @Override
    public long countPaymentTransactions() {
        try {
            String countQuery = "SELECT COUNT(*) FROM paymenttransactions";
            Long total = jdbcTemplate.queryForObject(countQuery, Long.class);
            return total != null ? total : 0L;
        } catch (DataAccessException ex) {
            log.severe("Database error occurred while counting transactions");
            throw new DatabaseQueryException("Database error occurred while counting transactions", ex);
        } catch (Exception ex) {
            log.severe("Unexpected error occurred while counting transactions");
            throw new DatabaseQueryException("Unexpected error occurred while counting transactions", ex);
        }
    }
}
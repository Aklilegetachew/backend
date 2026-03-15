package com.safaricom.tibcoLogger.controller;

import com.safaricom.tibcoLogger.dto.ApiResponse;
import com.safaricom.tibcoLogger.dto.PagedApiResponse;
import com.safaricom.tibcoLogger.service.QueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
public class QueryController {

    private final QueryService queryService;

    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/transactions/all")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getAllTransactions() {
        List<Map<String, Object>> transactions = queryService.getPaymentTransactions();

        ApiResponse<List<Map<String, Object>>> response = new ApiResponse<>(
                true,
                "Transactions retrieved successfully",
                transactions,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/transactions")
    public ResponseEntity<PagedApiResponse<List<Map<String, Object>>>> getPaginatedTransactions(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        if (page < 1) {
            page = 1;
        }

        if (limit < 1) {
            limit = 10;
        }

        long totalRecords = queryService.countPaymentTransactions();
        int totalPages = (int) Math.ceil((double) totalRecords / limit);

        List<Map<String, Object>> transactions = queryService.getPaymentTransactions(page, limit);

        PagedApiResponse<List<Map<String, Object>>> response = new PagedApiResponse<>(
                true,
                "Paginated transactions retrieved successfully",
                transactions,
                page,
                limit,
                totalRecords,
                totalPages,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }
}
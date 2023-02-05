package com.example.demo.controller;

import com.example.demo.Entity.ExpenseByCategory;
import com.example.demo.repositories.ExpenseRepository;
import com.example.demo.services.ExpenseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpensesByCategoryController {

    private final ExpenseService expenseService;
    private final ExpenseRepository expenseRepository;

    public ExpensesByCategoryController(ExpenseService expenseService,
                                        ExpenseRepository expenseRepository) {
        this.expenseService = expenseService;
        this.expenseRepository = expenseRepository;
    }


    @GetMapping ("/expenses-by-category")
    public List<ExpenseByCategory> getExpenseByCategories(){
        return expenseService.getExpensesByCategory();
    }

}

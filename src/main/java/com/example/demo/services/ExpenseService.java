package com.example.demo.services;

import com.example.demo.Entity.Book;
import com.example.demo.Entity.Expense;
import com.example.demo.Entity.ExpenseByCategory;
import com.example.demo.repositories.ExpenseRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;


    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense createExpense (Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense findExpenseById (Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    public Expense updateExpense (Expense expense){
        return expenseRepository.save(expense);
    }

    public void deleteExpense (Long id) {
        expenseRepository.deleteById(id);
    }

// По Названию, По Дате, По Части названия
    public Expense findBookByNameIgnoreCase (String name){
        return expenseRepository.findByNameIgnoreCase(name);
    }

    public Collection<Expense> findByDate (LocalDate date) {
        return expenseRepository.findExpenseByDate(date);
    }

    public  Collection<Expense> findByNamePart (String part) {
        return  expenseRepository.findAllByNameContainsIgnoreCase(part);
    }

    public Collection<Expense> getAllExpense() {
        return expenseRepository.findAll();
    }
    public Collection<Expense> getAllExpenseByPage(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return expenseRepository.findAll(pageRequest).getContent();
    }

    public List<ExpenseByCategory> getExpensesByCategory (){
        return expenseRepository.getExpenseByCategory();
    }

}

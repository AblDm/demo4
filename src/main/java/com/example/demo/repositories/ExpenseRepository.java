package com.example.demo.repositories;

import com.example.demo.Entity.Expense;
import com.example.demo.Entity.ExpenseByCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Expense findByNameIgnoreCase(String name);

    Collection<Expense> findExpenseByDate(LocalDate date);

    Collection<Expense> findAllByNameContainsIgnoreCase(String part);

    @Query(value = "SELECT category, SUM(amount) as amount FROM expense GROUP BY category",nativeQuery = true)
    List<ExpenseByCategory> getExpenseByCategory();

}
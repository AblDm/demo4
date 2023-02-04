package com.example.demo.controller;

import com.example.demo.Entity.Expense;
import com.example.demo.services.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }


    @GetMapping("{id}")  // GET http://localhost:8080/expense/23
    public ResponseEntity<Expense> findExpenseInfo(@PathVariable long id) {

        Expense expense = expenseService.findExpenseById(id);
        if (expense == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(expense);
    }

    @PostMapping // POST http://localhost:8080/expense
    public Expense createExpense (@RequestBody Expense expense) {
        return expenseService.createExpense(expense);
    }

    @PutMapping// PUT http://localhost:8080/books
    public ResponseEntity<Expense> editBook(@RequestBody Expense expense) {
        Expense findExpense = expenseService.updateExpense(expense);
        if (findExpense == null) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(findExpense);
    }

    @DeleteMapping ("{id}") //DELETE http://localhost:8080/expense/23
    public ResponseEntity deleteExpense (@PathVariable long id){
        expenseService.deleteExpense(id);
        return ResponseEntity.ok().build();
    }

    public  ResponseEntity findExpense(@RequestParam(required = false) String BookName, @RequestParam(required = false) LocalDate date, @RequestParam(required = false) String part){
        if (BookName!=null && !BookName.isBlank()){
            return ResponseEntity.ok(expenseService.findBookByNameIgnoreCase(BookName));
        }
        if (date!=null){
            return ResponseEntity.ok(expenseService.findByDate(date));
        }
        if (part!=null && !part.isBlank()){
            return ResponseEntity.ok(expenseService.findByNamePart(part));
        }
        return ResponseEntity.ok(expenseService.getAllExpense());
    }

    @GetMapping
    public ResponseEntity<Collection<Expense>> getAllExpenses(@RequestParam("page")Integer pageNumber, @RequestParam("size")Integer pageSize) {
        Collection<Expense> expenses = expenseService.getAllExpenseByPage(pageNumber,pageSize);
        return ResponseEntity.ok(expenses);
    }
}

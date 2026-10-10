package com.herschaft.ExpenseTracker.exception;

public class NotUniqueRegisterUsername extends RuntimeException{

    public NotUniqueRegisterUsername() {
        super("Username used to register is already in use");
    }

}

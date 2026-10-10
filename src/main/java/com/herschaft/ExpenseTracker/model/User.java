package com.herschaft.ExpenseTracker.model;

import java.util.UUID;

public record User (
    String username,
    UUID uuid,
    String passwordHash
) {

}

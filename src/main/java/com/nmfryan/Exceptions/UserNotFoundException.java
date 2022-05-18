package com.nmfryan.Exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException  {

    public UserNotFoundException(UUID id) {
            super("Could not find employee " + id);
        }
}

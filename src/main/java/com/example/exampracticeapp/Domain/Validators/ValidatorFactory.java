package com.example.exampracticeapp.Domain.Validators;

import com.example.exampracticeapp.Exception.ValidationException;

public class ValidatorFactory implements Factory {
    private static ValidatorFactory instance;
    @Override
    public Validator createValidator(ValidatorStrategy strategy) {
        switch (strategy){
//            case Flight -> {return new FlightValidator();}
//            case Ticket -> {return new TicketValidator();}
            default -> throw new ValidationException("Invalid Strategy!");
        }
    }

    /**
     *
     * @return instance if it doesn't exist
     */
    public static ValidatorFactory getInstance()
    {
        if(instance==null)
        {
            instance=new ValidatorFactory();
        }
        return instance;
    }
}

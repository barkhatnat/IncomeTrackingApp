package ru.barkhatnat.controller.payload;

import java.math.BigDecimal;

public record UpdateAccountPayload (String title, BigDecimal balance){
}

package ru.barkhatnat.utils;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.barkhatnat.DTO.AccountDto;
import ru.barkhatnat.DTO.AccountResponseDto;
import ru.barkhatnat.entity.Account;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {
    AccountDto toAccountDto(Account account);
    AccountResponseDto toAccountResponseDto(Account account);

    Iterable<AccountDto> toAccountDtoCollection(Iterable<Account> accounts);

    Iterable<AccountResponseDto> toAccountResponseDtoCollection(Iterable<Account> accounts);
}

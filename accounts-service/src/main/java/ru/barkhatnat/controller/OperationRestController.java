package ru.barkhatnat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.barkhatnat.DTO.OperationDto;
import ru.barkhatnat.service.OperationService;

@RestController
@RequestMapping("accounts/{accountId:\\d+}/operations/{operationId:\\d+}")
@RequiredArgsConstructor
public class OperationRestController {
    private final OperationService operationService;

    @PatchMapping
    public ResponseEntity<?> updateOperation(@PathVariable("operationId") int operationId, @PathVariable("accountId") int accountId, @Valid @RequestBody OperationDto operationDto,
                                             BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            operationService.updateOperation(operationId, operationDto.amount(), operationDto.datePurchase(), operationDto.categoryId(), operationDto.note(), accountId);
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCategory(@PathVariable("operationId") int operationId, @PathVariable("accountId") int accountId) {
        operationService.deleteOperation(operationId, accountId);
        return ResponseEntity.noContent().build();
    }
}

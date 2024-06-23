package ru.barkhatnat.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.barkhatnat.DTO.OperationDto;
import ru.barkhatnat.DTO.OperationResponseDto;
import ru.barkhatnat.entity.Operation;
import ru.barkhatnat.service.OperationService;
import ru.barkhatnat.utils.OperationMapper;

import java.util.Map;

@RestController
@RequestMapping("accounts/{accountId:\\d+}/operations")
@RequiredArgsConstructor
public class OperationsRestController {
    private final OperationService operationService;
    private final OperationMapper operationMapper;

    @GetMapping
    public ResponseEntity<Iterable<OperationResponseDto>> getOperationList(@PathVariable("accountId") int accountId) {
        Iterable<Operation> operations = operationService.findAllAccountOperations(accountId);
        Iterable<OperationResponseDto> operationResponseDto = operationMapper.toOperationResponseDtoCollection(operations);
        return ResponseEntity.ok(operationResponseDto);
    }

    @PostMapping
    public ResponseEntity<?> createOperation(@Valid @RequestBody OperationDto operationDto,
                                             BindingResult bindingResult,
                                             UriComponentsBuilder uriComponentsBuilder, @PathVariable String accountId) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            OperationResponseDto operationResponseDto = operationService.createOperation(operationDto, Integer.valueOf(accountId));
            return ResponseEntity.created(uriComponentsBuilder
                            .replacePath("/accounts/{accountId}/operations/{operationId}")
                            .build(Map.of("accountId", accountId, "operationId", operationResponseDto.id())))
                    .body(operationResponseDto);
        }
    }
}

package ru.barkhatnat.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.barkhatnat.entity.Operation;

public interface OperationRepository extends CrudRepository<Operation, Integer> {
    Iterable<Operation> findOperationsByAccount_Id(Integer account_id);
}

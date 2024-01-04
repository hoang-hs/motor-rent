package com.example.rentcar.core.service;

import com.example.rentcar.core.domain.Inventory;
import com.example.rentcar.core.domain.Motor;
import com.example.rentcar.core.domain.repository.InventoryRepository;
import com.example.rentcar.core.domain.repository.MotorRepository;
import com.example.rentcar.exception.SystemErrorException;
import com.example.rentcar.present.request.AddMotorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MotorService {
    private final MotorRepository motorRepository;
    private final InventoryRepository inventoryRepository;

    public Inventory addMotor(AddMotorRequest req) {
        Optional<Motor> optionalMotor = motorRepository.findByType(req.getType());
        if (optionalMotor.isPresent()) {
            Inventory inventory = inventoryRepository.findByMotor(optionalMotor.get()).
                    orElseThrow(SystemErrorException::Default);
            inventory.setTotal(inventory.getTotal() + req.getNumber());
            inventory.setUpdatedAt(Instant.now());
            inventoryRepository.save(inventory);
            return inventory;
        }
        Motor motor = new Motor();
        motor.setType(req.getType());
        motorRepository.save(motor);
        Inventory inventory = new Inventory();
        inventory.setMotor(motor);
        inventory.setReserved(0);
        inventory.setTotal(req.getNumber());
        inventoryRepository.save(inventory);
        return inventory;
    }

}

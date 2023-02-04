package com.example.demo.services;

import com.example.demo.Entity.Position;
import com.example.demo.repositories.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {
    private final PositionRepository positionRepository ;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<Position> findByName(String name) {
        return positionRepository.findByName(name);
    }

    public Position findById(Long id) {
        return positionRepository.findById(id).get();
    }

    public Position createPosition(Position position) {
        return positionRepository.save(position);
    }

    public void deleteById(Long id) {
        positionRepository.deleteById(id);
    }

}
package com.example.demo.repositories;

import com.example.demo.Entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    List<Position> findByName(String position);
}

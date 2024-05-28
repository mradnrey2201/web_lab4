package com.example.helpme.Repositories;

import com.example.helpme.Models.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface PointRepo extends JpaRepository<Point,Long> {
 Collection<Point> findByUsername(String username);
}

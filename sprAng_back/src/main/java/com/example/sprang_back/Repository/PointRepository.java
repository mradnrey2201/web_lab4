package com.example.sprang_back.Repository;

import com.example.sprang_back.Model.Hit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.Collection;
@Repository
public interface PointRepository extends JpaRepository<Hit,Long> {

    @Query("select p.x,p.y,p.r from Hit p where p.username = :username")
    Collection<Hit> findAllByUsername(@Param("username")String username);

    @Transactional
    void removeAllByUsername(@Param("username")String username);


}

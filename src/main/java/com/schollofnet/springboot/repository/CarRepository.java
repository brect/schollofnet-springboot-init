package com.schollofnet.springboot.repository;

import com.schollofnet.springboot.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    String findByName(String name);

}

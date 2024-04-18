package com.uco.hackathon.repositories;

import com.uco.hackathon.entidades.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixtureRepository extends JpaRepository<Fixture, Long> {

}

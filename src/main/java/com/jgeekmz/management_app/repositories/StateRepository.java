package com.jgeekmz.management_app.repositories;

import com.jgeekmz.management_app.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

    /** Finds all states from datenbank */
    List<State> findAll();

}
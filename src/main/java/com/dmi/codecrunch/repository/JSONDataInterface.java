package com.dmi.codecrunch.repository;

import com.dmi.codecrunch.model.JSONDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JSONDataInterface extends JpaRepository<JSONDataModel, Integer> {
}

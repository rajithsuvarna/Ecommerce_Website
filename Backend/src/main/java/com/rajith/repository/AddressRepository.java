package com.rajith.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajith.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}

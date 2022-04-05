package com.petClinic.petClinic.repository;

import com.petClinic.petClinic.entity.Owner;
import com.petClinic.petClinic.entity.Role;

import java.util.Optional;

public interface OwnerRepository {

    public Optional<Owner> findOwnerByID(int id);

    public boolean addOwner(Owner owner);
}

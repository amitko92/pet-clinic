package com.petClinic.petClinic.repository.owner;

import com.petClinic.petClinic.entity.Owner;
import com.petClinic.petClinic.entity.Role;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository {

    public Optional<Owner> findOwnerByID(int id);

    public boolean addOwner(Owner owner);


    Optional<List<Owner>> findOwners(int projectId);
}

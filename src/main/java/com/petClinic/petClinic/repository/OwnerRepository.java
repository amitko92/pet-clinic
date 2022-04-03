package com.petClinic.petClinic.repository;

import com.petClinic.petClinic.entity.Owner;
import com.petClinic.petClinic.entity.Role;

public interface OwnerRepository {

    public Owner findOwnerByID(int id);

    public boolean addOwner(Owner owner);
}

package com.dyq.demo.Service;

import com.dyq.demo.entity.Address;
import com.dyq.demo.entity.Role;

import java.util.List;

public interface AddressService {
    Address find(Long id);
    Address save(Address address);
    void delete(Long id);
    void remove(Long id);
    void update(Address address);
    List<Address> findAll();
}

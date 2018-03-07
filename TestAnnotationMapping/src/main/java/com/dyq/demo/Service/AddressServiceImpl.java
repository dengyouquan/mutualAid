package com.dyq.demo.Service;

import com.dyq.demo.Repository.AddressRepository;
import com.dyq.demo.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address find(Long id) {
        return addressRepository.findOne(id);
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void remove(Long id) {
        addressRepository.removeAddress(id);
    }

    @Override
    public void update(Address address) {
        addressRepository.save(address);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }
}

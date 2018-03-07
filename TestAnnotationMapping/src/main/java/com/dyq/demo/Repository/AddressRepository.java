package com.dyq.demo.Repository;

import com.dyq.demo.entity.Address;
import com.dyq.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Long> {
    void deleteById(Long id);
    @Modifying
    @Query("delete from Address where id=?1")
    void removeAddress(Long id);
}

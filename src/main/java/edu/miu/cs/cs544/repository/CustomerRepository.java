package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Address;
import edu.miu.cs.cs544.domain.AuditData;
import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    public Optional<Customer> findByLastName(String lastName);
    public  void deleteCustomerById(Integer id);
//    @Modifying
//    @Query("UPDATE Customer c SET c.user = :newUser, c.email = :newEmail, c.auditData = :newAudit, c.firstName = :firstName," +
//            "c.lastName = :lastName, c.physicalAddress = :physicalAddress, c.billingAddress = :billingAddress" +
//            " WHERE c.id = :customerId")
//    public void updateCustomer(@Param("newUser") User user, @Param("newEmail") String email,
//                                   @Param("newAudit") AuditData auditData, @Param("firstName") String firstName, @Param("lastName") String lastName,
//                                   @Param("physicalAddress") Address pAddress, @Param("billingAddress") Address bAddress,
//                                   @Param("customerId") Integer customerId);
}

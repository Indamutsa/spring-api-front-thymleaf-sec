package com.example.arsenedata.repository;

import com.example.arsenedata.dataEntity.Staff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StaffRepository extends PagingAndSortingRepository<Staff, Integer>
{
    @Query(nativeQuery = true, value = "select count(*) from staff_member")
    int getNumberOfStuff();
}

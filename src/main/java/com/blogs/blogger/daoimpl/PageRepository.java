package com.blogs.blogger.daoimpl;

import com.blogs.blogger.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PageRepository extends PagingAndSortingRepository<User, Long> {

    @Query("select s from User s where s.name like %?1%")
    Page<User> findByWord (String name, Pageable pageable);


}

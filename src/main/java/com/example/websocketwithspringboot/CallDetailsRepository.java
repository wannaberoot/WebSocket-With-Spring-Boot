package com.example.websocketwithspringboot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallDetailsRepository extends JpaRepository<CallDetails, Integer> {

    CallDetails findCallByToWhom(String phoneNumber);
}

package com.example.websocketwithspringboot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CallDetailsRepository extends JpaRepository<CallDetails, Integer> {

    CallDetails findCallByToWhom(String phoneNumber);
}

package com.example.websocketwithspringboot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CallDetailsRepository extends JpaRepository<CallDetails, Integer> {

    List<CallDetails> findCallByToWhom(String phoneNumber);

    List<CallDetails> findCallByToWhomAndFromWho(String toWhom, String fromWho);
}

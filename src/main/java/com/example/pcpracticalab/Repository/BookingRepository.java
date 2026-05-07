package com.example.pcpracticalab.Repository;

import com.example.pcpracticalab.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}

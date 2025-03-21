package org.example.persistence.repository;

import org.example.persistence.model.SinglePaymentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SinglePaymentRepository extends BaseRepository<SinglePaymentEntity> {
    @Query("SELECT s FROM SinglePaymentEntity s WHERE s.date BETWEEN :startDate AND :endDate AND s.user.id = :userId")
    Optional<List<SinglePaymentEntity>> findPaymentsByDateRangeAndUserId(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("userId") Long userId
    );

    Optional<List<SinglePaymentEntity>> findByDateAndUser_Id(LocalDate date, Long userId);

    @Query("SELECT s FROM SinglePaymentEntity s WHERE s.user.id = :userId ORDER BY s.date DESC")
    List<SinglePaymentEntity> findLastThreePaymentsByUserId(@Param("userId") Long userId);

    Optional<List<SinglePaymentEntity>> findByDateBetweenAndUser_Id(LocalDate startDate, LocalDate endDate, Long userId);

}

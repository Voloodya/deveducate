package ru.cinimex.deveducate.dal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.cinimex.deveducate.dal.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    @Query("select ord from OrderEntity ord")
    Page<OrderEntity> findAllPage(Pageable pageable);

    @Query("select ord from OrderEntity ord")
    Slice<OrderEntity> findAllSlice(Pageable pageable);
}

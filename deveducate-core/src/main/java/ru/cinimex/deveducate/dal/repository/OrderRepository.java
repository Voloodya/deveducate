package ru.cinimex.deveducate.dal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.cinimex.deveducate.dal.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    @Modifying
    @Query("update OrderEntity o set o.orderTotal = :orderTotal where o.orderId = :orderId")
    int updateOrderSetOrderTotal(@Param("orderTotal") Integer orderTotal,
                                 @Param("orderId") Integer orderId);

    @Query("select ord from OrderEntity ord")
    Page<OrderEntity> findAllPage(Pageable pageable);

    @Query("select ord from OrderEntity ord")
    Slice<OrderEntity> findAllSlice(Pageable pageable);
}

package ru.cinimex.deveducate.dal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.cinimex.deveducate.dal.entity.OrderEntity;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer>, JpaSpecificationExecutor<OrderEntity>, QuerydslPredicateExecutor<OrderEntity> {

    @Modifying
    @Query("update OrderEntity o set o.orderTotal = :orderTotal where o.orderId = :orderId")
    int updateOrderSetOrderTotal(@Param("orderTotal") Integer orderTotal,
                                 @Param("orderId") Integer orderId);

    @Query(value = "SELECT * FROM Orders ord WHERE ord.order_total = :count", nativeQuery = true)
    List<OrderEntity> findByOrderTotal(@Param("count") Integer count);

//    @Query("select ord from OrderEntity ord")
//    Slice<OrderEntity> findAllSlice(Pageable pageable);
//
//    @Query("select ord from OrderEntity ord")
//    Page<OrderEntity> findAll(Specification specification, Pageable pageable);
}

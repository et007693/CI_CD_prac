package com.hd.sample_jpa_mysql.repository;

import com.hd.sample_jpa_mysql.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // 기본적인 CRUD는 이미 만들어져 있음
    List<Item> findByItemNum(String itemNum); // SELECT * FROM item WHERE item_nm = ' '
    List<Item> findByItemNumOrItemDetail(String itemName, String itemDetail);

    // @Query : JPQL, nativeQuery
//    @Query("SELECT i FROM Item i.itemDetail LIKE %:itemDetail% ORDER BY i.price desc")
//    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

     // NativeQuery : 해당하는 DB의 실제 쿼리를 작성, 복잡한 쿼리를 작성 가능
//    @Query(value = "SELECT * FROM item i WHERE i.item_detail LIKE %:itemDetail% ORDER BY i.price desc", nativeQuery = true)
//    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);
}

package desarrolloempresarial.com.bookstoreapi.repository;

import desarrolloempresarial.com.bookstoreapi.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

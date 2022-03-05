package com.evertcode.order.services;

import com.evertcode.order.dtos.OrderDTO;
import com.evertcode.order.dtos.ProductDTO;
import com.evertcode.order.entities.Order;
import com.evertcode.order.entities.OrderStatus;
import com.evertcode.order.entities.Product;
import com.evertcode.order.repositories.OrderRepository;
import com.evertcode.order.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;

	public OrderService(final OrderRepository orderRepository, final ProductRepository productRepository) {
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
	}

	@Transactional
	public List<OrderDTO> findAll() {
		List<Order> orderList = orderRepository.findByStatusIsOrderByMomentAsc(OrderStatus.PENDING);
		return orderList.stream().map(OrderDTO::new).collect(Collectors.toList());
	}

	@Transactional
	public OrderDTO insert(OrderDTO orderDto) {
		Order order = new Order(null, orderDto.getAddress(), orderDto.getLatitude(), orderDto.getLongitude(),
				Instant.now(), OrderStatus.PENDING);

		for (ProductDTO productDto : orderDto.getProducts()) {
			Optional<Product> product = productRepository.findById(productDto.getId());
			if (product.isPresent())
				order.getProducts().add(product.get());
		}

		order = orderRepository.save(order);

		return new OrderDTO(order);
	}

	@Transactional
	public OrderDTO setDelivered(Long id) {
		Order order = orderRepository.findById(id).get();
		order.setStatus(OrderStatus.DELIVERED);
		order = orderRepository.save(order);
		return new OrderDTO(order);


	}
}

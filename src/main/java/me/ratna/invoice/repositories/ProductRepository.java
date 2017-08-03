package me.ratna.invoice.repositories;

import me.ratna.invoice.Model.Product;
import org.springframework.data.repository.CrudRepository;

public interface  ProductRepository extends CrudRepository <Product,Long>{
}

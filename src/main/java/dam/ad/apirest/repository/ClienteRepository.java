package dam.ad.apirest.repository;

import dam.ad.apirest.model.Cliente;
import org.springframework.data.repository.ListCrudRepository;

public interface ClienteRepository extends ListCrudRepository<Cliente, Integer> {
}

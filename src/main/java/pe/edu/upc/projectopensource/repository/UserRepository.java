package pe.edu.upc.projectopensource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.projectopensource.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

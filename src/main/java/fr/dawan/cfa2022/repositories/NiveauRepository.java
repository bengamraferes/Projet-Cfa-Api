package fr.dawan.cfa2022.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.cfa2022.entities.Niveau;
@Repository
public interface NiveauRepository extends JpaRepository<Niveau, Long>  {

	long countByDescriptionContaining(String description);

	

}

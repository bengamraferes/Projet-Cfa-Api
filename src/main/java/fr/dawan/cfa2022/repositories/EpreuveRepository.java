package fr.dawan.cfa2022.repositories;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.cfa2022.entities.Epreuve;
import fr.dawan.cfa2022.entities.Epreuve.Type;
@Repository
public interface EpreuveRepository extends JpaRepository<Epreuve, Long> {

	long countByTitreContainingOrDescriptionContaining(String titre, String descreption);

	List<Epreuve> getAllByBlocCompetencesId(long id);
	
//	@Query("SELECT e FROM Epreuve e WHERE e.type = :type")
	List<Epreuve> getAllByType(Type type);

	Page<Epreuve> findByTitreContainingOrDescriptionContaining(String titre, String description, Pageable pageable);

}

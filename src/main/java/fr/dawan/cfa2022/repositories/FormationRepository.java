package fr.dawan.cfa2022.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.cfa2022.entities.Formation;
@Repository
public interface FormationRepository extends JpaRepository<Formation, Long>  {

	long countByObjectifsPedagogiqueContainingOrTitreContaining( String objectifsPedagogique,String titre);


	Page<Formation> findByObjectifsPedagogiqueContainingOrTitreContaining(String objectifsPedagogique,String titre, Pageable pageable);


	Optional<Formation> findBySlug(String slug);

}

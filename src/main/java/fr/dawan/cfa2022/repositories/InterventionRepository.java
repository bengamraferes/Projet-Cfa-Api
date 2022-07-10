package fr.dawan.cfa2022.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.dawan.cfa2022.entities.Intervention;
@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Long>  {

	long countByFormationTitreContainingOrFormationObjectifsPedagogiqueContaining(String titre, String objectifsPedagogique);


	Page<Intervention> findByFormationTitreContainingOrFormationObjectifsPedagogiqueContaining(String titre, String objectifsPedagogique, Pageable pageable);

	
	List<Intervention> getAllByFormationId(long idFromation);
	
	
	List<Intervention> getAllByPromotionId(long idPromotion);
	
	
	List<Intervention> getAllByFormateurId(long idUtilisateur);
	
}

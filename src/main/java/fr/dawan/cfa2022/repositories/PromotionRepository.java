package fr.dawan.cfa2022.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.dawan.cfa2022.entities.Promotion;
@Repository
public interface PromotionRepository  extends JpaRepository<Promotion, Long>{

	long countByTitreProfessionnelTitreContainingOrVilleNomContaining(String Titre, String ville);
	
	@Query("SELECT  p FROM Promotion p JOIN FETCH p.etudiants")
	List<Promotion> getAll();
	
	Page<Promotion> findByTitreProfessionnelTitreContainingOrVilleNomContaining(String Titre, String ville,Pageable pageable);

	List<Promotion> getAllByTitreProfessionnelId(long id);

	List<Promotion> getAllByVilleId(long id);
}

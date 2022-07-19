package fr.dawan.cfa2022.repositories;



import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import fr.dawan.cfa2022.entities.TitreProfessionnel;
@Repository
public interface TitreProfessionnelRepository  extends JpaRepository<TitreProfessionnel, Long> {

	
	Page<TitreProfessionnel> findByTitreContaining(String titre,Pageable pageable);
	
	long countByTitreContaining(String titre);
	
	@Query("SELECT p.titreProfessionnel FROM TitreProfessionnel t JOIN Promotion p ON  p.id = :promotionId")
	Optional<TitreProfessionnel> getTitreProfessionnelByPromotionId(@Param("promotionId") long promotionId );

	TitreProfessionnel findBySlug(String slug);
	
}

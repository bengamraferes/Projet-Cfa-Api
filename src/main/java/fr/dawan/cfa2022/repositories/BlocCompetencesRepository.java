package fr.dawan.cfa2022.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.cfa2022.entities.BlocCompetences;
@Repository
public interface BlocCompetencesRepository  extends JpaRepository<BlocCompetences, Long>{

	Page<BlocCompetences> findByTitreContainingOrDescriptionContaining(String titre , String description,Pageable pageable);
	
	long countByTitreContainingOrDescriptionContaining(String titre, String description);

	List<BlocCompetences> getAllByTitreProfessionnelId(long id);
	
    @Query("FROM BlocCompetences bc JOIN bc.titreProfessionnel t Where t.id = :id And (bc.titre LIKE %:titre% OR bc.description LIKE %:description)")
	List<BlocCompetences> getAllByTitreProfessionnelId(@Param("id") long id, @Param("titre")String titre,  @Param("description") String description);
}

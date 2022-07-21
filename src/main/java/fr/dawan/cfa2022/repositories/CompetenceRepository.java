package fr.dawan.cfa2022.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.cfa2022.entities.BlocCompetences;
import fr.dawan.cfa2022.entities.Competence;
@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Long>  {

	long countByTitreContainingOrDescriptionContaining(String titre, String description);

	@Query("SELECT c FROM Competence c JOIN TitreProfessionnel t ON c.blocCompetences.id = :id")
	List<Competence> getAllByTitreProfessionnelId(long id);

	 @Query("FROM Competence c JOIN c.blocCompetences bc Where bc.id = :id And (c.titre LIKE %:titre% OR c.description LIKE %:description)")
		List<Competence> getAllByBlocCompetencesId(@Param("id") long id, @Param("titre")String titre,  @Param("description") String description);

	Page<Competence> findByTitreContainingOrDescriptionContaining(String titre, String description, Pageable pageable);

	List<Competence> getAllByBlocCompetencesId(long id);

}
 
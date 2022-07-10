package fr.dawan.cfa2022.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.cfa2022.entities.Ville;
@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {

	@Query("FROM Ville v WHERE v.slug = :slug")
	Ville findBySlug(@Param("slug") String slug);

	long countByNomContaining(String search);

}

package fr.dawan.cfa2022.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.dawan.cfa2022.entities.Positionnement;
@Repository
public interface PositionnementRepository extends JpaRepository<Positionnement, Long>  {

	long countByEtudiantLastNameContainingOrEtudiantFirstNameContaining(String nom , String prenom);

	List<Positionnement> getAllByInterventionId(long idIntervention);

	@Query("SELECT p FROM Positionnement p JOIN p.etudiant etu JOIN etu.promotions promo On promo.id = :idPromotion ")
	List<Positionnement> getAllByPromotionId(long idPromotion);
}

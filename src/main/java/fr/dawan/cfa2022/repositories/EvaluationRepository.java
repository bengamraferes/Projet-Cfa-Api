 package fr.dawan.cfa2022.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.cfa2022.entities.Evaluation;
@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation,Long> {
	//évaluations d'un étudiant donné
	@Query("FROM Evaluation e WHERE e.etudiant.id= :etudiantId")
	List<Evaluation> findAllByEtudiantId(@Param("etudiantId") long etudiantId);
	
	@Query("FROM Evaluation e JOIN e.etudiant etu JOIN etu.promotions promo ON promo.id= :promotionId")
	List<Evaluation> findAllByPromotionId(@Param("promotionId") long promotionId);
	
//	@Query("SELECT AVG(e.note) FROM Evaluation e JOIN e.etudiant etu JOIN etu.promotions promo WHERE etu.id= :etudiantId AND promo.id= :promotionId")
	@Query("SELECT AVG(e.note) FROM Evaluation e JOIN e.etudiant etu ON etu.id = :etudiantId JOIN etu.promotions p ON p.id = :promotionId")
	double getAvgByEtudiantIdAndPromotionId(@Param("etudiantId") long etudiantId, @Param("promotionId") long promotionId);
	
	@Query("SELECT AVG(e.note) FROM Evaluation e JOIN e.etudiant etu JOIN etu.promotions promo WHERE promo.id= :promotionId")
//	@Query("SELECT AVG(e.note) FROM Evaluation e JOIN e.etudiant.promotions promo ON promo.id = :promotionId")
	double getAvgByPromotionId(@Param("promotionId") long promotionId);
	
	@Query("SELECT AVG(e.note) FROM Evaluation e JOIN e.epreuve ep JOIN ep.blocCompetences bc JOIN e.etudiant etu JOIN etu.promotions promo WHERE promo.id = :promotionId AND bc.id = :bcId")
//	@Query("SELECT AVG(e.note) FROM Evaluation e JOIN e.epreuve.blocCompetences bc ON bc.id = :bcId JOIN e.etudiant.promotions promo ON promo.id = :promotionId ")
	double getAvgByPromotionIdAndBlocDeCompetences(@Param("bcId") long bcId, @Param("promotionId") long promotionId);
	
	@Query("SELECT AVG(e.note) FROM Evaluation e JOIN e.etudiant etu WHERE etu.id= :etudiantId AND e.epreuve.blocCompetences.id = :blocCompetencesId")
	double getAvgByEtudiantIdAndBlocCompId(@Param("etudiantId") long etudiantId, @Param("blocCompetencesId") long blocCompetencesId);


	Optional<Evaluation> getByEtudiantIdAndEpreuveId(long idEtudiant, long idEpreuve);
	
//	@Query("SELECT COUNT(a) FROM Absence a JOIN a.etudiant e JOIN e.utilisateur u WHERE u.nom LIKE :search OR u.prenom LIKE :search")
//	long countByUtilisateurEtudiantNomOrPrenomContaining(String search);
	
}

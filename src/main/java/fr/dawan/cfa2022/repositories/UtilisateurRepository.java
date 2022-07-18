package fr.dawan.cfa2022.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dawan.cfa2022.entities.Utilisateur;
import fr.dawan.cfa2022.entities.Utilisateur.Role;
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

	@Query("FROM Utilisateur u WHERE u.email= :email") //si SQL native, mettre nativeQuery=true
	Utilisateur findByEmail(@Param("email") String email);
	
	Page<Utilisateur> findAllByFirstNameContainingOrLastNameContainingOrEmailContaining(
			String firstName, String lastName, String email, Pageable pageable);
	
	long countByFirstNameContainingOrLastNameContainingOrEmailContaining(
			String firstName, String lastName, String email);
	
	List<Utilisateur> findByRole(Role role);
}

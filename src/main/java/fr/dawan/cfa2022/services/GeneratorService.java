package fr.dawan.cfa2022.services;



public interface GeneratorService {

	String getBulinEval(long idEtudiant, long idPromotion)throws Exception ;

	String getGrillePositionnement(long idPromotion) throws Exception;

}

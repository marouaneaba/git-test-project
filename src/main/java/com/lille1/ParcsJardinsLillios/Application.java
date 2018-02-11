package com.lille1.ParcsJardinsLillios;
import com.lille1.ParcsJardinsLillios.Service.Interfaces.CategorieInterface;
import com.lille1.ParcsJardinsLillios.Service.Interfaces.CommentaireInterface;
import com.lille1.ParcsJardinsLillios.Service.Interfaces.ParcJardinInterface;
import com.lille1.ParcsJardinsLillios.Storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.lille1.ParcsJardinsLillios.DAO.*;

import com.lille1.ParcsJardinsLillios.Entity.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
@EnableJpaRepositories

public class Application implements CommandLineRunner{

	
	@Resource
	StorageService storageService;
	@Autowired
	private CommentaireRepository mCommentaireRepository;
	@Autowired
	private CategorieRepository mCategorieRepository;
	@Autowired
	private ParcJardinRepository mParcJardinRepository;
	@Autowired
	private HoraireRepository mHoraireRepository;
	@Autowired
	private ParcJardinRepository parcJardinRepository;
	@Autowired
	private CommentaireRepository commentaireRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private HoraireRepository horaireRepository;
	
	@Autowired
	private CategorieInterface categorieInterfaceMetier;
	@Autowired
	private ParcJardinInterface parcJardinInterfaceMetier;
	@Autowired
    private CommentaireInterface commentaireInterfaceMetier;
	
	
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(java.lang.String[] args) {
        SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(java.lang.String... arg0) throws Exception {

		ParcJardin PJ2= new ParcJardin("jardin1","descJardin1", "JARDIN","30.0","3.16","adressJardin1");
		ParcJardin PJ3= new ParcJardin("jardin2","descJardin2", "PARC","21.0","2.0","adressJardin2");
		ParcJardin PJ4= new ParcJardin("jardin4","descJardin2", "PARC","24.0","2.4","adressJardin2");

		Categorie catN1 = new Categorie("toto1");
		Categorie catN2 = new Categorie("toto2");
		Categorie catN3 = new Categorie("toto3");
		mCategorieRepository.save(catN1);
		mCategorieRepository.save(catN2);
		mCategorieRepository.save(catN3);

		parcJardinInterfaceMetier.ajouterCategorieToPJ(catN1,PJ2);
		parcJardinInterfaceMetier.ajouterCategorieToPJ(catN2,PJ3);
		parcJardinInterfaceMetier.ajouterCategorieToPJ(catN3,PJ2);


		Commentaire cm1 = new Commentaire("com1",3,"pers1",false, PJ2);
		Commentaire cm2 = new Commentaire("com2",5,"pers2",false,PJ2);
		Commentaire cm3 = new Commentaire("com3",5,"pers3",false,PJ3);
		Commentaire cm5 = new Commentaire("com5",5,"pers3",false,PJ4);

		Commentaire commentaire1 = mCommentaireRepository.save(cm1);
		Commentaire commentaire2 = mCommentaireRepository.save(cm2);
		Commentaire commentaire3 = mCommentaireRepository.save(cm3);

		ParcJardin parcjardin1 = mParcJardinRepository.save(PJ2);
		ParcJardin parcjardin2 = mParcJardinRepository.save(PJ3);
		ParcJardin parcjardin3 = mParcJardinRepository.save(PJ4);





		Categorie cat2 = new Categorie("ETUDE");
		Categorie cat3 = new Categorie("SPORT");
		Categorie cat4 = new Categorie("RESTAURATION");
		Categorie cat5 = new Categorie("PROMENER");
		Categorie cat6 = new Categorie("ECOUTER");
		Categorie cat7 = new Categorie("OBSERVER");

        Categorie catsave2 = mCategorieRepository.save(cat2);
		Categorie catsave3 = mCategorieRepository.save(cat3);
		Categorie catsave4 = mCategorieRepository.save(cat4);
		Categorie catsave5 = mCategorieRepository.save(cat5);
		Categorie catsave6 = mCategorieRepository.save(cat6);
		Categorie catsave7 = mCategorieRepository.save(cat7);

		List<Categorie> ltmpcat = parcJardinInterfaceMetier.ConsulterCategoriesPJ(parcjardin1.getId());
		parcJardinInterfaceMetier.AjouterListCatToPJ(ltmpcat,PJ3);



		Horaire h1 = new Horaire("8:00","18:00", "lundi",parcjardin1);
		mHoraireRepository.save(h1);

		List<ParcJardin> listPJByCat = parcJardinInterfaceMetier.chercherPJParCategorie(cat2);

		for(ParcJardin pj : listPJByCat){
			System.out.println("PJ avec Cat 2"+pj.toString());
		}



		List<Categorie> listCat= parcJardinInterfaceMetier.ConsulterCategoriesPJ(parcjardin1.getId());

		for(Categorie cat : listCat){
			System.out.println("cat de PJ1"+cat.toString());
		}





		ParcJardin PJLG = parcJardinInterfaceMetier.chercherPJLG("30.0","3.16");
		System.out.println("PJLGGGG"+PJLG.toString());
		

		commentaireInterfaceMetier.ValiderCommentaire(commentaire1);
		commentaireInterfaceMetier.SupprimerCommentaire(commentaire3);



		//test suppression parc

		ParcJardin nvPJ = parcJardinRepository.save(new ParcJardin("jardin3","descJardin3", "JARDIN","37.6","3.10","adressJardin1"));
		commentaireRepository.save( new Commentaire("com4",5,"pers4",false,nvPJ));

		Categorie catPJSup = categorieRepository.save(new Categorie("test supp cat"));
		parcJardinInterfaceMetier.ajouterCategorieToPJ(catPJSup, nvPJ);


		List<ParcJardin> listPl= parcJardinInterfaceMetier.ConsulterParcsJardin();
		for(ParcJardin pj : listPl){
			System.out.println(pj.toString());
		}


		//parcJardinInterfaceMetier.supprimercatFromPJ(nvPJ.getId());

		//parcJardinInterfaceMetier.SupprimerPJ(nvPJ);


		//parcJardinInterfaceMetier.SupprimerPJ(parcjardin2);
		List<ParcJardin> listPJ= parcJardinInterfaceMetier.ConsulterParcsJardin();
		for(ParcJardin pj : listPJ){
			System.out.println(pj.toString());
		}
        


		List<Commentaire> listCom = commentaireRepository.findByConfirmerAndParcJardinn(false,nvPJ);
		for(Commentaire com : listCom){
			System.out.println(com.toString());
		}



		/*ParcJardin PJ1= new ParcJardin("parc1","descParc1", "Parc",125.0,2121.0,"adressParc1");
		ParcJardin PJ2= new ParcJardin("jardin1","descJardin1", "Jardin",122.0,211.0,"adressJardin1");
		ParcJardin PJ3= new ParcJardin("jardin2","descJardin2", "Jardin",122.0,211.0,"adressJardin2");

		Categorie cat1 = new Categorie("cat1");
		Categorie cat2 = new Categorie("cat2");
		Categorie cat3 = new Categorie("cat3");

		Commentaire cm1 = new Commentaire("com1",3,"pers1",false);
        Commentaire cm2 = new Commentaire("com2",5,"pers2",false);


        //tester la classe metier parcjardin

		//ajouter PJ
		System.out.println("test ParcJardin Services");
		boolean ajouter1 =parcJardinInterfaceMetier.AjouterPJ(PJ1);
		System.out.println("PJ1 ajouter : " + ajouter1);
		boolean ajouter3 =parcJardinInterfaceMetier.AjouterPJ(PJ3);
		System.out.println("PJ1 ajouter : " + ajouter3);
		boolean ajouter2 =parcJardinInterfaceMetier.AjouterPJ(PJ2);
		System.out.println("PJ2 ajouter : " + ajouter2);

		//ajouter les cat
        categorieInterfaceMetier.AjouterCategoriePJ(cat1);
        categorieInterfaceMetier.AjouterCategoriePJ(cat2);
        categorieInterfaceMetier.AjouterCategoriePJ(cat3);


        //chercher une caegorie
		Categorie catFound1 = categorieInterfaceMetier.ColsulterCategorieId(Long.parseLong(java.lang.String.valueOf(1)));
		Categorie catFound2 = categorieInterfaceMetier.ColsulterCategorieId(Long.parseLong(java.lang.String.valueOf(2)));

		//chercher un pqrc pqr id
		ParcJardin pjById1 = parcJardinInterfaceMetier.ChercherPJParId(Long.parseLong(java.lang.String.valueOf(1)));
		//System.out.println("PJ1 trouver " + pjById1.toString());

		//chercher un parc par nom
        ParcJardin pjByNom2 = parcJardinInterfaceMetier.chercherPJParNom("jardin1");
       // System.out.println("PJ2 trouver " + pjByNom2.toString());


        //

        //ajouter categorie a PJ

        parcJardinInterfaceMetier.ajouterCategorieToPJ(catFound1,pjById1);
        parcJardinInterfaceMetier.ajouterCategorieToPJ(catFound2,pjById1);
		parcJardinInterfaceMetier.ajouterCategorieToPJ(catFound1,pjByNom2);



        //ajouter des commentaire confirmer false
        Commentaire comAjouter1 = commentaireInterfaceMetier.AjouterCommentaire(cm1);
        Commentaire comAjouter2 = commentaireInterfaceMetier.AjouterCommentaire(cm2);

        //consulter les categories de pj1
        
        //List<Categorie> listCatPJ1 = categorieRepository.findByParcJardinn(pjById1);
        //System.out.println("categorie de pj1 = " + listCatPJ1.toString());

		parcJardinInterfaceMetier.SupprimerPJ(pjByNom2);

		//commentaireInterfaceMetier.ValiderCommentaire(cm1);


        //chercher parc par categorie

        List<ParcJardin> listPjCat = parcJardinInterfaceMetier.chercherPJParCategorie(cat1);
        /*for(int i=0 ; i< listPjCat.size();i++) {
            System.out.println("PJ avec cat1 = " + listPjCat.toString());
        }*/








		//tester la classe metier categorie
/*
		Categorie foundCat = categorieInterfaceMetier.ColsulterCategorieId(Long.parseLong(java.lang.String.valueOf(3)));
		categorieInterfaceMetier.ConsulterParcJardinParCategorie(cat3);


		//System.out.println("cat found "+foundCat.toString());


		ParcJardin pjLG = parcJardinRepository.trouverPJparLG(PJ1.getL(),PJ1.getG());
		System.out.println("parc Trouver Par L et G"+ pjLG.toString());



		//chercher tout les parcs et jardins
		List<ParcJardin> ListPJconsulter = parcJardinInterfaceMetier.ConsulterParcsJardin();
		for(int i=0 ; i< ListPJconsulter.size();i++) {
			System.out.println("PJ = "+" " +i+" " + ListPJconsulter.get(i).getName());
		}


/*
		Admin a = new Admin("mouss", "123", "toto", "25448");
		adminRepository.save(a);


		adminRepository.findAll().forEach(c->{
			System.out.println(c.getName());
		});*/
/*
storageService.deleteAll();
storageService.init();


		List<Categorie> catList = parcJardinInterfaceMetier.ConsulterCategoriesPJ(pjById1);
		for(int i=0 ; i< catList.size() ; i++){
			System.out.println("test service retour des cat de PJ"+ catList.get(i));
		}*/

// image 1
		/*ClassPathResource backImgFile = new ClassPathResource("files/index.jpeg");
		byte[] arrayPic = new byte[(int) backImgFile.contentLength()];
		backImgFile.getInputStream().read(arrayPic);
		PJ1.setPhotos(backImgFile.getFilename());
		// store image to MySQL via SpringJPA
		parcJardinRepository.save(PJ1);*/


		/*// retrieve image from MySQL via SpringJPA
		for(ImageModel imageModel : imageRepository.findAll()){
			Files.write(Paths.get("retrieve-dir/" + imageModel.getName() + "." + imageModel.getType()), imageModel.getPic());
		}*/


		//System.out.println("photo de oj1"+PJ1.getPhotos());
	}
}

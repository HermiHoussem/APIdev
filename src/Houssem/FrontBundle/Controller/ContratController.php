<?php

namespace Houssem\FrontBundle\Controller;



use Houssem\FrontBundle\Entity\demandeAnnulation;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class ContratController extends Controller
{
    public function homeAction()
    {
        return $this->render('@HoussemFront/Produit/Home.html.twig', array(// ...
        ));
    }

    function affichageContratByIdAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $Contrat = $em->getRepository("HoussemBackBundle:ContratVoiture")->findBy(array('idClient' => $id));
        return $this->render('@HoussemFront\Contrat\affiher.html.twig', array('contrat' => $Contrat));
    }

    public function AnnulerAction($nom,$prenom,$id,$idCli)
    {
        $demandeAnnulation = new demandeAnnulation();

        $demandeAnnulation->setNomCli($nom);
        $demandeAnnulation->setPrenomCli($prenom);
        $demandeAnnulation->setIdCnt($id);


        $em = $this->getDoctrine()->getManager();
        $em->persist($demandeAnnulation);
        $em->flush();
        return $this->redirectToRoute("contratview",array('id'=>$idCli));


    }
}

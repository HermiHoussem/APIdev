<?php

namespace Houssem\BackBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Houssem\BackBundle\Entity\ContratVoiture;
use Houssem\BackBundle\Form\ContratVoitureType;
use Symfony\Component\HttpFoundation\Request;

class ContratVoitureController extends Controller
{
    public function ajoutcontratAction(Request $request,$id,$nom,$prenom)
    {
        $contrat= new ContratVoiture();

        $contrat->setIdClient($id);

        $form= $this->createForm(ContratVoitureType::class,$contrat);
        $form=$form->handleRequest($request);
        if($form->isSubmitted()){
            $em=$this->getDoctrine()->getManager();
            $em->persist($contrat);
            $em->flush();
            return $this->redirectToRoute("AffichageContrat") ;
        }
        return $this->render('@HoussemBack/Contrat/AjoutContrat.html.twig',array('fa'=>$form->createView(),'id'=>$id,'nom'=>$nom,'prenom'=>$prenom));
    }


    function affichageCAction(){
        $em=$this->getDoctrine()->getManager();
        $Contrat=$em->getRepository("HoussemBackBundle:ContratVoiture")->findAll();
        return $this->render('@HoussemBack\Contrat\AffichageContrat.html.twig',array('contrat'=>$Contrat));
    }

    function suppcontratAction($id){
        $em=$this->getDoctrine()->getManager();
        $Contrat=$em->getRepository("HoussemBackBundle:ContratVoiture")->find($id);
        $em->remove($Contrat);
        $em->flush();
        return $this->redirectToRoute("AffichageContrat") ;

    }
    function modifcontratAction(Request $request,$id)
    {
        $contrat = $this->getDoctrine()->getRepository(ContratVoiture::class)->find($id);
        $form = $this->createForm(ContratVoitureType::class, $contrat);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($contrat);
            $em->flush();
            return $this->redirectToRoute("AffichageContrat");

        }
        return $this->render('@HoussemBack/Contrat/AjoutContrat.html.twig', array('fa'=>$form->createView()));

    }
}

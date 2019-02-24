<?php

namespace Houssem\BackBundle\Controller;

use Houssem\BackBundle\Entity\Produit;
use Houssem\BackBundle\Form\ProduitType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class HomeController extends Controller
{
    public function homeAction()
    {
        return $this->render('@HoussemBack/Produit/Home.html.twig');
    }
    public function ajoutAction(Request $request)
    {
        $produit= new Produit();
        $form= $this->createForm(ProduitType::class,$produit);
        $form=$form->handleRequest($request);
        if($form->isSubmitted()){
            $em=$this->getDoctrine()->getManager();
            $em->persist($produit);
            $em->flush();
            return $this->redirectToRoute("Affichage") ;
        }
        return $this->render('@HoussemBack/Produit/Ajout.html.twig',array('f'=>$form->createView()));
    }
    public function affichageAction()
    {
        $em=$this->getDoctrine()->getManager();
        $produit=$em->getRepository("HoussemBackBundle:Produit")->findAll();
        return $this->render('@HoussemBack/Produit/Affichage.html.twig',array('voitures'=>$produit));
    }
    function suppproduitAction($id){
        $em=$this->getDoctrine()->getManager();
        $produit=$em->getRepository("HoussemBackBundle:Produit")->find($id);
        $em->remove($produit);
        $em->flush();
        return $this->redirectToRoute("Affichage") ;

    }
    function modifproduitAction(Request $request,$id)
    {
        $produit = $this->getDoctrine()->getRepository(Produit::class)->find($id);
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($produit);
            $em->flush();
            return $this->redirectToRoute("Affichage");

        }
        return $this->render('@HoussemBack/Produit/Ajout.html.twig', array('f'=>$form->createView()));

    }
}

<?php

namespace Houssem\FrontBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class ProduitController extends Controller
{
    public function homeAction()
    {
        return $this->render('@HoussemFront/Produit/Home.html.twig', array(
            // ...
        ));
    }
    public function produitsAction()
    {
        $em=$this->getDoctrine()->getManager();
        $produit=$em->getRepository("HoussemBackBundle:Produit")->findAll();
        return $this->render('@HoussemFront/Produit/produits.html.twig',array('produits'=>$produit));
    }
    public function ressourcesAction()
    {
        return $this->render('@HoussemFront/Produit/ressources.html.twig', array(
            // ...
        ));
    }
    public function contactAction()
    {
        return $this->render('@HoussemFront/Produit/contact.html.twig', array(
            // ...
        ));
    }


}

<?php

namespace Houssem\BackBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class ClientController extends Controller
{
    function afficherCliAction(){
        $em=$this->getDoctrine()->getManager();
        $Clients=$em->getRepository("MyAppUserBundle:User")->findAll();
        return $this->render('@HoussemBack\Contrat\AffichAvantAjout.html.twig',array('Clients'=>$Clients));
    }

}

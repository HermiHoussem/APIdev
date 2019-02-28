<?php

namespace Moez\BackBundle\Controller;

use Moez\BackBundle\Entity\promotion;
use Moez\BackBundle\Form\promotionType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('@MoezBack/Default/index.html.twig');
    }
   /* public function ajoutAction(Request $request)
    {
        $promotion= new promotion();
        $form= $this->createForm(promotionType::class,$promotion);
        $form=$form->handleRequest($request);
        return $this->render('@MoezBack/Default/ajoutVieVoitureVoyage.html.twig',array('f'=>$form->createView()));
    }*/
}

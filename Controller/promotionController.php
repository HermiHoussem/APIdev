<?php

namespace Moez\FrontBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Moez\BackBundle\Entity\promotion;
use Symfony\Component\HttpFoundation\Request;

class promotionController extends Controller
{
    public function afficheAction()
    {
        $em = $this->getDoctrine()->getManager();
        $repository = $this
            ->getDoctrine()
            ->getManager()
            ->getRepository('MoezBackBundle:promotion');
        $listePromotions=$repository->FindPromotionsNotExpired();

        return $this->render('@MoezFront/promotion/affichage.html.twig', array(
            'promotions' => $listePromotions,
        ));
    }
    public function infoAction(Request $request,$id)
    {
        $em=$this->getDoctrine()->getManager();
        $promotion=$em->getRepository("MoezBackBundle:promotion")->find($id);
        return $this->render('@MoezFront/promotion/info.html.twig', array(
            'promotion' => $promotion,
        ));

    }
    public function achatAction(Request $request,$id)
    {
        $em=$this->getDoctrine()->getManager();
        $promotion=$em->getRepository("MoezBackBundle:promotion")->find($id);
        $promotion->setNombreVendu($promotion->getNombreVendu()+1);
        $this->getDoctrine()->getManager()->flush();
        return $this->render('@MoezFront/promotion/affichage.html.twig');

    }
}

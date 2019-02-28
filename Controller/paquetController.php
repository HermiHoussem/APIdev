<?php

namespace Moez\FrontBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class paquetController extends Controller
{
    public function afficheAction()
    {
        $em = $this->getDoctrine()->getManager();
        $repository = $this
            ->getDoctrine()
            ->getManager()
            ->getRepository('MoezBackBundle:paquet');
        $listePaquets=$repository->FindPaquetsNotExpired();

        return $this->render('@MoezFront/paquet/affichage.html.twig', array(
            'paquets' => $listePaquets,
        ));
    }
    public function infoAction(Request $request,$id)
    {
        $em=$this->getDoctrine()->getManager();
        $paquet=$em->getRepository("MoezBackBundle:paquet")->find($id);
        return $this->render('@MoezFront/paquet/info.html.twig', array(
            'paquet' => $paquet,
        ));

    }
}

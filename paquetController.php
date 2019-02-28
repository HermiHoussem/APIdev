<?php

namespace Moez\BackBundle\Controller;

use Moez\BackBundle\Entity\paquet;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\File\File;

/**
 * Paquet controller.
 *
 */
class paquetController extends Controller
{
    /**
     * Lists all paquet entities.
     *
     */
    public function viewAction()
    {
        $em = $this->getDoctrine()->getManager();
        $repository = $this
            ->getDoctrine()
            ->getManager()
            ->getRepository('MoezBackBundle:paquet');
        $listePaquets=$repository->FindPaquetExpirired();
        foreach ($listePaquets as $paquet)
        {
            $paquet->setEtat("fini");

            $em->flush();
        }

        $paquets = $em->getRepository('MoezBackBundle:paquet')->findAll();

        return $this->render('@MoezBack/paquet/v.html.twig', array(
            'paquets' => $paquets,
        ));
    }
    public function chooseAction()
    {

        return $this->render('@MoezBack/paquet/choosetypes.html.twig');
    }
    public function AjoutchoisirAction(Request $request)
    {
        $inputTypeVie = $request->get("typevie");
        $inputTypeVoiture = $request->get("typevoiture");
        $inputTypeVoyage = $request->get("typevoyage");
       if(($inputTypeVoyage==""&&$inputTypeVie=="")||($inputTypeVoyage==""&&$inputTypeVoiture=="")||($inputTypeVoiture==""&&$inputTypeVie=="")||($inputTypeVoyage==""&&$inputTypeVie==""&&$inputTypeVoiture==""))
        {
            return $this->render('@MoezBack/paquet/choosetypes.html.twig', array(
                'erreur' => "wrong",


            ));
        }
        if($inputTypeVoiture=="assurancevoiture"&&$inputTypeVie=="assurancevie"&&$inputTypeVoyage=="assurancevoyage")
        {
            return $this->redirectToRoute('moez_back_paquet_ajout_VieVoitureVoyage');
        }
        if ($inputTypeVoiture=="assurancevoiture"&&$inputTypeVie=="assurancevie"&&$inputTypeVoyage=="" )
        {
            return $this->redirectToRoute('moez_back_paquet_ajout_VieVoiture');
        }
        if ($inputTypeVoyage=="assurancevoyage"&&$inputTypeVie=="assurancevie"&&$inputTypeVoiture=="")
        {
            return $this->redirectToRoute('moez_back_paquet_ajout_VieVoyage');
        }
        if ($inputTypeVoyage=="assurancevoyage"&&$inputTypeVoiture=="assurancevoiture"&&$inputTypeVie=="")
        {
            return $this->redirectToRoute('moez_back_paquet_ajout_VoitureVoyage');
        }
    }
    public function chooseModifAction(Request $request,$id)
    {
        $em=$this->getDoctrine()->getManager();
        $paquet=$em->getRepository("MoezBackBundle:paquet")->find($id);
        if($paquet->getProduitTypeVie()==null)
        {
            return $this->redirectToRoute('moez_back_paquet_modification_VoitureVoyage', array('id' => $id));
        }
        elseif($paquet->getProduitTypeVoiture()==null)
        {
            return $this->redirectToRoute('moez_back_paquet_modification_VieVoyage', array('id' => $id));
        }
        elseif($paquet->getProduitTypeVoyage()==null)
        {
            return $this->redirectToRoute('moez_back_paquet_modification_VieVoiture', array('id' => $id));
        }
        else
        {
            return $this->redirectToRoute('moez_back_paquet_modification_VieVoitureVoyage', array('id' => $id));
        }


    }
    /**
     * Creates a new paquet entity.
     *
     */
    public function ajoutVieVoitureVoyageAction(Request $request)
    {
        $paquet = new Paquet();
        $paquet->setEtat("en cours");
        $form = $this->createForm('Moez\BackBundle\Form\paquetTypeVieVoitureVoyage', $paquet);

        $form->handleRequest($request);
        if ($form->isSubmitted()&&$form->isValid()) {
            $paquet = $this->imageTraitement($paquet);
            $em = $this->getDoctrine()->getManager();
            $em->persist($paquet);
            $em->flush();
            return $this->redirectToRoute('moez_back_paquet_view');
        }
        return $this->render('@MoezBack/paquet/ajoutVieVoitureVoyage.html.twig', array(
            'paquet' => $paquet,
            'form' => $form->createView(),

        ));
    }
    /**
     * Creates a new paquet entity.
     *
     */
    public function ajoutVieVoitureAction(Request $request)
    {
        $paquet = new Paquet();
        $paquet->setEtat("en cours");

        $form = $this->createForm('Moez\BackBundle\Form\paquetTypeVieVoiture', $paquet);

        $form->handleRequest($request);
        if ($form->isSubmitted()&&$form->isValid()) {
            $paquet = $this->imageTraitement($paquet);
            $em = $this->getDoctrine()->getManager();
            $em->persist($paquet);
            $em->flush();
            return $this->redirectToRoute('moez_back_paquet_view');
        }
        return $this->render('@MoezBack/paquet/ajoutVieVoiture.html.twig', array(
            'paquet' => $paquet,
            'form' => $form->createView(),

        ));
    }
    /**
     * Creates a new paquet entity.
     *
     */
    public function ajoutVieVoyageAction(Request $request)
    {
        $paquet = new Paquet();
        $paquet->setEtat("en cours");

        $form = $this->createForm('Moez\BackBundle\Form\paquetTypeVieVoyage', $paquet);

        $form->handleRequest($request);
        if ($form->isSubmitted()&&$form->isValid()) {
            $paquet = $this->imageTraitement($paquet);
            $em = $this->getDoctrine()->getManager();
            $em->persist($paquet);
            $em->flush();
            return $this->redirectToRoute('moez_back_paquet_view');
        }
        return $this->render('@MoezBack/paquet/ajoutVieVoyage.html.twig', array(
            'paquet' => $paquet,
            'form' => $form->createView(),

        ));
    }
    /**
     * Creates a new paquet entity.
     *
     */
    public function ajoutVoitureVoyageAction(Request $request)
    {
        $paquet = new Paquet();
        $paquet->setEtat("en cours");

        $form = $this->createForm('Moez\BackBundle\Form\paquetTypeVoitureVoyage', $paquet);

        $form->handleRequest($request);
        if ($form->isSubmitted()&&$form->isValid()) {
            $paquet = $this->imageTraitement($paquet);
            $em = $this->getDoctrine()->getManager();
            $em->persist($paquet);
            $em->flush();
            return $this->redirectToRoute('moez_back_paquet_view');
        }
        return $this->render('@MoezBack/paquet/ajoutVoitureVoyage.html.twig', array(
            'paquet' => $paquet,
            'form' => $form->createView(),

        ));
    }
    /**
     * Finds and displays a paquet entity.
     *
     */
    public function showAction(paquet $paquet)
    {
        $deleteForm = $this->createDeleteForm($paquet);

        return $this->render('@MoezBack/paquet/show.html.twig', array(
            'paquet' => $paquet,
            'delete_form' => $deleteForm->createView(),
        ));
    }
    /**
     * Displays a form to edit an existing paquet entity.
     *
     */
    public function editAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $paquet=$em->getRepository("MoezBackBundle:paquet")->find($id);
        $editForm = $this->createForm('Moez\BackBundle\Form\paquetType', $paquet);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('moez_back_paquet_view');
        }

        return $this->render('@MoezBack/paquet/modifier.html.twig', array(
            'paquet' => $paquet,
            'edit_form' => $editForm->createView(),
        ));
    }
    /**
     * Creates a new paquet entity.
     *
     */
    public function editVieVoitureVoyageAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $paquet=$em->getRepository("MoezBackBundle:paquet")->find($id);
        $f=new File($this->getParameter('images_directory') .'/'. $paquet->getImage());
        $paquet->setImage($f);
        $form = $this->createForm('Moez\BackBundle\Form\paquetTypeVieVoitureVoyage', $paquet);

        $form->handleRequest($request);
        if ($form->isSubmitted()&&$form->isValid()) {
            $this->getDoctrine()->getManager()->flush();
            return $this->redirectToRoute('moez_back_paquet_view');
        }
        return $this->render('@MoezBack/paquet/modifierVieVoitureVoyage.html.twig', array(
            'paquet' => $paquet,
            'form' => $form->createView(),

        ));
    }
    /**
     * Creates a new paquet entity.
     *
     */
    public function editVieVoitureAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $paquet=$em->getRepository("MoezBackBundle:paquet")->find($id);
        $f=new File($this->getParameter('images_directory') .'/'. $paquet->getImage());
        $paquet->setImage($f);
        $form = $this->createForm('Moez\BackBundle\Form\paquetTypeVieVoiture', $paquet);


        $form->handleRequest($request);
        if ($form->isSubmitted()&&$form->isValid()) {
            $this->getDoctrine()->getManager()->flush();
            return $this->redirectToRoute('moez_back_paquet_view');
        }
        return $this->render('@MoezBack/paquet/modifierVieVoiture.html.twig', array(
            'paquet' => $paquet,
            'form' => $form->createView(),

        ));
    }
    /**
     * Creates a new paquet entity.
     *
     */
    public function editVieVoyageAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $paquet=$em->getRepository("MoezBackBundle:paquet")->find($id);
        $f=new File($this->getParameter('images_directory') .'/'. $paquet->getImage());
        $paquet->setImage($f);
        $form = $this->createForm('Moez\BackBundle\Form\paquetTypeVieVoyage', $paquet);

        $form->handleRequest($request);
        if ($form->isSubmitted()&&$form->isValid()) {
            $this->getDoctrine()->getManager()->flush();
            return $this->redirectToRoute('moez_back_paquet_view');
        }
        return $this->render('@MoezBack/paquet/modifierVieVoyage.html.twig', array(
            'paquet' => $paquet,
            'form' => $form->createView(),

        ));
    }
    /**
     * Creates a new paquet entity.
     *
     */
    public function editVoitureVoyageAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $paquet=$em->getRepository("MoezBackBundle:paquet")->find($id);
        $f=new File($this->getParameter('images_directory') .'/'. $paquet->getImage());
        $paquet->setImage($f);
        $form = $this->createForm('Moez\BackBundle\Form\paquetTypeVoitureVoyage', $paquet);

        $form->handleRequest($request);
        if ($form->isSubmitted()&&$form->isValid()) {
            $this->getDoctrine()->getManager()->flush();
            return $this->redirectToRoute('moez_back_paquet_view');
        }
        return $this->render('@MoezBack/paquet/modifierVoitureVoyage.html.twig', array(
            'paquet' => $paquet,
            'form' => $form->createView(),

        ));
    }
    /**
     * Deletes a paquet entity.
     *
     */
    public function deleteAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
            $paquet=$em->getRepository("MoezBackBundle:paquet")->find($id);
            $em->remove($paquet);
            $em->flush();

        return $this->redirectToRoute('moez_back_paquet_view');
    }
    public function imageTraitement(paquet $paquet)
    {
        $file = $paquet->getImage();
        $fileName = md5(uniqid()).'.'.$file->guessExtension();
        $file->move(
            $this->getParameter('images_directory'),$fileName
        );
        $paquet->setImage($fileName);
        return $paquet;
    }

}

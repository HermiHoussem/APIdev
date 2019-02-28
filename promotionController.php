<?php

namespace Moez\BackBundle\Controller;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\ColumnChart;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\Diff\DiffColumnChart;
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;
use http\Env\Response;
use Moez\BackBundle\Entity\promotion;
use Moez\BackBundle\command\SMSCommand;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\Routing\Annotation\Route;
use \Twilio\Rest\Client;
require_once('PHPMailer-master/src/PHPMailer.php');
require 'PHPMailer-master/PHPMailerAutoload.php';
/**
 * Promotion controller.
 *
 */
class promotionController extends Controller
{
    /**
     * Lists all promotion entities.
     *
     */
    public function viewAction()
    {
        $em = $this->getDoctrine()->getManager();
        $repository = $this
            ->getDoctrine()
            ->getManager()
            ->getRepository('MoezBackBundle:promotion');
        $listePromotions=$repository->FindPromotionsExpirired();
        foreach ($listePromotions as $promotion)
        {
                $produit=$em->getRepository("MoezBackBundle:produit")->find($promotion->getProduit()->getId());
                $promotion->setEtat("fini");
                $produit->setPromotion(0);
                $produit->getPromotion();
                $em->flush();
        }
        $Updatedpromotions = $em->getRepository('MoezBackBundle:promotion')->findAll();

        return $this->render('@MoezBack/promotion/view.html.twig', array(
            'promotions' => $Updatedpromotions,
        ));
    }

    /**
     * Creates a new promotion entity.
     *
     */
    function afficherClientAction(){


        $userManager = $this->get('fos_user.user_manager');
        $Clients = $userManager->findUsers();
        return $Clients;
    }
    public function newAction(Request $request)
    {

        $promotion = new Promotion();
        $promotion->setEtat("en cours");
        $form = $this->createForm('Moez\BackBundle\Form\promotionType', $promotion);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            /*$c =new Client();
            $sms = new SMSCommand($c);
            $sms->execute();*/
            $promotion->setNombreVendu(0);
            $file = $promotion->getImage();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('images_directory'),$fileName);
            //$f=new File($this->getParameter('images_directory') .'/'. $promotion->getImage());
            //$promotion->setImage($f);
            $promotion->setImage($fileName);
            $em = $this->getDoctrine()->getManager();
            $em->persist($promotion);
            $em->flush();
            $em->clear();
            $produit=$em->getRepository("MoezBackBundle:produit")->find($promotion->getProduit()->getId());
            $produit->setPromotion(1);
            $em->flush();
            $clients=$this->afficherClientAction();
            $this->mailAction($promotion,$clients);
            
            return $this->redirectToRoute('moez_back_promotion_view');
        }


        return $this->render('@MoezBack/promotion/ajout.html.twig', array(
            'promotion' => $promotion,
            'form' => $form->createView(),
        ));
    }
    public function mailAction(promotion $promotion,$clients)
    {
        $mail = new PHPMailer(true);
        $mail->isSMTP(); // Paramétrer le Mailer pour utiliser SMTP
        $mail->SMTPOptions = array('ssl' =>
            array(
                'verify_peer' => false,
                'verify_peer_name' => false,
                'allow_self_signed' => true));
        $mail->Host = 'smtp.gmail.com'; // Spécifier le serveur SMTP
        $mail->SMTPAuth = true; // Activer authentication SMTP
        $mail->Username = 'moez.jouini@esprit.tn'; // Votre adresse email d'envoi
        $mail->Password = 'wukonglol97'; // Le mot de passe de cette adresse email
        $mail->SMTPSecure = 'ssl'; // Accepter SSL
        $mail->Port = 465;

        $mail->setFrom('moez.jouini@esprit.tn', 'assurance gestion offreS'); // Personnaliser l'envoyeur
        //$em = $this->getDoctrine()->getManager();
        //$clients = $em->getRepository('MoezBackBundle:promotion')->findAll();
        foreach ($clients as $cl) {
            $mail->addAddress($cl->getEmail()); // Ajouter le destinataire
        }

        $mail->isHTML(true); // Paramétrer le format des emails en HTML ou non

        $mail->Subject = 'Nouvelle Promotion';
        $mail->Body = 'cher client nou avons une noouvelle promotion dont le nom est '.$promotion->getProduit()->getNom().
            ' qui debutera le '.$promotion->getDateDebutPromotion()->format('Y-m-d ').' et finira le '.$promotion->getDateFinPromotion()->format('Y-m-d').' venez consulter notre promotion';

        $mail->SMTPDebug = 1;

        if(!$mail->send()) {
            echo 'Erreur, message non envoyé.';
            echo 'Mailer Error: ' . $mail->ErrorInfo;
        } else {
            echo 'Le message a bien été envoyé !';
        }
    }
    /**
     * Finds and displays a promotion entity.
     *
     */
    public function showAction(promotion $promotion)
    {
        $deleteForm = $this->createDeleteForm($promotion);

        return $this->render('promotion/show.html.twig', array(
            'promotion' => $promotion,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing promotion entity.
     *
     */
    public function editAction(\Symfony\Component\HttpFoundation\Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $promotion=$em->getRepository("MoezBackBundle:promotion")->find($id);
         $f=new File($this->getParameter('images_directory') .'/'. $promotion->getImage());
        $promotion->setImage($f);
        $editForm = $this->createForm('Moez\BackBundle\Form\promotionType', $promotion);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {

            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('moez_back_promotion_view');
        }

        return $this->render('@MoezBack/promotion/modifier.html.twig', array(
            'promotion' => $promotion,
            'edit_form' => $editForm->createView(),
        ));
    }

    /**
     * Deletes a promotion entity.
     *
     */
    public function deleteAction($id)
    {
            $em=$this->getDoctrine()->getManager();
            $promotion=$em->getRepository("MoezBackBundle:promotion")->find($id);
            $produit=$em->getRepository("MoezBackBundle:produit")->find($promotion->getProduit()->getId());
            $produit->setPromotion(0);
            $em->remove($promotion);
            $em->flush();
        return $this->redirectToRoute('moez_back_promotion_view');

    }

    public function PrixAction(Request $request)
    {
        if($request->isXmlHttpRequest()){
            $nom=$request->get('nomProduit');
            $em=$this->getDoctrine()->getManager();
            $produit=$em->getRepository("MoezBackBundle:produit")->find($nom);
            $result=['data'=> $produit->getPrix()];
            return new JsonResponse($result);

        }
        else new Response("erreur");
    }
    public function statAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $promotions = $em->getRepository('MoezBackBundle:promotion')->findAll();
        $data = array();
        $stat=['nom du produit','nombre de vente'];
        array_push($data,$stat);
        foreach ($promotions as $promo)
        {
            $stat =array();
            array_push($stat,$promo->getProduit()->getNom(),$promo->getNombreVendu());
            $stat=[$promo->getProduit()->getNom(),$promo->getNombreVendu()];
            array_push($data,$stat);
        }
        $oldColumnChart = new ColumnChart();
        $oldColumnChart->getData()->setArrayToDataTable(
                $data
        );
        $oldColumnChart->getOptions()->getLegend()->setPosition('top');
        $oldColumnChart->getOptions()->setWidth(700);
        $oldColumnChart->getOptions()->setHeight(400);

        /*$newColumnChart = new ColumnChart();
        $newColumnChart->getData()->setArrayToDataTable(
            [
                ['nom', 'nombre de contrat'],
                ['Cesar', 370],
                ['Rachel', 600],
                ['Patrick', 700],
                ['Eric', 1500]
            ]
        );
        $newColumnChart->setOptions($oldColumnChart->getOptions());

        /*$diffColumnChart = new DiffColumnChart($oldColumnChart, $newColumnChart);
        $diffColumnChart->getOptions()->getLegend()->setPosition('top');
        $diffColumnChart->getOptions()->setWidth(700);
        $diffColumnChart->getOptions()->setHeight(400);
        $diffColumnChart->getOptions()->getDiff()->getNewData()->setWidthFactor(0.5);*/

        return $this->render('@MoezBack/promotion/stat.html.twig', array(
            'oldColumnChart' => $oldColumnChart,

        ));
    }
    public function RechercheAction(Request $request)
    {

        $em=$this->getDoctrine()->getManager();
        if ($_GET!=null){
            $x=$_GET['x'];

            $query = $em->createQuery("SELECT a FROM MoezBackBundle:promotion a WHERE a.idPromotion LIKE :motcle or a.etat LIKE :motcle or a.pourcentageRemise LIKE :motcle or a.dateDebutPromotion LIKE :motcle or a.DateFinPromotion LIKE :motcle ");

            //$results=$em->getRepository('MoezBackBundle:promotion')->findByEtat($x);
            $query->setParameter('motcle', '%'.$x.'%');
            $results = $query->getResult();
            return $this->render('@MoezBack/promotion/ajaxSearch.html.twig', array(
                'promotions' => $results,
            ));
        } else
            $jeux= $em->getRepository('MoezBackBundle:promotion')->findAll();


        return $this->render('@MoezBack/promotion/view.html.twig', array(
            'promotons' => $jeux,
        ));





    }





}

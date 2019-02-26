<?php

namespace Houssem\BackBundle\Controller;

use MyApp\UserBundle\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Security\Core\Role\Role;

class ClientController extends Controller
{
    function afficherCliAction(){


        $userManager = $this->get('fos_user.user_manager');
        $Clients = $userManager->findUsers();




        return $this->render('@HoussemBack\Contrat\AffichAvantAjout.html.twig', array('Clients' => $Clients));



    }

}

<?php

namespace Houssem\FrontBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('HoussemFrontBundle:Default:index.html.twig');
    }
}

<?php

namespace Houssem\BackBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('HoussemBackBundle:Default:index.html.twig');
    }
}

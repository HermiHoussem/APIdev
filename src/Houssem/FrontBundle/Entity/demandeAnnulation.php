<?php

namespace Houssem\FrontBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * demandeAnnulation
 *
 * @ORM\Table(name="demande_annulation")
 * @ORM\Entity(repositoryClass="Houssem\FrontBundle\Repository\demandeAnnulationRepository")
 */
class demandeAnnulation
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="nomCli", type="string", length=255)
     */
    private $nomCli;

    /**
     * @var string
     *
     * @ORM\Column(name="prenomCli", type="string", length=255)
     */
    private $prenomCli;

    /**
     * @var int
     *
     * @ORM\Column(name="idCnt", type="integer")
     */
    private $idCnt;

    /**
     * @var int
     *
     * @ORM\Column(name="demand", type="integer")
     */
    private $demand;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set nomCli
     *
     * @param string $nomCli
     *
     * @return demandeAnnulation
     */
    public function setNomCli($nomCli)
    {
        $this->nomCli = $nomCli;

        return $this;
    }

    /**
     * Get nomCli
     *
     * @return string
     */
    public function getNomCli()
    {
        return $this->nomCli;
    }

    /**
     * Set prenomCli
     *
     * @param string $prenomCli
     *
     * @return demandeAnnulation
     */
    public function setPrenomCli($prenomCli)
    {
        $this->prenomCli = $prenomCli;

        return $this;
    }

    /**
     * Get prenomCli
     *
     * @return string
     */
    public function getPrenomCli()
    {
        return $this->prenomCli;
    }

    /**
     * Set idCnt
     *
     * @param integer $idCnt
     *
     * @return demandeAnnulation
     */
    public function setIdCnt($idCnt)
    {
        $this->idCnt = $idCnt;

        return $this;
    }

    /**
     * Get idCnt
     *
     * @return int
     */
    public function getIdCnt()
    {
        return $this->idCnt;
    }

    /**
     * Set demand
     *
     * @param integer $demand
     *
     * @return demandeAnnulation
     */
    public function setNbr($demand)
    {
        $this->demand = $demand;

        return $this;
    }

    /**
     * Get demand
     *
     * @return int
     */
    public function getNbr()
    {
        return $this->demand;
    }
}


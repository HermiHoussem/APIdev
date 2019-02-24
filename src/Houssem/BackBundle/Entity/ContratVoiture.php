<?php

namespace Houssem\BackBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * ContratVoiture
 *
 * @ORM\Entity
 * @ORM\Table(name="contrat_voiture")
 */
class ContratVoiture
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;
public function __construct()
{
    $this->dateDebut=new \DateTime();
}
    /**
     * @var int
     *
     * @ORM\Column(name="id_client", type="integer")
     */
    private $idClient;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="Date_MEC", type="date")
     */
    private $dateMEC;

    /**
     * @var string
     *
     * @ORM\Column(name="Usage_", type="string", length=255)
     */
    private $usage;

    /**
     * @var float
     *
     * @ORM\Column(name="valeur", type="float")
     */
    private $valeur;

    /**
     * @var string
     *
     * @ORM\Column(name="Puissance", type="string", length=255)
     */
    private $puissance;



    /**
     * @var \DateTime
     *
     * @ORM\Column(name="Date_debut", type="date")
     */
    private $dateDebut;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_fin", type="date")
     */
    private $dateFin;
    /**
     * @var boolean
     *
     * @ORM\Column(name="Vol", type="boolean")
     */
    private $Vol;
    /**
     * @var boolean
     *
     * @ORM\Column(name="Incendie", type="boolean")
     */
    private $Incendie;
    /**
     * @var boolean
     *
     * @ORM\Column(name="Bris", type="boolean")
     */
    private $Bris;
    /**
     * @var boolean
     *
     * @ORM\Column(name="Assistance", type="boolean")
     */
    private $Assistance;
    /**
     * @var boolean
     *
     * @ORM\Column(name="Tous", type="boolean")
     */
    private $Tous;




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
     * Set dateMEC
     *
     * @param \DateTime $dateMEC
     *
     * @return ContratVoiture
     */
    public function setDateMEC($dateMEC)
    {
        $this->dateMEC = $dateMEC;

        return $this;
    }

    /**
     * Get dateMEC
     *
     * @return \DateTime
     */
    public function getDateMEC()
    {
        return $this->dateMEC;
    }

    /**
     * Set usage
     *
     * @param string $usage
     *
     * @return ContratVoiture
     */
    public function setUsage($usage)
    {
        $this->usage = $usage;

        return $this;
    }

    /**
     * Get usage
     *
     * @return string
     */
    public function getUsage()
    {
        return $this->usage;
    }

    /**
     * Set valeur
     *
     * @param float $valeur
     *
     * @return ContratVoiture
     */
    public function setValeur($valeur)
    {
        $this->valeur = $valeur;

        return $this;
    }

    /**
     * Get valeur
     *
     * @return float
     */
    public function getValeur()
    {
        return $this->valeur;
    }

    /**
     * Set puissance
     *
     * @param string $puissance
     *
     * @return ContratVoiture
     */
    public function setPuissance($puissance)
    {
        $this->puissance = $puissance;

        return $this;
    }

    /**
     * Get puissance
     *
     * @return string
     */
    public function getPuissance()
    {
        return $this->puissance;
    }



    /**
     * Set dateDebut
     *
     * @param \DateTime $dateDebut
     *
     * @return ContratVoiture
     */
    public function setDateDebut($dateDebut)
    {
        $this->dateDebut = $dateDebut;

        return $this;
    }

    /**
     * Get dateDebut
     *
     * @return \DateTime
     */
    public function getDateDebut()
    {
        return $this->dateDebut;
    }

    /**
     * Set dateFin
     *
     * @param \DateTime $dateFin
     *
     * @return ContratVoiture
     */
    public function setDateFin($dateFin)
    {
        $this->dateFin = $dateFin;

        return $this;
    }

    /**
     * Get dateFin
     *
     * @return \DateTime
     */
    public function getDateFin()
    {
        return $this->dateFin;
    }

    /**
     * @param int $idClient
     */
    public function setIdClient($idClient)
    {
        $this->idClient = $idClient;
    }

    /**
     * @return int
     */
    public function getIdClient()
    {
        return $this->idClient;
    }

    /**
     * @return bool
     */
    public function isVol()
    {
        return $this->Vol;
    }

    /**
     * @param bool $Vol
     */
    public function setVol($Vol)
    {
        $this->Vol = $Vol;
    }

    /**
     * @return bool
     */
    public function isIncendie()
    {
        return $this->Incendie;
    }

    /**
     * @param bool $Incendie
     */
    public function setIncendie($Incendie)
    {
        $this->Incendie = $Incendie;
    }

    /**
     * @return bool
     */
    public function isBris()
    {
        return $this->Bris;
    }

    /**
     * @param bool $Bris
     */
    public function setBris($Bris)
    {
        $this->Bris = $Bris;
    }

    /**
     * @return bool
     */
    public function isAssistance()
    {
        return $this->Assistance;
    }

    /**
     * @param bool $Assistance
     */
    public function setAssistance($Assistance)
    {
        $this->Assistance = $Assistance;
    }

    /**
     * @return bool
     */
    public function isTous()
    {
        return $this->Tous;
    }

    /**
     * @param bool $Tous
     */
    public function setTous($Tous)
    {
        $this->Tous = $Tous;
    }


}

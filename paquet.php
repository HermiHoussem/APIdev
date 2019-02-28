<?php

namespace Moez\BackBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * paquet
 *
 * @ORM\Table(name="paquet")
 * @ORM\Entity(repositoryClass="Moez\BackBundle\Repository\paquetRepository")
 */
class paquet
{
    /**
     * @var int
     *
     * @ORM\Column(name="idPaquet", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $idPaquet;
    /**
     * @var string
     *
     * @ORM\Column(name="Etat", type="string")
     */
    private $etat;
    public function __construct()
    {
        $this->dateDebutOffre = new \Datetime();
        $this->dateFinOffre = new \Datetime();
    }
    /**
     * @ORM\ManyToOne(targetEntity="Moez\BackBundle\Entity\produit")
     */
    private $ProduitTypeVie;

    /**
     * @ORM\ManyToOne(targetEntity="Moez\BackBundle\Entity\produit")
     */
    private $ProduitTypeVoiture;
    /**
     * @ORM\ManyToOne(targetEntity="Moez\BackBundle\Entity\produit")
     */
    private $ProduitTypeVoyage;
    /**
     * @var \DateTime
     *
     * @ORM\Column(name="DateDebutOffre", type="date")
     */
    private $dateDebutOffre;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="DateFinOffre", type="date")
     */
    private $dateFinOffre;

    /**
     * @var float
     *
     * @ORM\Column(name="PrixOffre", type="float")
     */
    private $prixOffre;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255)
     */
    private $nom;


    /**
     * @var string
     * @Assert\Image()
     * @ORM\Column(name="Image", type="string")
     */
    private $image;
    /**
     * Set dateDebutOffre
     *
     * @param \DateTime $dateDebutOffre
     *
     * @return paquet
     */
    public function setDateDebutOffre($dateDebutOffre)
    {
        $this->dateDebutOffre = $dateDebutOffre;

        return $this;
    }

    /**
     * Get dateDebutOffre
     *
     * @return \DateTime
     */
    public function getDateDebutOffre()
    {
        return $this->dateDebutOffre;
    }

    /**
     * Set dateFinOffre
     *
     * @param \DateTime $dateFinOffre
     *
     * @return paquet
     */
    public function setDateFinOffre($dateFinOffre)
    {
        $this->dateFinOffre = $dateFinOffre;

        return $this;
    }

    /**
     * Get dateFinOffre
     *
     * @return \DateTime
     */
    public function getDateFinOffre()
    {
        return $this->dateFinOffre;
    }

    /**
     * Set prixOffre
     *
     * @param float $prixOffre
     *
     * @return paquet
     */
    public function setPrixOffre($prixOffre)
    {
        $this->prixOffre = $prixOffre;

        return $this;
    }

    /**
     * Get prixOffre
     *
     * @return float
     */
    public function getPrixOffre()
    {
        return $this->prixOffre;
    }

    /**
     * Set produitTypeVie
     *
     * @param \Moez\BackBundle\Entity\produit $produitTypeVie
     *
     * @return paquet
     */
    public function setProduitTypeVie(\Moez\BackBundle\Entity\produit $produitTypeVie = null)
    {
        $this->ProduitTypeVie = $produitTypeVie;

        return $this;
    }

    /**
     * Get produitTypeVie
     *
     * @return \Moez\BackBundle\Entity\produit
     */
    public function getProduitTypeVie()
    {
        return $this->ProduitTypeVie;
    }

    /**
     * Set produitTypeVoiture
     *
     * @param \Moez\BackBundle\Entity\produit $produitTypeVoiture
     *
     * @return paquet
     */
    public function setProduitTypeVoiture(\Moez\BackBundle\Entity\produit $produitTypeVoiture = null)
    {
        $this->ProduitTypeVoiture = $produitTypeVoiture;

        return $this;
    }

    /**
     * Get produitTypeVoiture
     *
     * @return \Moez\BackBundle\Entity\produit
     */
    public function getProduitTypeVoiture()
    {
        return $this->ProduitTypeVoiture;
    }

    /**
     * Set produitTypeVoyage
     *
     * @param \Moez\BackBundle\Entity\produit $produitTypeVoyage
     *
     * @return paquet
     */
    public function setProduitTypeVoyage(\Moez\BackBundle\Entity\produit $produitTypeVoyage = null)
    {
        $this->ProduitTypeVoyage = $produitTypeVoyage;

        return $this;
    }

    /**
     * Get produitTypeVoyage
     *
     * @return \Moez\BackBundle\Entity\produit
     */
    public function getProduitTypeVoyage()
    {
        return $this->ProduitTypeVoyage;
    }

    /**
     * Set nom
     *
     * @param string $nom
     *
     * @return paquet
     */
    public function setNom($nom)
    {
        $this->nom = $nom;

        return $this;
    }

    /**
     * Get nom
     *
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * Get idPaquet
     *
     * @return integer
     */
    public function getIdPaquet()
    {
        return $this->idPaquet;
    }

    /**
     * Set image
     *
     * @param string $image
     *
     * @return paquet
     */
    public function setImage($image)
    {
        $this->image = $image;

        return $this;
    }

    /**
     * Get image
     *
     * @return string
     */
    public function getImage()
    {
        return $this->image;
    }

    /**
     * Set etat
     *
     * @param string $etat
     *
     * @return paquet
     */
    public function setEtat($etat)
    {
        $this->etat = $etat;

        return $this;
    }

    /**
     * Get etat
     *
     * @return string
     */
    public function getEtat()
    {
        return $this->etat;
    }
}

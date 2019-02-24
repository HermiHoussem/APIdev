<?php
/**
 * Created by PhpStorm.
 * User: dell user
 * Date: 10/02/2019
 * Time: 22:06
 */

namespace MyApp\UserBundle\Entity;
use FOS\UserBundle\Model\User as BaseUser;
use Doctrine\ORM\Mapping as ORM;
/**
 * @ORM\Entity
 * @ORM\Table(name="fos_user")
 */
class User extends BaseUser
{
    /**
     * @ORM\Id
     * @ORM\Column(type="integer")
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $id;
    /**
     * @var string
     *
     * @ORM\Column(name="nom",type="string", length=255)
     *
     */
    protected $nom;
    /**
     * @var string
     *
     * @ORM\Column(name="prenom",type="string", length=255)
     *
     */
    protected $prenom;
    /**
     * @var int
     *
     * @ORM\Column(name="cin",type="integer")
     *
     */
    protected $cin;
    /**
     * @var string
     *
     * @ORM\Column(name="adresse",type="string", length=255)
     *
     */
    protected $adresse;
    /**
     * @var string
     *
     * @ORM\Column(name="tel",type="string", length=255)
     *
     */
    protected $tel;



    /**
     * Set nom
     *
     * @param string $nom
     *
     * @return User
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
     * Set prenom
     *
     * @param string $prenom
     *
     * @return User
     */
    public function setPrenom($prenom)
    {
        $this->prenom = $prenom;

        return $this;
    }

    /**
     * Get prenom
     *
     * @return string
     */
    public function getPrenom()
    {
        return $this->prenom;
    }


    /**
     * Set cin
     *
     * @param integer $cin
     *
     * @return User
     */
    public function setCin($cin)
    {
        $this->cin = $cin;

        return $this;
    }

    /**
     * Get cin
     *
     * @return integer
     */
    public function getCin()
    {
        return $this->cin;
    }


    /**
     * Set adresse
     *
     * @param string $adresse
     *
     * @return User
     */
    public function setAdresse($adresse)
    {
        $this->adresse = $adresse;

        return $this;
    }

    /**
     * Get adresse
     *
     * @return string
     */
    public function getAdresse()
    {
        return $this->adresse;
    }




    /**
     * Set tel
     *
     * @param string $tel
     *
     * @return Uer
     */
    public function setTel($tel)
    {
        $this->tel = $tel;

        return $this;
    }

    /**
     * Get tel
     *
     * @return string
     */
    public function getTel()
    {
        return $this->tel;
    }

    public function __construct()
    {
        parent::__construct();
        // your own logic
    }
}
?>
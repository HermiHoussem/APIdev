<?php

namespace Houssem\FrontBundle\Entity;

/**
 * demandeAnnulation
 */
class demandeAnnulation
{
    /**
     * @var int
     */
    private $id;

    /**
     * @var string
     */
    private $nomCli;

    /**
     * @var string
     */
    private $prenomCli;

    /**
     * @var int
     */
    private $idCnt;


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
}


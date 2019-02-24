<?php

namespace Houssem\BackBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\ResetType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\FileType;

class ProduitType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('nom')->add('categorie',ChoiceType::class, [
            'choices' => [
                'Catégories' => [
                    'Habitation' => 'Habitation',
                    'Automobile' => 'Automobile',
                'Voyage' => 'Voyage']]])->add('description')->add('image', FileType::class, array('label' => 'Image(JPG)','data_class' => null))
            ->add('Enregistrer',SubmitType::class,array('label'=>'Enregistrer'
            ,'attr'=>array( "class"=>"btn btn-success")))
        ->add('annuler',ResetType::class,array('label'=>'Annuler'
        ))
        ;
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'Houssem\BackBundle\Entity\Produit'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'houssem_backbundle_produit';
    }


}

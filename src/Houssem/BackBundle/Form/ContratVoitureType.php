<?php

namespace Houssem\BackBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\CheckboxType;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
class ContratVoitureType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {/**
        $builder->add('idClient')->add('dateMEC')->add('usage')->add('valeur')->add('puissance')->add('dateDebut')->add('dateFin');
    */

        $builder


            ->add('dateMEC',DateType::class, array('label'=>'Date de mise en circulation  : '
            ,'attr'=>array( "class"=>"form-control")))

            ->add('usage',ChoiceType::class,[
                'choices' => [
                    'Usage' => [
                        'Privé' => 'Privé',
                        'Professionnel' => 'Professionnel']]],array('attr'=>array( "class"=>"form-control custom-select"))
                )


            ->add('valeur',TextType::class, array('label'=>'Saisir la valeur : '
            ,'attr'=>array( "class"=>"form-control")))


            ->add('puissance',ChoiceType::class,[
                'choices' => [
                    'Puissance' => [
                        '3 CV' => '3 CV',
                        '4 CV' => '5 CV',
                        '5 CV' => '5 CV',
                        '9 CV' => '9 CV']]],array('attr'=>array( "class"=>"form-control custom-select")))

        ->add('Vol', CheckboxType::class, [
            'label'    => "  Vol",
            'required' => false,
        ])
            ->add('Incendie', CheckboxType::class, [
                'label'    => '  Incendie',
                'required' => false,
            ])
            ->add('Bris', CheckboxType::class, [
                'label'    => '  Bris de glasses',
                'required' => false,
            ])
            ->add('Assistance', CheckboxType::class, [
                'label'    => '  Assistance remorquage',
                'required' => false,
            ])
            ->add('Tous', CheckboxType::class, [
                'label'    => '  Tous Risques',
                'required' => false,
            ])

            ->add('dateDebut',DateType::class, [
                'label'=>'la date début',
                'widget' => 'single_text',
                'attr' => ['class' => 'form-control'],
                'disabled' => true


            ])
            ->add('dateFin',DateType::class, array('label'=>'Saisir la date fin de contrat  : '
            ,'attr'=>array( "class"=>"form-control")))







            ->add('Effectuer',SubmitType::class, array('label'=>'Effectuer'
            ,'attr'=>array( "class"=>"btn btn-success"))) ;

        ;



    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'Houssem\BackBundle\Entity\ContratVoiture'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'houssem_backbundle_contratvoiture';
    }


}

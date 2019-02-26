<?php

namespace Houssem\BackBundle\Form;


use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\CheckboxType;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\RangeType;
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


            ->add('valeur',IntegerType::class,[
                'label'=>'Saisir la valeur de vhicule :',
                'attr' => ['class' => 'form-control','min'=>0,'max'=>500000,],
                'invalid_message' => 'You entered an invalid value, it should include %num% letters',
                'invalid_message_parameters' => ['%num%' => 6],
            ])

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
            'attr' => ['checked' => 'checked'],
            'disabled'=>true

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
                'label'=>'La date Début :',
                'data'=>new \DateTime(),
                'attr' => ['class' => 'form-control'],
                'disabled' => false,



            ])
            ->add('dateFin',DateType::class, [
                'label'=>'La date Fin :',
                'data'=>new \DateTime(),
                'attr' => ['class' => 'form-control'],
                'disabled' => false


            ])







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

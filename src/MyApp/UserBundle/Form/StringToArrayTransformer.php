<?php
namespace MyApp\UserBundle\Form ;
use Symfony\Component\Form\DataTransformerInterface;
use Symfony\Component\Form\Exception\TransformationFailedException;
class StringToArrayTransformer implements DataTransformerInterface
{
public function transform($array)
{
return $array[0];
}
public function reverseTransform($string)
{
return array($string);
}
}

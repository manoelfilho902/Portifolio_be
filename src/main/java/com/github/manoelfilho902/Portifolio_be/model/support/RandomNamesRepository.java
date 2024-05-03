/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.manoelfilho902.Portifolio_be.model.support;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
public class RandomNamesRepository {

    private static final List<String> firstNames = Arrays.asList(new String[]{
        "Andréia","Vitória","Larissa","Marlene","Cristiane","Ana","Carla","Ana","Eliane","Ana","Raquel","Andréia","Maria","Aline","Débora","Vanessa","Raquel","Mariana","Francisca","Andréia","Aparecida","Fernanda","Rita","Adriana","Rita","Simone","Francisca","Bruna","Sônia","Daniela","Antônia","Eliane","Raquel","Rosa","Aline","Lúcia","Simone","Jéssica","Mariana","Juliana","Adriana","Bruna","Vera","Camila","Natália","Márcia","Bruna","Larissa","Vanessa","Bruna","Luíz","Adriano","Thiago","Luíz","Alexandre","André","José","Francisco","Alexandre","Marcelo","Lucas","Marcos","Gabriel","Bruno","Lucas","Júlio","Marcos","Guilherme","Lucas","Daniel","Guilherme","Renato","Lucas","Luís","Marcelo","Francisco","Jorge","Matheus","Fábio","Roberto","Felipe","Matheus","Cláudio","Leandro","Fábio","Roberto","Rodrigo","Cláudio","Júlio","Mateus","Jorge","Leandro","Sérgio","Lucas","Gustavo","Marcelo","Luís","Rafael","Antônio","Ânderson"
    });

    private static final List<String> lastNames = Arrays.asList(new String[]{
        "Marques","de Carvalho","Costa","Santos","Santana","Cardoso","de Oliveira","de Lima","do Nascimento","Oliveira","da Conceiçao","Silva","Gomes","Marques","Dias","Araujo","Barbosa","Lima","Rocha","Almeida","Borges","Silva","Souza","Machado","Pereira","Barbosa","Soares","do Nascimento","de Araujo","Almeida","de Souza","Ribeiro","Marques","de Sousa","Gomes","Santos","Gonçalves","Mendes","de Sousa","Ribeiro","Sousa","Bezerra","dos Santos","Dias","Carvalho","Cardoso","de Lima","de Carvalho","de Carvalho","Cardoso","Alves","Araujo","Santana","de Oliveira","Barbosa","Nunes","Dias","Soares","Ribeiro","Costa","Carvalho","Almeida","Nascimento","Martins","Soares","Teixeira","de Lima","da Costa","da Costa","Martins","Oliveira","Moreira","Dias","Silva","de Oliveira","Martins","Ribeiro","Lima","de Sousa","Mendes","Pereira","Santos","Ribeiro","Gonçalves","Lopes","Sousa","Marques","Gomes","Marques","de Araujo","Santos","Oliveira","Oliveira","Batista","de Lima","Vieira","de Araujo","Mendes","de Souza","Mendes"
    });

    
    public static List<String> getFirstNames() {
        return firstNames;
    }

    public static List<String> getLastNames() {
        return lastNames;
    }
    
    public static String getRandomFirstName() {
        return firstNames.get(ThreadLocalRandom.current().nextInt(0, firstNames.size()));
    }

    public static String getRandomLastName() {
        return lastNames.get(ThreadLocalRandom.current().nextInt(0, lastNames.size()));
    }
    
    
}

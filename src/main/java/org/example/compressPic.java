package org.example;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ROUND_UNNECESSARY;
import static java.math.BigDecimal.ROUND_UP;
import static java.util.stream.Collectors.toList;

public class compressPic {

    public static void main(String[] args) throws IOException {

//        File fromPic=new File("C:\\Users\\11622\\Pictures\\lADPDhJzuYC6T9XNByDNCYA_2432_1824.jpg");
//        File toPic=new File("C:\\Users\\11622\\Pictures\\结果图片.jpg");
//        Thumbnails.of(fromPic).scale(1f).outputQuality(0.1f).toFile(toPic);
//        Thumbnails.of(fromPic).scale(0.2f).toFile(toPic);

//        public static void main(String[] args) {
//            new BigDecimal(0.256).setScale(ROUND_UNNECESSARY);
//            System.out.println(new BigDecimal(0.256).setScale(6, RoundingMode.HALF_UP));
//        }

//        List<String> lines = Arrays.asList("spring", "node", "mkyong");
//
//        List<String> result = lines.stream()                // convert list to stream
//                .filter(line -> !"mkyong".equals(line))     // we dont like mkyong
//                .collect(toList());              // collect the output and convert streams to a List
//
//        result.forEach(System.out::println);


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> twoEvenSquares =
                numbers.stream()
                        .filter(n -> {
                            System.out.println("filtering " + n);
                            return n % 2 == 0;
                        })
                        .map(n -> {
                            System.out.println("mapping " + n);
                            return n * n;
                        })
                        .limit(2)
                        .collect(toList());

    }


    private static ExecutorService executor = Executors.newSingleThreadExecutor();


}

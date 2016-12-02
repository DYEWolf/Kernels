import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by cristopher_ramirez on 24/11/16.
 */
public class Grayscale {

    public static void main(String args[])throws IOException {
        BufferedImage img = null;
        File f = null;
        int count=0;
        int sum=0;
        int j,c=0;
        int i = 0;
        int LimitA = 3;
        int LimitB = 3;
        int l,m=0;
        int v=0;
        int w;
        int holderX;
        int holderY;
        int holderV=0;
        int cunt=0;


        BufferedImage edge = null;
        File edgeimg = null;



        //read image
        try{
            f = new File("/home/cristopher_ramirez/Documents/TESTMATRIX/bear.jpg");
            img = ImageIO.read(f);
        }catch(IOException e){
            System.out.println(e);
        }

        //get image width and height
        int width = img.getWidth();
        int height = img.getHeight();

        int matrix[][] = new int[width][height];
        int edgeMatrix[][] = {

                {-1,-1,-1},
                {-1,8,-1},
                {-1,-1,-1}

        };
        int ConEdgeMatrix[][] = new int[width][height];


        //convert to grayscale
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int p = img.getRGB(x,y);

                int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;

                //calculate average
                int avg = (r+g+b)/3;

                //replace RGB value with avg
                p = (a<<24) | (avg<<16) | (avg<<8) | avg;

                matrix[x][y]=avg;


                //Printing the pixel value
                System.out.print(matrix[x][y] + " ");
                //Counter for the pixels
                count++;

                img.setRGB(x, y, p);
            }
            System.out.println();
        }
        System.out.println("Numero de pixeles en la imagen:" + count);
        count=0;


        //function to get the convolved feature (multiply the matrix and the edgematrix)

        for(int y=0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                w = 0;
                holderX = x;
                for (w = 0; w < 3; w++) {
                    v = 0;
                    if (w > 0)
                        x++;
                    for (v = 0; v < 3; v++) {
                        sum = sum + matrix[v][x] * edgeMatrix[v][w];
                        if (w == 2 && v == 2)
                            x = holderX;
                    }
                }
                System.out.println("Valor final de la matriz:" + sum);
                cunt++;
            }
            System.out.println("Numero de salidas de la matriz:" + cunt);
        }




        //write image
        try{
            f = new File("/home/cristopher_ramirez/Documents/TESTMATRIX/graybear.jpg");
            ImageIO.write(img, "jpg", f);

        }catch(IOException e){
            System.out.println(e);
        }
    }//main() ends here
}

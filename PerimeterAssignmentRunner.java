import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int pointsCount = 0;
        for (Point p : s.getPoints()) {
            pointsCount += 1;
        }
        return pointsCount;
    }

    public double getAverageLength(Shape s) {
        double totalLength = getPerimeter(s);
        int pointsCount = getNumPoints(s);
        double averageLength = totalLength / pointsCount;
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        double maxLength = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist > maxLength) {
                maxLength = currDist;
            }
            prevPt = currPt;
        }
        return maxLength;
    }

    public double getLargestX(Shape s) {
        double maxX = 0.0;
        for (Point p : s.getPoints()) {
            if (maxX < p.getX()) {
                maxX = p.getX();
            }
        }
        return maxX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (largestPerimeter < length) {
                largestPerimeter = length;
            }
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;
        double largestPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (largestPerimeter < length) {
                largestPerimeter = length;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int pointsCount = getNumPoints(s);
        System.out.println("number of points = " + pointsCount);
        double averageLength = getAverageLength(s);
        System.out.println("average length = " + averageLength);
        double maxLength = getLargestSide(s);
        System.out.println("max side length = " + maxLength);
        double maxX = getLargestX(s);
        System.out.println("max X = " + maxX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter = " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
       String fileName = getFileWithLargestPerimeter();
       System.out.println(fileName);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
        //pr.testPerimeter();
    }
}

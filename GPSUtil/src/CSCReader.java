import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class CSCReader {
    public static void main(String[] args) throws IOException {
        String csvFile = "/home/yuan/csv/test1.csv";
        String line;
        ArrayList<String[]> allLine = new ArrayList<>();
        try(
            BufferedReader br = new BufferedReader(new FileReader(csvFile)) ) {
            while ( (line = br.readLine() )!= null){
                String[] field = line.split(",");
//                System.out.println(field[0] + " ," + field[1] + " , " + field[2] + " ," + field[3] + " ," + field[4]);
//                System.out.println(field.length);
                allLine.add(field);
            }
        }

        int i = 0;
        double distance = 0;
        for ( i = 0; i < allLine.size(); i++ ) {
            if ( allLine.get(i).length >=5 && allLine.get(i)[4].equals("Picked Up")) {
                for (; i < allLine.size(); i++) {
                    if (allLine.get(i).length>=5 && allLine.get(i)[4].equals("Dropped Off")) break;
                    int nextRow = i + 1;
                    double lon1 = Double.parseDouble(allLine.get(i)[1]);
                    double lat1 = Double.parseDouble(allLine.get(i)[2]);
                    Timestamp time1 = Timestamp.valueOf(allLine.get(i)[3]);
                    double lon2 = Double.parseDouble(allLine.get(nextRow)[1]);
                    double lat2 = Double.parseDouble(allLine.get(nextRow)[2]);
                    Timestamp time2 = Timestamp.valueOf(allLine.get(nextRow)[3]);

                    double time3 = time2.getTime() - time1.getTime();
                    time3 = time3 /1000;
//                    System.out.println("time" + time3);


                    double currentDistance = GpsUtils.distance(lat1,lon1,lat2,lon2, 'K');
                    while ( !GpsUtils.isValidGps(currentDistance, time3))   {
                        nextRow++;
                        lon2 = Double.parseDouble(allLine.get(nextRow)[1]);
                        lat2 = Double.parseDouble(allLine.get(nextRow)[2]);
                        time2 = Timestamp.valueOf(allLine.get(nextRow)[3]);
                        time3 = (time2.getTime() - time1.getTime()) /1000;
                        currentDistance = GpsUtils.distance(lat1,lon1,lat2,lon2, 'K');
                    }
                    i = nextRow;

//                    System.out.println("current distance: " + currentDistance);
                    distance += currentDistance;
                }
            }
        }

        System.out.println("total distance: " + distance);
    }
}

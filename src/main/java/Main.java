public class Main {

    private static final String[] ORIENTATIONS = "N/S/E/W".split("/");

    public static void main(String[] args) {

//        double distance = calculateDistance(57.144156, -2.114864, 57.124274, -2.127206);
//        System.out.println("Distance: " + distance);

//        AB10 1XG
//        AB10 7JB

//        double distance2 = haversine(57.144156, -2.114864, 57.124274, -2.127206);
//        System.out.println("Distance: " + distance2);

        System.out.println(SeparararDMS("57,144156", 1));
        System.out.println(SeparararDMS("-2,114864", 2));

        System.out.println(decimalToDMS(57.144156f));
        System.out.println(decimalToDMS(-2.114864f));

        final float[] coordinates = { -71.3854f, -30.8199f };
        String dmsResult = processCoordinates(coordinates);
        final String coords_txt = coordinates[1] + "," + coordinates[0];
        System.out.println(coords_txt + " converted -> " + dmsResult);

        final float[] coordenadas = { 57.144156f, -2.114864f };
        String result = processCoordinates(coordenadas);
        final String response = coordenadas[1] + "," + coordenadas[0];
        System.out.println(response + " converted -> " + result);



    }

    public static String SeparararDMS(String coordenada, int type) {

        String grados = null;
        String minutos = null;
        String segundos = null;
        String direccion = null;


        switch (type) {
            case 1: // latitude
                grados = coordenada.substring(0, 2);
                minutos = coordenada.substring(2, 4);
                segundos = coordenada.substring(5, coordenada.length() - 1);
                break;
            case 2: // longitude
                grados = coordenada.substring(0, 3);
                minutos = coordenada.substring(3, 5);
                segundos = coordenada.substring(6, coordenada.length() - 1);
                break;
            default:

        }

        double sec = 0;
        try {
            sec = Double.parseDouble("0." + segundos);
        } catch (Exception e) {

        }


        sec = (sec * 60);
        direccion = coordenada.substring(coordenada.length() - 1);

        return grados + "| " + minutos + " " + segundos + " " + direccion;
    }

    private static String processCoordinates(float[] coordinates) {
        String converted0 = decimalToDMS(coordinates[1]);
        final String dmsLat = coordinates[0] > 0 ? ORIENTATIONS[0] : ORIENTATIONS[1];
        converted0 = converted0.concat(" ").concat(dmsLat);

        String converted1 = decimalToDMS(coordinates[0]);
        final String dmsLng = coordinates[1] > 0 ? ORIENTATIONS[2] : ORIENTATIONS[3];
        converted1 = converted1.concat(" ").concat(dmsLng);

        return converted0.concat(", ").concat(converted1);
    }

    private static String decimalToDMS(float coord) {

        float mod = coord % 1;
        int intPart = (int) coord;

        String degrees = String.valueOf(intPart);

        coord = mod * 60;
        mod = coord % 1;
        intPart = (int) coord;
        if (intPart < 0)
            intPart *= -1;

        String minutes = String.valueOf(intPart);

        coord = mod * 60;
        intPart = (int) coord;
        if (intPart < 0)
            intPart *= -1;

        String seconds = String.valueOf(intPart);
        String output = Math.abs(Integer.parseInt(degrees)) + "°" + minutes + "'" + seconds + "\"";

        return output;
    }

    static double haversine(double lat1, double lon1,
                            double lat2, double lon2) {
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }

    private final static double EARTH_RADIUS = 6371; // radius in kilometers

    private static double calculateDistance(double latitude, double longitude, double latitude2, double
            longitude2) {
        // Using Haversine formula! See Wikipedia;
        double lon1Radians = Math.toRadians(longitude);
        double lon2Radians = Math.toRadians(longitude2);
        double lat1Radians = Math.toRadians(latitude);
        double lat2Radians = Math.toRadians(latitude2);
        double a = haversine(lat1Radians, lat2Radians)
                + Math.cos(lat1Radians) * Math.cos(lat2Radians) * haversine(lon1Radians, lon2Radians);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (EARTH_RADIUS * c);
    }

    private static double haversine(double deg1, double deg2) {
        return square(Math.sin((deg1 - deg2) / 2.0));
    }

    private static double square(double x) {
        return x * x;
    }
}

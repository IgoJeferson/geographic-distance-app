package com.igojeferson.geographic.distance.utils;

import com.igojeferson.geographic.distance.dto.LatLng;

/**
 * Utility class for converting a coordinate into its DMS (degrees, minutes,
 * seconds) representation
 * 
 * @author marlonlom
 */
public final class LatLngConverter {
	/**
	 * Constant for latitude/longitude orientations
	 */
	private static final String[] ORIENTATIONS = "N/S/E/W".split("/");

	/**
	 * Given a array of coordinates [longitude, latitude], returns the dms
	 * (degrees, minutes, seconds) representation
	 * 
	 * @param latitude and longitude coordinates
	 * @return dms representation for given coordinates
	 */
	public static LatLng processCoordinates(String latitude, String longitude) {
		String convertedLat = LatLngConverter.decimalToDMS(Float.valueOf(latitude));
		final String dmsLat = Float.valueOf(latitude) > 0 ? ORIENTATIONS[0] : ORIENTATIONS[1];
		convertedLat = convertedLat.concat(" ").concat(dmsLat);

		String convertedLng = LatLngConverter.decimalToDMS(Float.valueOf(longitude));
		final String dmsLng = Float.valueOf(longitude) > 0 ? ORIENTATIONS[2] : ORIENTATIONS[3];
		convertedLng = convertedLng.concat(" ").concat(dmsLng);

		return new LatLng(convertedLat, convertedLng);
	}

	/**
	 * Given a decimal longitudinal coordinate such as <i>-79.982195</i> it will
	 * be necessary to know whether it is a latitudinal or longitudinal
	 * coordinate in order to fully convert it.
	 * 
	 * @param coord coordinate in decimal format
	 * @return coordinate in D°M′S″ format
	 * @see <a href='https://goo.gl/pWVp60'>Geographic coordinate conversion
	 *      (wikipedia)</a>
	 */
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
		String output = Math.abs(Integer.parseInt(degrees)) + "° " + minutes + "' " + seconds + "\"";

		return output;
	}

}
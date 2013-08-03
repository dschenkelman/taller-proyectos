using System.IO;
using System.Globalization;

var lines = File.ReadAllLines("mcdonalds.txt", Encoding.UTF8);
var attrs = 0;

var outLines = new List<string>();

foreach (var line in lines)
{
	McDonalds md;
	
	if (attrs >= 3 || attrs == 0) {
		if (attrs > 0) {
			outLines.Add(md.ToString());
		}

		md = new McDonalds();
		attrs = 0;
	}
		
	if (line.Contains("lat\":")) {
		var begin = line.IndexOf(":");
		var end = line.IndexOf(",");
		string lat = line.Substring(begin + 1, end - (begin + 1)).Trim();
		double latitude;
		if (double.TryParse(lat, NumberStyles.Number, CultureInfo.InvariantCulture, out latitude)) {
			md.Latitude = latitude;
		}

		attrs++;
	}

	if (line.Contains("lon\":")) {
		var begin = line.IndexOf(":");
		var end = line.IndexOf(",");
		string lon = line.Substring(begin + 1, end - (begin + 1)).Trim();
		double longitude;
		if (double.TryParse(lon, NumberStyles.Number, CultureInfo.InvariantCulture, out longitude)) {
			md.Longitude = longitude;
		}

		attrs++;
	}

	if (line.Contains("address\":")) {
		var begin = line.IndexOf(":");
		var end = line.IndexOf(",");
		md.Address = line.Substring(begin + 1, end - (begin + 1)).Trim();
		attrs++;
	}
}

File.WriteAllLines("out.txt", outLines);

public class McDonalds{
	public double Latitude {get; set;}
	public double Longitude {get; set;}
	public string Address {get; set;}

	public override string ToString(){
		return "context.Toilets.Add(new Toilet { Address = \"" + Address + 
		"\", Description = \"Mc Donalds\", Location = DbGeography.FromText(\"POINT(" + Longitude + " " + Latitude + ")\") });";
	}
}
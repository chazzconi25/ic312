import java.util.List;

/** Program to read in ARCOS data and report how many pills were
 * sold by each pharmacy.
 * The results should be sorted in alphabetical order by pharmacy name.
 * The pharmacy name is equal to the BUYER_NAME field, or, if the
 * BUYER_ADDL_CO_INFO field is not "null", then the pharmacy name
 * should be BUYER_NAME and BUYER_ADDL_CO_INFO, separated by one space.
 */
public class Pharmacies {
  public static void main(String[] args) {
    // read the name of the pills file from command line or the console
    String pillsFile=null;
    if (args.length >= 1) pillsFile = args[0];
    if (pillsFile == null)
      pillsFile = System.console().readLine("pills file: ");
    
    TsvReader pillsLines = new TsvReader(pillsFile);
    Map<String,Double> map = new TreeMap<String,Double>();
    for (Map<String,String> aLine : pillsLines) {
      String buyer = aLine.get("BUYER_NAME");
      if(!aLine.get("BUYER_ADDL_CO_INFO").equals("null")) {
        buyer += " " + aLine.get("BUYER_ADDL_CO_INFO");
      }
      Double dosage = Double.parseDouble(aLine.get("DOSAGE_UNIT"));
      if(map.containsKey(buyer)) {
        Double total = map.get(buyer);
        total += dosage;
        map.put(buyer, total);
      } else {
        map.put(buyer, dosage);
      }
    }
    List<String> buyers = map.keys();
    for(String buyer : buyers) {
      System.out.format("%8d %s\n", Math.round(map.get(buyer)), buyer);
    }

    // TODO you write the rest!

    // To print the output, use a line like this.
    // Remember the output lines should be sorted alphabetically by
    // pharmacy name.
    // System.out.format("%8d %s\n", pillCount, pharmacyName);
  }
}

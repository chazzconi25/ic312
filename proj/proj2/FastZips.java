import java.util.List;

/** Program to identify which zip codes had the highest per-capity
 * number of opioid pills sold.
 * Input will be two filenames (for ARCOS data and zip code data)
 * and an integer k.
 * The k zipcodes with the highest pills/population ratios should be
 * printed out.
 */
public class FastZips {
  public static void main(String[] args) {
    // Read pills and zip codes filenames, as well as k value,
    // either from command line arguments or from the console.

    String pillsFile, zipsFile, kstring;

    if (args.length >= 1)
      pillsFile = args[0];
    else
      pillsFile = System.console().readLine("pills file: ");

    if (args.length >= 2)
      zipsFile = args[1];
    else
      zipsFile = System.console().readLine("zips file: ");

    if (args.length >= 3)
      kstring = args[2];
    else
      kstring = System.console().readLine("k: ");
    int k = Integer.valueOf(kstring);

    // TODO you write the rest!
    TsvReader pillsLines = new TsvReader(pillsFile);
    Map<String,Integer> zipTotals = new TreeMap<String,Integer>();
    for (Map<String,String> aLine : pillsLines) {
      String zipcode = aLine.get("BUYER_ZIP");
      int dosage = (int)(Double.parseDouble(aLine.get("DOSAGE_UNIT")));
      if(zipTotals.containsKey(zipcode)) {
        int total = zipTotals.get(zipcode);
        total += dosage;
        zipTotals.put(zipcode, total);
      } else {
        zipTotals.put(zipcode, dosage);
      }
    }

    TopK<Double> combine = new TopK<Double>(k);

    TsvReader zipsLines = new TsvReader(zipsFile);
    Map<String,String[]> zipCity = new TreeMap<String,String[]>();
    Map<Double,String[]> ratioZip = new TreeMap<Double,String[]>();
    for (Map<String,String> aLine : zipsLines) {
      String zipcode = aLine.get("zip");
      String pop = aLine.get("population");
      if(zipTotals.containsKey(zipcode)) {
        double ratio = ((double)zipTotals.get(zipcode))/Double.parseDouble(pop);
        /*if(!zipCity.containsKey(zipcode)) {
          zipCity.put(zipcode, new String[]{"" + ratio,
                                            aLine.get("city"),
                                            aLine.get("state_id"),
                                            zipcode});*/
          ratioZip.put(ratio,new String[]{"" + ratio,
                                            aLine.get("city"),
                                            aLine.get("state_id"),
                                            zipcode});
          combine.add(ratio);
        //}
      }
    }
    List<Double> ret = combine.getTop();

    for(double ratio : ret) {
      System.out.format("%8.2f %s, %s %d\n", Double.parseDouble(ratioZip.get(ratio)[0]),
                                             ratioZip.get(ratio)[1],
                                             ratioZip.get(ratio)[2],
                                             Integer.parseInt(ratioZip.get(ratio)[3]));
    }
    // Use code like this to print the output.
    // Remember that only the top k pills/population ratios should be printed.
    // If there are fewer than k zip codes in the data set, then print all of them.
    // The output lines should be sorted by the pills/population ratio, largest first.
    // System.out.format("%8.2f %s, %s %d\n", ratio, city, state, zipCode);
  }
}

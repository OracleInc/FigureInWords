import java.util.Scanner;

public class FigureInWords {

    public static String[] tensNames = {"", " ten", " twenty", " thirty", " forty", " fifty", " sixty", " seventy", " eighty", " ninety"};
    public static String[] numNames = {"", " one", " two", " three", " four", " five", " six", " seven", " eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen", " seventeen", " eighteen", " nineteen"};

    public static void main(String[] args) {
        //System.out.println("MAIN");
        everyThing();

    }

    public static String enterNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number to convert to words (or 'done' to quit): ");
        return input.nextLine();
    }

    public static String tens(int a) {
        if (a % 100 < 20) {
            return numNames[a];
        } else {
            return tensNames[(a % 100) / 10] + numNames[a % 10];
        }
    }

    public static String hunds(int a) {
        String addAnd = (a % 100 != 0) ? " and" : "";
        String addHund = ((a % 1000) / 100 > 0) ? " hundred" : "";
        return numNames[(a % 1000) / 100] + addHund + addAnd + tens(a % 100);
    }

    public static String thous(int a) {
        String addThous = ((a % 1000000) > 999) ? " thousand" : "";
        return numNames[(a % 10000) / 1000] + addThous + hunds(a);
    }

    public static String tenThous(int a) {
        if ((a % 100000) / 1000 < 20) {
            String addThous = ((a % 1000000) > 999) ? " thousand" : "";
            return numNames[(a % 100000) / 1000] + addThous + hunds(a);
        } else {
            return tensNames[(a % 100000) / 10000] + thous(a);
        }
    }

    public static String hundThous(int a) {
        String addHundAnd = "";
        if ((a % 1000000) / 100000 == 0 && (a % 100000) > 9999) {
            addHundAnd = " and";
        } else if ((a % 1000000) / 100000 > 0) {
            addHundAnd = " hundred";
        } else if ((a % 100000) > 9999) {
            addHundAnd = " hundred and";
        }
        return numNames[(a % 1000000) / 100000] + addHundAnd + tenThous(a);
    }

    public static String milln(int a) {
        String addMilln = "";
        if ((a % 1000000000) > 99999) {
            addMilln = " million";
        }
        return numNames[(a % 10000000) / 1000000] + addMilln + hundThous(a);
    }

    public static String tenMilln(int a) {
        String addMilln = "";
        if ((a % 1000000000) > 99999) {
            addMilln = " million";
        }
        if ((a % 100000000) / 1000000 < 20) {
            return numNames[(a % 100000000) / 1000000] + addMilln + hundThous(a);
        } else {
            return tensNames[(a % 100000000) / 10000000] + milln(a);
        }
    }

    public static String hunMilln(int a) {
        String addHundMlnAnd = "";
        if ((a % 100000000) > 999999) {
            addHundMlnAnd = " hundred and";
        } else if ((a % 1000000000) / 100000000 > 0) {
            addHundMlnAnd = " hundred";
        }
        if ((a % 100000000) / 1000000 < 20) {
            return numNames[(a % 1000000000) / 100000000] + addHundMlnAnd + tenMilln(a % 100000000);
        } else {
            return numNames[(a % 1000000000) / 100000000] + addHundMlnAnd + tensNames[(a % 100000000) / 10000000] + milln(a);
        }
    }

    public static String bln(int a) {
        return numNames[a / 1000000000] + " billion" + hunMilln(a);
    }

    public static void everyThing() {

        String value = enterNumber();

        while (!value.equals("done")) {

            try {
                // the String to int conversion happens here
                int receivedValue = Integer.parseInt(value.trim());

                String negative = "";
                if (receivedValue < 0) {
                    negative = "negative";
                    receivedValue *= -1;
                }
                // converts back to String so it can be converted to char array
                String validNumber = String.valueOf(receivedValue);

                // creates char array
                char[] validNumberArray = validNumber.toCharArray();
                // gets the position
                int position = validNumber.length();
                // System.out.println(position);

                if (position <= 2) {
                    if (receivedValue == 0) {
                        System.out.println("Zero");
                    } else {
                        System.out.println(negative + tens(receivedValue));
                    }
                } else if (position == 3) {
                    System.out.println(negative + hunds(receivedValue));
                } else if (position == 4) {
                    System.out.println(negative + thous(receivedValue));
                } else if (position == 5) {
                    System.out.println(negative + tenThous(receivedValue));
                } else if (position == 6) {
                    System.out.println(negative + hundThous(receivedValue));
                } else if (position == 7) {
                    System.out.println(negative + milln(receivedValue));
                } else if (position == 8) {
                    System.out.println(negative + tenMilln(receivedValue));
                } else if (position == 9) {
                    System.out.println(negative + hunMilln(receivedValue));
                } else if (position == 10) {
                    System.out.println(negative + bln(receivedValue));
                }
            } catch (NumberFormatException e) {
                System.out.println("You entered a wrong value.");
                break;
            }

            value = enterNumber();
        }
        if (value.equals("done")) {
            System.out.println("Thank you for using our service.");
        }
    }

}

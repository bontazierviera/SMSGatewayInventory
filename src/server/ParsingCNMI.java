package server;
public class ParsingCNMI {
    private String cnmi;
    public ParsingCNMI(String cnmi) {
        this.cnmi=cnmi;
    }
    public ParsingCNMI() {}
    public void setCNMI(String cnmi) {
        this.cnmi=cnmi;
    }
    public void Parsing() {
        String tampung="";
        boolean flag=false;
        for(int i=0;i<cnmi.length();i++) {
                if(!flag) {
                        if(!(cnmi.charAt(i) == '(' || cnmi.charAt(i) == ')'))
                                tampung+=String.valueOf(cnmi.charAt(i));
                }
                else
                        tampung+=String.valueOf('-');
                if(cnmi.charAt(i) == ')') flag=true;
                else flag=false;
        }
        String arr[]=tampung.split("-");
        cnmi="";
        for(int i=0;i<arr.length;i++) {
                String data[]=arr[i].split(",");
                cnmi+="," + data[data.length - 1];
        }
        cnmi=cnmi.substring(1);
    }
    public String getCNMI() {
        return cnmi;
    }
}
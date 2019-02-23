
import business.Quota;
import java.time.LocalDate;

public class QuotaTeste {

    public static void main(String[] args){
        Quota q1  = new Quota("1000", LocalDate.of(2010,11,12),LocalDate.now(), 5, false);
        Quota q2  = new Quota(q1);
        Quota q3  = new Quota("1000", LocalDate.of(2010,11,12),LocalDate.now(), 5, false);

        System.out.println(q1.equals(q3));
    }
}

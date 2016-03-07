package junit.theory.externalize;

import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pan on 2016/3/5.
 */
public class NumberSupplier extends ParameterSupplier {

    public NumberSupplier() {
        System.out.println("NumberSupplier constructor!!!");
    }

    @Override
    public List<PotentialAssignment> getValueSources(ParameterSignature sig) throws Throwable {
        List<PotentialAssignment> list = new ArrayList<PotentialAssignment>();
        list.add(PotentialAssignment.forValue("long", 2L));
        list.add(PotentialAssignment.forValue("float", 5.00f));
        list.add(PotentialAssignment.forValue("double", 89d));
        return list;
    }
}

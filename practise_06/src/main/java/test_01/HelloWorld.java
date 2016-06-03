package test_01;

import lombok.Data;

import java.util.List;

/**
 * @author dshvedchenko on 6/3/16.
 */
@Data
public class HelloWorld implements PStates {
    private List<String> states;
    private TrickyBean tt;

    public HelloWorld(TrickyBean tt) {
        this.tt = tt;
    }

    @Override
    public List<String> getStatesList() {
        return getStates();
    }

    private MessageGenerator msGen;

    private MessageGenerator jsonGen;

    public String getMessages() {
        return msGen.getMessage();
    }

    @Override
    public TrickyBean getTB() {
        return tt;
    }

}

import java.io.InputStream;
import java.io.IOException;
// s
class BitwiseCalculator {
    private final InputStream in;

    private int lookahead;

    public BitwiseCalculator(InputStream in) throws IOException {
        this.in = in;
        lookahead = in.read();
    }

    private void consume(int symbol) throws IOException, ParseError {
        if (lookahead == symbol)
            lookahead = in.read();
        else
            throw new ParseError();
    }

    private boolean isDigit(int c) {
        return '0' <= c && c <= '9';
    }

    private int evalDigit(int c) {
        return c - '0';
    }

    public int eval() throws IOException, ParseError {
        int value = Exp();

        if (lookahead != -1 && lookahead != '\n')
            throw new ParseError();

        return value;
    }

    private int Exp() throws IOException, ParseError {
        if (isDigit(lookahead) || lookahead == '(') {
            // int number = evalDigit(lookahead);
           
            int res = XorTerm();
            return Exp2(res); 
        }

        throw new ParseError();
    }

    private int Exp2(int res) throws IOException, ParseError {
        switch (lookahead) {
            case '^':
                consume('^');
                res = res ^ XorTerm();
                return Exp2(res);

            case -1:
            case '\n':
            case ')':
                return res;
        }

        throw new ParseError();
    }

    private int XorTerm() throws IOException, ParseError {
        if (isDigit(lookahead) || lookahead == '(') {
            // int number = evalDigit(lookahead);

            int res = AndTerm();
            return XorTerm2(res); 
        }

        throw new ParseError();
    }

    private int XorTerm2(int res) throws IOException, ParseError {
        switch (lookahead) {
            case '&':
                consume('&');
                res = res & AndTerm();
                return XorTerm2(res);

            case -1:
            case '\n':
            case ')':
            case '^':
                return res;
        }

        throw new ParseError();
    }

    private int AndTerm() throws IOException, ParseError {
        if (isDigit(lookahead)) {
            int number = evalDigit(lookahead);

            consume(lookahead);

            return number;

        }
        else if(lookahead == '(') {
            consume('(');
            int res = Exp();
            consume(')');

            return res;
        }
        throw new ParseError();
    }
}
package australchess.cli;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class ParsedPositionParserTest {

    @Test
    public void can_parse_3_e() {
        var toTest = "(3,e)";
        Optional<ParsedPosition> position = ParsedPositionParser.parse(toTest);
        assertThat(position).isPresent();
        assertThat(position).map(ParsedPosition::getNumber).hasValue(3);
        assertThat(position).map(ParsedPosition::getLetter).hasValue('e');
    }

    @Test
    public void cannot_parse_e_3() {
        var toTest = "(e,3)";
        Optional<ParsedPosition> position = ParsedPositionParser.parse(toTest);
        assertThat(position).isNotPresent();
    }

    @Test
    public void cannot_parse_something() {
        var toTest = "something";
        Optional<ParsedPosition> position = ParsedPositionParser.parse(toTest);
        assertThat(position).isNotPresent();
    }
}

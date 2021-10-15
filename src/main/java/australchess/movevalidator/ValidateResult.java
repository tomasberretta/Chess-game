package australchess.movevalidator;

import lombok.Data;

@Data
public class ValidateResult {
    boolean valid;
    String message;

    public ValidateResult(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }
}

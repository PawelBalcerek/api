package AI.dtapijava.Enums;

public enum TransactionType {
    SELL_OFFER(1),
    BUY_OFFER(2);

    int typeNumber;

    private TransactionType(int typeNumber) {
        this.typeNumber = typeNumber;
    }
}
